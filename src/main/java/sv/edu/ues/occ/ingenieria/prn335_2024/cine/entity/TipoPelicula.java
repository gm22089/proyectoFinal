package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_pelicula", schema = "public")
@NamedQueries({
        @NamedQuery(name = "TipoPelicula.findAll", query = "SELECT t FROM TipoPelicula t"),
        @NamedQuery(name = "TipoPelicula.findByIdTipoPelicula", query = "SELECT t FROM TipoPelicula t WHERE t.idTipoPelicula = :idTipoPelicula"),
        @NamedQuery(name = "TipoPelicula.findByNombre", query = "SELECT t FROM TipoPelicula t WHERE t.nombre = :nombre"),
        @NamedQuery(name = "TipoPelicula.findByActivo", query = "SELECT t FROM TipoPelicula t WHERE t.activo = :activo"),
        @NamedQuery(name = "TipoPelicula.findByComentarios", query = "SELECT t FROM TipoPelicula t WHERE t.comentarios = :comentarios"),
        @NamedQuery(name = "TipoPelicula.findByExpresionRegular", query = "SELECT t FROM TipoPelicula t WHERE t.expresionRegular = :expresionRegular")})
public class TipoPelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_pelicula", nullable = false)
    private Integer idTipoPelicula;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    @Lob
    @Column(name = "expresion_regular")
    private String expresionRegular;

    @OneToMany(mappedBy = "idTipoPelicula", fetch = FetchType.LAZY)
    private List<PeliculaCaracteristica> peliculaCaracteristicaList;

    public TipoPelicula() {}

    public TipoPelicula(Integer idTipoPelicula) {this.idTipoPelicula = idTipoPelicula;}

    public Integer getIdTipoPelicula() {
        return idTipoPelicula;
    }

    public void setIdTipoPelicula(Integer id) {
        this.idTipoPelicula = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

    public List<PeliculaCaracteristica> getPeliculaCaracteristicaList() {
        return peliculaCaracteristicaList;
    }

    public void setPeliculaCaracteristicaList(List<PeliculaCaracteristica> peliculaCaracteristicaList) {
        this.peliculaCaracteristicaList = peliculaCaracteristicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPelicula != null ? idTipoPelicula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPelicula)) {
            return false;
        }
        TipoPelicula other = (TipoPelicula) object;
        if ((this.idTipoPelicula == null && other.idTipoPelicula != null) || (this.idTipoPelicula != null && !this.idTipoPelicula.equals(other.idTipoPelicula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPelicula[ idTipoPelicula=" + idTipoPelicula + " ]";
    }
}