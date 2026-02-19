using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FerreteriaAPI.Models
{
        public class Venta
    {
        public int Id { get; set; }

        public DateTime Fecha { get; set; }

        public int IdProducto { get; set; }

        [ForeignKey("IdProducto")]
        public Producto Producto { get; set; }

        [Range(1, int.MaxValue)]
        public int CantidadVendida { get; set; }

        public decimal Total { get; set; }
    }
}

