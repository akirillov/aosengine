package ru.agiledev.aos.queryanalyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.agiledev.aos.commons.dto.LightWord;
import ru.agiledev.aos.core.util.QueryUtils;
import ru.agiledev.aos.data.domain.QType;
import ru.agiledev.aos.data.domain.WordTypes;
import ru.agiledev.aos.morph.MorphAnalyzer;

import java.util.HashMap;
import java.util.List;

/**
 * Analyzer for input query. Main logic here.
 * Created by a.kirillov
 */
public class QueryAnalyzer {
    Logger logger = LoggerFactory.getLogger(QueryAnalyzer.class);
    /*



    */
    public QueryAnalyzer(){}

    //todo: сгенерить метод, который будет собирать большой объект и отдавать наверх

    /**
     * Method finds objects of interest in query
     * @param input
     * @return
     */
    public List<LightWord> getOI(String input){
        String[] cleanQuery = QueryUtils.cleanAndSplitQuery(input);
        List<LightWord> query = QueryUtils.stemQuery(cleanQuery);


        QueryParser parser = new QueryParser();

        logger.info("input: "+input);
        QType type = getQueryType(query);
        logger.info("type: "+type);

        return parser.parse(type, query);
    }

    /**
     * Public method for defining query type depending on input presented as a String
     * @param input String to be analyzed as query
     * @return type of input query if defined
     */
    public QType getQueryType(String input){
        String[] cleanQuery = QueryUtils.cleanAndSplitQuery(input);
        List<LightWord> query = QueryUtils.stemQuery(cleanQuery);


        QueryParser parser = new QueryParser();

        logger.info("input: "+input);
        QType type = getQueryType(query);
        logger.info("type: "+type);

        return type;
    }


    /**
     * Common entry method for defining query type on pre-stemmed query
     * @param query pre-stemmed query
     * @return QType of query
     */
    private QType getQueryType(List<LightWord> query){ //todo: RETURN TYPE?
        QType type = null;
        String startWord = query.get(0).getValue();
        if(getQueryTypesForStartWord(startWord)!=null){
            type = defineQueryTypeByFlags(query, getQueryTypesForStartWord(startWord), WordTypes.TYPE_WORD);
        } else {
            type = defineQueryTypeByFlags(query, QType.getTypesAsList(), WordTypes.TYPE_WORD);
        }
        return type;
        //todo: по идее, если тип не определен, то дальше и делать нечего. возвращаем null
    }

    /**
     * Method identifies whether the start word is unique (START_WORD) and returns possible question types in case if the word is unique
     * @param word input word
     * @return list of corresponding question types if word is unique, null in other case
     */
    private List<QType> getQueryTypesForStartWord(String word){
        HashMap<String, List<QType>> allowedTypes = Dictionaries.getInstance().getUniqueStartWords();

        MorphAnalyzer analyzer = new MorphAnalyzer();
        List<String> stems = analyzer.stem(word);

        if(stems.get(0).equals(word)){
            if((allowedTypes!=null)&&!allowedTypes.isEmpty()) return allowedTypes.get(word);
        }
        return null;
    }

    /**
     * Method looks trough input query and tries to identify one of the query' words as characterizing query type (TYPE_WORD)
     */
    private QType defineQueryTypeByFlags(List<LightWord> query, List<QType> types, WordTypes wordType){

        for(LightWord word: query){
            if(wordType.equals(WordTypes.TYPE_WORD)){
                if(types.contains(QType.STRUCTURE_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getStructureDescriptionFlags())) return QType.STRUCTURE_DESCRIPTION;
                }
                if(types.contains(QType.CHARACTERISTICS_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getCharacteristicsDescriptionFlags())) return QType.CHARACTERISTICS_DESCRIPTION;
                }
                if(types.contains(QType.WORK_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getWorkDescriptionFlags())){
                        QType result;
                        //TODO:fix
                        //result =  defineQueryTypeByFlags(Arrays.copyOfRange(query, i, query.length), Arrays.asList(QType.FEATURES_DESCRIPTION), WordTypes.SECOND_TYPE_WORD);

                        //if(result!=null) return result;

                        return QType.WORK_DESCRIPTION;
                    }
                }      //TODO: move dictionary inside util method
                if(types.contains(QType.FUNCTIONS_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getFunctionsDescriptionFlags())) return QType.FUNCTIONS_DESCRIPTION;
                }
                if(types.contains(QType.MISSION_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getMissionDescriptionFlags())) return QType.MISSION_DESCRIPTION;
                }
                if(types.contains(QType.USAGE_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getUsageDescriptionFlags())) return QType.USAGE_DESCRIPTION;
                }
                if(types.contains(QType.CORRESPONDS_TO_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getCorrespondsToDescriptionFlags())) return QType.CORRESPONDS_TO_DESCRIPTION;
                }
                if(types.contains(QType.DIFFERENCES_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getDifferencesDescriptionFlags())) return QType.DIFFERENCES_DESCRIPTION;
                }
                if(types.contains(QType.COMMONS_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getCommonsDescriptionFlags())) return QType.COMMONS_DESCRIPTION;
                }
                if(types.contains(QType.FEATURES_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getFeaturesDescriptionFlags())) return QType.FEATURES_DESCRIPTION;
                }
                if(types.contains(QType.POSSIBILITIES_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getPossibilitiesDescriptionFlags())) return QType.POSSIBILITIES_DESCRIPTION;
                }
                if(types.contains(QType.GOALS_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getGoalsDescriptionFlags())) return QType.GOALS_DESCRIPTION;
                }
                if(types.contains(QType.SETS_CHANGES_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getSetChangesDescriptionFlags())) return QType.SETS_CHANGES_DESCRIPTION;
                }

            } else if (wordType.equals(WordTypes.SECOND_TYPE_WORD)){
                if(types.contains(QType.FEATURES_DESCRIPTION)){
                    if(QueryUtils.isWordStemsInDictionary(word, Dictionaries.getInstance().getFeaturesDescriptionSecondaryFlags())) return QType.FEATURES_DESCRIPTION;
                }
                /*
                   Add branches here in order to check for secondary flags
                */
            }
        }

        //TODO: implement!!!!!!
        if(query.get(0).getValue().equals("из")&&query.get(1).getValue().equals("чего")||query.get(0).getValue().equals("из")&&query.get(1).getValue().equals("каких")) return QType.STRUCTURE_DESCRIPTION;
        if(query.get(0).getValue().equals("за")&&query.get(1).getValue().equals("что")) return QType.FUNCTIONS_DESCRIPTION;
        if(query.get(0).getValue().equals("зачем")) return QType.MISSION_DESCRIPTION;
        if(query.get(0).getValue().equals("для")) return QType.MISSION_DESCRIPTION;
        if(query.get(0).getValue().equals("к")) return QType.CORRESPONDS_TO_DESCRIPTION;
        if(query.get(0).getValue().equals("как")&&query.get(1).getValue().equals("ведет")) return QType.FEATURES_DESCRIPTION;
        return null;
    }
}
