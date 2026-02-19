using System.ComponentModel.DataAnnotations;

namespace FerreteriaAPI.DTOs
{
    public class ProductoCreateDTO
    {
        [Required]
        public string Nombre { get; set; }

        public string? Descripcion { get; set; }

        [Range(0, double.MaxValue, ErrorMessage = "El precio no puede ser negativo")]
        public decimal Precio { get; set; }

        [Range(0, int.MaxValue, ErrorMessage = "El stock no puede ser negativo")]
        public int Stock { get; set; }

        [Required]
        public int IdProveedor { get; set; }
    }
}
