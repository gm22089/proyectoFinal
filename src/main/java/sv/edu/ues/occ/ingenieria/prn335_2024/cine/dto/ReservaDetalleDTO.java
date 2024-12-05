package sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto;

public class ReservaDetalleDTO {
    private Long idReserva;
    private Long idAsiento;
    private String estado;

    // Constructor vacío
    public ReservaDetalleDTO() {}

    // Getters y Setters
    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Long getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Long idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
