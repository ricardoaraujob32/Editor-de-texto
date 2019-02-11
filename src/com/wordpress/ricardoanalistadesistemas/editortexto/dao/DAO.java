/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.ricardoanalistadesistemas.editortexto.dao;

import com.wordpress.ricardoanalistadesistemas.editortexto.model.Documento;

/**
 *
 * @author ricardobalduino
 */
public interface DAO {

    /**
     *
     * @param d
     * @throws GenericDAOException
     */
    void criar(Documento d) throws GenericDAOException;

    /**
     *
     * @param caminho
     * @return
     * @throws GenericDAOException
     */
    Documento abrir(String caminho) throws GenericDAOException;

    /**
     *
     * @param d
     * @throws GenericDAOException
     */
    void salvar(Documento d) throws GenericDAOException;

    /**
     *
     * @param d
     * @throws GenericDAOException
     */
    void excluir(Documento d) throws GenericDAOException;
}
