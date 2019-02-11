/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.ricardoanalistadesistemas.editortexto.model;

import java.io.Serializable;

/**
 *
 * @author ricardobalduino
 */
public class Documento implements Serializable {
    private String texto;
    private String caminhoArquivo;
    private boolean modificado;
    private boolean novo;

    /**
     *
     */
    public Documento() {
        texto = "";
        caminhoArquivo = "";
        modificado = false;
        novo = true;
    }   
    
    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the caminhoArquivo
     */
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    /**
     * @return the modificado
     */
    public boolean isModificado() {
        return modificado;
    }

    /**
     * @return the novo
     */
    public boolean isNovo() {
        return novo;
    }

    /**
     * @param caminhoArquivo the caminhoArquivo to set
     */
    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    /**
     * @param modificado the modificado to set
     */
    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    /**
     * @param novo the novo to set
     */
    public void setNovo(boolean novo) {
        this.novo = novo;
    }
    
}
