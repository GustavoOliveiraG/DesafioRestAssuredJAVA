package com.javarestassuredtemplate.requests.filters;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetFiltersRequest extends RequestRestBase {
    public GetFiltersRequest(){
        requestService = "/api/rest/filters";
        method = Method.GET;
    }

}
