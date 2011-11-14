package ru.agiledev.wikiparser.parsers;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.agiledev.wiki.domain.core.Page;
import ru.agiledev.wiki.domain.transformed.Word;
import ru.agiledev.wikiparser.util.HibernateUtil;

import java.util.List;

/**
 * Created by a.kirillov
 * Date: 10/15/11 Time: 9:57 AM
 */
public class ArticleParserThread extends Thread{
    Logger logger = LoggerFactory.getLogger(ArticleParserThread.class);
    List<Page> pagesToParse;

    public ArticleParserThread(List<Page> pagesToParse) {
        this.pagesToParse = pagesToParse;
    }

    @Override
    public void run() {
        logger.info("Thread "+this.getName()+" started!");
        //for page titles
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //0 - is for Russian namespace
        int i = 0;
        int wordCounter = 0;
        for(Page page:pagesToParse){

            String title = page.getPageTitle();
            if(title.contains("'")){
                title = title.replace("'","''");
            }

            List<String> texts = session.createSQLQuery("SELECT old_text FROM page,text WHERE page.page_title='"+title+"' AND page_namespace=0 AND page.page_latest=text.old_id").list();
            String text = texts.get(0);

            if(text!=null){
                ArticleParser parser = new ArticleParser(title);
                List<Word> words = parser.textToWordList(text);
                if((words!=null)&&!words.isEmpty()){
                    for(Word word:words){

                        session.save(word);
                        wordCounter++;
                    }
                }
            }


            logger.warn(this.getName()+" :::: "+(++i)+" of "+pagesToParse.size() +" parsed ::::");

            if(wordCounter>30){
               session.flush();
                session.clear();

               wordCounter =0;
            }
        }
        session.getTransaction().commit();
        session.close();




    }
}
