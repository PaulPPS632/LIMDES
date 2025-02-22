package upn.pe.inventario_back.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingreso")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String documento;
    private String condicion;
    private String tipoPago;
    private String formapago;

    private Double total;
    private Double gravada;
    private Double impuesto;

    private LocalDateTime fecha_emision;
    private LocalDateTime fecha_vencimiento;
    private String nota;
    @ManyToOne
    @JoinColumn(name = "usuario_empresa_id", nullable = false)
    private UsuarioModel usuarioEmpresa;

    // Usuario proveedor asociado al ingreso
    @ManyToOne
    @JoinColumn(name = "usuario_proveedor_id", nullable = false)
    private UsuarioModel usuarioProveedor;

    @OneToMany(mappedBy = "ingreso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleIngreso> detalles = new ArrayList<>();
}