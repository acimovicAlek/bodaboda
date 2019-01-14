using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace BodaBodaServer.Models{
    static public class UserType{
        public static string Customer { get { return "Customer"; } }

        public static string Taxi { get { return "Taxi"; } }

        public static string Admin { get { return "Admin"; } }
    }

    public class User{
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long UserId {get; set;}
        public string Username {get; set;}
        public string Password {get; set;}
        public string UserType {get; set;}
        public string FirstName {get; set;}
        public string LastName {get; set;}
        public string Email {get; set;}
        public string PhoneNumber {get; set;}

        //Navigation to taxi price if user is a taxi driver
        public virtual TaxiPrice TaxiPrice {get; set;}
        //Navigation to payment options for a user
        public virtual ICollection<PaymentOption> PaymentOptions {get; set;}
    }
}