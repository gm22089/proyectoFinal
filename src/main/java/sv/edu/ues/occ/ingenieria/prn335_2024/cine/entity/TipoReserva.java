package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_reserva", schema = "public")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TipoReserva.findAll", query = "SELECT t FROM TipoReserva t"),
        @NamedQuery(name = "TipoReserva.findByIdTipoReserva", query = "SELECT t FROM TipoReserva t WHERE t.idTipoReserva = :idTipoReserva"),
        @NamedQuery(name = "TipoReserva.findByNombre", query = "SELECT t FROM TipoReserva t WHERE t.nombre = :nombre"),
        @NamedQuery(name = "TipoReserva.findByActivo", query = "SELECT t FROM TipoReserva t WHERE t.activo = :activo"),
        @NamedQuery(name = "TipoReserva.findByComentarios", query = "SELECT t FROM TipoReserva t WHERE t.comentarios = :comentarios")})
public class TipoReserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_reserva", nullable = false)
    private Integer idTipoReserva;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    @OneToMany(mappedBy = "idTipoReserva", fetch = FetchType.LAZY)
    @JsonbTransient
    private List<Reserva> reservaList;

    public TipoReserva() {}

    public TipoReserva(Integer idTipoReserva) {this.idTipoReserva = idTipoReserva;}

    public Integer getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(Integer id) {
        this.idTipoReserva = id;
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

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoReserva != null ? idTipoReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoReserva)) {
            return false;
        }
        TipoReserva other = (TipoReserva) object;
        if ((this.idTipoReserva == null && other.idTipoReserva != null) || (this.idTipoReserva != null && !this.idTipoReserva.equals(other.idTipoReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva[ idTipoReserva=" + idTipoReserva + " ]";
    }
}