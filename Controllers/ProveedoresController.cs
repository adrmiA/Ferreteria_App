using Microsoft.AspNetCore.Mvc;
using FerreteriaAPI.Services;
using FerreteriaAPI.DTOs;

namespace FerreteriaAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class ProveedoresController : ControllerBase
    {
        private readonly ProveedorService _service;

        public ProveedoresController(ProveedorService service)
        {
            _service = service;
        }

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            return Ok(await _service.GetAll());
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetById(int id)
        {
            var proveedor = await _service.GetById(id);
            if (proveedor == null)
                return NotFound();

            return Ok(proveedor);
        }

        [HttpPost]
        public async Task<IActionResult> Create(ProveedorCreateDTO dto)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            var proveedor = await _service.Create(dto);

            return CreatedAtAction(nameof(GetById),
                new { id = proveedor.Id }, proveedor);
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Update(int id, ProveedorUpdateDTO dto)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            var updated = await _service.Update(id, dto);
            if (!updated)
                return NotFound();

            return Ok();
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var deleted = await _service.Delete(id);
            if (!deleted)
                return NotFound();

            return Ok();
        }
    }
}
