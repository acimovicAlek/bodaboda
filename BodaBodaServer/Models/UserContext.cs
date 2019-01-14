using Microsoft.EntityFrameworkCore;
using Pomelo.EntityFrameworkCore.MySql;

namespace BodaBodaServer.Models{
    public class UserContext : DbContext
    {
        public UserContext(DbContextOptions<UserContext> options):base(options)
        {
        }
        
        public DbSet<User> Users {get; set;}
        public DbSet<TaxiPrice> TaxiPrices {get; set;}
        public DbSet<Location> Locations {get; set;}
        public DbSet<PaymentOption> PaymentOptions {get; set;}
        public DbSet<Payment> Payments {get; set;}
        public DbSet<Trip> Trips {get; set;}
        
    }
}