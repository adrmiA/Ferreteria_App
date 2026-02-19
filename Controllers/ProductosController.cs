using Microsoft.AspNetCore.Mvc;
using FerreteriaAPI.Services;
using FerreteriaAPI.DTOs;

namespace FerreteriaAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class ProductosController : ControllerBase
    {
        private readonly ProductoService _service;

        public ProductosController(ProductoService service)
        {
            _service = service;
        }

        // GET api/productos
        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            var productos = await _service.GetAll();
            return Ok(productos); // 200
        }

        // GET api/productos/id
        [HttpGet("{id}")]
        public async Task<IActionResult> GetById(int id)
        {
            var producto = await _service.GetById(id);
            if (producto == null)
                return NotFound(); // 404

            return Ok(producto); // 200
        }

        // POST api/productos
        [HttpPost]
        public async Task<IActionResult> Create(ProductoCreateDTO dto)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState); // 400

            try
            {
                var producto = await _service.Create(dto);
                return CreatedAtAction(nameof(GetById),
                    new { id = producto.Id }, producto); // 201
            }
            catch (ArgumentException ex)
            {
                return BadRequest(ex.Message); // 400
            }
        }

        // PUT api/productos/id
        [HttpPut("{id}")]
        public async Task<IActionResult> Update(int id, ProductoUpdateDTO dto)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            try
            {
                var updated = await _service.Update(id, dto);
                if (!updated)
                    return NotFound(); // 404

                return Ok(); // 200
            }
            catch (ArgumentException ex)
            {
                return BadRequest(ex.Message); // 400
            }
        }

        // DELETE api/productos/id
        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var deleted = await _service.Delete(id);
            if (!deleted)
                return NotFound(); // 404

            return Ok(); // 200
        }
    }
}
