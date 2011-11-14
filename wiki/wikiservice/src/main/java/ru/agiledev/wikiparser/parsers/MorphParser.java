package ru.agiledev.wikiparser.parsers;

import ru.agiledev.aos.morph.MorphAnalyzer;
import ru.agiledev.wiki.domain.attribute.PartOfSpeech;
import ru.agiledev.wiki.domain.transformed.Word;

/**
 * Created by a.kirillov
 * Date: 10/14/11 Time: 9:49 AM
 */
public class MorphParser {

    //TODO: specify type of exception

    public void addMorphologyToWord(Word word) throws Exception {
        MorphAnalyzer mAnalyzer = new MorphAnalyzer();

        String properties = mAnalyzer.getPropertiesForBase(word.getWord());
        if(properties!=null){
            word.setProperties(properties);


            if(PartOfSpeech.fromProperties(properties)!=PartOfSpeech.UNDEFINED){
                word.setPartOfSpeech(PartOfSpeech.fromProperties(properties));
            } else {

                try{
                    word.setPartOfSpeech(PartOfSpeech.fromProperties(properties.substring(0,properties.indexOf(","))));
                }catch (RuntimeException e){
                    e.getCause();
                }
            }
        } else {
            throw new Exception("Properties not found, possibly word is incorrect. Word: "+word.getWord());
        }
    }
}
