package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.ReservaDTO;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.ReservaResponseDTO;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;

@Path("reserva")
public class ReservaResource implements Serializable {
    @Inject
    ReservaBean rBean;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response crearReserva(ReservaDTO reservaDTO) {
        try {
            // Crear reserva a partir del DTO
            Reserva reserva = new Reserva();

            // Validar y asignar Programacion
            if (reservaDTO.getIdProgramacion() != null) {
                Programacion programacion = rBean.findProgramacionById(reservaDTO.getIdProgramacion());
                if (programacion == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Programacion no encontrada con el ID especificado.")
                            .build();
                }
                reserva.setIdProgramacion(programacion);
            }

            // Validar y asignar TipoReserva
            if (reservaDTO.getIdTipoReserva() != null) {
                TipoReserva tipoReserva = rBean.findTipoReservaById(reservaDTO.getIdTipoReserva());
                if (tipoReserva == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("TipoReserva no encontrada con el ID especificado.")
                            .build();
                }
                reserva.setIdTipoReserva(tipoReserva);
            }

            // Asignar otros campos
            reserva.setFechaReserva(reservaDTO.getFechaReserva());
            reserva.setEstado(reservaDTO.getEstado());
            reserva.setObservaciones(reservaDTO.getObservaciones());

            // Crear la reserva
            rBean.create(reserva);

            // Convertir a ReservaResponseDTO para la respuesta
            ReservaResponseDTO responseDTO = new ReservaResponseDTO(
                    reserva.getIdReserva(), // Asume que Reserva tiene un método getId()
                    reservaDTO.getIdProgramacion(),
                    reservaDTO.getIdTipoReserva(),
                    reserva.getEstado()
            );

            // Devolver la respuesta como DTO
            return Response.status(Response.Status.CREATED).entity(responseDTO).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear la reserva: " + e.getMessage())
                    .build();
        }
    }
}