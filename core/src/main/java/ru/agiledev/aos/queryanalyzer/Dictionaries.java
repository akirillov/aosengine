package ru.agiledev.aos.queryanalyzer;

import ru.agiledev.aos.data.DictionaryLoader;
import ru.agiledev.aos.data.domain.Indicator;
import ru.agiledev.aos.data.domain.QType;
import ru.agiledev.aos.data.domain.WordTypes;

import java.util.*;

/**
 * Class contains HashSet fields for representing question types indicators
 * Created by a.kirillov
 */
public class Dictionaries {

    //Aspect-oriented set of questions' types' indicators
    private HashSet<String> structureDescriptionFlags = new HashSet<String>();
    private HashSet<String> characteristicsDescriptionFlags = new HashSet<String>();
    private HashSet<String> workDescriptionFlags = new HashSet<String>();
    private HashSet<String> functionsDescriptionFlags = new HashSet<String>();
    private HashSet<String> missionDescriptionFlags = new HashSet<String>();
    private HashSet<String> usageDescriptionFlags = new HashSet<String>();
    private HashSet<String> correspondsToDescriptionFlags = new HashSet<String>();
    private HashSet<String> differencesDescriptionFlags = new HashSet<String>();
    private HashSet<String> commonsDescriptionFlags = new HashSet<String>();
    private HashSet<String> featuresDescriptionFlags = new HashSet<String>();
    private HashSet<String> possibilitiesDescriptionFlags = new HashSet<String>();

    //set of questions' types' indicators for question about goals
    private HashSet<String> goalsDescriptionFlags = new HashSet<String>();

    //set of questions' types' indicators for changes in sets
    private HashSet<String> setChangesDescriptionFlags = new HashSet<String>();

    //todo: replace with check in method with return type QType (if in one of sets, then the type is ...)
    private HashSet<String> allowedWords = new HashSet<String>();

    //HashMap represents mapping of unique start word to possible types of questions(value) starting with this word(key)
    private HashMap<String, List<QType>> uniqueStartWords = new HashMap<String, List<QType>>();

    //HashMap for secondary indicator words (например "работа в условиях", "фунционирование при обстоятельсятвах")
    private HashSet<String> featuresDescriptionSecondaryFlags= new HashSet<String>();

    //Empty chain producers for types
    private HashSet<String> structureDescriptionEmptyChainProducers = new HashSet<String>();


    // Private constructor prevents instantiation from other classes
    private Dictionaries() {
        DictionaryLoader loader = new DictionaryLoader();
        List<Indicator> indicatorList = loader.loadDictionaries();

        for (Iterator<Indicator> iterator = indicatorList.iterator(); iterator.hasNext();) {
            Indicator next = iterator.next();

            if(next.getWordType().equals(WordTypes.TYPE_WORD)){
                allowedWords.add(next.getValue());

                switch (next.getqType()){
                    case STRUCTURE_DESCRIPTION: structureDescriptionFlags.add(next.getValue()); break;
                    case CHARACTERISTICS_DESCRIPTION: characteristicsDescriptionFlags.add(next.getValue()); break;
                    case WORK_DESCRIPTION: workDescriptionFlags.add(next.getValue()); break;
                    case FUNCTIONS_DESCRIPTION: functionsDescriptionFlags.add(next.getValue()); break;
                    case MISSION_DESCRIPTION: missionDescriptionFlags.add(next.getValue()); break;
                    case USAGE_DESCRIPTION: usageDescriptionFlags.add(next.getValue()); break;
                    case CORRESPONDS_TO_DESCRIPTION: correspondsToDescriptionFlags.add(next.getValue()); break;
                    case DIFFERENCES_DESCRIPTION: differencesDescriptionFlags.add(next.getValue()); break;
                    case COMMONS_DESCRIPTION: commonsDescriptionFlags.add(next.getValue()); break;
                    case FEATURES_DESCRIPTION: featuresDescriptionFlags.add(next.getValue()); break;
                    case POSSIBILITIES_DESCRIPTION: possibilitiesDescriptionFlags.add(next.getValue()); break;
                    case GOALS_DESCRIPTION: goalsDescriptionFlags.add(next.getValue()); break;
                    case SETS_CHANGES_DESCRIPTION: setChangesDescriptionFlags.add(next.getValue());
                }
            }else if(next.getWordType().equals(WordTypes.SECOND_TYPE_WORD)){
                allowedWords.add(next.getValue());

                switch (next.getqType()){
                  /*  case STRUCTURE_DESCRIPTION: structureDescriptionFlags.add(next.getValue()); break;
                    case CHARACTERISTICS_DESCRIPTION: characteristicsDescriptionFlags.add(next.getValue()); break;
                    case WORK_DESCRIPTION: workDescriptionFlags.add(next.getValue()); break;
                    case FUNCTIONS_DESCRIPTION: functionsDescriptionFlags.add(next.getValue()); break;
                    case MISSION_DESCRIPTION: missionDescriptionFlags.add(next.getValue()); break;
                    case USAGE_DESCRIPTION: usageDescriptionFlags.add(next.getValue()); break;
                    case CORRESPONDS_TO_DESCRIPTION: correspondsToDescriptionFlags.add(next.getValue()); break;
                    case DIFFERENCES_DESCRIPTION: differencesDescriptionFlags.add(next.getValue()); break;
                    case COMMONS_DESCRIPTION: commonsDescriptionFlags.add(next.getValue()); break;                */
                    case FEATURES_DESCRIPTION: featuresDescriptionSecondaryFlags.add(next.getValue()); break;
                    /*case POSSIBILITIES_DESCRIPTION: possibilitiesDescriptionFlags.add(next.getValue()); break;
                    case GOALS_DESCRIPTION: goalsDescriptionFlags.add(next.getValue()); break;
                    case SETS_CHANGES_DESCRIPTION: setChangesDescriptionFlags.add(next.getValue());*/
                }
            }else if(next.getWordType().equals(WordTypes.EMPTY_CHAIN_PRODUCER)){
                allowedWords.add(next.getValue());

                switch (next.getqType()){
                    case STRUCTURE_DESCRIPTION: structureDescriptionEmptyChainProducers.add(next.getValue()); break;
                  /*  case CHARACTERISTICS_DESCRIPTION: characteristicsDescriptionFlags.add(next.getValue()); break;
                    case WORK_DESCRIPTION: workDescriptionFlags.add(next.getValue()); break;
                    case FUNCTIONS_DESCRIPTION: functionsDescriptionFlags.add(next.getValue()); break;
                    case MISSION_DESCRIPTION: missionDescriptionFlags.add(next.getValue()); break;
                    case USAGE_DESCRIPTION: usageDescriptionFlags.add(next.getValue()); break;
                    case CORRESPONDS_TO_DESCRIPTION: correspondsToDescriptionFlags.add(next.getValue()); break;
                    case DIFFERENCES_DESCRIPTION: differencesDescriptionFlags.add(next.getValue()); break;
                    case COMMONS_DESCRIPTION: commonsDescriptionFlags.add(next.getValue()); break;
                    case FEATURES_DESCRIPTION: featuresDescriptionSecondaryFlags.add(next.getValue()); break;
                    case POSSIBILITIES_DESCRIPTION: possibilitiesDescriptionFlags.add(next.getValue()); break;
                    case GOALS_DESCRIPTION: goalsDescriptionFlags.add(next.getValue()); break;
                    case SETS_CHANGES_DESCRIPTION: setChangesDescriptionFlags.add(next.getValue());*/
                }
            } else {

                if(uniqueStartWords.containsKey(next.getValue())){
                    uniqueStartWords.get(next.getValue()).add(next.getqType());
                } else {
                    List<QType> types = new ArrayList<QType>();
                    types.add(next.getqType());
                    uniqueStartWords.put(next.getValue(), types);
                }
            }

        }

    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class DictionariesHolder {
        public static final Dictionaries instance = new Dictionaries();
    }

    public static Dictionaries getInstance() {
        return DictionariesHolder.instance;
    }



    //getters
    public HashSet<String> getStructureDescriptionFlags() {
        return structureDescriptionFlags;
    }

    public HashSet<String> getCharacteristicsDescriptionFlags() {
        return characteristicsDescriptionFlags;
    }

    public HashSet<String> getWorkDescriptionFlags() {
        return workDescriptionFlags;
    }

    public HashSet<String> getFunctionsDescriptionFlags() {
        return functionsDescriptionFlags;
    }

    public HashSet<String> getMissionDescriptionFlags() {
        return missionDescriptionFlags;
    }

    public HashSet<String> getUsageDescriptionFlags() {
        return usageDescriptionFlags;
    }

    public HashSet<String> getCorrespondsToDescriptionFlags() {
        return correspondsToDescriptionFlags;
    }

    public HashSet<String> getDifferencesDescriptionFlags() {
        return differencesDescriptionFlags;
    }

    public HashSet<String> getCommonsDescriptionFlags() {
        return commonsDescriptionFlags;
    }

    public HashSet<String> getFeaturesDescriptionFlags() {
        return featuresDescriptionFlags;
    }

    public HashSet<String> getPossibilitiesDescriptionFlags() {
        return possibilitiesDescriptionFlags;
    }

    public HashSet<String> getGoalsDescriptionFlags() {
        return goalsDescriptionFlags;
    }

    public HashSet<String> getSetChangesDescriptionFlags() {
        return setChangesDescriptionFlags;
    }

    public HashSet<String> getAllowedWords() {
        return allowedWords;
    }

    public HashMap<String, List<QType>> getUniqueStartWords() {
        return uniqueStartWords;
    }

    public HashSet<String> getFeaturesDescriptionSecondaryFlags() {
        return featuresDescriptionSecondaryFlags;
    }

    public HashSet<String> getStructureDescriptionEmptyChainProducers() {
        return structureDescriptionEmptyChainProducers;
    }
}
