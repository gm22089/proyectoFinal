package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name = "pelicula_caracteristica", schema = "public")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "PeliculaCaracteristica.findAll", query = "SELECT p FROM TipoPelicula p"),
        @NamedQuery(name = "PeliculaCaracteristica.findByIdPelicula", query = "SELECT pc FROM PeliculaCaracteristica pc WHERE pc.idPelicula.idPelicula = :idPelicula order by pc.idPeliculaCaracteristica asc "),
        @NamedQuery(name = "PeliculaCaracteristica.countByIdPelicula", query = "SELECT COUNT (pc.idPeliculaCaracteristica) FROM PeliculaCaracteristica pc WHERE pc.idPelicula.idPelicula = :idPelicula "),
        @NamedQuery(name = "PeliculaCaracteristica.findByValor", query = "SELECT p FROM PeliculaCaracteristica p WHERE p.valor = :valor")})
public class PeliculaCaracteristica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula_caracteristica", nullable = false)
    private Long idPeliculaCaracteristica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_pelicula")
    private TipoPelicula idTipoPelicula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelicula")
    private Pelicula idPelicula;

    @Lob
    @Column(name = "valor")
    private String valor;

    public PeliculaCaracteristica() {}

    public PeliculaCaracteristica(Long idPeliculaCaracteristica){this.idPeliculaCaracteristica = idPeliculaCaracteristica;}

    public Long getIdPeliculaCaracteristica() {
        return idPeliculaCaracteristica;
    }

    public void setIdPeliculaCaracteristica(Long id) {
        this.idPeliculaCaracteristica = id;
    }

    public TipoPelicula getIdTipoPelicula() {
        return idTipoPelicula;
    }

    public void setIdTipoPelicula(TipoPelicula idTipoPelicula) {
        this.idTipoPelicula = idTipoPelicula;
    }

    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeliculaCaracteristica != null ? idPeliculaCaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeliculaCaracteristica)) {
            return false;
        }
        PeliculaCaracteristica other = (PeliculaCaracteristica) object;
        if ((this.idPeliculaCaracteristica == null && other.idPeliculaCaracteristica != null) || (this.idPeliculaCaracteristica != null && !this.idPeliculaCaracteristica.equals(other.idPeliculaCaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica[ idPeliculaCaracteristica=" + idPeliculaCaracteristica + " ]";
    }

}