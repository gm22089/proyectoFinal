package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ProgramacionBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class FrmProgramacion extends AbstractFrm<Programacion> implements Serializable {

    @Inject
    ProgramacionBean programacionBean; // Bean para la lógica de Programación
    @Inject
    FacesContext fc; // Contexto de Faces

    @Override
    public void instanciarRegistro() {
        this.registro = new Programacion();
        // Aquí puedes inicializar los valores por defecto para el objeto Programacion, si es necesario
    }

    @Override
    public FacesContext getFC() {
        return fc;
    }

    @Override
    public AbstractDataPersistence<Programacion> getAbstractDataPersistence() {
        return programacionBean; // Retorna el bean de Programación
    }

    @Override
    public String getIdByObject(Programacion object) {
        if (object != null) {
            return object.getIdProgramacion().toString(); // Retorna el ID de la programación como String
        }
        return null;
    }

    @Override
    public Programacion getObjectById(String id) {
        if (id != null && modelo != null && modelo.getWrappedData() != null) {
            return modelo.getWrappedData().stream().
                    filter(p -> id.equals(p.getIdProgramacion().toString())).findFirst().
                    orElseGet(() -> {
                        Logger.getLogger(getClass().getName()).log(Level.INFO, "No se ha encontrado la programación");
                        return null;
                    });
        }
        return null;
    }

    @Override
    public void selecionarFila(SelectEvent<Programacion> event) {
        Programacion programacionSeleccionada = (Programacion) event.getObject();
        FacesMessage mensaje = new FacesMessage("Programación seleccionada", programacionSeleccionada.getIdProgramacion().toString());
        fc.addMessage(null, mensaje);
        this.estado = ESTADO_CRUD.MODIFICAR; // Cambia el estado a MODIFICAR
    }

    @Override
    public String paginaNombre() {
        return "Programación"; // Retorna el nombre de la página
    }

}
