package de.bp.pacman;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


/**
 * A field contains for each tile a type.
 */
public class Field {
    /**
     * Number of tiles in a fieldrow
     */
    public int WIDTH;
    /**
     * Number of tiles in a fieldcolumn
     */
    public int HEIGHT;
    private FieldType[][] field;

    /**
     * Initializes the Level according to xml
     *
     * @param levelNumber selected level
     */
    public Field(int levelNumber) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream levelStream = this.getClass().getClassLoader().getResourceAsStream("Levels.xml");
            Document doc = db.parse(levelStream);

            if(doc.getChildNodes().item(0).hasChildNodes()){
                NodeList levels=doc.getChildNodes().item(0).getChildNodes();
                for(int i=0;i<levels.getLength();i++){
                    Node level=levels.item(i);
                    if(level.getNodeType()==Node.ELEMENT_NODE&&level.getAttributes().getNamedItem("nr").getNodeValue().equals(String.valueOf(levelNumber))){

                        this.HEIGHT=Integer.parseInt(level.getAttributes().getNamedItem("height").getNodeValue());
                        this.WIDTH=Integer.parseInt(level.getAttributes().getNamedItem("width").getNodeValue());

                        FieldType[][] arr = new FieldType[WIDTH][HEIGHT];
                        if(level.hasChildNodes()){
                            NodeList children=level.getChildNodes();
                            for(int j=0;j< children.getLength();j++){
                                if(children.item(j).getNodeType()==Node.ELEMENT_NODE&&
                                        children.item(j).getNodeName().equals("field")){
                                    if(children.item(j).hasChildNodes()) {
                                        NodeList rows = children.item(j).getChildNodes();
                                        for(int k=0;k< rows.getLength();k++){
                                            Node row = rows.item(k);
                                            if(row.getNodeType()==Node.ELEMENT_NODE) {
                                                arr[Integer.parseInt(row.getAttributes().getNamedItem("nr").getNodeValue())] = Field.strToFieldType(row.getTextContent().split(","));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        this.field = arr;
                    }
                }
            }
        }catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * turns an array of String Letters into FieldTypes, to make XML readable
     *
     * @param arr array of Field in String representation
     * @return array of FieldTypes
     */
    private static FieldType[] strToFieldType(String[] arr) {
        FieldType[] res = new FieldType[arr.length];
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case "B":
                    res[i] = FieldType.BLOCK;
                    break;
                case "P":
                    res[i] = FieldType.POINT;
                    break;
                case "U":
                    res[i] = FieldType.POWER_UP;
                    break;
                default:
                    res[i] = FieldType.EMPTY;
            }
        }
        return res;
    }

    /**
     * Changes the fieldtype at x,y coordinate
     * @param positionX x
     * @param positionY y
     * @param type new fieldtype
     */
    public void set(int positionX, int positionY, FieldType type) {
        field[positionX][positionY] = type;
    }

    /**
     * A game is won if all power_ups and points are eaten. Returns if the game is won or not.
     * @return true if the field contains  only empties or blocks
     */
    public boolean isWithoutPointsAndPowerUps() {
        for (FieldType[] fieldTypes : field) {
            if (Arrays.asList(fieldTypes).contains(FieldType.POINT) || Arrays.asList(fieldTypes).contains(FieldType.POWER_UP)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Rollendes Feld (am Ende Kommt man wieder zum Anfang
     *
     * @param x Koordinate
     * @param y Koordinate
     * @return typ
     */
    public FieldType getType(int x, int y) {
        return field[Math.floorMod(x, WIDTH)][Math.floorMod(y, HEIGHT)];
    }

}
