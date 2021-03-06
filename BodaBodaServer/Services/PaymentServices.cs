using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using BodaBodaServer.Models;
using Microsoft.EntityFrameworkCore;

namespace BodaBodaServer.Services{

    public interface IPaymentServices
    {
        IEnumerable<PaymentOption> GetPaymentOptions(long userId);
        PaymentOption AddOption(PaymentOption payemenetOption, long userId);
        void DeleteOption(long id, long userId);
        PaymentOption UpdateOption(PaymentOption paymentOption, long userId);
        Payment Pay(Payment payment);
        IEnumerable<Payment> GetPayments(long userId, string userType);
    }

    public class PaymentServices : IPaymentServices{

        private readonly UserContext _context;
        
        public PaymentServices(UserContext context){
            _context = context;
        }

        public IEnumerable<PaymentOption> GetPaymentOptions(long userId){
            return _context.PaymentOptions.ToList().Where(x => x.UserId == userId);
        }

        public PaymentOption AddOption(PaymentOption paymentOption, long userId){
            try{
                var _user = _context.Users.Find(userId);
                paymentOption.User = _user;
                _context.PaymentOptions.Add(paymentOption);
                _context.SaveChanges();
                return paymentOption;
            }catch(Exception e){
                throw e;
            }
        }

        public void DeleteOption(long id, long userId){
            var _user = _context.Users.Find(userId);
            var _option = _context.PaymentOptions.Find(id);
            if(_option != null && _user != null) {
                if(_user != _option.User) throw new AccessViolationException("You aren't allowed to delete this!");
                try{
                    _context.PaymentOptions.Remove(_option);
                    _context.SaveChanges();
                }catch(Exception e){
                    throw e;
                }
            }else throw new Exception("Bad request parameters!");
        }

        public PaymentOption UpdateOption(PaymentOption paymentOption, long userId){
            var _user = _context.Users.Find(userId);
            if(_user != null) {
                if(_user != paymentOption.User) throw new AccessViolationException("You aren't allowed to modify this!");
                try{
                    var _option = _context.PaymentOptions.Find(paymentOption.PaymentOptionId);
                    if(_option == null) throw new Exception("Wrong parameters!");
                    _option.OptionType = paymentOption.OptionType;
                    _option.Details = paymentOption.Details;
                    _context.PaymentOptions.Update(_option);
                    _context.SaveChanges();
                    return _option;
                }catch(Exception e){
                    throw e;
                }
            }else throw new Exception("Bad request parameters!");
        }

        public Payment Pay(Payment payment){
            //TODO Encrypt, processes
            _context.Payments.Add(payment);
            _context.SaveChanges();
            SendConfirmationMail(payment);
            //ADD PAYMENT LOGIC
            return payment;
        }

        public IEnumerable<Payment> GetPayments(long userId, string userType){
            if(userType == "Taxi") return _context.Payments.ToList().Where(x => x.PayerId == userId);
            else if (userType == "Customer") return _context.Payments.ToList().Where(x => x.PayeeId == userId);
            else throw new Exception("User type does not exist!");
        }

        private void SendConfirmationMail(Payment payment){
            using (var message = new MailMessage())
                {
                    message.To.Add(new MailAddress(payment.Payer.Email, payment.Payer.Username));
                    message.From = new MailAddress("BodaBodaTaxiSe2@gmail.com", "BodaBoda");
                    message.Subject = "Registration";
                    message.Body = "Dear "+payment.Payer.Username+" your payment has been completed for your reacent trio and you paid"+payment.Amount+"for it!\n Your BodaBoda team!";
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