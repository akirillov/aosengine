package ru.agiledev.wikiparser.parsers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.agiledev.wiki.domain.transformed.Word;
import ru.agiledev.wikiparser.util.HibernateUtil;

import java.util.List;

/**
 * Thread for parsing formed wiki DB and adding morphological properties to words
 * currently not in use but who knows
 *
 * Created by a.kirillov
 * Date: 10/14/11 Time: 5:54 PM
 */
public class MorphologyParserThread extends Thread {
    Logger logger = LoggerFactory.getLogger(MorphologyParserThread.class);
    private int start;
    private int size;

    public MorphologyParserThread(int start, int size) {
        this.start = start;
        this.size = size;
    }

    @Override
    public void run() {
        logger.info("Thread started. Range: ["+start+"; "+(start+size)+"]");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("FROM Word");
        query.setFirstResult(start);
        query.setMaxResults(size);

        List<Word> words = query.list();
        MorphParser parser = new MorphParser();
        int counter=0;

        for(Word word:words){
            try {
                parser.addMorphologyToWord(word);
            } catch (Exception e) {
                logger.warn(e.getMessage());
                continue;
            }

            session.saveOrUpdate(word);
            counter++;


            if(counter>30){
                session.flush();
                session.clear();

                counter =0;
            }

        }

        session.getTransaction().commit();
        session.close();
    }
}
