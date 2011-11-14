package ru.agiledev.wikiparser.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.agiledev.wiki.domain.transformed.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Article parser parses wiki markup and creates number of words (depends on number of meanings).
 * It includes in word next fields: definition and list of synonyms, antonyms, hyponyms and hypernyms
 * Part of speech and morphological properties implemented in @see ru.agiledev.wikiparser.parsers.MorphParser
 * TODO: implement morphological parser usage here for single-iteration wiki parsing
 *
 *
 * Created by a.kirillov
 * Date: 10/3/11 Time: 10:54 PM
 */
public class ArticleParser {
    Logger logger = LoggerFactory.getLogger(ArticleParserThread.class);
    String title;

    public ArticleParser(String title) {
        this.title = title.replace("_"," ");
    }

    public List<Word> textToWordList(String wikiText){
        List<Word> result = new ArrayList<Word>();
        //clean text from tags
        int iod = wikiText.indexOf("=== Значение ===")<0?wikiText.indexOf("===Значение==="):wikiText.indexOf("=== Значение ===");

        if(iod<0) return null;

        String necessary = wikiText.substring(iod);
        if(necessary.length() == 0){
            Word word = new Word();
            word.setWord(title);
            result.add(word);
            return result;
        }

        //results section
        ArrayList<String> definitions = new ArrayList<String>();
        ArrayList<String> synonyms = new ArrayList<String>();
        ArrayList<String> antonyms = new ArrayList<String>();
        ArrayList<String> hyponyms = new ArrayList<String>();
        ArrayList<String> hypernyms = new ArrayList<String>();
        ArrayList<String> tmpList = null;

        String[] lines = necessary.split("\n");
        //iterating lines after "==== Значение ===="
        if(lines.length>0){
            for(int i=0; i<lines.length; i++){
                if(!lines[i].equals("")){
                    if(lines[i].contains("=== Значение ===")||lines[i].contains("===Значение===")){
                        tmpList = definitions;
                    } else if(lines[i].contains("=== Синонимы ===")||lines[i].contains("===Синонимы===")){
                        tmpList = synonyms;
                    } else if(lines[i].contains("=== Антонимы ===")||lines[i].contains("===Антонимы===")){
                        tmpList = antonyms;
                    } else if(lines[i].contains("=== Гиперонимы ===")||lines[i].contains("===Гиперонимы===")){
                        tmpList = hypernyms;
                    } else if(lines[i].contains("=== Гипонимы ===")||lines[i].contains("===Гипонимы===")){
                        tmpList = hyponyms;
                    } else if(lines[i].contains("==")) break;

                    if(lines[i].startsWith("#")){ //FIXME: всегда ли?
                        String[] line;
                        if(lines[i].indexOf("{{пример")>0){
                            line = lines[i].substring(0,lines[i].indexOf("{{пример")).split("");
                        } else {
                            line = lines[i].split("");
                        }

                        //iterating line in style of Finite State Machine
                        StringBuffer resString = new StringBuffer();
                        StringBuffer tmpString = new StringBuffer();

                        boolean isLink = false;
                        boolean isPattern = false;
                        boolean pipeOccured = false;

                        for(int j=0; j< line.length; j++){
                            String symbol = line[j];

                            if(symbol.equals("#")||symbol.equals("'")) continue;

                            if(symbol.equals("{")){
                                isPattern = true;
                                if(line[j-1].equals("]")||line[j-1].equals("}")){
                                    resString.append(" ");
                                }
                                continue;
                            }

                            if(symbol.equals("[")){
                                isLink = true;
                                if(line[j-1].equals("]")||line[j-1].equals("}")){
                                    resString.append(" ");
                                }
                                continue;
                            }

                            if(symbol.equals("]")){
                                isLink = false;
                                if(!isPattern){
                                    resString.append(tmpString);
                                    tmpString = new StringBuffer();
                                }
                                continue;
                            }

                            if(symbol.equals("}")){
                                isPattern = false;
                                if(pipeOccured){
                                    resString.append(tmpString);
                                    tmpString = new StringBuffer();
                                    pipeOccured = false;
                                } else {
                                    tmpString = new StringBuffer();
                                }
                                continue;
                            }

                            if(symbol.equals("|")){
                                pipeOccured = true;
                                if (isLink||isPattern){
                                    tmpString = new StringBuffer();
                                }
                                continue;
                            }

                            if(isLink || isPattern){
                                tmpString.append(symbol);
                                continue;
                            }

                            if(!isLink&&!isPattern){
                                resString.append(symbol);
                            }

                        }
                        String res = resString.toString();
                        if(!res.trim().equals("")&&!res.trim().equals("uk")){
                            tmpList.add(resString.toString());
                        }
                    }
                }
            }
        }

        //for debug purpose only
        /*StringBuffer buffer = new StringBuffer();
        buffer.append("\nСлово: "+title+" - - - - -   \n=== Значения ===\n");
        for(String s: definitions){
            buffer.append(clear(s)+"\n");
        }
        buffer.append("=== Синонимы ===\n");
        for(String s: synonyms){
            buffer.append(clear(s)+"\n");
        }
        buffer.append("=== Антонимы ===\n");
        for(String s: antonyms){
            buffer.append(clear(s)+"\n");
        }
        buffer.append("=== Гиперонимы ===\n");
        for(String s: hypernyms){
            buffer.append(clear(s)+"\n");
        }
        buffer.append("=== Гипонимы ===\n");
        for(String s: hyponyms){
            buffer.append(clear(s)+"\n");
        }*/

        //logger.info(buffer.toString());
        for(String s: definitions){
            Word word = new Word();
            word.setWord(title);
            word.setMeaning(clear(s));

            //now adding morphology to word
            MorphParser morphParser = new MorphParser();
            try {
                morphParser.addMorphologyToWord(word);
            } catch (Exception e) {
                logger.warn(e.getMessage());
                continue;
            }

            int index = definitions.indexOf(s);

            if((synonyms.size() > index)&&!synonyms.isEmpty()){
                word.setSynonyms(clear(synonyms.get(index)));
            } else {
                word.setSynonyms("");
            }

            if((antonyms.size() > index)&&!antonyms.isEmpty()){
                word.setAntonyms(clear(antonyms.get(index)));
            } else {
                word.setAntonyms("");
            }

            if((hypernyms.size() > index)&&!hypernyms.isEmpty()){
                word.setHypernyms(clear(hypernyms.get(index)));
            } else {
                word.setHypernyms("");
            }

            if((hyponyms.size() > index)&&!hyponyms.isEmpty()){
                word.setHyponyms(clear(hyponyms.get(index)));
            } else {
                word.setHyponyms("");
            }

            result.add(word);
        }

        return result;
    }

    String clear(String input){
        String result = input.replaceAll("\\s+", " ");

        if(result.trim().equals("-")){
            return "";
        }

        else return result.trim();
    }
}

//Wiktionary article text example


/*
{{wikipedia}}
= {{-ru-}} =

=== Морфологические и синтаксические свойства ===
{{сущ ru m ina 1c(1)
|основа=до́м
|основа1=дом
|слоги={{по-слогам|дом}}
|М=на дому́
|клитика=''и́з дому'' или ''на́ дом''
}}

{{морфо||дом}}

=== Произношение ===
{{transcriptions|ˈdom|dɐˈma}} {{медиа|Ru-дом.ogg}}

=== Семантические свойства ===
{{илл|20031012-002-houses-st-albans.jpg|Дома [1]}}

==== Значение ====
# архитектурное сооружение, предназначенное для жилья, и имеющее, как правило, [[стена|стены]], [[дверь]] и [[крыша|крышу]] {{пример|Просторный дом.}} {{пример|Трёхэтажный дом.}} {{пример|{{выдел|Дом}} в центре города.}}
# [[место]], где кто-либо постоянно проживает {{пример|Здесь мой родной {{выдел|дом}}.}} {{пример|Всё смешалось в {{выдел|доме}} Облонских.|Л. Н. Толстой|Анна Каренина}}
# {{офиц.}} совокупность жилых или производственных корпусов, а также служебных строений, расположенных на одном земельном участке и имеющих один учётный номер {{пример|Около полуночи с 12 на 13 сентября он подъехал ко второму подъезду {{выдел|дома}} номер 33, корпус 1 по улице Паши Савельевой.|Дмитрий Соколов-Митрич|Смерть за Круга. В Твери расстрелян человек, которому молва приписывает убийство знаменитого шансонье|издание=Известия|дата=2002.09.20|источник=НКРЯ}}
# {{п.}} [[фирма]], [[предприятие]] {{пример|Торговый дом.}} {{пример|Издательский дом.}}
# {{спорт.}} в [[кёрлинг]]е{{-}}[[мишень]] в конце ледовой полосы, образованная четырьмя концентрическими [[круг]]ами {{пример|Команда, чей камень (или камни) в конце энда располагается ближе к центру {{выдел|дома}}, выигрывает энд.}}
# {{спорт.}} {{помета|в [[бейсбол]]е}} [[база]], с которой начинается и которой заканчивается [[пробежка]] [[игрок]]а {{пример}}
# {{п.}} [[семья]], династия, клан {{пример|Трёхсотлетие {{выдел|дома}} Романовых}} {{пример|главы двух враждующих {{выдел|домов}}}} {{пример|чума на оба ваши {{выдел|дома}}}}

==== Синонимы ====
# [[здание]], [[корпус]]
# [[жилище]], [[жильё]], [[жилплощадь]], [[резиденция]]
# -
# [[фирма]], [[предприятие]]
# -
# -
# [[семья]], [[династия]], [[клан]]

==== Антонимы ====
# -
# -

==== Гиперонимы ====
# [[строение]], [[постройка]]
# -
# [[учреждение]]
# -

==== Гипонимы ====
# [[барак]], [[вилла]], [[коттедж]], [[многоэтажка]], [[небоскрёб]], [[хрущоба]]
#

=== Родственные слова ===
{{родств-блок
|умласк=[[домик]]
|уничиж=[[домишко]]

*/
