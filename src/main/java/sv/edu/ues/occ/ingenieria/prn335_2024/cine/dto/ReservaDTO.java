package sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto;

import java.util.Date;

public class ReservaDTO {

    private Long idProgramacion;
    private Integer idTipoReserva;
    private Date fechaReserva;
    private String estado;
    private String observaciones;

    // Constructor vacío
    public ReservaDTO() {}

    // Getters y Setters

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Integer getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(Integer idTipoReserva) {
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
