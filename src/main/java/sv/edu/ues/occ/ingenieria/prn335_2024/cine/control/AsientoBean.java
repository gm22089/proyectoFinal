package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.AsientoCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;

import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class AsientoBean extends AbstractDataPersistence<Asiento> implements Serializable {

    @PersistenceContext(name = "CinePU")
    EntityManager em;

    public AsientoBean() {
        super(Asiento.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public List<Asiento> findAsientosBySalaandProgramacion(Sala idSala, Programacion programacionSelecionada) {
        return List.of();
    }
}