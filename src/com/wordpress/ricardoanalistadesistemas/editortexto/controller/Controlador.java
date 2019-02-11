/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.ricardoanalistadesistemas.editortexto.controller;

import com.wordpress.ricardoanalistadesistemas.editortexto.dao.DAO;
import com.wordpress.ricardoanalistadesistemas.editortexto.dao.DocumentoDAO;
import com.wordpress.ricardoanalistadesistemas.editortexto.dao.GenericDAOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.wordpress.ricardoanalistadesistemas.editortexto.model.Documento;

/**
 *
 * @author ricardobalduino
 */
public class Controlador {
    private DAO dao;

    /**
     *
     */
    public Controlador() {
        dao = new DocumentoDAO();
    }    
    
    /**
     *
     * @param d
     */
    public void criar(Documento d) {
        try {
            dao.criar(d);
        } catch (GenericDAOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param caminho
     * @return
     */
    public Documento abrir(String caminho) {
        try {
            return dao.abrir(caminho);
        } catch (GenericDAOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Documento();
    }

    /**
     *
     * @param d
     */
    public void salvar(Documento d) {
        try {
            dao.salvar(d);
        } catch (GenericDAOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param d
     */
    public void excluir(Documento d) {
        try {
            dao.excluir(d);
        } catch (GenericDAOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
