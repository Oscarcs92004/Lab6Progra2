
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oscar
 */
import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame{
    
    private JPanel panel;
    private JTextArea consola;
    private JTextField entrada;
    private Controlador controlador = new Controlador ();
    private JPanel panelEditor;
    private JTextArea areaEditor;
    private JLabel editor;
    private boolean modoEditor = false;
    private boolean modoAppend = false;
    private String archivoEditando;
    private JPanel contenedorScroll;
    private JPanel panelEntrada;
   
    public GUI(){
        super("CMD");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setResizable(false);
        crearPanelEditor();
        configurarEditor();
        InitPanel();
        setVisible(true);
    }
    
    private void crearPanelEditor() {

        panelEditor = new JPanel(new BorderLayout());
        panelEditor.setBackground(Color.BLACK);
        panelEditor.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        editor = new JLabel(" VIM - EDITOR ");
        editor.setForeground(Color.GREEN);
        editor.setBackground(Color.BLACK);
        editor.setOpaque(true);
        editor.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        panelEditor.add(editor, BorderLayout.NORTH);

        areaEditor = new JTextArea();

        areaEditor.setBackground(Color.BLACK);
        areaEditor.setForeground(Color.GREEN);
        areaEditor.setCaretColor(Color.WHITE);
        areaEditor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

        areaEditor.setLineWrap(true);
        areaEditor.setWrapStyleWord(false);

        areaEditor.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));

        JScrollPane scrollEditor = new JScrollPane(areaEditor);

        scrollEditor.setBorder(null);
        scrollEditor.setBackground(Color.BLACK);
        scrollEditor.getViewport().setBackground(Color.BLACK);

        panelEditor.add(scrollEditor,BorderLayout.CENTER);

        JLabel lblAyuda =new JLabel(" ESC para cancelar | EXIT para guardar ");
        lblAyuda.setForeground(Color.WHITE);
        lblAyuda.setBackground(Color.BLACK);
        lblAyuda.setOpaque(true);
        lblAyuda.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        panelEditor.add(lblAyuda,BorderLayout.SOUTH);
        panelEditor.setPreferredSize(new Dimension(800, 450));
        panelEditor.setMinimumSize(new Dimension(800, 450));
        panelEditor.setMaximumSize(new Dimension(800, 450));
    }
    
    public void InitPanel(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(800, 500));
        panel.setMinimumSize(new Dimension(800, 500));
        panel.setMaximumSize(new Dimension(800, 500));
        panel.setBackground(Color.BLACK);
        
        
        consola = new JTextArea();
        consola.setEditable(false);
        consola.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        consola.setForeground(Color.GREEN);
        consola.setBackground(Color.BLACK);
        consola.setCaretColor(Color.WHITE);
        consola.setLineWrap(true);
        consola.setWrapStyleWord(true);
        consola.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        consola.append(controlador.getRutaActual() + "\n");// ////////////////////////////////////////////marca de agua

        
        
        JScrollPane scroll = new JScrollPane(consola);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Color.BLACK);    
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        scroll.setBackground(Color.BLACK);
        scroll.getViewport().setBackground(Color.BLACK);
        scroll.getVerticalScrollBar().setBackground(Color.BLACK);
        scroll.getHorizontalScrollBar().setBackground(Color.BLACK);

        contenedorScroll = new JPanel(new BorderLayout()){
            @Override
            public Dimension getMaximumSize(){
                return getPreferredSize();
            }
        };
        contenedorScroll.setBackground(Color.BLACK);
        contenedorScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        contenedorScroll.setPreferredSize(new Dimension(800, 20));
        contenedorScroll.setMinimumSize(new Dimension(800, 20));
        contenedorScroll.setMaximumSize(new Dimension(800, 20));
        contenedorScroll.add(scroll, BorderLayout.CENTER);

        
        
        panel.add(contenedorScroll);
        
        
        panelEntrada = new JPanel(new BorderLayout()){
            @Override
            public Dimension getMaximumSize(){
                return getPreferredSize();
            }
        };
        panelEntrada.setBackground(Color.BLACK);
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        panelEntrada.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEntrada.setPreferredSize(new Dimension(800, 20));
        panelEntrada.setMinimumSize(new Dimension(800, 20));
        panelEntrada.setMaximumSize(new Dimension(800, 20));
        
        
        JLabel lblUsuario = new JLabel("$ ");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        
        entrada = new JTextField();
        
        entrada.setBackground(Color.BLACK);
        entrada.setForeground(Color.WHITE);
        entrada.setCaretColor(Color.WHITE);
        entrada.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));
        
        entrada.addActionListener(e -> {
            String comando = entrada.getText().trim();
            
            if (!comando.isEmpty()) {
                
                consola.append("$ " + comando + "\n"); 
                //llamar a logica//////////////////////////////////////////////////////////////////////////////
                boolean continuar = realizarAccion();
                
                entrada.setText("");
                if(!continuar){
                    return;
                }
                consola.setCaretPosition(consola.getDocument().getLength());
                consola.append(" " + "\n");
                consola.append(controlador.getRutaActual() + "\n");
                AjustarTamaño(contenedorScroll);
                entrada.requestFocusInWindow();


                
               
                
                
            }
        });
        
        panelEntrada.add(lblUsuario, BorderLayout.WEST);
        panelEntrada.add(entrada, BorderLayout.CENTER);
        
        panel.add(panelEntrada);

        
        add(panel, BorderLayout.CENTER);

        panel.revalidate();
        panel.repaint();
    }
    
    
    private void AjustarTamaño(JPanel scroll){
        int lineas = consola.getDocument().getDefaultRootElement().getElementCount();
        
        int tamañoPixeles= lineas *20;
        
        if (tamañoPixeles> 430){
            tamañoPixeles=430;
            
        }
        
        scroll.setPreferredSize(new Dimension(800, tamañoPixeles));
         scroll.setMinimumSize(new Dimension(800, tamañoPixeles));
          scroll.setMaximumSize(new Dimension(800, tamañoPixeles));
        panel.repaint();
        panel.revalidate();
        
    }
    
    public void agregartTexto(String texto){
        
        
        
        
        consola.append( texto + "\n");
        consola.setCaretPosition(consola.getDocument().getLength());
        consola.append(" ");
      
       
       
    }
    
    private void mostrarEditor(String nombreArchivo, boolean append) {
        modoEditor = true;
        modoAppend = append;
        archivoEditando = nombreArchivo;

        editor.setText(append ? " VIM - APPEND: " + nombreArchivo + " " : " VIM - WRITE: " + nombreArchivo + " ");

        if (append) {
            areaEditor.setText(controlador.leerArchivoParaEditor(nombreArchivo));
            areaEditor.setCaretPosition(areaEditor.getDocument().getLength());
        } else {
            areaEditor.setText("");
        }

        panel.removeAll();
        panel.add(panelEditor);
        panel.revalidate();
        panel.repaint();

        areaEditor.requestFocusInWindow();
    }
    
    private void ocultarEditor() {
        modoEditor = false;
        panel.removeAll();
        panel.add(contenedorScroll);
        panel.add(panelEntrada);
        panel.revalidate();
        panel.repaint();
        entrada.setText("");
        entrada.requestFocusInWindow();
    }
    
    private void guardarDesdeEditor(String contenido) {
        String resultado = controlador.guardarDesdeEditor(archivoEditando, contenido, modoAppend);
        ocultarEditor();
        consola.append(resultado + "\n");
        consola.append(controlador.getRutaActual() + "\n");
        consola.setCaretPosition(consola.getDocument().getLength());
        AjustarTamaño(contenedorScroll);
    }
    
    private void procesarLineaEditor() {
        areaEditor.replaceSelection("\n");

        String texto = areaEditor.getText();
        String[] lineas = texto.split("\n", -1);

        if (lineas.length >= 2 && lineas[lineas.length - 2].trim().equals("EXIT")) {
            StringBuilder contenido = new StringBuilder();
            for (int i = 0; i < lineas.length - 2; i++) {
                contenido.append(lineas[i]);
                if (i < lineas.length - 3) {
                    contenido.append(System.lineSeparator());
                }
            }
            guardarDesdeEditor(contenido.toString());
        }
    }
    
    private void configurarEditor() {
        areaEditor.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "procesarLinea");
        areaEditor.getActionMap().put("procesarLinea", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                procesarLineaEditor();
            }
        });

        areaEditor.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "cancelarEditor");
        areaEditor.getActionMap().put("cancelarEditor", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ocultarEditor();
            }
        });
    }
    
    private boolean realizarAccion(){
        String texto = controlador.getInterprete().ejecutar(entrada.getText());
        String comando = entrada.getText().trim();
        
        if(texto.equals("CLS")){
            consola.setText("");
            return true;
        } 
       
        if(texto.equals("EXIT_APP")){
            dispose();
            return false;
        } 
        
        if(texto.equals("MODO_ESCRITURA")){
            String[] partes = comando.split("\\s+");

            mostrarEditor(partes[1],false);
            return true;
        } 
        
        if(texto.equals("MODO_APPEND")){
            String[] partes = comando.split("\\s+");
            mostrarEditor(partes[1],true);
            return true;
        } 
        
        if(!texto.isEmpty()){
            consola.append(texto + "\n");
        }
        return true;
    }
    
    
}