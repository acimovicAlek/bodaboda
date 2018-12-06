using System.ComponentModel.DataAnnotations.Schema;

namespace BodaBodaServer.Models{
    public class TaxiPrice{
        public long TaxiPriceId {get; set;}
        public long UserId {get; set;}
        public double StartingPrice {get; set;}
        public double PricePerUnit {get; set;}
        public double PricePerHour {get; set;}
        public double SpecialPrice {get; set;}

        //Foregin key to user
        [ForeignKey("UserId")]
        public virtual User User {get; set;}

    }
}