package ru.agiledev.aos.data;

import org.hibernate.Session;
import ru.agiledev.aos.data.domain.Indicator;

import java.util.List;

/**
 * Class for loading dictionaries from DB
 * Created by a.kirillov
 */
public class DictionaryLoader {
    public DictionaryLoader() {
    }

    public List<Indicator> loadDictionaries(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Indicator> result = session.createCriteria(Indicator.class).list();
        session.close();

        return result;
    }

    //public HashMap<>
}
