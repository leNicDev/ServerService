package de.lenic.serverservice.spigot.server.resources.filters;

import spark.Filter;
import spark.Request;
import spark.Response;

public class ContentTypeFilter implements Filter {

    private String contentType;


    public ContentTypeFilter(String contentType) {
        this.contentType = contentType;
    }


    @Override
    public void handle(Request request, Response response) throws Exception {
        response.type(contentType);
    }

}
