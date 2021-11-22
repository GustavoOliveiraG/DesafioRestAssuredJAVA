package com.javarestassuredtemplate.dbsteps;

import com.javarestassuredtemplate.utils.DBUtils;
import com.javarestassuredtemplate.utils.GeneralUtils;

import java.util.ArrayList;


public class FiltersDBSteps {
    private static String queriesPath = System.getProperty("user.dir")+"/src/test/java/com/javarestassuredtemplate/queries/";

    public static void apagarFiltros(){
        String query = GeneralUtils.readFileToAString(queriesPath + "deleteFiltersQuery.sql");
        DBUtils.executeQuery(query);
    }

    public static void cadastrarFiltros(){
        String query = GeneralUtils.readFileToAString(queriesPath + "insertFiltersQuery.sql");
        DBUtils.executeQuery(query);
    }

}