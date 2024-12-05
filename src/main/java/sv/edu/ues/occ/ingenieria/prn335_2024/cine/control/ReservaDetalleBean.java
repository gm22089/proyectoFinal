/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

/**
 *
 * @author jjvargas
 */

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.ReservaDetalle;

import java.io.Serializable;
@Stateless
@LocalBean
public class ReservaDetalleBean extends AbstractDataPersistence<ReservaDetalle> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;
    public ReservaDetalleBean() {
        super(ReservaDetalle.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public Reserva findReservaById(Long idReserva) {
        if(idReserva != null) {
            return em.find(Reserva.class, idReserva);
        }
        return null;
    }

    public Asiento findAsientoById(Long idAsiento) {
        if(idAsiento != null) {
            return em.find(Asiento.class, idAsiento);
        }
        return null;
    }
}
