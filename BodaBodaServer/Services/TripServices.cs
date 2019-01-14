using System;
using System.Collections.Generic;
using System.Linq;
using BodaBodaServer.Models;

namespace BodaBodaServer.Services{
    
    enum simpleEnum {REQUESTED, ACCEPTED}
    public interface ITripServices
    {
        Trip Create(Trip _trip);
        IEnumerable<Trip> GetRequested();
        IEnumerable<Trip> GetByCustomerId(long id);
        IEnumerable<Trip> GetByTaxiId(long id);
        Trip GetTrip(long id);
        Trip UpdateSatus(Trip _trip);
        void Delete(long id);
        
    }

    public class TripServices : ITripServices{
        
        private readonly UserContext _context;

        public TripServices (UserContext context){
            _context = context;
        }

        
        public Trip Create(Trip _trip){
            try{
                var user = _context.Users.Find(_trip.CustomerId);
                var startingLocation = _context.Locations.Find(_trip.StartingLocationId);
                var endingLocation = _context.Locations.Find(_trip.EndingLocationId);
                _trip.Customer = user;
                _trip.TripStart = DateTime.Now;
                _context.Trips.Add(_trip);
                _context.SaveChanges();
                var t = _context.Trips.Last();
                return t;
            }catch(Exception e){
                throw e;
            }
        }

        public IEnumerable<Trip> GetRequested(){
            return _context.Trips.ToList().Where(x => x.Status == TripStatus.REQUESTED);
        }

        public IEnumerable<Trip> GetByCustomerId(long id){
            var _customer = _context.Users.Find(id);
            if(_customer != null) return _context.Trips.ToList().Where(x => x.Customer == _customer);
            else throw new System.Exception("No customers with the given ID!");
        }
        public IEnumerable<Trip> GetByTaxiId(long id){
            var _taxi = _context.Users.Find(id);
            if(_taxi != null) return _context.Trips.ToList().Where(x => x.TaxiId == _taxi.UserId);
            else throw new System.Exception("No Taxi with the given ID!");
        }
        public Trip GetTrip(long id){
            return _context.Trips.Find(id);
        }
        public Trip UpdateSatus(Trip _trip){
            switch(_trip.Status){
                case "ACCEPTED":
                    break;
                case "IN_PROGRESS":
                    break;
                case "COMPLETED":
                    break;
                case "REQUESTED":
                    break;
                default:
                    throw new ArgumentException("Status not valid!");
            }
            try{
                var trip = _context.Trips.Find(_trip.TripId);
                trip.Status = _trip.Status;
                _context.Trips.Update(trip);
                _context.SaveChanges();
                return trip;
            }catch(Exception e){
                throw e;
            }
        }
        public void Delete(long id){
            var _trip = _context.Trips.Find(id);
            if(_trip != null)
            try{
                _context.Trips.Remove(_trip);
                _context.SaveChanges();
            }catch(Exception e){
                throw e;
            }
            else throw new Exception("No Trips with the given ID!");
        }

        

    }
}
