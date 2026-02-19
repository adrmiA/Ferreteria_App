package sv.ferreteria.gestorferreteria.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sv.ferreteria.gestorferreteria.models.Producto;
import sv.ferreteria.gestorferreteria.models.Proveedor;
import sv.ferreteria.gestorferreteria.utils.ApiClient;

import java.lang.reflect.Type;
import java.util.List;

public class ProveedorService {

    private final ApiClient apiClient;
    private final Gson gson;

    public ProveedorService() {
        this.apiClient = new ApiClient();
        this.gson = new Gson();
    }

    public List<Proveedor> obtenerProveedor() throws Exception{
        String json = apiClient.get("/proveedores/");

        Type listType = new TypeToken<List<Proveedor>>(){}.getType();
        return gson.fromJson(json, listType);
    }

    public Proveedor obtenerProveedorPorId(int id) throws Exception{
        String json = apiClient.get("/proveedores/" + id);
        return gson.fromJson(json, Proveedor.class);
    }

    public void agregarProveedor(Proveedor proveedor) throws Exception{
        String json = gson.toJson(proveedor);
        apiClient.post("/proveedores/", json);
    }

    public void actualizarProveedor(int id, Proveedor proveedor) throws Exception{
        String json = gson.toJson(proveedor);
        apiClient.put("/proveedores/" + id, json);
    }


}
