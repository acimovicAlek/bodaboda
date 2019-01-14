using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BodaBodaServer.Models{
    public class Payment{
        [Key]
        public long PaymentId {get; set;}
        public string Description {get; set;}
        public double Amount {get; set;}
        public string Status{get; set;}
        public DateTime TimeStamp {get; set;}

        //Foregin key to trip

        //Foregin key to payer
        public long PayerId{get; set;}
        public virtual User Payer{get; set;}
        //Foregin key to Payee
        public long PayeeId {get; set;}
        public virtual User Payee {get; set;}
        public Trip Trip {get; set;}
        public long TripId {get;set;}
    }
}