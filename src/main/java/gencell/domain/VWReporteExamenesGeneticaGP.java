/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gencell.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "VWReporteExamenesGeneticaGP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VWReporteExamenesGeneticaGP.findAll", query = "SELECT v FROM VWReporteExamenesGeneticaGP v")
    , @NamedQuery(name = "VWReporteExamenesGeneticaGP.findByIdPeticion", query = "SELECT v FROM VWReporteExamenesGeneticaGP v WHERE v.idPeticion = :idPeticion")
    , @NamedQuery(name = "VWReporteExamenesGeneticaGP.findByNombreDelPaciente", query = "SELECT v FROM VWReporteExamenesGeneticaGP v WHERE v.nombreDelPaciente = :nombreDelPaciente")
    , @NamedQuery(name = "VWReporteExamenesGeneticaGP.findByGrupo", query = "SELECT v FROM VWReporteExamenesGeneticaGP v WHERE v.grupo = :grupo")
    , @NamedQuery(name = "VWReporteExamenesGeneticaGP.findByUrlResultado", query = "SELECT v FROM VWReporteExamenesGeneticaGP v WHERE v.urlResultado = :urlResultado")
    , @NamedQuery(name = "VWReporteExamenesGeneticaGP.findByFechaResultado", query = "SELECT v FROM VWReporteExamenesGeneticaGP v WHERE v.fechaResultado = :fechaResultado")})
public class VWReporteExamenesGeneticaGP implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PETICION")
    @Id
    private int idPeticion;
    @Size(max = 403)
    @Column(name = "NOMBRE_DEL_PACIENTE")
    private String nombreDelPaciente;
    @Size(max = 21)
    @Column(name = "GRUPO")
    private String grupo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "EXAMEN")
    private String examen;
    @Size(max = 115)
    @Column(name = "URL_RESULTADO")
    private String urlResultado;
    @Column(name = "FECHA_RESULTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResultado;

    public VWReporteExamenesGeneticaGP() {
    }

    public int getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(int idPeticion) {
        this.idPeticion = idPeticion;
    }

    public String getNombreDelPaciente() {
        return nombreDelPaciente;
    }

    public void setNombreDelPaciente(String nombreDelPaciente) {
        this.nombreDelPaciente = nombreDelPaciente;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getUrlResultado() {
        return urlResultado;
    }

    public void setUrlResultado(String urlResultado) {
        this.urlResultado = urlResultado;
    }

    public Date getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(Date fechaResultado) {
        this.fechaResultado = fechaResultado;
    }
    
}
