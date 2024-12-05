package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TemporalType;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class ProgramacionBean extends AbstractDataPersistence<Programacion> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public ProgramacionBean() {
        super(Programacion.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    //Metodo para traer asientos de una programación.
    public List<Programacion> findProgramacionesByDate(Date fechaReservaSeleccionada) {
        return em.createQuery(
                        "SELECT p FROM Programacion p WHERE FUNCTION('DATE', p.desde) = :fecha",
                        Programacion.class)
                .setParameter("fecha", fechaReservaSeleccionada, TemporalType.DATE)
                .getResultList();
    }

    //Metodo con DTO
    public List<ProgramacionDTO> obtenerTodasProgramacionesComoDTO() {
        List<Programacion> programaciones = findRange(0, count());
        return programaciones.stream()
                .map(this::convertirAProgramacionDTO)
                .collect(Collectors.toList());
    }

    public ProgramacionDTO convertirAProgramacionDTO(Programacion programacion) {
        ProgramacionDTO dto = new ProgramacionDTO();
        dto.setIdProgramacion(programacion.getIdProgramacion());
        dto.setDesde(programacion.getDesde());
        dto.setHasta(programacion.getHasta());
        dto.setComentarios(programacion.getComentarios());

        // Convertir Pelicula a DTO
        if (programacion.getIdPelicula() != null) {
            PeliculaDTO peliculaDTO = new PeliculaDTO();
            peliculaDTO.setIdPelicula(programacion.getIdPelicula().getIdPelicula());
            peliculaDTO.setNombre(programacion.getIdPelicula().getNombre());
            peliculaDTO.setSinopsis(programacion.getIdPelicula().getSinopsis());

            // Convertir PeliculaCaracteristica a DTO
            List<PeliculaCaracteristicaDTO> peliculaCaracteristicaDTOs = programacion.getIdPelicula().getPeliculaCaracteristicaList().stream()
                    .map(caracteristica -> {
                        PeliculaCaracteristicaDTO caracteristicaDTO = new PeliculaCaracteristicaDTO();
                        caracteristicaDTO.setIdPeliculaCaracteristica(caracteristica.getIdPeliculaCaracteristica());
                        caracteristicaDTO.setValor(caracteristica.getValor());

                        // Asignar TipoPeliculaDTO desde TipoPelicula
                        if (caracteristica.getIdTipoPelicula() != null) {
                            TipoPeliculaDTO tipoPeliculaDTO = new TipoPeliculaDTO();
                            tipoPeliculaDTO.setIdTipoPelicula(caracteristica.getIdTipoPelicula().getIdTipoPelicula());
                            tipoPeliculaDTO.setNombre(caracteristica.getIdTipoPelicula().getNombre());
                            caracteristicaDTO.setTipoPelicula(tipoPeliculaDTO);
                        }

                        return caracteristicaDTO;
                    })
                    .collect(Collectors.toList());

            peliculaDTO.setPeliculaCaracteristicas(peliculaCaracteristicaDTOs);

            dto.setPelicula(peliculaDTO);
        }

        // Convertir Sala y Asientos a DTO
        if (programacion.getIdSala() != null) {
            SalaDTO salaDTO = new SalaDTO();
            salaDTO.setIdSala(programacion.getIdSala().getIdSala());
            salaDTO.setNombre(programacion.getIdSala().getNombre());
            salaDTO.setActivo(programacion.getIdSala().getActivo());
            salaDTO.setObservaciones(programacion.getIdSala().getObservaciones());

            // Convertir Sucursal a DTO
            if (programacion.getIdSala().getIdSucursal() != null) {
                SucursalDTO sucursalDTO = new SucursalDTO();
                sucursalDTO.setIdSucursal(programacion.getIdSala().getIdSucursal().getIdSucursal());
                sucursalDTO.setNombre(programacion.getIdSala().getIdSucursal().getNombre());
                sucursalDTO.setLongitud(programacion.getIdSala().getIdSucursal().getLongitud());
                sucursalDTO.setLatitud(programacion.getIdSala().getIdSucursal().getLatitud());

                salaDTO.setSucursal(sucursalDTO);
            }

            List<AsientoDTO> asientoDTOs = programacion.getIdSala().getAsientoList().stream()
                    .map(asiento -> {
                        AsientoDTO asientoDTO = new AsientoDTO();
                        asientoDTO.setIdAsiento(asiento.getIdAsiento());
                        asientoDTO.setNombre(asiento.getNombre());
                        return asientoDTO;
                    })
                    .collect(Collectors.toList());

            salaDTO.setAsientoList(asientoDTOs);
            dto.setSala(salaDTO);
        }

        return dto;
    }
}
