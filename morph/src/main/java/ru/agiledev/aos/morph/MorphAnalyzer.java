package ru.agiledev.aos.morph;

import ru.agiledev.aos.morph.utils.MCRMorphologyAnalyzer;

import java.io.IOException;
import java.util.*;

/**
 * Top-level API for calling stemmer or morphology analyzer
 *
 *
 * Created by a.kirillov
 * Date: 9/30/11 Time: 5:58 PM
 */
public class MorphAnalyzer {
    /**
     * Get all base forms for input word
     * @param input word
     * @return List of base form words, null if error occurred or nothing found
     */
    public List<String> stem(String input){
        String stem = null;
        MyStemStemmer stemmer = new MyStemStemmer();

        try {
            stem = stemmer.stem(input);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }  catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }

        if(stem!=null){
            stem = stem.substring(stem.toString().indexOf("{") + 1, stem.toString().indexOf("}"));
        }

        List<String> stems = new ArrayList<String>();
        stems.addAll(Arrays.asList(stem.split("\\|")));
        return stems;
    }

    /**
     * Method returns string of properties for word in mystem format
     * @param base input word
     * @return properties listed as String
     */
    public String getPropertiesForBase(String base){
        String result = null;
        MyStemStemmer stemmer = new MyStemStemmer();

        try {
            result = stemmer.getBaseFormProperties(base);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }  catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }

        return result;
    }


    public String transformNounToMultipleForm(String base){
        MCRMorphologyAnalyzer m = new MCRMorphologyAnalyzer();
        return  m.getMultipleNounForm(base);
    }


    public static void main(String ... args){
        MorphAnalyzer m = new MorphAnalyzer();

        String word = "";
        if((args != null)&&(args.length>0)){
            word = args[0];
        } else {
            System.out.println("No input specified! You should specify one word to be stemmed");
            return;
        }

        System.out.println("Слово: "+word);
        System.out.println("Базовые формы: ");

        List<String> bases = m.stem(word);

        Iterator<String> iter = bases.iterator();

        while (iter.hasNext()){
            String base = iter.next();

            System.out.println(base+" Свойства: "+m.getPropertiesForBase(base));

        }

    }
}
