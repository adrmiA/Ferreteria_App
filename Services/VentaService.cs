using FerreteriaAPI.Data;
using FerreteriaAPI.Models;
using FerreteriaAPI.DTOs;
using Microsoft.EntityFrameworkCore;

namespace FerreteriaAPI.Services
{
    public class VentaService
    {
        private readonly FerreteriaContext _context;

        public VentaService(FerreteriaContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<VentaDTO>> GetAll()
        {
            return await _context.Ventas
                .Include(v => v.Producto) 
                .Select(v => new VentaDTO
                {
                    Id = v.Id,
                    Fecha = v.Fecha,
                    IdProducto = v.IdProducto,
                    CantidadVendida = v.CantidadVendida,
                    Total = v.Total,
                    Producto = v.Producto != null ? new ProductoDTO
                    {
                        Id = v.Producto.Id,
                        Nombre = v.Producto.Nombre
                    } : null
                })
                .ToListAsync();
        }

        public async Task<VentaDTO> RegistrarVenta(VentaCreateDTO dto)
        {
            var producto = await _context.Productos.FindAsync(dto.IdProducto);

            if (producto == null)
                throw new ArgumentException("Producto no encontrado");

            if (producto.Stock < dto.CantidadVendida)
                throw new ArgumentException("Stock insuficiente");

            producto.Stock -= dto.CantidadVendida;

            var venta = new Venta
            {
                Fecha = DateTime.Now,
                IdProducto = dto.IdProducto,
                CantidadVendida = dto.CantidadVendida,
                Total = producto.Precio * dto.CantidadVendida
            };

            _context.Ventas.Add(venta);
            await _context.SaveChangesAsync();

            return new VentaDTO
            {
                Id = venta.Id,
                Fecha = venta.Fecha,
                IdProducto = venta.IdProducto,
                CantidadVendida = venta.CantidadVendida,
                Total = venta.Total,
                Producto = new ProductoDTO
                {
                    Id = producto.Id,
                    Nombre = producto.Nombre
                }
            };
        }
    }
}