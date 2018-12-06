using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;
using BodaBodaServer.Models;
using Microsoft.AspNetCore.Authorization;
using BodaBodaServer.Services;
using System;
using System.Security.Claims;
using System.Net;

namespace BodaBodaServer.Controllers
{   
    [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class UsersController : ControllerBase{

        private IUserServices _userService;

        public UsersController(IUserServices userService)
        {
            _userService = userService;
        }

        [AllowAnonymous]
        [HttpPost("authenticate")]
        public IActionResult Authenticate([FromBody]Login userParam){
            Token token = _userService.Authenticate(userParam.username, userParam.password);

            if (token == null)
                return BadRequest(new { message = "Username or password is incorrect" });

            return Ok(token);
        }

        [HttpGet("{id}", Name="GetUser")]
        public ActionResult<User> GetById(long id){
            var item = _userService.GetById(id);
            if(item == null){
                return NotFound();
            }
            return item;
        }

        [AllowAnonymous]
        [HttpPost]
        public ActionResult<User> Register([FromBody]User _user){
            try{
                return Created("New user created!", _userService.Register(_user));
            }catch(Exception e){
                return BadRequest(e.ToString());
            }
        }

        [Authorize(Roles = "Taxi")]
        [HttpGet("prices")]
        public ActionResult GetTaxiPrice(){
            var identitiy = HttpContext.User.Identity as ClaimsIdentity;
            var userId = Convert.ToInt64(identitiy.FindFirst("Name").Value);
            try{
                return Ok(_userService.GetTaxiPrice(userId));
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [Authorize(Roles = "Taxi")]
        [HttpPost("prices")]
        public ActionResult AddTaxiPrice([FromBody] TaxiPrice taxiPrice){
            var identitiy = HttpContext.User.Identity as ClaimsIdentity;
            var userId = Convert.ToInt64(identitiy.FindFirst("Name").Value);
            taxiPrice.UserId = userId;
            try{
                return Ok(_userService.AddTaxiPrice(taxiPrice));
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [Authorize(Roles = "Taxi")]
        [HttpPut("prices")]
        public ActionResult UpdateTaxiprice([FromBody] TaxiPrice taxiPrice){
            var identitiy = HttpContext.User.Identity as ClaimsIdentity;
            var userId = Convert.ToInt64(identitiy.FindFirst("Name").Value);
            taxiPrice.UserId = userId;
            try{
                return Ok(_userService.UpdateTaxiPrice(taxiPrice));
            }catch(AccessViolationException e){
                return StatusCode((int)HttpStatusCode.Forbidden, e.Message);
            }catch(ArgumentNullException e){
                return StatusCode((int)HttpStatusCode.NotFound, e.Message);
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

    }    
}