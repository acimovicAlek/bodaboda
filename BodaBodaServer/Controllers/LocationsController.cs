using System;
using BodaBodaServer.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using BodaBodaServer.Models;
using System.Security.Claims;
using System.Collections.Generic;

namespace BodaBodaServer.Controllers{
    [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class LocationController : ControllerBase{
        
        private ILocationServices _locationService;

        public LocationController(ILocationServices LocationServices){
            _locationService = LocationServices;
        }

        [HttpGet("{id}")]
        public ActionResult GetLocation(long id){
            try{
                return Ok(_locationService.GetLocation(id));
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [HttpDelete("{id}")]
        public ActionResult DeleteLocation(long id){
            try{
                _locationService.DeleteLocation(id);
                return Ok("Location deleted!");
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [HttpPost]
        public ActionResult CreateLocation([FromBody]Location _location){
            try{
                return Created("New location created!", _locationService.CreateLocation(_location));
            }catch(Exception e){
                return BadRequest(e.ToString());
            }
        }
    }

}