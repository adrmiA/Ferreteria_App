package sv.ferreteria.gestorferreteria.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sv.ferreteria.gestorferreteria.models.Venta;
import sv.ferreteria.gestorferreteria.utils.ApiClient;
import java.lang.reflect.Type;
import java.util.List;

public class VentaService {
    private final ApiClient apiClient = new ApiClient();
    private final Gson gson = new Gson();

    public List<Venta> obtenerVentas() throws Exception {
        String json = apiClient.get("/ventas");
        Type listType = new TypeToken<List<Venta>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    public void registrarVenta(int idProducto, int cantidad) throws Exception {

        String json = String.format("{\"IdProducto\": %d, \"CantidadVendida\": %d}", idProducto, cantidad);
        apiClient.post("/ventas", json);
    }
}