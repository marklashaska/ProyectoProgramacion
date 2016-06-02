/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.GUI;

import Database.MyException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Database.DataBase;
import Database.Caso;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;

/**
 * Clase VentanaAlta
 *
 * @author Marcos Pineda Burriel
 * @version 29/05/2016
 */
public class VentanaAlta extends JFrame implements ActionListener, WindowListener {

    JPanel contenedor;
    JButton bAlta, bAtras, bLimpiar;
    JTextField[] texto;
    JLabel[] etiquetas;
    String textoEtiquetas[] = {"* ID Caso:", "* Nombre:", "* Descripción:", "* Valor Desviado:", " * ID Juez:"};
    DataBase db;
    ArrayList<Caso> casos;
    String numeros = "1234567890";

    /**
     * Constructor de la clase VentanaAlta.
     *
     * @param db La Base de Datos a la que nos vamos a conectar.
     * @param casos Un ArrayList de casos de corrupción.
     */
    public VentanaAlta(DataBase db, ArrayList casos) {
        this.db = db;
        this.casos = casos;
        try {
            this.setIconImage(ImageIO.read(new File("res/database.png")));
        } catch (IOException ex) {
            System.out.println("Archivo no encontrado.");
        }
        this.setTitle("Alta Caso Corrupción");
        this.setVisible(true);
        initComponents();
        this.setLocationRelativeTo(null);
        this.pack();
        this.setSize(400, 400);
    }

    /**
     * Metodo de la clase VentanaAlta que se encarga de inicializar todos los
     * componentes que son mostrados a través de ella.
     */
    public void initComponents() {

        texto = new JTextField[textoEtiquetas.length];
        etiquetas = new JLabel[textoEtiquetas.length];
        contenedor = (JPanel) this.getContentPane();
        contenedor.setLayout(new GridLayout(7, 1, 5, 5));
        contenedor.setBorder(new EmptyBorder(20, 20, 20, 20));
        for (int x = 0; x < etiquetas.length; x++) {
            etiquetas[x] = new JLabel(textoEtiquetas[x]);
            texto[x] = new JTextField();
            contenedor.add(etiquetas[x]);
            contenedor.add(texto[x]);
        }
        bAlta = new JButton("Alta");
        bAlta.addActionListener(this);
        bLimpiar = new JButton("Limpiar");
        bLimpiar.addActionListener(this);
        bAtras = new JButton("Atrás");
        bAtras.addActionListener(this);
        contenedor.add(bAlta);
        contenedor.add(bLimpiar);
        contenedor.add(bAtras);
    }

    /**
     * Método que crea un nuevo caso de corrupción con los datos introducidos en
     * los TextField y los inserta en la base de datos.
     */
    public void alta() {
        boolean correcto = true;
        int x = 0;
        try {
            Caso c1 = new Caso(compruebaID(), compruebaNombre(), compruebaDesc(), compruebaValor(), compruebaJuez());
            while (correcto == true && (x < c1.getId().length() && x < c1.getJuez().length())) {
                if (numeros.contains(c1.getId().substring(x, x + 1)) && numeros.contains(c1.getJuez().substring(x, x + 1)) && numeros.contains(c1.getValor().substring(x, x + 1))) {
                    correcto = true;
                    x++;
                } else {
                    correcto = false;
                }
            }
            if (correcto == true) {
                String sentencia = "insert into casos_corrupcion values ('" + c1.getId() + "', '" + c1.getNombre() + "', '" + c1.getDesc() + "', '" + c1.getValor() + "', '" + c1.getJuez() + "')";
                db.Update(sentencia);
                JOptionPane.showMessageDialog(this, "Caso dado de alta correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new MyException();
            }
        } catch (MyException ex) {
            JOptionPane.showMessageDialog(this, "El caso de corrupción no se ha podido dar de alta correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
        Ventana.Limpiar(textoEtiquetas, texto);
    }

    /**
     * Método que comprueba qué se ha escrito en el TextField de ID.
     *
     * @return String que contiene el ID del caso.
     * @throws MyException
     */
    public String compruebaID() throws MyException {
        if (texto[0].getText().length() > 0 && texto[0].getText().length() < 10) {
            return texto[0].getText();
        } else {
            JOptionPane.showMessageDialog(this, "ID de caso no válida, ha de tener entre 1 y 20 caracteres.", "Error", JOptionPane.INFORMATION_MESSAGE);
            throw new MyException("ID no válida.");
        }
    }

    /**
     * Método que comprueba qué se ha escrito en el TextField de nombre.
     *
     * @return El nombre que se ha escrito en el TextField.
     * @throws MyException
     */
    public String compruebaNombre() throws MyException {
        if (texto[1].getText().length() > 0 && texto[1].getText().length() < 50) {
            return texto[1].getText();
        } else {
            JOptionPane.showMessageDialog(this, "Nombre de caso no válido, ha de tener entre 1 y 50 caracteres.", "Error", JOptionPane.INFORMATION_MESSAGE);
            throw new MyException("Nombre no válido.");
        }
    }

    /**
     * Método que comprueba qué se ha escrito en el TextField de descripción.
     *
     * @return String que contiene la descripción del caso de corrupción.
     * @throws MyException
     */
    public String compruebaDesc() throws MyException {
        if (texto[2].getText().length() > 0 && texto[2].getText().length() < 200) {
            return texto[2].getText();
        } else {
            JOptionPane.showMessageDialog(this, "Descripción de caso no válida, ha de tener entre 1 y 200 caracteres.", "Error", JOptionPane.INFORMATION_MESSAGE);
            throw new MyException("Descripción no válida.");
        }
    }

    /**
     * Método que comprueba qué se ha escrito en el TextField que contiene el
     * valor total de dinero desviado.
     *
     * @return String que contiene el valor total de dinero desviado.
     * @throws MyException
     */
    public String compruebaValor() throws MyException {
        if (texto[3].getText().length() > 0 && texto[3].getText().length() < 26) {
            return texto[3].getText();
        } else {
            JOptionPane.showMessageDialog(this, "Valor de dinero desviado no válido, ha de encontrarse entre 1 y 35 cifras", "Error", JOptionPane.INFORMATION_MESSAGE);
            throw new MyException("Valor no válido.");
        }
    }

    /**
     * Método que comprueba qué se ha escrito en el TextField de ID de Juez.
     *
     * @return String que contiene la Id del Juez que se ocupa del caso.
     * @throws MyException
     */
    public String compruebaJuez() throws MyException {
        if (texto[4].getText().length() > 0 && texto[4].getText().length() < 16) {
            return texto[4].getText();
        } else {
            JOptionPane.showMessageDialog(this, "Id de Juez no válida, ha de tener entre 1 y 20 caracteres", "Error", JOptionPane.INFORMATION_MESSAGE);
            throw new MyException("ID de Juez no válida.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Alta":
                alta();
                break;
            case "Limpiar":
                Ventana.Limpiar(textoEtiquetas, texto);
                break;
            case "Atrás":
                this.dispose();
                break;
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
