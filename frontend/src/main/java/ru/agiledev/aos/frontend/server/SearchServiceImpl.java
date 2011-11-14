package ru.agiledev.aos.frontend.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.agiledev.aos.commons.dto.SearchEntry;
import ru.agiledev.aos.core.SemanticEngine;
import ru.agiledev.aos.frontend.client.SearchService;
import ru.agiledev.aos.frontend.shared.FieldVerifier;
import ru.agiledev.aos.frontend.shared.SearchResult;
import ru.agiledev.aos.frontend.shared.TransformationResults;

import java.util.ArrayList;
import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SearchServiceImpl extends RemoteServiceServlet implements
        SearchService {

  public String greetServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);

    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   *
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }

    @Override
    public TransformationResults getTransformationResults(String query) {

        return getTR(query);//todo: CORE IMPL
    }

    @Override
    public List<SearchResult> getSearchResults(String query, int page) {
        SemanticEngine engine = new SemanticEngine();

        List<SearchEntry> results = engine.search(query, page);

        return Transformer.transformSearchResult(results);
    }

    @Override
    public List<SearchResult> getSearchResults(int transformationResultsID, int page) {
        /*SemanticEngine engine = new SemanticEngine();
        engine.search()*/
        return null;
    }

    //DEBUG DATA
    private TransformationResults getTR(String query){
        List<String> queries = new ArrayList<String>();
        queries.add("запрос 1");
        queries.add("запрос 2");
        queries.add("запрос ..");
        queries.add("запрос N");

        //String initialQuery, String transformedQuery, String firstObject, String secondObject, String thirdObject, String questionType, List<String> queries
        TransformationResults TR = new TransformationResults(query, "трансформированный запрос", "Объект1", "Объект2", "", "Тип вопроса", queries);
        TR.setId(1);
        return TR;
    }

    /*private List<SearchResult> getDummySearchResults(){

        List<SearchResult> results = new ArrayList<SearchResult>();

        results.add(new SearchResult("Понедельник — Википедия ", "http://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%BD%D0%B5%D0%B4%D0%B5%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA", "Понеде́льник — день недели между воскресеньем и вторником. У древних римлян и у северогерманских племён был посвящён Луне (фр. lundi, нем. Mon(d)tag = день луны). в русском языке слово «понедельник» образовалось от выражения «после недели»."));
        results.add(new SearchResult("Деловая газета Тольятти «Понедельник» | Реклама в Тольятти ", "http://www.ponedelnik.info/", "© 2006—2011 ООО «Понедельник.ИНФО». Свидетельство о регистрации СМИ Эл № ФС77-30548 от 07.12.2005 г."));
        results.add(new SearchResult("Поздравляю с Понедельником", "http://priroda.inc.ru/blog/dei/ponedelnik.html", "Понедельник - День Луны! Астрологические параметры понедельника. Узнай, что предпринять в понедельник и как правильно его провести. "));
        results.add(new SearchResult("Прожектора и светильники / Компания - «Понедельник»", "http://www.ponedelnic.ru/proz_svet", "Copyright © 2004 - 2010, Компания «Понедельник» Москва, 111524, ул. Электродная, д. 2, стр. 33 | Тел.: 258-91-91 | e-mail: office@ponedelnic.ru Вывески, светодиодные лампы, световые..."));
        results.add(new SearchResult("ВЕЛИКИЙ ПОНЕДЕЛЬНИК - Древо", "http://drevo-info.ru/articles/12650.html", "В Великий понедельник Церковью воспоминается ветхозаветный патриарх Иосиф Прекрасный, из зависти проданный братьями в Египет, прообразовавший страдания Спасителя (Быт 37 и далее)."));
        results.add(new SearchResult("Как сделать так, чтобы понедельник перестал быть тяжелым днем?", "http://netler.ru/articles/monday.htm", "Нужно сломать традицию считать началом недели понедельник и установить первым днем недели воскресенье. В соответствии с этим печатать все календари."));
        results.add(new SearchResult("ПОНЕДЕЛЬНИК", "http://dic.academic.ru/dic.nsf/enc_colier/5614/", "Супер хохма. Лучшие номера из репертуара театров миниатюр Люкс, Понедельник, Калейдоскоп, БИС [1998г., VHSRip] Скачать torrent файл (Развлекательные телепередачи и шоу, приколы и юмор)"));
        results.add(new SearchResult("понедельник — Викисловарь", "http://ru.wiktionary.org/wiki/%D0%BF%D0%BE%D0%BD%D0%B5%D0%B4%D0%B5%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA", "понеде́льниках. по-не-де́ль-ник. Существительное, неодушевлённое, мужской род, 2-е склонение (тип склонения 3a по классификации А. Зализняка). Корень: -понедельник-. [править] Произношение. "));
        results.add(new SearchResult("Виктор Владимирович Понедельник — пресс-портрет", "http://news.yandex.ru/people/ponedel1nik_viktor.html", "Дата рождения: 22.05.1937.  Сборная СССР, сотрудник. 13 апреля 2011 Чемпион Европы-1960 в составе сборной СССР Виктор Понедельник поделился ожиданиями от ответного матча между \"Спартаком\" и \"Порту\" в рамках Лиги Европы."));
        results.add(new SearchResult("Сборная России по футболу. Понедельник Виктор Владимирович.", "http://www.rusteam.permian.ru/players/ponedelnik.html", "Ребята беззлобно подшучивали: \"А где Понедельник?\" - \"До вторника Бакалавр запер его у Паулюса\". Хотя мне самому было не до шуток: до последнего момента сомневался, что возьмут во Францию."));

        return results;
    }*/
}
