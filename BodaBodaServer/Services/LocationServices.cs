using System;
using System.Collections.Generic;
using System.Linq;
using BodaBodaServer.Models;

namespace BodaBodaServer.Services{
    public interface ILocationServices
    {
        Location CreateLocation(Location _location);
        Location GetLocation(long id);
        void DeleteLocation(long id);
    }

    public class LocationServices : ILocationServices{
        
        private readonly UserContext _context;

        public LocationServices (UserContext context){
            _context = context;
        }

        public Location CreateLocation(Location _location){
            try{
                _context.Locations.Add(_location);
                _context.SaveChanges();
                var t = _context.Locations.Last();
                return t;
            }catch(Exception e){
                throw e;
            }
        }
        
        public Location GetLocation(long id){
            return _context.Locations.Find(id);
        }

        public void DeleteLocation(long id){
            var _location = _context.Locations.Find(id);
            if(_location != null)
            try{
                _context.Locations.Remove(_location);
                _context.SaveChanges();
            }catch(Exception e){
                throw e;
            }
            else throw new Exception("No Location with the given ID!");
        }
    }
}