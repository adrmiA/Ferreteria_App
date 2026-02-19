package sv.ferreteria.gestorferreteria.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sv.ferreteria.gestorferreteria.models.Producto;
import sv.ferreteria.gestorferreteria.utils.ApiClient;

import java.lang.reflect.Type;
import java.util.List;

public class ProductoService {

    private final ApiClient apiClient;
    private final Gson gson;

    public ProductoService() {
        this.apiClient = new ApiClient();
        this.gson = new Gson();
    }

    public List<Producto> obtenerProductos() throws Exception {
        String json = apiClient.get("/productos/");
        Type listType = new TypeToken<List<Producto>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    public void agregarProducto(Producto producto) throws Exception {
        String json = gson.toJson(producto);
        apiClient.post("/productos/", json);
    }

    public void actualizarProducto(int id, Producto producto) throws Exception {
        String json = gson.toJson(producto);
        apiClient.put("/productos/" + id, json);
    }

    public Producto obtenerProductoPorId(int id) throws Exception {
        String json = apiClient.get("/productos/" + id);
        return gson.fromJson(json, Producto.class);
    }
}