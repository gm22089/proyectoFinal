package sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto;

import java.util.List;

public class PeliculaDTO {

    private Long idPelicula;
    private String nombre;
    private String sinopsis;
    private List<PeliculaCaracteristicaDTO> peliculaCaracteristicas;

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public List<PeliculaCaracteristicaDTO> getPeliculaCaracteristicas() {
        return peliculaCaracteristicas;
    }

    public void setPeliculaCaracteristicas(List<PeliculaCaracteristicaDTO> peliculaCaracteristicas) {
        this.peliculaCaracteristicas = peliculaCaracteristicas;
    }
}
