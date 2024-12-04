package sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto;

import java.util.Date;
import java.util.List;

public class ProgramacionDTO {

    private Long idProgramacion;
    private Date desde;
    private Date hasta;
    private String comentarios;
    private PeliculaDTO pelicula;
    private SalaDTO sala;
    private List<AsientoDTO> asientos;

    // Constructor vacío
    public ProgramacionDTO() {}

    // Constructor completo
    public ProgramacionDTO(Long idProgramacion, Date desde, Date hasta, String comentarios, PeliculaDTO pelicula, SalaDTO sala, List<AsientoDTO> asientos) {
        this.idProgramacion = idProgramacion;
        this.desde = desde;
        this.hasta = hasta;
        this.comentarios = comentarios;
        this.pelicula = pelicula;
        this.sala = sala;
        this.asientos = asientos;
    }

    // Getters y setters...

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public PeliculaDTO getPelicula() {
        return pelicula;
    }

    public void setPelicula(PeliculaDTO pelicula) {
        this.pelicula = pelicula;
    }

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }

    public List<AsientoDTO> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<AsientoDTO> asientos) {
        this.asientos = asientos;
    }
}
