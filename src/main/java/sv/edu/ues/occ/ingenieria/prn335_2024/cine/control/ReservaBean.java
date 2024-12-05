/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;

import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

/**
 *
 * @author jjvargas
 */
@LocalBean
@Stateless
public class ReservaBean extends AbstractDataPersistence<Reserva> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    private EntityManager em;
    public ReservaBean() {
        super(Reserva.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public Programacion findProgramacionById(Long idProgramacion) {
        if (idProgramacion != null) {
            return (Programacion) em.find(Programacion.class, idProgramacion);
        }
        return null;
    }

    public TipoReserva findTipoReservaById(Integer idTipoReserva) {
        if (idTipoReserva != null) {
            return (TipoReserva) em.find(TipoReserva.class, idTipoReserva);
        }
        return null;
    }
}