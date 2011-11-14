package ru.agiledev.aos.frontend.server;

import ru.agiledev.aos.commons.dto.SearchEntry;
import ru.agiledev.aos.frontend.shared.SearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Kirillov
 * Date: 8/16/11
 */
public class Transformer {
    public static List<SearchResult> transformSearchResult(List<SearchEntry> list){
        List<SearchResult> resultList = new ArrayList<SearchResult>(list.size());

        for(SearchEntry entry:list){

            SearchResult sResult = new SearchResult(entry.getTitle(), entry.getLink(), entry.getText(), entry.getDomain());
            resultList.add(sResult);
        }

        return resultList;
    }
}
