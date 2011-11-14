package ru.agiledev.aos.morph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DIY wrapper for Iliya Segalovitch's mystem stemmer
 * @see <http://company.yandex.ru/technology/mystem/help.xml>
 * Created by Anton Kirillov
 * Date: 9/29/11
 */
public class MyStemStemmer {

    public String stem(String input) throws Exception, InterruptedException {
        return getStemmerOutput(input, null);
    }

    public String getBaseFormProperties(String input) throws Exception, InterruptedException {
        String result = getStemmerOutput(input, new String[] {"-i"});
        return clearProperties(result, input);
    }

    public String clearProperties(String input, String word){
        //here we move to lower case because stemmer always pruduces lowercase output (except part of speech sign)
        if("".equals(input)||input.toString().contains("??")||(input.lastIndexOf(word.toLowerCase())<=input.indexOf(word))) return null;   //todo: switch to exception?

        String pre = null;

        try{
            pre = input.substring(input.lastIndexOf(word.toLowerCase().replaceAll("ё","е"))).replace(word.toLowerCase().replaceAll("ё","е"), "");
        } catch (RuntimeException e){
            e.getCause();
        }
        String tmp = pre.contains("|")?pre.substring(0, pre.indexOf("|")):pre;

        String[] symbols = tmp.split("");

        StringBuffer result = new StringBuffer();

        for(String symbol:symbols){

            if(symbol.equals("=")||symbol.equals(",")){
                result.append(" ");
                continue;
            }

            if(symbol.equals("{")||symbol.equals("}")){
                continue;
            } else {
                result.append(symbol);
            }
        }

        return result.toString().trim().replaceAll("\\s+", ",");
    }

    private String getStemmerOutput(String input, String[] flags) throws Exception {
        String path = "/tmp/";
        String filename1 = System.currentTimeMillis()+input.hashCode()+"_source";
        String filename2 = System.currentTimeMillis()+input.hashCode()+"_result";

        //Input file creation
        File file = new File(path+filename1);
        FileWriter fstream = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(input);

        out.close();
        fstream.close();
        List<String> command = new ArrayList<String>();

        if("".equals(System.getenv("MYSTEM_HOME"))||(System.getenv("MYSTEM_HOME")==null)){
            throw new Exception("You should specify MYSTEM_HOME environment variable"); //TODO:customize exception
        }
        command.add(System.getenv("MYSTEM_HOME")+"mystem"); //ru.agiledev.aos.morph.runnable program //TODO: adopt to auto 32/64 bit switching
        command.add("-eutf-8");           //default encoding is UTF8

        if((flags!=null)&&(flags.length>0)){
            for(String flag:flags){
                command.add(flag);
            }
        }

        command.add(path+filename1);
        command.add(path+filename2);

        Process pb = new ProcessBuilder(command).start();

        pb.waitFor();

        file.delete();
        file = new File(path+filename2);

        FileInputStream in = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuffer result = new StringBuffer();
        String tmp;

        while ((tmp = br.readLine()) != null)
        {
            result.append(tmp);
        }
        br.close();
        in.close();

        file.delete();

        pb.destroy();

        return result.toString();
    }
}
/*


============================== Mystem characteristics ==============================
Часть речи
A 	    прилагательное
ADV 	наречие
ADVPRO 	местоименное наречие
ANUM 	порядковое числительное
APRO 	местоименное прилагательное
COM 	часть композита (первая часть сложных слов)
CONJ 	союз
INTJ 	междометие
NUM 	числительное
PART 	частица
PR 	    предлог
S 	    существительное
SPRO 	местоимение
V 	    глагол


Время (глаголов)
наст 	настоящее
непрош 	непрошедшее
прош 	прошедшее


Падеж
им 	    именительный
род 	родительный
дат 	дательный
вин 	винительный
твор 	творительный
пр 	    предложный
парт 	партитив (второй родительный)
местн 	местный (второй предложный)
зват 	звательный


Число
ед 	    единственное
мн 	    множественное

Репрезентация и наклонение глагола
деепр 	деепричастие
инф 	инфинитив
прич 	причастие
изъяв 	изьявительное наклонение
пов 	повелительное наклонение


Форма прилагательных
кр 	    краткая
полн 	полная
притяж 	притяжательная

Степень сравнения
прев 	превосходная
срав 	сравнительная

Лицо глагола
1-л 	1-е лицо
2-л 	2-е лицо
3-л 	3-е лицо

Род
жен 	женский
муж 	мужской
сред 	средний

Вид (аспект) глагола
сов 	совершенный
несов 	несовершенный

Залог
действ 	действительный
страд 	страдательный

Одушевленность
од 	    одушевленное
неод 	неодушевленное

Переходность
пе 	    переходный глагол
нп 	    непереходный глагол

Прочие обозначения
вводн 	вводное слово
гео 	географическое название
затр 	образование формы затруднено
имя 	имя собственное
искаж 	искаженная форма
мж 	    общая форма мужского и женского рода
обсц 	обсценная лексика
отч 	отчество
прдк 	предикатив
разг 	разговорная форма
редк 	редко встречающееся слово
сокр 	сокращение
устар 	устаревшая форма
фам 	фамилия





====================================== MCR ======================================

Для существительного
Vid 	Описание
0	Все формы одинаковы
1	Ед.ч. И.П. (единственное число, иментиельный падеж)
2	Ед.ч. Р.П.
3	Ед.ч. Д.П.
4	Ед.ч. В.П.
5	Ед.ч. Т.П.
6	Ед.ч. П.П.
7	Мн.ч. И.П. (множественное число, именительный падеж)
8	Мн.ч. Р.П.
9	Мн.ч. Д.П.
10	Мн.ч. В.П.
11	Мн.ч. Т.П.
12	Мн.ч. П.П.
13	только мн. ч. (все формы одинаковы)


Для прилагательных и схожих частей
Vid 	Описание
1	И.П. М.р ед.ч.од/неод (Иментельный падеж, мужск. род, ед. число, одушевленное и неодушевленное)
2	И.П. С.р ед.ч.од/неод
3	Р.П. М/С.р ед.ч.од/неод
4	Д.П. М/С.р ед.ч.од/неод
5	В.П. М.р ед.ч.неод.
6	В.П. М.р ед.ч.одуш.
7	В.П. С.р ед.ч.од/неод
8	Т.П. М/С.р ед.ч.од/неод
9	П.П. М/С ед.ч.од/неод
10	И. Ж.р ед.ч.од/неод
11	Р,Д,П и Ж ед.ч.од/неод
12	В. Ж.р ед.ч.од/неод
13	Т. Ж.р ед.ч.од/неод
14	И. Мн.ч. од/неод
15	Р.Мн.ч. од/неод
16	Д. Мн.ч. од/неод
17	В. Мн.ч. неод.
18	В. Мн.ч. од.
19	Т. Мн.ч. од/неод
20	Т. Ж.р еч.од/неод
21	Кратк.форма М.р
22	Кратк.форма Ж.р
23	Кратк.форма С.р
24	Кратк.форма Мн. всех родов
25	Сравнительная степень
26	Сравнительная степень (параллельный вариант ее/ей)


Числительное
Vid 	Описание
0	все формы одинаковы
1	И.П.
2	Р.П.
3	Д.П.
4	В.П.
5	В.П. одушевленное
6	Т.П.
7	П.П.
8	Т.П. (параллельн)
9	М/С род И.П.
10	М/С род Р.П.
11	М/С род Д.П.
12	М/С род В.П.
13	М/С род В.П. одушевл
14	М/С род Т.П.
15	М/С род П.П.
16	Ж род И.П.
17	Ж род Р.П.
18	Ж род Д.П.
19	Ж род В.П.
20	Ж род В.П. одушевл
21	Ж род Т.П.
22	Ж род П.П.


Глагол
Vid 	Описание
1	ИнФинитив
2	Н.вр Ед.ч 1 лицо
3	Н.вр Ед.ч 2 лицо.
4	Н.вр Ед.ч 3 лицо
5	Н.вр Мн.ч 1 лицо
6	Н.вр Мн.ч 2 лицо
7	Н.вр Мн.ч 3 лицо
8	Пр.вр Ед.всех лиц М род
9	Пр.вр Ед.всех лиц Ж род
10	Пр.вр Ед.всех лиц С род
11	Пр.вр Мн.всех лиц родов
12	Повел. 2 лицо Ед.
13	Повел. 2 лицо Мн.
14	Повел. 1 лицо Мн.(к одному)
15	Повел. 1 лицо Мн.(ко многим)
16	Буд.вр Ед.ч 1 лицо
17	Буд.вр Ед.ч 2 лицо
18	Буд.вр Ед.ч 3 лицо
19	Буд.вр Мн.ч 1 лицо
20	Буд.вр Мн.ч 2 лицо
21	Буд.вр Мн.ч 3 лицо
25	Н/Буд. вр Ед.ч 1 лицо
26	Н/Буд. вр Ед.ч 2 лицо
27	Н/Буд. вр Ед.ч 3 лицо
28	Н/Буд. вр Мн.ч 1 лицо
29	Н/Буд. вр Мн.ч 2 лицо
30	Н/Буд. вр Мн.ч 3 лицо


Деепричастие
Лингвисты как правило не различают времени у прилагательного, но раз такая инфрмация была введена в словаре Зализнякак, то 2 характеристики имеют место
Vid 	Описание
1	Настоящего времени
2	Прошедшего времен

*/
