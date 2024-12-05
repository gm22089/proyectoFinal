package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ProgramacionBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.ProgramacionDTO;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("programacion")
public class ProgramacionResource {

    @Inject
    ProgramacionBean prBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProgramacion(
            @QueryParam("fecha")
            String fecha) {
        try {
            List<Programacion> programaciones;

            // Verificar si el parámetro de fecha ha sido proporcionado
            if (fecha != null && !fecha.isEmpty()) {
                // Parsear la fecha desde el String, asumiendo el formato "yyyy-MM-dd"
                Date fechaReservaSeleccionada = java.sql.Date.valueOf(fecha);
                programaciones = prBean.findProgramacionesByDate(fechaReservaSeleccionada);
                List<ProgramacionDTO> encontrados = programaciones.stream()
                        .map(prBean::convertirAProgramacionDTO)
                        .collect(Collectors.toList());
                Long total = (long) programaciones.size();
                Response.ResponseBuilder builder = Response.ok(encontrados)
                        .header("Total-Records", total)
                        .type(MediaType.APPLICATION_JSON);
                return builder.build();
            } else {
                return Response.status(422).header("Wrong-Parameter", "A date is needed").build();
            }

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
