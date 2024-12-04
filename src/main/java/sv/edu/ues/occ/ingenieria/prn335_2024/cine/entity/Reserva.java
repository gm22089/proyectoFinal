package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reserva", schema = "public")
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva", nullable = false)
    private Long idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programacion")
    private Programacion idProgramacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_reserva")
    private TipoReserva idTipoReserva;

    @Column(name = "fecha_reserva")
    private Date fechaReserva;

    @Size(max = 155)
    @Column(name = "estado", length = 155)
    private String estado;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    public Reserva() {}

    public Reserva(Long idReserva) {this.idReserva = idReserva;}

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long id) {
        this.idReserva = id;
    }

    public Programacion getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Programacion idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public TipoReserva getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(TipoReserva idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}