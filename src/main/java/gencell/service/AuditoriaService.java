/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gencell.service;

import com.google.gson.JsonObject;
import gencell.domain.VWReporteExamenesGeneticaGP;
import gencell.ejb.SessionBeanFachadaLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author devjava
 */
public class AuditoriaService {
    
    @EJB
    private SessionBeanFachadaLocal sessionBeanBaseFachada;
    
    public AuditoriaService() {
        if (sessionBeanBaseFachada == null) {
            System.out.println("****************  fachada null  DESCARGA DE RESULTADOS: ");
            sessionBeanBaseFachada = this.lookupSessionBeanBaseFachadaLocal();
            if (sessionBeanBaseFachada != null) {
                System.out.println("****************  fachada obtenida DESCARGA DE RESULTADOS: ");
            }
        }
    }

    public JsonObject guardarDatos(JsonObject jsonDatos) throws IOException {

       
            
        JsonObject objReturn = new JsonObject();
        Integer archivosDescargados = 0 ;
       
        try{
        //Variables Json
        String descripcion = jsonDatos.get("descripcion").getAsString();
        
        
        switch(descripcion){
        
            case "SFTP":{
                System.out.println("Procesando SFTP");
                archivosDescargados = this.ejecutarDescargaSFTP();
                break;
            }
            
            case "Local" :{
                System.out.println("Procesando Local");
                break;
            }
            default:{
                System.out.println("Obcion invalida");
            }
        
        }
        
            objReturn.addProperty("Exito", "Se Descargaron los resultados");
            objReturn.addProperty("Cantidad", archivosDescargados);
            
        } catch (Exception e) {

            objReturn.addProperty("Estado", "NO Descargo correctamente");

        }

        return objReturn;

    }
    
    public Integer  ejecutarDescargaSFTP() throws IOException {
       
        Integer archivos = 0;
        //List<VWReporteExamenesGeneticaGP> listaResultadosGP = sessionBeanBaseFachada.BuscarTodos(VWReporteExamenesGeneticaGP.class); // se consulta la vista 
        VWReporteExamenesGeneticaGP archivo = sessionBeanBaseFachada.consultaResultadosLimit();
        List<VWReporteExamenesGeneticaGP> listaResultadosGP  = new ArrayList<>();
        listaResultadosGP.add(archivo);
        for (VWReporteExamenesGeneticaGP lista : listaResultadosGP) {

            //String url = lista.getUrlResultado().substring(15, lista.getUrlResultado().length());
            this.descargarUrl("http://"+lista.getUrlResultado(), lista.getUrlResultado().substring(48, lista.getUrlResultado().length()));
            //this.writeByteTMPDescargaResultados(url, lista.getUrlResultado().substring(48, lista.getUrlResultado().length()));
            //this.verSoporte("http://files.gencellpharma.com.co"+url);
            archivos = archivos + 1;
        }
        System.out.println("**** FIN DEL PROCESO ****");
        return archivos ;
    }

    public void descargarUrl(String url, String nombreArchivo) throws MalformedURLException, IOException {

        try {
        File pegar = new File("D:" + File.separator + "ResultadosSFTP4" + File.separator + nombreArchivo);
        URLConnection conn = new URL(url).openConnection();
        conn.connect();
        InputStream in = conn.getInputStream();
        OutputStream out = new FileOutputStream(pegar);

        int b = 0;
        while (b != -1) {
            b = in.read();
            if (b != -1) {
                out.write(b);
            }
        }
        
        out.close();
        in.close();
        System.out.println("URL: " + url);
        System.out.println("Archivo Copiado " + nombreArchivo + "\n -- \n");
        
        }catch(Exception e){
            System.out.println("ERROR: no se copio el archivo: " + url);
        }
    }
    
    private SessionBeanFachadaLocal lookupSessionBeanBaseFachadaLocal() {
        try {
            Context c = new InitialContext();
            return (SessionBeanFachadaLocal) c.lookup("java:global/DescargaResultados/SessionBeanFachada!gencell.ejb.SessionBeanFachadaLocal");
        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER LA FACHADA  ");
            e.printStackTrace();
            return null;
        }
    }
}
