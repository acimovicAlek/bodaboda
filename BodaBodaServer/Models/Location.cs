using System.ComponentModel.DataAnnotations.Schema;

namespace BodaBodaServer.Models{
    public class Location{
        public long LocationId {get; set;}
        public double Longitude {get; set;}
        public long Latitude {get; set;}
        public string LocationType {get; set;}
        public long UserId {get;set;}
        public long TTL {get;set;}

        //Foregin key to user
        [ForeignKey("UserId")]
        public virtual User User {get; set;}
    }
}