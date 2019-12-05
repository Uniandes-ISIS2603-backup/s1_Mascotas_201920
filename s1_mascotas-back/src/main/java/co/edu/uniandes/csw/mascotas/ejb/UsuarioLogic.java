/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Tobia Gasparoni
 */
@Stateless
public class UsuarioLogic
{
    @Inject
    private UsuarioPersistence persistence;
    
    public UsuarioEntity createUsuario(UsuarioEntity usuario) throws BusinessLogicException
    {  
        checkUsuario(usuario);
        //Correo unico
        if(!persistence.findByEmail(usuario.getCorreo()).isEmpty())
            throw new BusinessLogicException("Ya existe un usuario con ese correo");
        
        usuario = persistence.create(usuario);
        return usuario;
    }
    
    public UsuarioEntity updateUsuario(UsuarioEntity usuario) throws BusinessLogicException
    {
        checkUsuario(usuario);
        //Correo unico
        UsuarioEntity usuarioExistente = persistence.find(usuario.getId());
        if(usuarioExistente != null)
        {
            if(!usuarioExistente.getCorreo().equals(usuario.getCorreo())
                && !persistence.findByEmail(usuario.getCorreo()).isEmpty())
                    throw new BusinessLogicException("Ya existe un usuario con ese correo");
        }
        else
            throw new BusinessLogicException("No existe el usuario que se va a actualizar");
            
        
        usuario = persistence.update(usuario);
        return usuario;
    }
    
    public void checkUsuario(UsuarioEntity usuario) throws BusinessLogicException
    {
        //Nombre
        if(usuario.getNombre() == null || usuario.getNombre().equals(""))
        {
            throw new BusinessLogicException("El nombre del usuario esta vacio");
        }
        
        //Correo
        if(usuario.getCorreo() == null || usuario.getCorreo().equals(""))
        {
            throw new BusinessLogicException("El correo del usuario esta vacio");
        }
        
        String[] mitadesCorreo = usuario.getCorreo().split("@");
        if(mitadesCorreo.length != 2 || mitadesCorreo[0].equals("") || mitadesCorreo[1].equals(""))
        {
            throw new BusinessLogicException("El correo debe tener una sola arroba (@) y texto a ambos lados de la arroba (length = " + mitadesCorreo.length +")");
        }
        
        String[] mitadesCorreoPunto = mitadesCorreo[1].split("\\.");
        if(mitadesCorreoPunto.length != 2)
        {
            throw new BusinessLogicException("El correo debe tener un punto (.) y texto a ambos lados del punto. (length = " + mitadesCorreoPunto.length +")");
        }
        
        if(mitadesCorreoPunto[0].equals("") || mitadesCorreoPunto[1].equals(""))
        {
            throw new BusinessLogicException("El correo debe tener texto del lado izquiero y derecho del punto (.)");
        }
        
        //Ciudad
        if(usuario.getCiudad() == null || usuario.getCiudad().equals(""))
        {
            throw new BusinessLogicException("La ciudad del usuario esta vacia");
        }
        
        //Celular
        if(usuario.getCelular() == null)
        {
            throw new BusinessLogicException("El celular del usuario esta vacio");
        }
        
        //Password
        /*if(usuario.getPassword() == null)
        {
            throw new BusinessLogicException("La contraseña del usuario esta vacía");
        }*/
    }
    
    public void deleteUsuario(Long usuarioID)
    {
        persistence.delete(usuarioID);
    }
    
    public UsuarioEntity findUsuario(Long usuarioID)
    {
        return persistence.find(usuarioID);
    }
    
    public Collection<UsuarioEntity> findAllUsuario()
    {
        return persistence.findAll();
    }
}