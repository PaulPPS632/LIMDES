package upn.pe.inventario_back.models.dto;

public record UsuarioRequest(
        String username,
        String password,
        String documento,
        String nombre,
        String direccion,
        String telefono
) {
}
