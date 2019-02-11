/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.ricardoanalistadesistemas.editortexto.view;

import com.wordpress.ricardoanalistadesistemas.editortexto.controller.Controlador;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.wordpress.ricardoanalistadesistemas.editortexto.model.Documento;

/**
 *
 * @author ricardobalduino
 */
public class FrmPrincipal extends JFrame implements ActionListener {
    private JTextArea txtArea;
    private Controlador controlador;
    private Documento documento;
    private String titulo;
    private Timer autoSaveTimer;

    /**
     *
     * @throws HeadlessException
     */
    public FrmPrincipal() throws HeadlessException {
        controlador = new Controlador();
        autoSaveTimer = new Timer(5000, this);
        documento = new Documento();
        titulo = "Editor de texto";
        JFrame thisFrame = this;
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem menuItemNovo = new JMenuItem("Novo");
        JMenuItem menuItemAbrir = new JMenuItem("Abrir");
        JMenuItem menuItemSalvar = new JMenuItem("Salvar");
        JMenuItem menuItemExcluir = new JMenuItem("Excluir");
        
        menuItemNovo.addActionListener(this);
        menuItemNovo.setName("Novo");
        
        menuItemAbrir.addActionListener(this);
        menuItemAbrir.setName("Abrir");
        
        menuItemSalvar.addActionListener(this);
        menuItemSalvar.setName("Salvar");
        
        menuItemExcluir.addActionListener(this);
        menuItemExcluir.setName("Excluir");
       
        menuArquivo.add(menuItemNovo);
        menuArquivo.add(menuItemAbrir);
        menuArquivo.add(menuItemSalvar);
        menuArquivo.add(menuItemExcluir);
        
        menuBar.add(menuArquivo);
        setJMenuBar(menuBar);
        
        txtArea = new JTextArea();        
        txtArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                JTextArea source = (JTextArea) e.getSource();
                
                if ( !source.getText().equals( documento.getTexto() ) ){
                    documento.setModificado(true);
                    
                    String s = getTitle() + " (modificado)";
                    
                    if ( !getTitle().endsWith("(modificado)") ){
                        setTitle(s);
                    }
                    
                }
            }
        });
        
        JScrollPane scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        JPanel painel = new JPanel( new BorderLayout() );
        painel.add(scroll, BorderLayout.CENTER);
        
        Container container = getContentPane(); 
        container.setLayout( new BorderLayout() );
        container.add(painel, BorderLayout.CENTER);
        
        setTitle(titulo);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);        
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if ( "".equals( documento.getCaminhoArquivo() ) || documento.isModificado() ){
                    int opcao = JOptionPane.showConfirmDialog(thisFrame, "Deseja salvar as modificações?", "Salvar modificações?",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    
                    if (opcao == JOptionPane.OK_OPTION){
                        salvarDocumento();
                    }
                }
            }
            
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
    
        if ( "Novo".equals(cmd) ){
            if ( "".equals( documento.getCaminhoArquivo() ) || documento.isModificado() ){
                salvarDocumento();
            }
            
            documento = new Documento();
            txtArea.setText("");
            setTitle(titulo);
        } else if ( "Abrir".equals(cmd) ){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos texto", "txt");
            
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setFileFilter(filtro);
            chooser.setDialogTitle("Abrir arquivo");
            
            int opcao = chooser.showOpenDialog(this);
            
            if (opcao == JFileChooser.APPROVE_OPTION){
                File arquivo = chooser.getSelectedFile();
                
                documento = controlador.abrir( arquivo.getAbsolutePath() );
                
                txtArea.setText( documento.getTexto() );
                documento.setCaminhoArquivo( arquivo.getAbsolutePath() );
                setTitle( getTitle() + " - " + arquivo.getAbsolutePath() );
            }
        } else if ( "Salvar".equals(cmd) ){
            salvarDocumento();
        } else if ( "Excluir".equals(cmd) ){
            int opcao = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir o arquivo?", "Excluir arquivo?",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            
            if (opcao == JOptionPane.OK_OPTION){
                controlador.excluir(documento);
                txtArea.setText("");
                documento = new Documento();
                setTitle(titulo);
            }
        } 
    }
    
    private void salvarDocumento(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos texto", "txt");

        chooser.setFileFilter(filtro);
        chooser.setDialogTitle("Salvar arquivo");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if ( documento.isNovo() ){
            int opcao = chooser.showSaveDialog(this);

            if (opcao == JFileChooser.APPROVE_OPTION){
                File diretorio = chooser.getSelectedFile();
                String arquivo = JOptionPane.showInputDialog(this, "Digite o nome do arquivo", "Nome do arquivo", JOptionPane.INFORMATION_MESSAGE);
                String caminho = diretorio.getAbsolutePath() + File.pathSeparator + arquivo;
                
                documento.setCaminhoArquivo(caminho);
                documento.setTexto( txtArea.getText() );
                documento.setNovo(false);
                controlador.criar(documento);
            }
        } else {
            documento.setTexto( txtArea.getText() );
            documento.setModificado(false);
            controlador.salvar(documento);
            setTitle( getTitle().replace(" (modificado)", "") );
        }
    }
    
    public static void main(String[] args) {
        try{
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new FrmPrincipal();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
}
