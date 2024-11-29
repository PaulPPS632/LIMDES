package upn.pe.inventario_back.models.dto;

import lombok.Builder;

@Builder
public record ProductoResponse(
        String id,
        String nombre,
        String descripcion,
        String fecha_creacion,
        String fecha_vencimiento,
        Double precio,
        int Stock,
        String unidad_medida,
        Long categoria_id,
        Long linea_id
) {
}
