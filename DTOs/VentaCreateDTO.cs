using System.ComponentModel.DataAnnotations;

namespace FerreteriaAPI.DTOs
{
    public class VentaCreateDTO
    {
        [Required]
        public int IdProducto { get; set; }

        [Range(1, int.MaxValue, ErrorMessage = "Debe vender al menos 1 unidad")]
        public int CantidadVendida { get; set; }
    }
}
