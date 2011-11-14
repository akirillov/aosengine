package ru.agiledev.aos.morph.utils;

import ru.narod.macrocosm.mcr.Ray; //TODO: import in maven-style!!!

/**
 * MCR per-word morphology analyzer
 *      kinda scary thing =)
 *
 *      Solved task: from base form return number of forms as String
 *      Helpful in creating unstemmed (stemmed back | denormalized) nouns!
 */
public class MCRMorphologyAnalyzer
{
    public String parse( String input) {
        StringBuilder buf = new StringBuilder();
	    Ray r=new Ray();
        //TODO: wrap errors ?
        r.a(input, buf);//-> явно с++ стиль
        String mResult = buf.toString();
        return mResult;
    }

    /**
     * Method produces multiple form from base noun
     * @param base noun in base form
     * @return multiple form of input noun
     */
    public String getMultipleNounForm(String base){

        String paradigms = parse(base);

        //Сущ.Мн.ч. И.П.
        String[] lines = paradigms.split("\n");
        String paradigm = null;
        for(String line:lines){
            if (line.contains("Сущ.")
                &&line.contains("Мн.ч.")
                &&line.contains("И.П.")){
                paradigm = line;
                break;
            }
        }

        if(paradigm!=null){
            return paradigm.substring(paradigm.indexOf(")")+1,paradigm.indexOf("(")).trim();
        }

        return null;
    }
}
