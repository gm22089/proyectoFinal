package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ReservaDetalleBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.ReservaDetalleDTO;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.ReservaDetalleResponseDTO;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.ReservaDetalle;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("reservadetalle")
public class ReservaDetalleResource implements Serializable {

    @Inject
    ReservaDetalleBean rdBean;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response crearReservaDetalle(ReservaDetalleDTO reservaDetalleDTO) {
        try {
            // Crear reserva detalle a partir del DTO
            ReservaDetalle reservaDetalle = new ReservaDetalle();

            // Validar y asignar Reserva
            if (reservaDetalleDTO.getIdReserva() != null) {
                Reserva reserva = rdBean.findReservaById(reservaDetalleDTO.getIdReserva());
                if (reserva == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Reserva no encontrada con el ID especificado.")
                            .build();
                }
                reservaDetalle.setIdReserva(reserva);
            }

            // Validar y asignar Asiento
            if (reservaDetalleDTO.getIdAsiento() != null) {
                Asiento asiento = rdBean.findAsientoById(reservaDetalleDTO.getIdAsiento());
                if (asiento == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Asiento no encontrado con el ID especificado.")
                            .build();
                }
                reservaDetalle.setIdAsiento(asiento);
            }

            // Asignar otros campos
            reservaDetalle.setEstado(reservaDetalleDTO.getEstado());

            // Crear la reserva detalle
            rdBean.create(reservaDetalle);

            // Convertir a ReservaDetalleResponseDTO para la respuesta
            ReservaDetalleResponseDTO responseDTO = new ReservaDetalleResponseDTO(
                    reservaDetalle.getIdReservaDetalle(),
                    reservaDetalleDTO.getIdReserva(),
                    reservaDetalleDTO.getIdAsiento(),
                    reservaDetalleDTO.getEstado()
                );

            // Devolver la respuesta como DTO
            return Response.status(Response.Status.CREATED).entity(responseDTO).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el detalle de la reserva: " + e.getMessage())
                    .build();
        }
    }
}
