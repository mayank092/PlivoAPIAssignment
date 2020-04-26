package request;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.*;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class TestBuilder {

    private String baseUri;
    private String resource;
    private String contentType;
    private Map<String, String> pathParameterMap=new HashMap<String, String>();
    private Map<String, String> queryParameterMap=new HashMap<String, String>();
    private Map<String, String> headerMap=new HashMap<String, String>();
    private RequestPOJO bodyObj;
    RequestSpecification requestSpecification;

    //Singleton Pattern Implementation
    private static TestBuilder testBuilderObj;
    public static TestBuilder getInstance()
    {
        if (testBuilderObj!=null)
            return testBuilderObj;
        else
            return testBuilderObj=new TestBuilder();
    }

    public TestBuilder setContentType(String contentType)
    {
        testBuilderObj.contentType=contentType;
        return testBuilderObj;
    }

    //Builder Pattern implementation
    public TestBuilder setBaseUri(String baseUri) {
        testBuilderObj.baseUri = baseUri;
        return testBuilderObj;
    }

    public TestBuilder setPathParameters(Map<String, String> pathParameterMap) {
        testBuilderObj.pathParameterMap = pathParameterMap;
        return testBuilderObj;
    }

    public TestBuilder setHeader(Map<String, String> headerMap) {
        testBuilderObj.headerMap = headerMap;
        return testBuilderObj;
    }

    public TestBuilder setQueryParameterMap(Map<String, String> queryParameterMap) {
        testBuilderObj.queryParameterMap = queryParameterMap;
        return testBuilderObj;
    }

    public TestBuilder setResource(String resource) {
        testBuilderObj.resource = resource;
        return testBuilderObj;

    }

    public TestBuilder setBody(RequestPOJO obj)
    {
        testBuilderObj.bodyObj = obj;
        return testBuilderObj;
    }

    public TestBuilder build() throws Exception
    {
        if(baseUri==null)
        {
            throw new Exception("baseUri is null");
        }
        RestAssured.baseURI= baseUri;
        requestSpecification=
                given()
                        .pathParams(pathParameterMap)
                        .headers(headerMap)
                        .queryParams(queryParameterMap)
                        .contentType(contentType)
                        .body(bodyObj);

        return testBuilderObj;
    }

    public Response execute()
    {
        return requestSpecification
                .post(resource);
    }
}
