
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
   
    public GUI(){
        super("CMD");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setResizable(false);
        InitPanel();
        setVisible(true);
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

        JPanel contenedorScroll = new JPanel(new BorderLayout()){
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
        
        
        JPanel panelEntrada = new JPanel(new BorderLayout()){
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
                realizarAccion();
                
                entrada.setText("");
                consola.setCaretPosition(consola.getDocument().getLength());
                consola.append(" " + "\n");
                consola.append(controlador.getRutaActual() + "\n");
                AjustarTamaño(contenedorScroll);

                
               
                
                
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
    
    
    private void realizarAccion(){
        String texto = controlador.getInterprete().ejecutar(entrada.getText());
        agregartTexto(texto);
        
    }
    
    
}