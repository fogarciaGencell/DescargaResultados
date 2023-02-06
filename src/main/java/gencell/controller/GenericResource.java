/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gencell.controller;

import com.google.gson.*;
import gencell.service.AuditoriaService;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author devjava
 */
@Path("auditoria")
public class GenericResource {

    @Context
    private UriInfo context;

    public GenericResource() {
    }

    @POST
    @Path("/descargar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String guardar(String jsonData) throws IOException {
        
        System.out.println("INICIO SERVICIO");
        
        JsonParser objJ = new JsonParser();
        JsonObject jDatos = (JsonObject) objJ.parse(jsonData);
        AuditoriaService auditoriaService = new AuditoriaService();

        jDatos = auditoriaService.guardarDatos(jDatos);

        return jDatos.toString();
    }
}
