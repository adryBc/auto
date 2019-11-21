/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blancoadriana.auto;

import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Roberto MuVe
 */
@Stateless
@Dependent
public class DaoAuto {

    @Inject
    private EntityManager em;

    public List<Auto> consulta() {
        /* Con el resultado de la consulta se llena una lista con objetos de la
     * clase "Pasatiempo". */
        return em.createQuery("SELECT c FROM Auto c ORDER BY c.marca",
                Auto.class).getResultList();
    }

    public Auto busca(Integer id) {
        return em.find(Auto.class, id);
    }

    public void agrega(Auto modelo) {
        em.persist(modelo); // Agrega el modelo a la base de datos.
    }

    public void modifica(Auto modelo) {
        em.merge(modelo);// Guarda los cambios al modelo.
    }

    public void elimina(Auto modelo) {
        // Busca el modelo en base a su id.
        final Auto anterior = em.find(Auto.class, modelo.getId());
        // Si el resultado es null, el chismoso ya no está registrado.
        if (anterior != null) {
            // Pero si la referencia es diferente de null, hay que eliminar el objeto.
            em.remove(anterior);
        }
    }
}
