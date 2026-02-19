using System.Text.Json.Serialization;

namespace FerreteriaAPI.DTOs
{
    public class VentaDTO
    {
        public int Id { get; set; }
        public DateTime Fecha { get; set; }

        public int CantidadVendida { get; set; }
        public decimal Total { get; set; }

        [JsonPropertyName("IdProducto")]
        public int IdProducto { get; set; }

        [JsonPropertyName("Producto")]
        public ProductoDTO? Producto { get; set; }

    }
}