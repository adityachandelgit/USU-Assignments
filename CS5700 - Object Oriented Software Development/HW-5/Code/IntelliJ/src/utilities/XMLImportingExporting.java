package utilities;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tasks.ParentTask;
import tasks.Task;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya on 12/11/2015.
 */
public class XMLImportingExporting {

    public static void exportToXML(Task task, String file_path) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("WBS");
            doc.appendChild(rootElement);

            // staff elements
            Element topLevel = doc.createElement(String.valueOf(task.getClass().getName()));
            rootElement.appendChild(topLevel);

            // set attribute to staff element
            topLevel.setAttribute("id", String.valueOf(task.getId()));
            topLevel.setAttribute("label", String.valueOf(task.getLabel()));
            topLevel.setAttribute("label", String.valueOf(task.getDescription()));

            /*// label elements
            Element label = doc.createElement("label");
            label.appendChild(doc.createTextNode(task.getLabel()));
            topLevel.appendChild(label);

            // description elements
            Element description = doc.createElement("description");
            description.appendChild(doc.createTextNode(task.getDescription()));
            topLevel.appendChild(description);*/


            if(task.getClass().getSimpleName().equalsIgnoreCase("ParallelParentTask") || task.getClass().getSimpleName().equalsIgnoreCase("SequentialParentTask")) {
                ParentTask p = (ParentTask)task;
                ArrayList<Task> tasks =  new ArrayList<>();
                tasks.addAll(p.getListTasks());

                for(Task t : tasks) {
                    Element innerLevel = doc.createElement(String.valueOf(t.getClass().getName()));
                    topLevel.appendChild(innerLevel);

                    // set attribute to staff element
                    innerLevel.setAttribute("id", String.valueOf(t.getId()));
                    innerLevel.setAttribute("label", String.valueOf(t.getLabel()));
                    innerLevel.setAttribute("id", String.valueOf(t.getDescription()));

                    /*Element innerLabel = doc.createElement("label");
                    innerLabel.appendChild(doc.createTextNode(t.getLabel()));
                    innerLevel.appendChild(label);

                    // description elements
                    Element innerDescription = doc.createElement("description");
                    innerDescription.appendChild(doc.createTextNode(t.getDescription()));
                    innerLevel.appendChild(description);*/
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file_path));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File exported at: " + file_path);

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }


    public static void ImportFromXml(Task task) {


    }
}
