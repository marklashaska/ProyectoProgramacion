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
import Database.Caso;
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
public class VentanaListado extends JFrame {

    JPanel contenedor;
    JTable table;
    DefaultTableModel model;
    ArrayList<Caso> casos;

    /**
     * Constructor de la clase VentanaListado.
     *
     * @param casos ArrayList de los casos de corrupción que han sido extraídos
     * de la base de datos.
     */
    public VentanaListado(ArrayList casos) {
        this.casos = casos;
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
        this.setSize(1200, 400);
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
            model.addColumn("ID del caso");
            model.addColumn("Nombre del caso");
            model.addColumn("Descripción del caso");
            model.addColumn("Cantidad desviada (euros)");
            model.addColumn("ID del juez que lo investiga");
            table.getColumnModel().getColumn(2).setMinWidth(550);
            table.getColumnModel().getColumn(1).setMinWidth(100);
            table.getColumnModel().getColumn(3).setMinWidth(150);
            table.getColumnModel().getColumn(0).setMinWidth(100);
            table.getColumnModel().getColumn(4).setMinWidth(150);
            muestraFilas();
        } catch (MyException ex) {
            JOptionPane.showMessageDialog(this, "No se ha encontrdo ningun resultado.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Método que crea las filas de la tabla y añade los datos de los casos de
     * corrupción a dicha tabla.
     *
     * @throws MyException
     */
    public void muestraFilas() throws MyException {
        String filas[] = new String[5];
        Iterator<Caso> c1 = casos.iterator();
        while (c1.hasNext()) {
            Caso caso = c1.next();
            filas[0] = caso.getId();
            filas[1] = caso.getNombre();
            filas[2] = caso.getDesc();
            filas[3] = caso.getValor();
            filas[4] = caso.getJuez();
            model.addRow(filas);
        }
        if (casos.size() < 1) {
            MyException error = new MyException("No se han encontrado resultados");
            throw error;
        }
    }
}
