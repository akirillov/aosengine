package ru.agiledev.aos.frontend.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import ru.agiledev.aos.frontend.client.AspectOrientedSearchApplication;
import ru.agiledev.aos.frontend.client.SearchService;
import ru.agiledev.aos.frontend.client.SearchServiceAsync;
import ru.agiledev.aos.frontend.shared.FieldVerifier;
import ru.agiledev.aos.frontend.shared.SearchResult;
import ru.agiledev.aos.frontend.shared.TransformationResults;

import java.util.List;

/**
 * Created by Anton Kirillov
 * Date: 8/10/11
 */
public class QueryPanel extends HorizontalPanel {

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final SearchServiceAsync searchService = GWT.create(SearchService.class);

    public QueryPanel() {
        final TextBox queryField = new TextBox();
        final Button searchButton = new Button("Поиск");
        queryField.setStyleName("queryField");
        // Focus the cursor on the name field when the app loads
        queryField.setFocus(true);
        queryField.selectAll();

        add(queryField);
        add(searchButton);

        // Create a handler for the sendButton and nameField
        class QueryHandler implements ClickHandler, KeyUpHandler {
            /**
             * Fired when the user clicks on the sendButton.
             */
            public void onClick(ClickEvent event) {
                queryServer();
            }

            /**
             * Fired when the user types in the nameField.
             */
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    queryServer();
                }
            }

            /**
             * Send the name from the nameField to the server and wait for a response.
             */
            private void queryServer() {
                // First, we validate the input.
                //errorLabel.setText("");
                String textToServer = queryField.getText();
                if (!FieldVerifier.isValidName(textToServer)) { //todo: поверхностный анализ запроса
                    //errorLabel.setText("Please enter at least four characters");
                    return;
                }

                searchService.getTransformationResults(textToServer, new AsyncCallback<TransformationResults>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        //TODO: определить тип ошибки и отобразить под строкой запроса
                    }

                    @Override
                    public void onSuccess(TransformationResults transformationResults) {
                        AspectOrientedSearchApplication.get().updateForSearchResults();
                        AspectOrientedSearchApplication.get().getResultPanel().setTransformationResults(transformationResults);

                        //todo: condition for search. depends on transformation results
                        searchService.getSearchResults(queryField.getValue(), 0, new AsyncCallback<List<SearchResult>>() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                int i = 0;
                            }

                            @Override
                            public void onSuccess(List<SearchResult> searchResults) {
                                AspectOrientedSearchApplication.get().getResultPanel().setResponse(searchResults);

                            }
                        });
                        //в TransformationResults д.б. флаг, обозначающий какого рода запрос будет проанализирован (просто запрос или улучшенный)
                        /*searchService.getSearchResults(transformationResults.getId(), 0, new AsyncCallback<List<SearchResult>>() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                int i = 0;
                                //TODO: определить тип ошибки и отобразить под строкой запроса
                            }

                            @Override
                            public void onSuccess(List<SearchResult> results) {
                                //results come already transformed to HTML by CORE module
                                AspectOrientedSearchApplication.get().getResultPanel().setResponse(results);
                            }
                        });*/

                    }
                });
            }
        }

        // Add a handler to send the name to the server
        QueryHandler handler = new QueryHandler();
        queryField.addKeyUpHandler(handler);
        searchButton.addClickHandler(handler);
    }
}
