/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Database.DataBase;
import Database.XML;
import Database.Caso;
import Database.Ciudadano;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 * Clase Ventana
 *
 * @author Marcos Pineda Burrriel
 * @version 29/05/2016
 */
public class Ventana extends JFrame implements ActionListener, WindowListener {

    JPanel contenedor;
    JButton botones[];
    DataBase db;
    ArrayList<Caso> casos = new ArrayList();

    /**
     * Constructor de la clase Ventana.
     *
     * @param db La base de datos a la que nos vamos a conectar.
     */
    public Ventana(DataBase db) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("Error al intentar cargar el tema.");
        }
        this.db = db;
        try {
            this.setIconImage(ImageIO.read(new File("res/database.png")));
        } catch (IOException ex) {
            System.out.println("Archivo no encontrado.");
        }
        this.setTitle("Casos de Corrupción");
        this.setVisible(true);
        initComponents();
        this.setLocationRelativeTo(null);
        this.pack();
        this.setSize(400, 400);
    }

    /**
     * Metodo que inicializa los componentes de la ventana.
     */
    private void initComponents() {
        String textoBotones[] = {"Alta Caso Corrupción", "Baja Caso Corrupción", "Modificación Caso Corrupción", "Crear Documento XML", "Listado Alfabético", "Listado Juez", "Listado Ciudadanos", "Fin"};
        botones = new JButton[textoBotones.length];
        contenedor = (JPanel) this.getContentPane();
        contenedor.setLayout(new GridLayout(8, 1, 0, 5));
        contenedor.setBorder(new EmptyBorder(20, 20, 20, 20));
        actualizar();
        for (int x = 0; x < textoBotones.length; x++) {
            botones[x] = new JButton();
            botones[x].setText(textoBotones[x]);
            botones[x].setActionCommand(Integer.toString(x));
            botones[x].addActionListener(this);
            contenedor.add(botones[x]);
        }
        this.addWindowListener(this);
    }

    /**
     * Método que genera un Array de Strings con los Id de los casos de
     * corrupción.
     *
     * @return Array con los ID de los casos de corrupción.
     */
    private String[] arrayIDCaso() {
        String[] id = new String[casos.size()];
        int x = 0;
        Iterator<Caso> c1 = casos.iterator();
        while (c1.hasNext()) {
            Caso caso = c1.next();
            id[x] = caso.getId();
            x++;
        }
        return id;
    }

    private String[] arrayIDJuez() {
        String[] id = new String[casos.size()];
        int x = 0;
        Iterator<Caso> c1 = casos.iterator();
        while (c1.hasNext()) {
            Caso caso = c1.next();
            id[x] = caso.getJuez();
            x++;
        }
        return id;
    }

    /**
     * Método que actualiza el Arraylist de casos de corrupción.
     *
     * @return ArrayList actualizado de los casos de corrupción.
     */
    private ArrayList actualizar() {
        ArrayList<Caso> casos = new ArrayList();
        db.creaArrayCasos(db.Consulta("Select * from casos_corrupcion order by id_caso"), casos);
        return casos;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "0":
                casos = actualizar();
                VentanaAlta vA = new VentanaAlta(db, casos);
                break;
            case "1":
                casos = actualizar();
                String[] ID = arrayIDCaso();
                String opcion = (String) JOptionPane.showInputDialog(null, "Selecciona un ID de caso", "Elegir ID", JOptionPane.QUESTION_MESSAGE, null, ID, ID[0]);
                if (opcion != null) {
                    db.Update("delete from casos_corrupcion where id_caso = '" + opcion + "'");
                    db.Update("delete from ciudadano where id_caso = '" + opcion + "'");
                    JOptionPane.showMessageDialog(this, "Caso de corrupción dado de baja correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "2":
                casos = actualizar();
                String[] ID2 = arrayIDCaso();
                try {
                    String opcion2 = (String) JOptionPane.showInputDialog(null, "Selecciona un ID de caso", "Elegir ID", JOptionPane.QUESTION_MESSAGE, null, ID2, ID2[0]);
                    if (opcion2 != null) {
                        VentanaModificacion vM = new VentanaModificacion(db, casos, opcion2);
                    }
                    System.out.println(opcion2);
                } catch (NullPointerException ex) {
                    System.out.println("Error al realizar la operación solicitada.");
                }
                break;
            case "3":
                casos = actualizar();
                XML doc = new XML();
                doc.escribo("Casos_Corrupción", casos);
                break;
            case "4":
                casos = actualizar();
                ArrayList<Caso> casos2 = new ArrayList();
                db.creaArrayCasos(db.Consulta("Select * from casos_corrupcion order by nombre"), casos2);
                VentanaListado vL1 = new VentanaListado(casos2);
                break;
            case "5":
                casos = actualizar();
                ArrayList<Caso> casos3 = new ArrayList();
                String[] ID3 = arrayIDJuez();
                String opcion3 = (String) JOptionPane.showInputDialog(null, "Selecciona un ID de juez", "Elegir ID", JOptionPane.QUESTION_MESSAGE, null, ID3, ID3[0]);
                db.creaArrayCasos(db.Consulta("select * from casos_corrupcion where id_juez = " + opcion3), casos3);
                VentanaListado vL2 = new VentanaListado(casos3);
                break;
            case "6":
                casos = actualizar();
                ArrayList<Ciudadano> ciudadanos = new ArrayList();
                String[] ID4 = arrayIDCaso();
                String opcion4 = (String) JOptionPane.showInputDialog(null, "Selecciona un ID de caso", "Elegir ID", JOptionPane.QUESTION_MESSAGE, null, ID4, ID4[0]);
                db.creaArrayCiudadanos(db.Consulta("select * from ciudadano where id_caso = " + opcion4), ciudadanos);
//                VentanaListadoC vLC3 = new VentanaListadoC(ciudadanos);
                break;
            case "7":
                db.disconnect();
                System.exit(0);
                break;
        }
    }

    /**
     * Método que sirve para limpiar los TextField de las ventanas.
     *
     * @param textoEtiquetas Array con los nombres de las etiquetas.
     * @param texto Array con los TextFields.
     */
    public static void Limpiar(String[] textoEtiquetas, JTextField[] texto) {
        for (int x = 0; x < textoEtiquetas.length; x++) {
            texto[x].setText(null);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Abierta");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Cerrando");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Cerrada");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("Minimizada");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("Maximizada");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("Activada");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("Desactivada");
    }
}
