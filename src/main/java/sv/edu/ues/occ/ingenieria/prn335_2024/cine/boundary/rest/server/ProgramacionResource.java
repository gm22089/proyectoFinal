package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ProgramacionBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.dto.ProgramacionDTO;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("programacion")
public class ProgramacionResource {

    @Inject
    ProgramacionBean prBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProgramacion(
            @QueryParam("first")
            @DefaultValue("0")
            int firstResult,
            @QueryParam("max")
            @DefaultValue("50")
            int maxResults) {
        try{
            if(firstResult >= 0 && maxResults > 0 && maxResults <= 50){
                List<ProgramacionDTO> encontrados = prBean.obtenerTodasProgramacionesComoDTO();
                Long total = (long) prBean.count();
                Response.ResponseBuilder builder = Response.ok(encontrados)
                        .header("Total-Records", total)
                        .type(MediaType.APPLICATION_JSON);
                return builder.build();
            }else{
                return Response.status(422).header("Wrong-Parameter", "first:"+firstResult+", max:"+maxResults).build();
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
