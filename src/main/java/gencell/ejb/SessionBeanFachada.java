/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gencell.ejb;


import gencell.domain.VWReporteExamenesGeneticaGP;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SessionBeanFachada implements SessionBeanFachadaLocal {

    @PersistenceContext(unitName = "AuditoriaUP")
    private EntityManager em;

    // Generales
    public <T> T BuscarPorID(Class<T> entityClass, Object primaryKey) {
        em.getEntityManagerFactory().getCache().evictAll();
        return em.find(entityClass, primaryKey);
    }

    public List BuscarTodos(Class entityClass) {
        em.getEntityManagerFactory().getCache().evictAll();
        return em.createQuery("select o "
                + "from " + entityClass.getSimpleName() + " o").getResultList();
    }

    public List BuscarTodosPorId(Class entityClass, List ids) {
        em.getEntityManagerFactory().getCache().evictAll();
        List entitys = new ArrayList();
        for (Object id : ids) {
            entitys.add(BuscarPorID(entityClass, id));
        }
        return entitys;
    }

    public void Crear(Object entity) {

        em.persist(entity);
        em.flush();

    }

    public <T> T Editar(T entity) {
        try {
            return em.merge(entity);
        } catch (Exception err) {
            return null;
        }
    }
    
     @Override
    public VWReporteExamenesGeneticaGP consultaResultadosLimit() {
        em.getEntityManagerFactory().getCache().evictAll();
        String query = "SELECT 	* from VWReporteExamenesGeneticaGP limit 1; ";
                
        Query q = em.createNativeQuery(query, VWReporteExamenesGeneticaGP.class);
        if (q.getResultList() == null) {
            return null;
        } else {
            return (VWReporteExamenesGeneticaGP) q.getResultList().get(0);
        }
    }
}
