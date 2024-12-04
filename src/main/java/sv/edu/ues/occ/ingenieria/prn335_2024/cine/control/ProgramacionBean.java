package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.AsientoDTO;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.PeliculaDTO;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.ProgramacionDTO;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.SalaDTO;
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

    public List<Programacion> findProgramacionesByDate(Date fechaReservaSelecionada) {
        return List.of();
    }

    //Metodo para traer asientos de una programación.
    public List<Programacion> findProgramacionWithAsientos() {
        return em.createQuery(
                        "SELECT p FROM Programacion p "
                                + "LEFT JOIN FETCH p.idSala s "
                                + "LEFT JOIN FETCH s.asientoList "
                                + "LEFT JOIN FETCH p.idPelicula",
                        Programacion.class)
                .getResultList();
    }

    //Metodo con DTO
    public List<ProgramacionDTO> obtenerTodasProgramacionesComoDTO() {
        List<Programacion> programaciones = findRange(0, count());
        return programaciones.stream()
                .map(this::convertirAProgramacionDTO)
                .collect(Collectors.toList());
    }

    private ProgramacionDTO convertirAProgramacionDTO(Programacion programacion) {
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
            // Otros campos que necesitas
            dto.setPelicula(peliculaDTO);
        }

        // Convertir Sala y Asientos a DTO
        if (programacion.getIdSala() != null) {
            SalaDTO salaDTO = new SalaDTO();
            salaDTO.setIdSala(programacion.getIdSala().getIdSala());
            salaDTO.setNombre(programacion.getIdSala().getNombre());
            // Otros campos que necesitas
            dto.setSala(salaDTO);

            List<AsientoDTO> asientoDTOs = programacion.getIdSala().getAsientoList().stream()
                    .map(asiento -> {
                        AsientoDTO asientoDTO = new AsientoDTO();
                        asientoDTO.setIdAsiento(asiento.getIdAsiento());
                        asientoDTO.setNombre(asiento.getNombre());
                        // Otros campos que necesitas
                        return asientoDTO;
                    })
                    .collect(Collectors.toList());

            dto.setAsientos(asientoDTOs);
        }

        return dto;
    }
}
