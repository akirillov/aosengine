package ru.agiledev.aos.frontend.server;

import ru.agiledev.aos.frontend.shared.SearchResult;
import ru.agiledev.aos.frontend.shared.TransformationResults;

import java.util.List;

/**
 * Created by Anton Kirillov
 * Date: 8/16/11
 */

//TODO: имплементировать все нужно в модуле CORE, чтобы не дублировать ничего
public class CoreFacade {
    static TransformationResults getTransformationResults(String query){

        return null;
    };

    static  List<SearchResult> getSearchResults(String query, int page){

        return null;
    };

    static List<SearchResult> getSearchResults(int transformationResultsID, int page){

        return null;
    };

    //todo: удалить все? или оставить для лучшей организации кода? подумать
}
