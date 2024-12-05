package sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto;

public class ReservaDetalleResponseDTO {

    private Long idReservaDetalle;
    private Long idReserva;
    private Long idAsiento;
    private String estado;

    // Constructor vacío
    public ReservaDetalleResponseDTO() {}

    // Constructor con argumentos
    public ReservaDetalleResponseDTO(Long idReservaDetalle, Long idReserva, Long idAsiento, String estado) {
        this.idReservaDetalle = idReservaDetalle;
        this.idReserva = idReserva;
        this.idAsiento = idAsiento;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getIdReservaDetalle() {
        return idReservaDetalle;
    }

    public void setIdReservaDetalle(Long idReservaDetalle) {
        this.idReservaDetalle = idReservaDetalle;
    }

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
