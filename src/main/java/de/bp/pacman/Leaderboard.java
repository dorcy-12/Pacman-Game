package de.bp.pacman;

import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

/**
 * Leaderboard reads and writes the Highscore XML
 */
public class Leaderboard {


    private List<Pair<String,Integer>> top10 = new ArrayList<>();
    private NodeList highscore;

    /**
     * Reads the Highscore xml
     * Turns the high score list xml into String, Integer Hashmap
     */
    public Leaderboard() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document doc = db.parse(new File("Highscore.xml"));
            this.highscore = doc.getFirstChild().getChildNodes();
            for(int i=1; i< highscore.getLength();i+=2){
                String name = highscore.item(i).getAttributes().getNamedItem("name").getNodeValue();
                int points = Integer.parseInt(highscore.item(i).getAttributes().getNamedItem("points").getNodeValue());
                top10.add(new Pair<>(name,points));
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *
     * @return high score (String,Integer) Array
     */
    public List<Pair<String, Integer>> getTop10() {
        return top10;
    }

    /**
     * Writes the highscore xml
     * @param top10 A string, Integer of player names and points
     * @throws TransformerException general exception that occurs during the course of a transformation.
     */
    public void setTop10(List<Pair<String, Integer>> top10) throws TransformerException {
        this.top10 = top10;

        int j = 0;
        int i = 0;
        while(i < highscore.getLength()){
            if (highscore.item(i).getNodeType() == Node.ELEMENT_NODE){
                highscore.item(i).getAttributes().getNamedItem("points").setNodeValue(Integer.toString(top10.get(j).getValue()));
                highscore.item(i).getAttributes().getNamedItem("name").setNodeValue(top10.get(j).getKey());
                j++;
            }
            i++;
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(highscore.item(0).getOwnerDocument());
        StreamResult result = new StreamResult(new File("Highscore.xml"));
        transformer.transform(source, result);

    }
}
