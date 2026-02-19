package sv.ferreteria.gestorferreteria.models;

import com.google.gson.annotations.SerializedName;

public class Producto {

    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    @SerializedName("IdProveedor")
    private int idProveedor;

    @SerializedName("Proveedor")
    private Proveedor proveedor;

    public Producto() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
}