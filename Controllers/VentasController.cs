using Microsoft.AspNetCore.Mvc;
using FerreteriaAPI.Services;
using FerreteriaAPI.DTOs;

namespace FerreteriaAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class VentasController : ControllerBase
    {
        private readonly VentaService _service;

        public VentasController(VentaService service)
        {
            _service = service;
        }

        // GET api/ventas
        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            var ventas = await _service.GetAll();
            return Ok(ventas);
        }

        // POST api/ventas
        [HttpPost]
        public async Task<IActionResult> RegistrarVenta(VentaCreateDTO dto)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            try
            {
                var venta = await _service.RegistrarVenta(dto);
                return Created("", venta); // 201
            }
            catch (ArgumentException ex)
            {
                return BadRequest(ex.Message); // 400
            }
        }
    }
}
