using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Security.Claims;
using System.Text;
using BodaBodaServer.Helpers;
using BodaBodaServer.Models;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;

namespace BodaBodaServer.Services
{
    public interface IUserServices
    {
        Token Authenticate(string username, string password);
        IEnumerable<User> GetAll();
        User GetById(long id);
        User Register(User _user);
        TaxiPrice AddTaxiPrice(TaxiPrice taxiPrice);
        TaxiPrice UpdateTaxiPrice(TaxiPrice taxiPrice);
        TaxiPrice GetTaxiPrice(long userId);
    }

    public class UserServices : IUserServices
    {
        private readonly UserContext _context;
        private readonly AppSettings _appSettings;
        
        //Initialize user services and add a new user if the table is empty
        public UserServices (IOptions<AppSettings> appSettings, UserContext context){
            _context = context;
            _appSettings = appSettings.Value;

            if(_context.Users.Count() == 0){
                
                _context.Users.Add(new User { 
                    Username = "user",
                    Password = "password",
                    LastName = "user",
                    FirstName = "user",
                    Email = "user@email.com",
                    PhoneNumber = "+111111111",
                    UserType = "CUSTOMER"
                    });
                _context.SaveChanges();

            }
        }

        public Token Authenticate(string username, string password){
            var user = _context.Users.SingleOrDefault(x => x.Username == username && x.Password == password);
            if(user == null) return null;

             // authentication successful so generate jwt token
            Token result = new Token{userId = user.UserId, username = user.Username, userType=user.UserType, token = ""};
            var tokenHandler = new JwtSecurityTokenHandler();
            var key = Encoding.ASCII.GetBytes(_appSettings.Secret);
            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(new Claim[] 
                {
                    new Claim(ClaimTypes.Name, user.UserId.ToString()),
                    new Claim(ClaimTypes.Role, user.UserType)
                }),
                Expires = DateTime.UtcNow.AddDays(7),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
            };
            var token = tokenHandler.CreateToken(tokenDescriptor);
            result.token = tokenHandler.WriteToken(token);

            // remove password before returning
            return result;
        }

        public IEnumerable<User> GetAll()
        {
            // return users without passwords
            return _context.Users.ToList().Select(x => {
                x.Password = null;
                return x;
            });
        }

        public User GetById(long id){
            var user = _context.Users.Find(id);
            if(user == null) return null;
            //remove password
            user.Password = "";
            return user;
        }

        public User Register(User _user){
            try{
                if(_context.Users.FirstOrDefault(u => u.Username == _user.Username) != null) throw new Exception("User with the given username already exists");
                if(_context.Users.FirstOrDefault(u => u.Email == _user.Email) != null) throw new Exception("User with the given e-mail already exists");
                _context.Users.Add(_user);
                _context.SaveChanges();
                SendConfirmationMail(_user.Username, _user.Email);
                return _context.Users.FirstOrDefault(user => user.Username==_user.Username);
            }catch(Exception e){
                throw e;
            }
        }

        public TaxiPrice AddTaxiPrice (TaxiPrice taxiPrice){
            _context.TaxiPrices.Add(taxiPrice);
            _context.SaveChanges();
            return taxiPrice;
        }

        public TaxiPrice UpdateTaxiPrice(TaxiPrice taxiPrice){
            var _taxiPrice = _context.TaxiPrices.Find(taxiPrice.TaxiPriceId);
            if(_taxiPrice == null) throw new ArgumentNullException("TaxiPrice Id does not exist!");
            if(_taxiPrice.UserId != taxiPrice.UserId) throw new AccessViolationException("You are not allowed to change this!");
            _taxiPrice.PricePerHour = taxiPrice.PricePerHour;
            _taxiPrice.PricePerUnit = taxiPrice.PricePerUnit;
            _taxiPrice.SpecialPrice = taxiPrice.SpecialPrice;
            _taxiPrice.StartingPrice = taxiPrice.StartingPrice;
            try{
                _context.TaxiPrices.Update(_taxiPrice);
                _context.SaveChanges();
                return _taxiPrice;
            }catch(Exception e){
                throw e;
            }
        }

        public TaxiPrice GetTaxiPrice(long userId){
            return _context.TaxiPrices.FirstOrDefault(x => x.UserId == userId);
        }

        private void SendConfirmationMail(String username, String email){
            using (var message = new MailMessage())
                {
                    message.To.Add(new MailAddress(email, username));
                    message.From = new MailAddress("BodaBodaTaxiSe2@gmail.com", "BodaBoda");
                    message.Subject = "Registration";
                    message.Body = "Dear "+username+" welcome to BodaBoda!\n Your BodaBoda team!";
                    message.IsBodyHtml = true;

                    using (var client = new SmtpClient("smtp.gmail.com"))
                    {
                        client.Port = 587;
                        client.Credentials = new NetworkCredential("BodaBodaTaxiSe2@gmail.com", "BodaBoda1234");
                        client.EnableSsl = true;
                        client.Send(message);
                    }
                }
        }
    }
}