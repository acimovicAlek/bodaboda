using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
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
                    username = "user",
                    password = "password",
                    lastName = "user",
                    firstName = "user",
                    email = "user@email.com",
                    phoneNumber = "+111111111",
                    userType = "CUSTOMER"
                    });
                _context.SaveChanges();

            }
        }

        public Token Authenticate(string username, string password){
            var user = _context.Users.SingleOrDefault(x => x.username == username && x.password == password);
            if(user == null) return null;

             // authentication successful so generate jwt token
            Token result = new Token{userId = user.Id, username = user.username, userType=user.userType, token = ""};
            var tokenHandler = new JwtSecurityTokenHandler();
            var key = Encoding.ASCII.GetBytes(_appSettings.Secret);
            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(new Claim[] 
                {
                    new Claim(ClaimTypes.Name, user.Id.ToString())
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
                x.password = null;
                return x;
            });
        }

        public User GetById(long id){
            var user = _context.Users.Find(id);
            if(user == null) return null;
            //remove password
            user.password = "";
            return user;
        }

    }
}