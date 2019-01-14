using System;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace BodaBodaServer.Models{
    public class Trip{

        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long TripId {get; set;}
        public string Status {get; set;}
        public double Price {get; set;}
        public bool Paid {get; set;}
        public DateTime TripStart {get; set;}
        public DateTime TripEnd {get; set;}

        //Foregin key to starting location
        public long StartingLocationId {get; set;}
        public virtual Location StartingLocation {get; set;}
        //Foregin key to ending location
        public long EndingLocationId {get; set;}
        public virtual Location EndingLocation{get; set;}
        //Foregin key to customer
        public long CustomerId {get; set;}
        public virtual User Customer {get; set;}
        //Foregin key to taxi
        public long TaxiId {get; set;}
        public virtual User Taxi {get; set;}
        public virtual Payment Payment {get; set;}
    }


    static public class TripStatus{
        public static string REQUESTED { get { return "REQUESTED"; } }

        public static string PROPOSED { get { return "PROPOSED"; } }

        public static string ACCEPTED { get { return "ACCEPTED"; } }

        public static string IN_PROGRESS { get { return "IN_PROGRESS"; } }

        public static string COMPLETED { get { return "COMPLETED"; } }
    }
}