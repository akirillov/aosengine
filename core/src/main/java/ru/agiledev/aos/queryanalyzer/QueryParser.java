package ru.agiledev.aos.queryanalyzer;

import ru.agiledev.aos.commons.dto.LightWord;
import ru.agiledev.aos.core.util.QueryUtils;
import ru.agiledev.aos.data.domain.QType;

import java.util.List;
import java.util.Set;

/**
 * Objects of Ineterest finder class. Contains methods for parsing query depending on its type.
 * todo: one iteration parser - we really can do it
 *
 * Created by a.kirillov
 * Date: 11/6/11 Time: 10:34 PM
 */
public class QueryParser {
    //todo: switch type
    public List<LightWord> parse(QType type, List<LightWord> query){
        List<LightWord> result;
        switch (type){
            case STRUCTURE_DESCRIPTION:{
                //Исключение: "Из чего состоит Х?"
                if(query.get(0).getStems().contains("из")&&query.get(1).getStems().contains("что")){
                    for(int i=2; i<query.size();i++){
                        if(query.get(i).getStems().contains("состоять")){
                            result = getRestAsObjectOfInterest(query.subList(i+1, query.size()-1), Dictionaries.getInstance().getStructureDescriptionEmptyChainProducers());
                            return result;
                        }
                    }
                }

                for(int i=0; i<query.size();i++){
                    if(QueryUtils.isWordStemsInDictionary(query.get(i), Dictionaries.getInstance().getStructureDescriptionFlags())){
                        result = getRestAsObjectOfInterest(query.subList(i+1, query.size()-1), Dictionaries.getInstance().getStructureDescriptionEmptyChainProducers());
                        return result;
                    }
                }
                break;
            }
            case CHARACTERISTICS_DESCRIPTION: {
                break;
            }
            case WORK_DESCRIPTION:{
                break;
            }
            case FUNCTIONS_DESCRIPTION:{
                break;
            }
            case MISSION_DESCRIPTION:{
                break;
            }
            case USAGE_DESCRIPTION:{
                break;
            }
            case CORRESPONDS_TO_DESCRIPTION:{
                break;
            }
            case DIFFERENCES_DESCRIPTION:{

                break;
            }
            case COMMONS_DESCRIPTION:{
                break;
            }
            case FEATURES_DESCRIPTION:{
                break;
            }
            case POSSIBILITIES_DESCRIPTION:{
                break;
            }
            case GOALS_DESCRIPTION: {
                break;
            }
            case SETS_CHANGES_DESCRIPTION:{
                break;
            }
        }

        return null;
    }


    /**
     * Method return the tail of query containing objects of interest filtered from empty chain producers
     * @param subQuery subset of initial list of words
     * @param emptyChains empty chains dictionary for current subquery depending on query type
     * @return list of LightWords representing the object of interest situated at the end of the query
     */
    private List<LightWord> getRestAsObjectOfInterest(List<LightWord> subQuery, Set<String> emptyChains){
        List<LightWord> result = null;

        for(LightWord lightWord: subQuery){
            if(!QueryUtils.isWordStemsInDictionary(lightWord, emptyChains)){
                result.add(lightWord);
            } else {
                //обработка ситуации вида "какими характеристиками главным образом обладает X?"
                result.removeAll(result);
            }
        }

        return result;
    }
}
