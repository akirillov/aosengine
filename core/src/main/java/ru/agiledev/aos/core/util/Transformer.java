package ru.agiledev.aos.core.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.agiledev.aos.commons.dto.SearchEntry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Kirillov
 * Date: 8/12/11
 */
public class Transformer {
    public static List<SearchEntry> transform(String xml){
        List<SearchEntry> result = new ArrayList<SearchEntry>(10);

        //Start parsing resulting xml using DOM
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document dom = null;

        //we should wrap String in InputSource
        InputSource inStream = new org.xml.sax.InputSource();
        inStream.setCharacterStream(new java.io.StringReader(xml));

        try {
            //Using factory get an instance of document builder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            dom = documentBuilder.parse(inStream);


        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch(SAXException se) {
            se.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
        if(dom!=null){
            //get a nodelist of found documents
            NodeList nodeList = dom.getElementsByTagName("doc");
            if(nodeList != null && nodeList.getLength() > 0) {
                for(int i = 0 ; i < nodeList.getLength();i++) {
                    //get the search entry element
                    Element element = (Element)nodeList.item(i);
                    //get SearchEntry
                    SearchEntry entry = getSearchEntry(element);
                    result.add(entry);
                }
            }

        }  //todo: else throw exception

        return result;
    }

    private static SearchEntry getSearchEntry(Element element){
        NodeList nodeList = element.getChildNodes();

        String title = "";
        String link = "";
        String text = "";
        String domain = "";

        if(nodeList != null && nodeList.getLength() > 0) {
            for(int i = 0 ; i < nodeList.getLength();i++) {

                //get the search entry element
                Node el = nodeList.item(i);
                //todo: обработать выделение ключевых слов болдом
                if(el.getNodeName().equals("title")){
                    title = el.getTextContent().replaceAll("<hlword>","<b>").replaceAll("</hlword>","</b>");
                } else if(el.getNodeName().equals("url")){
                    link = el.getTextContent();
                } else if(el.getNodeName().equals("headline")){
                    if(text.length()>0){
                        text = el.getTextContent().replaceAll("<hlword>","<b>").replaceAll("</hlword>","</b>") + "<br>" + text;
                    } else {
                        text = el.getTextContent().replaceAll("<hlword>","<b>").replaceAll("</hlword>","</b>");
                    }
                } else if(el.getNodeName().equals("passages")){
                    if(text.length()>0){
                        text += "<br>" + el.getTextContent().replaceAll("<hlword>","<b>").replaceAll("</hlword>","</b>");
                    } else {
                        text = el.getTextContent().replaceAll("<hlword>","<b>").replaceAll("</hlword>","</b>");
                    }
                } else if(el.getNodeName().equals("domain")){
                    domain = el.getTextContent();
                }
            }
        }
        SearchEntry entry = new SearchEntry(title, link, text, domain);
        return entry;
    }

}
