using System.ComponentModel.DataAnnotations;

namespace FerreteriaAPI.DTOs
{
    public class ProveedorUpdateDTO
    {
        [Required]
        public string Nombre { get; set; }

        public string? Telefono { get; set; }

        [EmailAddress]
        public string? Email { get; set; }
    }
}
