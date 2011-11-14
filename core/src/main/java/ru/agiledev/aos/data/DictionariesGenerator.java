package ru.agiledev.aos.data;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.agiledev.aos.data.domain.Indicator;
import ru.agiledev.aos.data.domain.QType;
import ru.agiledev.aos.data.domain.WordTypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Helper class for dictionaries generation
 * Created by a.kirillov
 */
public class DictionariesGenerator {
    Logger logger = LoggerFactory.getLogger(DictionariesGenerator.class);

    public DictionariesGenerator() {}


    private void generateDictionaries(){
        //Very ugly solution but there is no other way
        List<Indicator> indicatorList = new ArrayList<Indicator>();  //TODO: запомнить "составляющая часть" - такие вещи нужно учитывать при разборе запроса

        addToList(indicatorList, new String[]{"устраивать", "структурировать", "организовывать"}, QType.STRUCTURE_DESCRIPTION, WordTypes.TYPE_WORD);
        addToList(indicatorList, new String[]{"устройство",  "структура", "организация",  "составляющая", "компонент", "элемент"}, QType.STRUCTURE_DESCRIPTION, WordTypes.TYPE_WORD);
        addToList(indicatorList, new String[]{"иметь",  "состоять", "из",  "что", "у", "обладать", "есть"}, QType.STRUCTURE_DESCRIPTION, WordTypes.EMPTY_CHAIN_PRODUCER);

        addToList(indicatorList, new String[]{"характеристика",  "свойство", "показатель"}, QType.CHARACTERISTICS_DESCRIPTION, WordTypes.TYPE_WORD);

        addToList(indicatorList, new String[]{"работать", "функционировать", "действовать"}, QType.WORK_DESCRIPTION, WordTypes.TYPE_WORD);
        addToList(indicatorList, new String[]{"работа", "функционирование", "действие"}, QType.WORK_DESCRIPTION, WordTypes.TYPE_WORD);

        addToList(indicatorList, new String[]{"функция", "обязанность"}, QType.FUNCTIONS_DESCRIPTION, WordTypes.TYPE_WORD);

        addToList(indicatorList, new String[]{"назначение", "предназначение", "цель", "миссия", "роль"}, QType.MISSION_DESCRIPTION, WordTypes.TYPE_WORD);
        addToList(indicatorList, new String[]{"предназначать", "создавать", "сконструировать", "построить", "разработать", "спроектировать"}, QType.MISSION_DESCRIPTION, WordTypes.TYPE_WORD);

        addToList(indicatorList, new String[]{"применять", "использовать", "задействовать",
                                              "употреблять", "эксплуатировать", "утилизировать",
                                              "пользоваться","применяться", "использоваться",
                                              "употребляться", "эксплуатироваться", "утилизироваться", "пользоваться"}, QType.USAGE_DESCRIPTION, WordTypes.TYPE_WORD);
        addToList(indicatorList, new String[]{"применение", "использование", "эксплуатация", "употребление", "утилизация", "пользование", "задействование"}, QType.USAGE_DESCRIPTION, WordTypes.TYPE_WORD);


        addToList(indicatorList, new String[]{"группа", "класс", "категория", "тип"}, QType.CORRESPONDS_TO_DESCRIPTION, WordTypes.TYPE_WORD);
        //addToList(indicatorList, new String[]{"относиться", "принадлежать"}, QType.CORRESPONDS_TO_DESCRIPTION, WordTypes.TYPE_WORD);//this for words that identify type in minor case

        addToList(indicatorList, new String[]{"отличаться", "различаться", "расходиться", "несхожий", "непохожий", "расхожий", "несоответствовать"}, QType.DIFFERENCES_DESCRIPTION, WordTypes.TYPE_WORD);
        addToList(indicatorList, new String[]{"отличие", "различие", "несхожесть", "непохожесть", "расхождение", "несовпадение", "несоответствие"}, QType.DIFFERENCES_DESCRIPTION, WordTypes.TYPE_WORD);

        addToList(indicatorList, new String[]{"похожий", "подобный", "аналогичный", "схожий", "общий"}, QType.COMMONS_DESCRIPTION, WordTypes.TYPE_WORD);
        addToList(indicatorList, new String[]{"аналогичность", "схожесть", "сходство", "похожесть"}, QType.COMMONS_DESCRIPTION, WordTypes.TYPE_WORD);

        addToList(indicatorList, new String[]{"особенность", "специфика","уникальность"}, QType.FEATURES_DESCRIPTION, WordTypes.TYPE_WORD);
        addToList(indicatorList, new String[]{"уникальный", "особенный", "отличный", "отличительный", "специфичный"}, QType.FEATURES_DESCRIPTION, WordTypes.TYPE_WORD);
        addToList(indicatorList, new String[]{"обстоятельство", "условие", "при", "ситуация"}, QType.FEATURES_DESCRIPTION, WordTypes.SECOND_TYPE_WORD);


        addToList(indicatorList, new String[]{"возможность","способный","мочь"}, QType.POSSIBILITIES_DESCRIPTION, WordTypes.TYPE_WORD);


        //todo: add goals and set here when ready



        addToList(indicatorList, new String[]{"как", "из"},QType.STRUCTURE_DESCRIPTION, WordTypes.START_WORD);
        addToList(indicatorList, new String[]{"как"},QType.WORK_DESCRIPTION, WordTypes.START_WORD);

        addToList(indicatorList, new String[]{"как", "когда"},QType.USAGE_DESCRIPTION, WordTypes.START_WORD);
        addToList(indicatorList, new String[]{"как"},QType.FEATURES_DESCRIPTION, WordTypes.START_WORD);
        addToList(indicatorList, new String[]{"что", "за"},QType.FUNCTIONS_DESCRIPTION, WordTypes.START_WORD);

        addToList(indicatorList, new String[]{"что", "зачем", "для"},QType.MISSION_DESCRIPTION, WordTypes.START_WORD);

        addToList(indicatorList, new String[]{"что"},QType.DIFFERENCES_DESCRIPTION, WordTypes.START_WORD);
        addToList(indicatorList, new String[]{"что"},QType.COMMONS_DESCRIPTION, WordTypes.START_WORD);

        addToList(indicatorList, new String[]{"что","на"},QType.POSSIBILITIES_DESCRIPTION, WordTypes.START_WORD);

        addToList(indicatorList, new String[]{"к"},QType.CORRESPONDS_TO_DESCRIPTION, WordTypes.START_WORD);





        //now writing to DB

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        for (Iterator<Indicator> iterator = indicatorList.iterator(); iterator.hasNext();) {
            Indicator next = iterator.next();
            session.save(next);
            logger.info(next.getValue()+" saved.");
        }

        session.getTransaction().commit();

        session.close();

    }

    /**
     * Helper method for filling list with data
     * @param targetList - list where indicator words will be added
     * @param words - array of words
     * @param type - type of indicator
     */
    private void addToList(List<Indicator> targetList, String[] words, QType type, WordTypes wordType){
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            targetList.add(new Indicator(word, type, wordType));
        }
    }

    public static void main(String ... args){
        DictionariesGenerator dictionariesGenerator = new DictionariesGenerator();
       dictionariesGenerator.generateDictionaries(); //todo: uncomment for filling DB
    }
}
