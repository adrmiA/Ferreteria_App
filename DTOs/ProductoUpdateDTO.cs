using System.ComponentModel.DataAnnotations;

namespace FerreteriaAPI.DTOs
{
    public class ProductoUpdateDTO
    {
        [Required]
        public string Nombre { get; set; }

        public string? Descripcion { get; set; }

        [Range(0, double.MaxValue)]
        public decimal Precio { get; set; }

        [Range(0, int.MaxValue)]
        public int Stock { get; set; }

        [Required]
        public int IdProveedor { get; set; }
    }
}
