package ru.agiledev.aos.frontend.client.panel;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.agiledev.aos.frontend.shared.SearchResult;
import ru.agiledev.aos.frontend.shared.TransformationResults;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Anton Kirillov
 * Date: 8/10/11
 */
public class ResultPanel extends VerticalPanel{

    private TransformResultsPanel transformResultsPanel;
    private SearchResultsPanel searchResultsPanel;
    private HorizontalPanel yandexBanner;
    private HTML yResults = new HTML();


    public ResultPanel() {
        setStyleName("resultsPanel");

        yResults.setStyleName("yandexStats");
        yandexBanner = new HorizontalPanel();
        yandexBanner.setStyleName("yandexBanner");
        Image banner = new Image("img/yandex.png");
        banner.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Window.open("http://www.yandex.ru","target","");
            }
        });
        banner.setStyleName("yandexLogo");

        yandexBanner.add(banner);
        yandexBanner.add(yResults);
        yResults.setHTML("Поиск реализован с использованием <a href=\"http://xml.yandex.ru/\" target=\"_blank\">Яндекс.XML</a>");


        transformResultsPanel = new TransformResultsPanel();
        searchResultsPanel = new SearchResultsPanel();

        add(transformResultsPanel);
        add(yandexBanner);
        add(searchResultsPanel);
    }

    public void setTransformationResults(TransformationResults results){
        transformResultsPanel.setResults(results);
    }

    public void setResponse(List<SearchResult> results){
        searchResultsPanel.setContent(results);
    }



    class TransformResultsPanel extends VerticalPanel{
        /*
         _________________________________
        |Объекты интереса, тип запроса    |
        |Какой запрос в итоге получился   |
        |_________________________________|
        |Сгенерированные запросы          |
        |_________________________________|

         */
        HorizontalPanel annotationPanel = new HorizontalPanel();
        HorizontalPanel objectsAndTypePanel = new HorizontalPanel();
        HorizontalPanel resultQueryPanel = new HorizontalPanel();
        HorizontalPanel generatedQueriesPanel = new HorizontalPanel();

        //Labels
        HTML object1, object2, object3, queryType, resultQuery, generatedQueries;
        public TransformResultsPanel(){
            setStyleName("transformPanel");

            //Annotation
            annotationPanel.add(new HTML("<b><u>Статистика по трансформации запроса</u></b>"));
            annotationPanel.getElement().getStyle().setPaddingBottom(5, Style.Unit.PX);
            add(annotationPanel);

            //Objects and type
            objectsAndTypePanel.add(new HTML("<b>Объекты интереса:</b>&nbsp"));
            object1 = new HTML();
            object2 = new HTML();
            object3 = new HTML();
            objectsAndTypePanel.add(object1);
            objectsAndTypePanel.add(object2);
            objectsAndTypePanel.add(object3);

            objectsAndTypePanel.add(new HTML("&nbsp&nbsp&nbsp<b>Тип запроса:</b>&nbsp"));
            queryType = new HTML();
            objectsAndTypePanel.add(queryType);
            add(objectsAndTypePanel);

            //Result query
            resultQueryPanel.add(new HTML("<b>Выделенный запрос:</b>&nbsp"));
            resultQuery = new HTML();
            resultQueryPanel.add(resultQuery);
            add(resultQueryPanel);

            //Generated queries
            generatedQueriesPanel.add(new HTML("<b>Сгенерированные запросы:</b>&nbsp"));
            generatedQueries = new HTML();
            generatedQueriesPanel.add(generatedQueries);
            add(generatedQueriesPanel);
        }

        public void setResults(TransformationResults tResults){
            //todo: checkResults()
            object1.setHTML(tResults.getFirstObject());
            object2.setHTML(tResults.getSecondObject());
            object3.setHTML(tResults.getThirdObject());
            queryType.setHTML(tResults.getQuestionType());
            resultQuery.setHTML(tResults.getTransformedQuery());

            List<String> queries = tResults.getQueries();

            if(queries!=null){
                String resQueries = "";
                for (Iterator<String> iterator = queries.iterator(); iterator.hasNext(); ) {
                    String next = iterator.next();
                    resQueries+=next;

                    if(iterator.hasNext()){
                        resQueries+=", ";
                    }
                }
                generatedQueries.setHTML(resQueries);
            }
        }
    }

    class SearchResultsPanel extends VerticalPanel{
        /*HTML content = new HTML();
        public SearchResultsPanel(){
            add(content);
        }*/

        public void setContent(List<SearchResult> results){
            if(getWidgetCount()>0){
                for(int i=0; i< getWidgetCount(); i++){
                    ((ResultEntryPanel)getWidget(i)).setResult(results.get(i));
                }
            } else {

                for(SearchResult result: results){
                    add(new ResultEntryPanel(result));
                }
            }
        }


    }
}
