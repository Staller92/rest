package com.api.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredRequestFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(RestAssuredRequestFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        Response response = filterContext.next(requestSpec, responseSpec);
        if (response.statusCode() != 200) {
            LOG.error(requestSpec.getMethod() + " " + requestSpec.getURI() + " => " +
                    response.getStatusCode() + " " + response.getStatusLine());
        }
        LOG.info("REQUEST: " + requestSpec.getMethod() + " " + requestSpec.getURI() + " Request Body =>" + requestSpec.getBody());
        LOG.info("RESPONSE: " + response.getStatusCode() + " " + response.getStatusLine() + " Response Body => \n " + response.getBody().prettyPrint());
        return response;
    }
}
