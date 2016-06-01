/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.GUI;

import Database.MyException;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Database.Ciudadano;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

/**
 * Clase VentanaListado
 *
 * @author Marcos Pineda Burriel
 * @version 29/05/2016
 */
public class VentanaListadoC extends JFrame {

    JPanel contenedor;
    JTable table;
    DefaultTableModel model;
    ArrayList<Ciudadano> ciudadanos;

    /**
     * Constructor de la clase VentanaListado.
     *
     * @param ciudadanos ArrayList de los ciudadanos que han sido extraídos de
     * la base de datos.
     */
    public VentanaListadoC(ArrayList ciudadanos) {
        this.ciudadanos = ciudadanos;
        try {
            this.setIconImage(ImageIO.read(new File("res/database.png")));
        } catch (IOException ex) {
            System.out.println("Archivo no encontrado.");
        }
        this.setTitle("Listado");
        this.setVisible(true);
        initComponents();
        this.setLocationRelativeTo(null);
        this.pack();
        this.setSize(1000, 400);
        this.setResizable(false);
    }

    /**
     * Método que inicializa los componentes que se van a mostrar posteriormente
     * por la ventana.
     */
    public void initComponents() {
        try {
            contenedor = (JPanel) this.getContentPane();
            contenedor.setBorder(new EmptyBorder(10, 10, 10, 10));
            model = new DefaultTableModel();
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            table.setFillsViewportHeight(true);
            table.setEnabled(false);
            contenedor.add(scrollPane, BorderLayout.CENTER);
            model.addColumn("ID del ciudadano");
            model.addColumn("DNI del ciudadano");
            model.addColumn("Nombre del ciudadano");
            model.addColumn("Apellidos del ciudadano");
            model.addColumn("Dirección del ciudadano");
            model.addColumn("Patrimonio");
            model.addColumn("ID del caso");
            hazFilas();
        } catch (MyException ex) {
            JOptionPane.showMessageDialog(this, "No se han encontrado resultados", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Método que crea las filas de la tabla y añade los datos de los ciudadanos
     * a dicha tabla.
     *
     * @throws MyException
     */
    public void hazFilas() throws MyException {
        String filas[] = new String[7];
        Iterator<Ciudadano> i1 = ciudadanos.iterator();
        while (i1.hasNext()) {
            Ciudadano c1 = i1.next();
            filas[0] = c1.getId();
            filas[1] = c1.getDni();
            filas[2] = c1.getNombre();
            filas[3] = c1.getApellidos();
            filas[4] = c1.getDireccion();
            filas[5] = c1.getPatrimonio();
            filas[6] = c1.getCaso();
            model.addRow(filas);
        }
        if (ciudadanos.size() < 1) {
            MyException error = new MyException("No se han encontrado resultados");
            throw error;
        }
    }
}
