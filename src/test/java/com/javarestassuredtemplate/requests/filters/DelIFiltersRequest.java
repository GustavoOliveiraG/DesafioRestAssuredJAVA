package com.javarestassuredtemplate.requests.filters;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class DelIFiltersRequest extends RequestRestBase {
    public DelIFiltersRequest(String filter_id){
        requestService = "/api/rest/filters/" + filter_id;
        method = Method.DELETE;
    }

}
