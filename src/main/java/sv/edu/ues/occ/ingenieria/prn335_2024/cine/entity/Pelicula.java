package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pelicula", schema = "public")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula", nullable = false)
    private Long idPelicula;

    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name = "sinopsis")
    private String sinopsis;

    public Long getId() {
        return idPelicula;
    }

    public void setId(Long id) {
        this.idPelicula = id;
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

}