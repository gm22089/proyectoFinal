package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AsientoCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.AsientoCaracteristica;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class FrmAsientoCaracteristica extends AbstractFrm<AsientoCaracteristica> implements Serializable {

    @Inject
    AsientoCaracteristicaBean acBean;
    @Inject
    FacesContext fc;

    @Override
    public void instanciarRegistro() {
        this.registro = new AsientoCaracteristica();
    }

    @Override
    public FacesContext getFC() {
        return fc;
    }

    @Override
    public AbstractDataPersistence<AsientoCaracteristica> getAbstractDataPersistence() {
        return acBean;
    }

    @Override
    public String getIdByObject(AsientoCaracteristica object) {
        if (object != null) {
            return object.getIdAsientoCaracteristica().toString(); // Asumiendo que 'idAsientoCaracteristica' es el ID
        }
        return null;
    }

    @Override
    public AsientoCaracteristica getObjectById(String id) {
        if (id != null && modelo != null && modelo.getWrappedData() != null) {
            return modelo.getWrappedData().stream()
                    .filter(r -> id.equals(r.getIdAsientoCaracteristica().toString()))
                    .findFirst()
                    .orElseGet(() -> {
                        Logger.getLogger(getClass().getName()).log(Level.INFO, "No se ha encontrado objeto");
                        return null;
                    });
        }
        return null;
    }

    @Override
    public void selecionarFila(SelectEvent<AsientoCaracteristica> event) {
        AsientoCaracteristica asientoCaracteristicaSelected = (AsientoCaracteristica) event.getObject();
        String idAsientoCaracteristica = asientoCaracteristicaSelected.getIdAsientoCaracteristica().toString();
        String idAsiento = (asientoCaracteristicaSelected.getIdAsiento() != null) ?
                asientoCaracteristicaSelected.getIdAsiento().toString() : "No asignado";
        String idTipoAsiento = (asientoCaracteristicaSelected.getIdTipoAsiento() != null) ?
                asientoCaracteristicaSelected.getIdTipoAsiento().toString() : "No asignado";
        String valor = asientoCaracteristicaSelected.getValor();

        FacesMessage mensaje = new FacesMessage(
                "Asiento Característica seleccionada",
                "ID Asiento Característica: " + idAsientoCaracteristica +
                        ", ID Asiento: " + idAsiento +
                        ", ID Tipo Asiento: " + idTipoAsiento +
                        ", Valor: " + valor
        );
        fc.addMessage(null, mensaje);
        this.estado = ESTADO_CRUD.MODIFICAR;
    }
}
