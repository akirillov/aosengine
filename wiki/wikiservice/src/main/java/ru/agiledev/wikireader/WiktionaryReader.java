package ru.agiledev.wikireader;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.agiledev.aos.commons.dto.LightWord;
import ru.agiledev.aos.morph.MorphAnalyzer;
import ru.agiledev.wiki.domain.transformed.Stem;
import ru.agiledev.wikiparser.util.HibernateUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Class for interacting with parsed wiktionary database in read-only mode
 * Created by a.kirillov
 *///TODO: EXPOSE AS WEB-SERVICE IN FUTURE RELEASE
public class WiktionaryReader {
    Logger logger = LoggerFactory.getLogger(WiktionaryReader.class);

    /**
     * Method for creating stem form of word for query analyzer
     * @param word input word in String format
     * @return LightWord with word value and set of stems
     */
    public LightWord getStemmedWord(String word){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Stem.class).add(Restrictions.eq("value", word));

        List<Stem> stems = criteria.list();

        session.close();

        LightWord resultWord;
        if(stems.size()>0){
            Stem domainStem = (Stem) criteria.list().get(0);
            resultWord = toLightWord(domainStem);
        } else {
            MorphAnalyzer analyzer = new MorphAnalyzer();

            List<String> stemmedList = analyzer.stem(word);

            Stem stem = writeNewStemToDatabase(word, stemmedList);

            resultWord = toLightWord(stem);
        }
        return resultWord;
    }


    /**
     * Method creates new Stem and writes it into DB
     * @param word initial form of word
     * @param stemmedList list of word's stems
     * @return Stem
     */
    private Stem writeNewStemToDatabase(String word, List<String> stemmedList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        StringBuilder stringBuilder = new StringBuilder();
        for(String stemmedWord:stemmedList){
            stringBuilder.append(stemmedWord);
            stringBuilder.append("|");
        }

        Stem stem = new Stem(word, stringBuilder.substring(0, stringBuilder.length()-2));

        session.save(stem);
        session.getTransaction().commit();
        session.close();

        return stem;
    }




    /**
     * Transforming domain entity Stem to LightWord
     * @param stem domain entity Stem
     * @return LightWord build on domain word
     */
    private LightWord toLightWord(Stem stem){

        HashSet<String> stems = new HashSet<String>();
        Collections.addAll(stems, stem.getStems().split("|"));

        LightWord result = new LightWord(stem.getValue(), stems);

        return result;
    }

}
