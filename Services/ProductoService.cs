using FerreteriaAPI.Data;
using FerreteriaAPI.Models;
using FerreteriaAPI.DTOs;
using Microsoft.EntityFrameworkCore;

namespace FerreteriaAPI.Services
{
    public class ProductoService
    {
        private readonly FerreteriaContext _context;

        public ProductoService(FerreteriaContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<ProductoDTO>> GetAll()
        {
            return await _context.Productos
                .Include(p => p.Proveedor) 
                .Select(p => new ProductoDTO
                {
                    Id = p.Id,
                    Nombre = p.Nombre,
                    Precio = p.Precio,
                    Stock = p.Stock,
                    IdProveedor = p.IdProveedor,
                    Proveedor = p.Proveedor != null ? new ProveedorDTO
                    {
                        Id = p.Proveedor.Id,
                        Nombre = p.Proveedor.Nombre
                    } : null
                })
                .ToListAsync();
        }

        public async Task<ProductoDTO?> GetById(int id)
        {
            var p = await _context.Productos
                .Include(p => p.Proveedor)
                .FirstOrDefaultAsync(x => x.Id == id);

            if (p == null) return null;

            return new ProductoDTO
            {
                Id = p.Id,
                Nombre = p.Nombre,
                Descripcion = p.Descripcion,
                Precio = p.Precio,
                Stock = p.Stock,
                IdProveedor = p.IdProveedor,
                Proveedor = p.Proveedor != null ? new ProveedorDTO
                {
                    Id = p.Proveedor.Id,
                    Nombre = p.Proveedor.Nombre
                } : null
            };
        }

        public async Task<ProductoDTO> Create(ProductoCreateDTO dto)
        {
            var proveedor = await _context.Proveedores.FindAsync(dto.IdProveedor);
            if (proveedor == null) throw new ArgumentException("Proveedor no existe");

            var producto = new Producto
            {
                Nombre = dto.Nombre,
                Descripcion = dto.Descripcion,
                Precio = dto.Precio,
                Stock = dto.Stock,
                IdProveedor = dto.IdProveedor
            };

            _context.Productos.Add(producto);
            await _context.SaveChangesAsync();

            return new ProductoDTO
            {
                Id = producto.Id,
                Nombre = producto.Nombre,
                Descripcion = producto.Descripcion,
                Precio = producto.Precio,
                Stock = producto.Stock,
                IdProveedor = producto.IdProveedor,
                Proveedor = new ProveedorDTO { Id = proveedor.Id, Nombre = proveedor.Nombre }
            };
        }

        public async Task<bool> Update(int id, ProductoUpdateDTO dto)
        {
            var producto = await _context.Productos.FindAsync(id);
            if (producto == null) return false;

            producto.Nombre = dto.Nombre;
            producto.Descripcion = dto.Descripcion;
            producto.Precio = dto.Precio;
            producto.Stock = dto.Stock;
            producto.IdProveedor = dto.IdProveedor;

            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<bool> Delete(int id)
        {
            var producto = await _context.Productos.FindAsync(id);
            if (producto == null) return false;

            _context.Productos.Remove(producto);
            await _context.SaveChangesAsync();
            return true;
        }
    }
}