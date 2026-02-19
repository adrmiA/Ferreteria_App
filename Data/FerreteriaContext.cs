using Microsoft.EntityFrameworkCore;
using FerreteriaAPI.Models;
using FerreteriaAPI.DTOs;

namespace FerreteriaAPI.Data
{
    public class FerreteriaContext : DbContext
    {
        public FerreteriaContext(DbContextOptions<FerreteriaContext> options)
            : base(options)
        {
        }
        public DbSet<Producto> Productos { get; set; }
        public DbSet<Proveedor> Proveedores { get; set; }
        public DbSet<Venta> Ventas { get; set; }
    }
}
    
