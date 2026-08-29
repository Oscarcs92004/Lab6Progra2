
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
    
    public GUI(){
        super("CMD");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);

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
        consola.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        consola.append(" Win64 ~" + "\n");// ////////////////////////////////////////////marca de agua
        consola.setPreferredSize(new Dimension(800, 450));
        consola.setMinimumSize(new Dimension(800, 450));
        consola.setMaximumSize(new Dimension(800, 450));
        
        
        JScrollPane scroll = new JScrollPane(consola);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBackground(Color.BLACK);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Color.BLACK);    

        
        
        panel.add(scroll);
        
        
        JPanel panelEntrada = new JPanel(new BorderLayout());
        panelEntrada.setBackground(Color.BLACK);
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        
        JLabel lblUsuario = new JLabel("$ ");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        
        entrada = new JTextField();
        
        entrada.setBackground(Color.BLACK);
        entrada.setForeground(Color.WHITE);
        entrada.setCaretColor(Color.WHITE);
        entrada.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        
        entrada.addActionListener(e -> {
            String comando = entrada.getText().trim();
            
            if (!comando.isEmpty()) {
                
                consola.append("$ " + comando + "\n"); //                     raiz del sistema
                entrada.setText("");
                consola.setCaretPosition(consola.getDocument().getLength());
                
                consola.append(" Win64 ~" + "\n");// Texto con raiz///////////////////////////
                
                //llamar a logica//////////////////////////////////////////////////////////////////////////////
                
            }
        });
        
        panelEntrada.add(lblUsuario, BorderLayout.WEST);
        panelEntrada.add(entrada, BorderLayout.CENTER);
        
        panel.add(panelEntrada, BorderLayout.SOUTH);
        
        add(panel);
    }
    
    public void agregatTexto(String texto){
        
        
        consola.append("CMD: " + texto + "\n");
        consola.setCaretPosition(consola.getDocument().getLength());
        consola.append(" ");
       
    }
    
   

}
