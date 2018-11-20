using Microsoft.EntityFrameworkCore;

namespace BodaBodaServer.Models{
    public class UserContext : DbContext
    {
        public UserContext(DbContextOptions<UserContext> options):base(options)
        {
        }
        
        public DbSet<User> Users {get; set;}
    }
}