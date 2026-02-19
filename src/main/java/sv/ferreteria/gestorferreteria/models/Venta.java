package sv.ferreteria.gestorferreteria.models;

import com.google.gson.annotations.SerializedName;

public class Venta {
    private int id;
    private String fecha;

    @SerializedName("IdProducto")
    private int idProducto;

    @SerializedName("Producto")
    private Producto producto;

    private int cantidadVendida;
    private double total;

    public Venta() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public Producto getProducto() { return producto; } // Corregido: Retorna Producto
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidadVendida() { return cantidadVendida; }
    public void setCantidadVendida(int cantidadVendida) { this.cantidadVendida = cantidadVendida; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}