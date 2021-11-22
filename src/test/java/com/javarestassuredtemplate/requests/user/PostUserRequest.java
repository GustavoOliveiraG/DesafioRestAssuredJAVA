package com.javarestassuredtemplate.requests.user;

import com.javarestassuredtemplate.bases.RequestRestBase;
import com.javarestassuredtemplate.utils.GeneralUtils;
import io.restassured.http.Method;

public class PostUserRequest extends RequestRestBase {
    public PostUserRequest(){
        requestService = "/api/rest/users/";
        method = Method.POST;
    }

    public void setJsonBodyUsingJsonFile(
                            String userName,
                            String password,
                            String nameReal,
                            String email,
                            int accessId,
                            int enableId,
                            int protectedId){
        jsonBody = GeneralUtils.readFileToAString("src/test/java/com/javarestassuredtemplate/jsons/user/PostUserJson.json").
                                replace("$userName", userName).
                                replace("$password", password).
                                replace("$nameReal", nameReal ).
                                replace("$email", email ).
                                replace("$accessId", String.valueOf(accessId)).
                                replace("$enableId", String.valueOf(enableId)).
                                replace("$protectedId", String.valueOf(protectedId));
    }


}
