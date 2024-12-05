package sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto;

public class ReservaResponseDTO {
    private Long idReserva;
    private Long idProgramacion;
    private Integer idTipoReserva;
    private String estado;

    // Constructor sin argumentos
    public ReservaResponseDTO() {}

    // Constructor con argumentos
    public ReservaResponseDTO(Long idReserva, Long idProgramacion, Integer idTipoReserva, String estado) {
        this.idReserva = idReserva;
        this.idProgramacion = idProgramacion;
        this.idTipoReserva = idTipoReserva;
        this.estado = estado;
    }

    // Getters y setters
    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}