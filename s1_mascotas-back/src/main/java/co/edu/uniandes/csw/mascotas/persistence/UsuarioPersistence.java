/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Tobia Gasparoni
 */
import javax.persistence.TypedQuery;
@Stateless
public class UsuarioPersistence
{
    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;
    
    /**
     * 
     * @param usuario
     * @return 
     */
    public UsuarioEntity create(UsuarioEntity usuario)
    {
        em.persist(usuario);
        return usuario;
    }
    
    /**
     * 
     * @param usuarioID
     * @return 
     */
    public UsuarioEntity find(Long usuarioID)
    {
        return em.find(UsuarioEntity.class, usuarioID);
    }
    
    /**
     * 
     * @return 
     */
    public List<UsuarioEntity> findAll()
    {
        TypedQuery query = em.createQuery("SELECT u FROM UsuarioEntity u", UsuarioEntity.class);
        return query.getResultList();
    }
    
    /**
     * 
     * @param usuarioID 
     */
    public void delete(Long usuarioID)    
    {
        UsuarioEntity aSerBorrado = find(usuarioID);
        em.remove(aSerBorrado);
    }
    
    /**
     * 
     * @param usuario
     * @return 
     */
    public UsuarioEntity update(UsuarioEntity usuario)
    {
        UsuarioEntity nueva = em.find(UsuarioEntity.class, usuario.getId());
        em.merge(usuario);

        return find(usuario.getId());
    }
    
    
}