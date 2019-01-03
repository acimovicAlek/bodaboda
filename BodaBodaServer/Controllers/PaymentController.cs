using System;
using System.Net;
using System.Security.Claims;
using BodaBodaServer.Models;
using BodaBodaServer.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace BodaBodaServer.Controllers{
    [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class PaymentController : ControllerBase{
        
        private IPaymentServices _paymentServices;

        public PaymentController(IPaymentServices  paymentServices){
            _paymentServices = paymentServices;
        }

        [HttpGet("options")]
        public ActionResult GetOptions(){
            var identitiy = HttpContext.User.Identity as ClaimsIdentity;
            var userId = Int32.Parse(identitiy.Name);
            try{
                return Ok(_paymentServices.GetPaymentOptions(userId));
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [HttpPost("options")]
        public ActionResult AddOption([FromBody]PaymentOption paymentOption){
            var identitiy = HttpContext.User.Identity as ClaimsIdentity;
            var userId = Convert.ToInt64(identitiy.Name);
            try{
                return Ok(_paymentServices.AddOption(paymentOption, userId));
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [HttpDelete("options/{id}")]
        public ActionResult DeleteOption(long id){
            var identitiy = HttpContext.User.Identity as ClaimsIdentity;
            var userId = Convert.ToInt64(identitiy.Name);
            try{
                _paymentServices.DeleteOption(id, userId);
                return Ok();
            }catch(AccessViolationException e){
                return StatusCode((int)HttpStatusCode.Forbidden, e.Message);
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [HttpPut("options")]
        public ActionResult UpdateOption([FromBody]PaymentOption paymentOption){
            var identitiy = HttpContext.User.Identity as ClaimsIdentity;
            var userId = Convert.ToInt64(identitiy.Name);
            try{
                return Ok(_paymentServices.UpdateOption(paymentOption, userId));
            }catch(AccessViolationException e){
                return StatusCode((int)HttpStatusCode.Forbidden, e.Message);
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [HttpPost]
        public ActionResult Pay([FromBody]Payment payment){
            var identitiy = HttpContext.User.Identity as ClaimsIdentity;
            var userId = Convert.ToInt64(identitiy.Name);
            try{
                return Ok(_paymentServices.Pay(payment));
            }catch(Exception e){
                return BadRequest(e.Message);
            }
        }

        [HttpGet]
        public ActionResult GetPayments(){
            var identitiy = HttpContext.User.Identity as ClaimsIdentity;
            var userId = Convert.ToInt64(identitiy.Name);
            var role = identitiy.RoleClaimType;
            try{
                return Ok(_paymentServices.GetPayments(userId, role));
            }catch(Exception e){
                return BadRequest(e.Message);
            }
            
        }

    }
}