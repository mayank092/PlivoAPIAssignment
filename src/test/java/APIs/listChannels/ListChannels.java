package APIs.listChannels;

import io.restassured.response.Response;
import request.RequestPOJO;
import request.TestBuilder;
import tools.PropertiesManager;
import java.util.HashMap;
import java.util.Map;

public class ListChannels {
    public static Response executeListChannelsTestBuilderAPI(Map<String, String> testData) throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        TestBuilder listChannelsTestBuilderObject = new TestBuilder();
        RequestPOJO listChannelsRequestPOJO = new RequestPOJO();
        listChannelsRequestPOJO.setLimit(Integer.parseInt(testData.get("ChannelsLimit")));
        headers.put("Authorization", PropertiesManager.getProperty("Api_Token"));
        try {
            return listChannelsTestBuilderObject.getInstance().setBaseUri(PropertiesManager.getProperty("baseURI")).setContentType("application/json").setHeader(headers).setResource(PropertiesManager.getProperty("listChannels")).setBody(listChannelsRequestPOJO).build().execute();
        } catch (AssertionError error) {
            throw error;
        }
    }
}
