package sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto;

import java.util.List;

public class SalaDTO {
    private Integer idSala;
    private SucursalDTO sucursal;
    private String nombre;
    private Boolean activo;
    private String observaciones;
    private List<AsientoDTO> asientoList;

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<AsientoDTO> getAsientoList() {
        return asientoList;
    }

    public void setAsientoList(List<AsientoDTO> asientoList) {
        this.asientoList = asientoList;
    }
}
