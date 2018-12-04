namespace BodaBodaServer.Models{
    public class PaymentOption{
        public long PaymentOptionId {get; set;}
        public string OptionType {get; set;}
        public string Details {get; set;}

        //Foreign key to user
        public long UserId {get; set;}
        public virtual User User {get; set;}
    }
}