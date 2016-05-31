/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Clase XML que sirve para crear un documento XML.
 *
 * @author Marcos Pineda Burriel
 * @version 29/05/2016
 */
public class XML {

    /**
     * Metodo de la clase XML que sirve para escribir el documento XML.
     *
     * @param nombre El nombre del documento XML que se va a generar.
     * @param casos Un ArrayList que contiene casos de corrupción que han sido
     * prviamente extraídos de la base de datos.
     */
    public void escribo(String nombre, ArrayList casos) {
        if (casos.isEmpty()) {
            System.out.println("ERROR datos de entrada no validos");
        } else {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;

            try {
                db = dbf.newDocumentBuilder();
                DOMImplementation implementation = db.getDOMImplementation();
                Document document = implementation.createDocument(null, nombre, null);
                document.setXmlVersion("1.0");
                Element raiz = document.getDocumentElement();
                System.out.println("Raiz: " + raiz.getNodeName());
                Iterator<Caso> c = casos.iterator();
                while (c.hasNext()) {
                    Caso c1 = c.next();
                    Element etiquetaCaso = document.createElement("Caso_Corrupción");
                    Element etiquetaID = document.createElement("ID_Caso");
                    Text textoID = document.createTextNode(c1.getId());
                    etiquetaID.appendChild(textoID);
                    Element etiquetaNombre = document.createElement("Nombre");
                    Text textoNombre = document.createTextNode(c1.getNombre());
                    etiquetaNombre.appendChild(textoNombre);
                    Element etiquetaDesc = document.createElement("Descripcion");
                    Text textoDesc = document.createTextNode(c1.getDesc());
                    etiquetaDesc.appendChild(textoDesc);
                    Element etiquetaValor = document.createElement("Valor_Desviado");
                    Text textoValor = document.createTextNode(c1.getValor());
                    etiquetaValor.appendChild(textoValor);
                    Element etiquetaJuez = document.createElement("ID_Juez");
                    Text textoJuez = document.createTextNode(c1.getJuez());
                    etiquetaJuez.appendChild(textoJuez);
                    etiquetaCaso.appendChild(etiquetaID);
                    etiquetaCaso.appendChild(etiquetaNombre);
                    etiquetaCaso.appendChild(etiquetaDesc);
                    etiquetaCaso.appendChild(etiquetaValor);
                    etiquetaCaso.appendChild(etiquetaJuez);
                    raiz.appendChild(etiquetaCaso);

                }
                Source source = new DOMSource(document);
                Result result = new StreamResult(new File("res/" + nombre + ".xml"));
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(null, "Documento XML generado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } catch (ParserConfigurationException ex) {
                System.out.println("Error escribiendo Fichero");
            } catch (TransformerConfigurationException ex) {
                System.out.println("Error escribiendo Fichero");
            } catch (TransformerException ex) {
                System.out.println("Error escribiendo Fichero");
            }

        }

    }
}
