package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_asiento", schema = "public")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TipoAsiento.findAll", query = "SELECT t FROM TipoAsiento t"),
        @NamedQuery(name = "TipoAsiento.findByIdTipoAsiento", query = "SELECT t FROM TipoAsiento t WHERE t.idTipoAsiento = :idTipoAsiento"),
        @NamedQuery(name = "TipoAsiento.findByNombre", query = "SELECT t FROM TipoAsiento t WHERE t.nombre = :nombre"),
        @NamedQuery(name = "TipoAsiento.findByActivo", query = "SELECT t FROM TipoAsiento t WHERE t.activo = :activo"),
        @NamedQuery(name = "TipoAsiento.findByComentarios", query = "SELECT t FROM TipoAsiento t WHERE t.comentarios = :comentarios"),
        @NamedQuery(name = "TipoAsiento.findByExpresionRegular", query = "SELECT t FROM TipoAsiento t WHERE t.expresionRegular = :expresionRegular")})
public class TipoAsiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_asiento", nullable = false)
    private Integer idTipoAsiento;

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

    @OneToMany(mappedBy = "idTipoAsiento", fetch = FetchType.LAZY)
    private List<AsientoCaracteristica> asientoCaracteristicaList;

    public TipoAsiento() {}

    public TipoAsiento(Integer idTipoAsiento){this.idTipoAsiento=idTipoAsiento;}

    public Integer getIdTipoAsiento() {
        return idTipoAsiento;
    }

    public void setIdTipoAsiento(Integer id) {
        this.idTipoAsiento = id;
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

    public List<AsientoCaracteristica> getAsientoCaracteristicaList() {
        return asientoCaracteristicaList;
    }

    public void setAsientoCaracteristicaList(List<AsientoCaracteristica> asientoCaracteristicaList) {
        this.asientoCaracteristicaList = asientoCaracteristicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoAsiento != null ? idTipoAsiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAsiento)) {
            return false;
        }
        TipoAsiento other = (TipoAsiento) object;
        if ((this.idTipoAsiento == null && other.idTipoAsiento != null) || (this.idTipoAsiento != null && !this.idTipoAsiento.equals(other.idTipoAsiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoAsiento[ idTipoAsiento=" + idTipoAsiento + " ]";
    }
}