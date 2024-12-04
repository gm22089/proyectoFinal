package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class FrmAsiento extends AbstractFrm<Asiento> implements Serializable {

    @Inject
    private AsientoBean asientoBean;

    @Inject
    private FacesContext facesContext;

    @Override
    public void instanciarRegistro() {
        this.registro = new Asiento(); // Crea un nuevo registro
    }

    @Override
    public FacesContext getFC() {
        return facesContext;
    }

    @Override
    public AbstractDataPersistence<Asiento> getAbstractDataPersistence() {
        return asientoBean; // Devuelve el bean de persistencia
    }

    @Override
    public String getIdByObject(Asiento object) {
        return (object != null && object.getIdAsiento() != null) ? object.getIdAsiento().toString() : null;
    }

    @Override
    public Asiento getObjectById(String id) {
        try {
            if (id != null && !id.isEmpty()) {
                Long asientoId = Long.parseLong(id);
                return asientoBean.findById(asientoId); // Recupera el asiento por ID
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al convertir el ID", e);
        }
        return null;
    }

    @Override
    public void selecionarFila(SelectEvent<Asiento> event) {
        Asiento asientoSeleccionado = event.getObject();
        if (asientoSeleccionado != null) {
            this.registro = asientoSeleccionado;
            this.estado = ESTADO_CRUD.MODIFICAR;

            FacesMessage message = new FacesMessage("Asiento seleccionado", asientoSeleccionado.getNombre());
            facesContext.addMessage(null, message);
        }
    }

    @Override
    public String paginaNombre() {
        return "Asiento";
    }

    public void btnGuardarHandler() {
        try {
            if (registro != null) {
                if (registro.getIdAsiento() == null) {
                    asientoBean.create(registro); // Crear nuevo
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Guardado exitoso", "El asiento fue creado.");
                } else {
                    asientoBean.update(registro); // Modificar existente
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Actualización exitosa", "El asiento fue actualizado.");
                }
                limpiarFormulario();
            }
        } catch (Exception e) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al guardar", e.getMessage());
        }
    }

    public void btnModificarHandler() {
        try {
            if (registro != null) {
                asientoBean.update(registro); // Actualiza el registro
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Modificación exitosa", "El asiento fue modificado.");

                // Recarga la lista de registros
                this.registros = getUpdateList();

                limpiarFormulario();
            }
        } catch (Exception e) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al modificar", e.getMessage());
        }
    }


    public void btnEliminarHandler() {
        try {
            if (registro != null) {
                asientoBean.delete(registro);
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Eliminación exitosa", "El asiento fue eliminado.");
                limpiarFormulario();
            }
        } catch (Exception e) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al eliminar", e.getMessage());
        }
    }

    public void btnNuevoHandler() {
        instanciarRegistro();
        this.estado = ESTADO_CRUD.CREAR;
    }

    public void btnCancelarHandler() {
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        this.registro = null;
        this.estado = ESTADO_CRUD.NINGUNO;
    }

    private void mostrarMensaje(FacesMessage.Severity severity, String resumen, String detalle) {
        FacesMessage mensaje = new FacesMessage(severity, resumen, detalle);
        facesContext.addMessage(null, mensaje);
    }
}
