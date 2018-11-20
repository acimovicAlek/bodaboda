using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;
using BodaBodaServer.Models;
using Microsoft.AspNetCore.Authorization;
using BodaBodaServer.Services;

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

    }    
}