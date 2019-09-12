/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
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
}

@RunWith(Arquillian.class)
public UsuarioEntity createUsuario(UsuarioEntity usuario) throws BusinessLogicException
{
    usuario = persistence.create(usuario);
    return usuario;
}