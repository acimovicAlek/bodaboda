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
    public class TripsController : ControllerBase{
        
        private ITripServices _tripService;

        public TripsController(ITripServices tripServices){
            _tripService = tripServices;
        }

        [HttpGet("requested")]
        public ActionResult GetRequested(){
            try{
                return Ok(_tripService.GetRequested());
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }


        //Based on the user type that is making the request the function will return the list of trips
        //from customer or taxi
        [HttpGet]
        public ActionResult GetByUser(){
            var identity = HttpContext.User.Identity as ClaimsIdentity;
            if(identity.FindFirst(c => c.Type == ClaimTypes.Role).Value == UserType.Customer) 
                return Ok(_tripService.GetByCustomerId(
                    Convert.ToInt64(identity.Name)
                ));
            else if(identity.FindFirst(c => c.Type == ClaimTypes.Role).Value == UserType.Taxi)
                return Ok(_tripService.GetByTaxiId(
                    Convert.ToInt64(identity.Name)
                ));
            else return BadRequest();
        }

        [HttpPut]
        public ActionResult UpdateTrip([FromBody] Trip _trip){
            try{
                return Ok(_tripService.UpdateSatus(_trip));
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [HttpDelete("{id}")]
        public ActionResult DeleteTrip(long id){
            try{
                _tripService.Delete(id);
                return Ok("Trip deleted!");
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [HttpPost]
        public ActionResult<Trip> Create([FromBody]Trip _trip){
            try{
                return Created("New trip created!", _tripService.Create(_trip));
            }catch(Exception e){
                return BadRequest(e.ToString());
            }
        }
    }

}