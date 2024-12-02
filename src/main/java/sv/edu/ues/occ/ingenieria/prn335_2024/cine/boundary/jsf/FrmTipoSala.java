package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class FrmTipoSala extends AbstractFrm<TipoSala> implements Serializable {

    @Inject
    TipoSalaBean tsBean;
    @Inject
    FacesContext fc;

    @Override
    public void instanciarRegistro() {
        this.registro=new TipoSala();
        this.registro.setActivo(true);
    }

    @Override
    public FacesContext getFC() {
        return fc;
    }

    @Override
    public AbstractDataPersistence<TipoSala> getAbstractDataPersistence() {
        return tsBean;
    }

    @Override
    public String getIdByObject(TipoSala object) {
        if (object!=null){
            return object.getIdTipoSala().toString();
        }
        return null;
    }

    @Override
    public TipoSala getObjectById(String id) {

        if (id!=null && modelo!=null && modelo.getWrappedData()!=null){

            return modelo.getWrappedData().stream().
                    filter(r->id.equals(r.getIdTipoSala().toString())).findFirst().
                    orElseGet(()-> {
                        Logger.getLogger(getClass().getName()).log(Level.INFO,"No se ha encontrado objeto");
                        return null;});
        }
        return null;
    }

    @Override
    public void selecionarFila(SelectEvent<TipoSala> event) {
        TipoSala tipoSalaSelected = (TipoSala) event.getObject();
        FacesMessage mensaje=new FacesMessage("Tipo de sala seleccionada",tipoSalaSelected.getNombre());
        fc.addMessage(null, mensaje);
        this.estado=ESTADO_CRUD.MODIFICAR;
    }

    @Override
    public String paginaNombre() {
        return "Tipo de Sala";
    }
}
