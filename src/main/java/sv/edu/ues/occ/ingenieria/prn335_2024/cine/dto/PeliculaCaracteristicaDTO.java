package sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto;

public class PeliculaCaracteristicaDTO {
    private Long idPeliculaCaracteristica;
    private TipoPeliculaDTO tipoPelicula;
    private String valor;

    public Long getIdPeliculaCaracteristica() {
        return idPeliculaCaracteristica;
    }

    public void setIdPeliculaCaracteristica(Long idPeliculaCaracteristica) {
        this.idPeliculaCaracteristica = idPeliculaCaracteristica;
    }

    public TipoPeliculaDTO getTipoPelicula() {
        return tipoPelicula;
    }

    public void setTipoPelicula(TipoPeliculaDTO tipoPelicula) {
        this.tipoPelicula = tipoPelicula;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
