using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace BodaBodaServer.Models{
    public class Location{
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long LocationId {get; set;}
        public double Longitude {get; set;}
        public double Latitude {get; set;}
        public string LocationType {get; set;}
        public long TTL {get;set;}
    }
}