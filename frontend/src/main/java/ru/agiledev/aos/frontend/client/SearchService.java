package ru.agiledev.aos.frontend.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.agiledev.aos.frontend.shared.SearchResult;
import ru.agiledev.aos.frontend.shared.TransformationResults;

import java.util.List;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("search")
public interface SearchService extends RemoteService {
    TransformationResults getTransformationResults(String query);

    List<SearchResult> getSearchResults(String query, int page);
    List<SearchResult> getSearchResults(int transformationResultsID, int page);
}
