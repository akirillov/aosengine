package ru.agiledev.wikiparser;

import org.hibernate.Session;
import org.junit.Test;
import ru.agiledev.wiki.domain.transformed.Stem;
import ru.agiledev.wikiparser.util.HibernateUtil;

/**
 * Created by a.kirillov
 * Date: 10/3/11 Time: 10:19 PM
 */
public class HibernateUtilTest {
    @Test
    public void test(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Stem stem = new Stem();

        session.beginTransaction();
        session.save(stem);
        session.getTransaction().commit();
        session.close();

    }
}
