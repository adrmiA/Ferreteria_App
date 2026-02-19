using FerreteriaAPI.Data;
using FerreteriaAPI.Models;
using FerreteriaAPI.DTOs;
using Microsoft.EntityFrameworkCore;

namespace FerreteriaAPI.Services
{
    public class ProveedorService
    {
        private readonly FerreteriaContext _context;

        public ProveedorService(FerreteriaContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<ProveedorDTO>> GetAll()
        {
            return await _context.Proveedores
                .Select(p => new ProveedorDTO
                {
                    Id = p.Id,
                    Nombre = p.Nombre,
                    Telefono = p.Telefono,
                    Email = p.Email
                })
                .ToListAsync();
        }

        public async Task<ProveedorDTO?> GetById(int id)
        {
            var p = await _context.Proveedores.FindAsync(id);
            if (p == null) return null;

            return new ProveedorDTO
            {
                Id = p.Id,
                Nombre = p.Nombre,
                Telefono = p.Telefono,
                Email = p.Email
            };
        }

        public async Task<ProveedorDTO> Create(ProveedorCreateDTO dto)
        {
            var proveedor = new Proveedor
            {
                Nombre = dto.Nombre,
                Telefono = dto.Telefono,
                Email = dto.Email
            };

            _context.Proveedores.Add(proveedor);
            await _context.SaveChangesAsync();

            return new ProveedorDTO
            {
                Id = proveedor.Id,
                Nombre = proveedor.Nombre,
                Telefono = proveedor.Telefono,
                Email = proveedor.Email
            };
        }

        public async Task<bool> Update(int id, ProveedorUpdateDTO dto)
        {
            var proveedor = await _context.Proveedores.FindAsync(id);
            if (proveedor == null) return false;

            proveedor.Nombre = dto.Nombre;
            proveedor.Telefono = dto.Telefono;
            proveedor.Email = dto.Email;

            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<bool> Delete(int id)
        {
            var proveedor = await _context.Proveedores.FindAsync(id);
            if (proveedor == null) return false;

            _context.Proveedores.Remove(proveedor);
            await _context.SaveChangesAsync();
            return true;
        }
    }
}
