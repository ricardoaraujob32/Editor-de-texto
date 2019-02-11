/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.ricardoanalistadesistemas.editortexto.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import com.wordpress.ricardoanalistadesistemas.editortexto.model.Documento;

/**
 *
 * @author ricardobalduino
 */
public class DocumentoDAO implements DAO {

    @Override
    public void criar(Documento d) throws GenericDAOException {
        File arquivo = new File( d.getCaminhoArquivo() );
       
        try ( BufferedWriter writer = Files.newBufferedWriter( arquivo.toPath(), StandardOpenOption.CREATE_NEW,
                StandardOpenOption.WRITE) )
        {
            writer.write( d.getTexto() );
        } catch (Exception ex) {
            throw new GenericDAOException("Erro ao criar arquivo " + arquivo.getAbsolutePath(), ex);
        }
    }

    @Override
    public Documento abrir(String caminho) throws GenericDAOException {
        Documento d = new Documento();
        File arquivo = new File(caminho);
        StringBuilder sb = new StringBuilder();
        
        try ( BufferedReader reader = Files.newBufferedReader( arquivo.toPath() ) )
        {           
            while ( reader.ready() ) {                
                sb.append( reader.readLine() );
                sb.append( System.lineSeparator() );
            }
        } catch (Exception ex) {
            throw new GenericDAOException("Erro ao ler arquivo " + arquivo.getAbsolutePath(), ex);
        }
        
        d.setTexto( sb.toString() );
        d.setNovo(false);
        
        return d;
    }

    @Override
    public void salvar(Documento d) throws GenericDAOException {
        File arquivo = new File( d.getCaminhoArquivo() );
        
        try ( BufferedWriter writer = Files.newBufferedWriter( arquivo.toPath(), StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING) )
        {
            writer.write( d.getTexto() );
        } catch (Exception ex) {
            throw new GenericDAOException("Erro ao salvar arquivo " + arquivo.getAbsolutePath(), ex);
        }
    }

    @Override
    public void excluir(Documento d) throws GenericDAOException {
        File arquivo = new File( d.getCaminhoArquivo() );
        
        try {
            Files.delete( arquivo.toPath() );
        } catch (Exception ex) {
            throw new GenericDAOException("Erro ao excluir arquivo " + arquivo.getAbsolutePath(), ex);
        }
    }
    
}
