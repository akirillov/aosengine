package ru.agiledev.aos.core.util;

import ru.agiledev.aos.commons.dto.LightWord;
import ru.agiledev.wikireader.WiktionaryReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Common query Utils
 *
 * Created by a.kirillov
 */
public class QueryUtils {
    /**
     * Method stems input array of words to list of internal objects of type LightWord
     * @param inputQuery Array of strings representing input query
     * @return internal representation of query as List of LightWords
     */
    public static List<LightWord> stemQuery(String[] inputQuery){
        List<LightWord> stemmedQuery = new ArrayList<LightWord>(inputQuery.length);
        WiktionaryReader reader = new WiktionaryReader();

        for (int i = 0; i < inputQuery.length; i++) {
            String word = inputQuery[i];
            stemmedQuery.add(reader.getStemmedWord(word));
        }

        return stemmedQuery;
    }

    /**
     * Method simply defines whether stems of input word are in type dictionary
     * @param word input LightWord object
     * @param dictionary target dictionary to check
     * @return true if target dictionary contains one of stems, false in other case
     */
    public static boolean isWordStemsInDictionary(LightWord word, Set dictionary){
        Set<String> stems = word.getStems();

        for(String stem: stems){
            if(dictionary.contains(stem)) return true;
        }

        return false;
    }

    /**
     * Query cleaner method. Lowercases cxvcxv
     * @param input
     * @return
     */
        public static String[] cleanAndSplitQuery(String input){
        //todo: IMPLEMENT как следует! ! !

        return input.toLowerCase().trim().replaceAll("\\?", "").replaceAll("\\s+", " ").split(" ");
    }
}
