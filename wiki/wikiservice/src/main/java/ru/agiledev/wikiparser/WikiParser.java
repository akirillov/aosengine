package ru.agiledev.wikiparser;

import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import ru.agiledev.wiki.domain.core.Page;
import ru.agiledev.wikiparser.parsers.ArticleParserThread;
import ru.agiledev.wikiparser.parsers.MorphologyParserThread;
import ru.agiledev.wikiparser.util.HibernateUtil;

import java.util.List;

/**
 * Top-level API for parsing.
 * TODO: make universal module with parameters
 * connection requisites must be set in hibernate.cfg.xml before usage
 *
 * Created by a.kirillov
 * Date: 10/14/11 Time: 9:52 AM
 */
public class WikiParser {
    /**
     * Method parses wiktionary DB but without morphological analysis for now
     */
    public void parseWiki(){

        //for page titles
        Session session = HibernateUtil.getSessionFactory().openSession();
        //0 - is for Russian namespace
        List<Page> pages = session.createCriteria(Page.class).add(Restrictions.eq("pageNamespace", 0)).list();

        int last = 0;


        for(int i=0; i<50;i++){
            if(i==49){
                ArticleParserThread thread = new ArticleParserThread(pages.subList(last, pages.size()));
                thread.start();
                break;
            }

            ArticleParserThread thread = new ArticleParserThread(pages.subList(last, last+=(pages.size()/50)));
            thread.start();
        }
    }

    /**
     * Method for morphological parsing of wiktionary.
     * Adds to parsed DB part of speech and set of properties
     */
    public void parseMorphWiki(){
        int max = 380000;
        int last = 0;

        for(int i=0;i<20;i++){
            MorphologyParserThread thread = new MorphologyParserThread(last, 19000);
            thread.start();
            last += 19000;
        }

    }

    public static void main(String ... args){

        WikiParser w = new WikiParser();
        //w.parseWiki();
        //w.parseMorphWiki();
    }
}
