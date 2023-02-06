/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gencell.ejb;

import gencell.domain.VWReporteExamenesGeneticaGP;
import java.util.List;
import javax.ejb.Local;


@Local
public interface SessionBeanFachadaLocal {
    
    public <T> T Editar(T entity);

    public <T> T BuscarPorID(Class<T> entityClass, Object primaryKey);

    public List BuscarTodos(Class entityClass);

    public List BuscarTodosPorId(Class entityClass, List ids);

    public void Crear(Object entity);
   
    public VWReporteExamenesGeneticaGP consultaResultadosLimit();
    
}
