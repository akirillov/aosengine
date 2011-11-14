package ru.agiledev.aos.core;

import ru.agiledev.aos.commons.dto.ExtendedQuery;
import ru.agiledev.aos.commons.dto.SearchEntry;
import ru.agiledev.aos.core.util.Transformer;
import ru.agiledev.ysearch.YSearch;

import java.io.IOException;
import java.util.List;

/**
 * Created by Anton Kirillov
 * Date: 8/12/11
 */
public class SemanticEngine {

    //top-level method for search
    public List<SearchEntry> search(String query, int page){
        List<SearchEntry> results = null;

        YSearch searcher = new YSearch("a-kirillov-a-search-in", "03.75627595:9689e3ecc186de751d138bc36a5a0069");
        String xmlResults = null;
        try {
            xmlResults = searcher.getSearchResults(query);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println(xmlResults);




        if(xmlResults!=null){
            results = Transformer.transform(xmlResults);
        }

        return results;
    }

    //method for transformation String NL-query to semantically extended
    public ExtendedQuery transformQuery(String query){
        //todo: implement!

        //todo: вот здесь вызываем QueryAnalyzer и анализируем запрос.


         return null;
    }

}
