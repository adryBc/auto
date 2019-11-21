/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blancoadriana.auto;

import com.blancoadriana.web.Mensajes;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Controlador que se utiliza en varias vistas. La anotación
 * <code>@ViewScoped</code> indica que los objetos se mantienen almacenados en
 * el archivo de sesión mientras se muestre la vista que está usando este bean.
 * Al cambiar de vista, los datos se pierden.
 */
@Named
@ViewScoped
public class CtrlAutoNuevo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Mensajes mensajes;
    @Inject
    private DaoAuto dao;
    private Auto modelo;

    @PostConstruct
    void init() {
        modelo = new Auto();
    }

    public Auto getModelo() {
        return modelo;
    }

    public String guarda() {
        try {
            dao.agrega(modelo);
            return "index?faces-redirect=true";
        } catch (Exception ex) {
            mensajes.procesa(ex);
            return null;
        }
    }
}
