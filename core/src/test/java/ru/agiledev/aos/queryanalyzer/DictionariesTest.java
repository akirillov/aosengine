package ru.agiledev.aos.queryanalyzer;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by a.kirillov
 * Date: 11/1/11 Time: 10:41 PM
 */
public class DictionariesTest {
    @Test
    public void loadDictionariesTest(){
        assertNotNull(Dictionaries.getInstance());

        assertTrue(Dictionaries.getInstance().getAllowedWords().size()>0);

        assertTrue(Dictionaries.getInstance().getStructureDescriptionFlags().size()>0);

        assertTrue(Dictionaries.getInstance().getUniqueStartWords().size()>0);
    }
}
