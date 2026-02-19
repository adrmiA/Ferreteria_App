using System.Text.Json.Serialization;

namespace FerreteriaAPI.DTOs
{
    public class ProductoDTO
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string? Descripcion { get; set; }
        public decimal Precio { get; set; }
        public int Stock { get; set; }

        [JsonPropertyName("IdProveedor")]
        public int IdProveedor { get; set; }

        [JsonPropertyName("Proveedor")]
        public ProveedorDTO? Proveedor { get; set; }
    }
}