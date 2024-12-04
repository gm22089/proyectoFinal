package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoProductoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoProducto;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class FrmTipoProducto extends AbstractFrm<TipoProducto> implements Serializable {


    @Inject
    TipoProductoBean tpBean; // Inyectar el bean de TipoProducto
    @Inject
    FacesContext fc;

    @Override
    public void instanciarRegistro() {
        this.registro = new TipoProducto(); // Instanciar un nuevo objeto de TipoProducto
        this.registro.setActivo(true); // Asumiendo que 'activo' es un campo booleano en TipoProducto
    }

    @Override
    public FacesContext getFC() {
        return fc;
    }

    @Override
    public AbstractDataPersistence<TipoProducto> getAbstractDataPersistence() {
        return tpBean; // Cambiar a tpBean para que el bean de TipoProducto sea utilizado
    }

    @Override
    public String getIdByObject(TipoProducto object) {
        if (object != null) {
            return object.getIdTipoProducto().toString(); // Asumiendo que existe un ID para TipoProducto
        }
        return null;
    }

    @Override
    public TipoProducto getObjectById(String id) {
        if (id != null && modelo != null && modelo.getWrappedData() != null) {
            return modelo.getWrappedData().stream()
                    .filter(r -> id.equals(r.getIdTipoProducto().toString())) // Filtrando por el ID de TipoProducto
                    .findFirst()
                    .orElseGet(() -> {
                        Logger.getLogger(getClass().getName()).log(Level.INFO, "No se ha encontrado objeto");
                        return null;
                    });
        }
        return null;
    }

    @Override
    public void selecionarFila(SelectEvent<TipoProducto> event) {
        TipoProducto tipoProductoSelected = (TipoProducto) event.getObject();
        FacesMessage mensaje = new FacesMessage("Tipo de producto seleccionado", tipoProductoSelected.getNombre());
        fc.addMessage(null, mensaje);
        this.estado = ESTADO_CRUD.MODIFICAR; // Cambiar estado para modificar un producto
    }

    @Override
    public String paginaNombre() {
        return "Tipo Producto";
    }
}
