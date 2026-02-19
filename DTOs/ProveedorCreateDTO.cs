using System.ComponentModel.DataAnnotations;

namespace FerreteriaAPI.DTOs
{
    public class ProveedorCreateDTO
    {
        [Required]
        public string Nombre { get; set; }

        public string? Telefono { get; set; }

        [EmailAddress(ErrorMessage = "Formato de email inválido")]
        public string? Email { get; set; }
    }
}
