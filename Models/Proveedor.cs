using System.ComponentModel.DataAnnotations;

namespace FerreteriaAPI.Models
{
    public class Proveedor
    {
        public int Id { get; set; }

        [Required]
        public string Nombre { get; set; }

        public string Telefono { get; set; }

        [EmailAddress]
        public string Email { get; set; }

        public List<Producto> Productos { get; set; }
    }
}

