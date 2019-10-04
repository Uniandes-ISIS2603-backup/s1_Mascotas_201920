/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Tobia Gasparoni
 */
@Path("mascotasadopcion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    
    
}
