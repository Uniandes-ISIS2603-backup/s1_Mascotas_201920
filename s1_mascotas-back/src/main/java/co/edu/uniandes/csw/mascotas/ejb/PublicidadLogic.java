/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.PublicidadPersistence;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author German Rozo
 */
@Stateless
public class PublicidadLogic {

    @Inject
    private PublicidadPersistence pp;

    public PublicidadEntity createPublicidad(PublicidadEntity pe) throws BusinessLogicException {
        check(pe);
        pe = pp.create(pe);
        return pe;
    }

    public PublicidadEntity updatePublicidad(PublicidadEntity pe) throws BusinessLogicException {
        check(pe);

        pe = pp.update(pe);
        return pe;
    }

    public PublicidadEntity findPublicidad(Long id) {
        return pp.find(id);
    }

    public List<PublicidadEntity> findAllPublicidad() {
        return pp.findAll();
    }

    public void deletePublicidad(Long id) {
        pp.delete(id);
    }

    private void check(PublicidadEntity pe) throws BusinessLogicException {
        if (pe.getCosto() == null) {
            throw new BusinessLogicException("El costo de la publicidad esta vacio");
        }
        if (pe.getDiasPorSemana() == null) {
            throw new BusinessLogicException("Los dias por semanade la publicidad esta vacio");
        }
        if (pe.getFecchaFin() == null) {
            throw new BusinessLogicException("La fecha final de la publicidad esta vacia");
        }
        if (pe.getFechaInicio() == null) {
            throw new BusinessLogicException("La fecha inicial de la publicidad esta vacia");
        }
        if (pe.getMensaje() == null) {
            throw new BusinessLogicException("El mensaje de la publicidad esta vacio");
        }
        if (pe.getCosto() <= 0) {
            throw new BusinessLogicException("El costo de la publicidad es menor o igual a 0");
        }

        if (pe.getDiasPorSemana() <= 0) {
            throw new BusinessLogicException("Los dias por semana tienen que ser mayores a 0");
        }

    }

    public PublicidadEntity getPublicidad() {
        List<PublicidadEntity> list = pp.getPublicidad();

        if (list.isEmpty()) {
            return emply();
        } else {
            java.util.Date date = new java.util.Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            date = c.getTime();
            int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
            c.add(Calendar.DATE, -i - 7);
            c.add(Calendar.DATE, 6);
            java.util.Date end = c.getTime();

            List<PublicidadEntity> aprobados = new ArrayList<>();

            for (PublicidadEntity publicidadEntity : list) 
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(date);                
                String strPublicacion = dateFormat.format(publicidadEntity.getUltimaPublicacion());
                
                //publicidadEntity.setMensaje(strDate+"-"+strPublicacion);
                
                if (strPublicacion.compareTo(strDate)==0)
                {    
                    aprobados.add(publicidadEntity);
                } else if (publicidadEntity.getUltimaPublicacion().before(end)) {
                    publicidadEntity.setUltimaPublicacion(date);
                    publicidadEntity.setPublicaciones(1);
                    pp.update(publicidadEntity);
                    aprobados.add(publicidadEntity);
                } else if (publicidadEntity.getPublicaciones() < publicidadEntity.getDiasPorSemana()) {
                    publicidadEntity.setUltimaPublicacion(date);
                    publicidadEntity.setPublicaciones(publicidadEntity.getPublicaciones() + 1);
                    pp.update(publicidadEntity);
                    aprobados.add(publicidadEntity);
                }
            }
            if (aprobados.isEmpty()) {
                return emply();
            } else {
                return aprobados.get((int) (Math.random() * ((list.size() - 1) + 1)));
            }
        }
    }

    private PublicidadEntity emply() {
        PublicidadEntity entity = new PublicidadEntity();
        List<MultimediaEntity> ml = new ArrayList<>();
        MultimediaEntity multimedia = new MultimediaEntity();
        multimedia.setUrl("https://i.ibb.co/9805J5y/2.png");
        ml.add(multimedia);
        multimedia = new MultimediaEntity();
        multimedia.setUrl("https://i.ibb.co/k9PdT6S/1.png");
        ml.add(multimedia);
        entity.setMultimedia(ml);
        return entity;
    }

}
