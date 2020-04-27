package APIs.createChannel;

import io.restassured.response.Response;
import request.RequestPOJO;
import request.TestBuilder;
import java.util.HashMap;
import java.util.Map;
import tools.PropertiesManager;

public class CreateChannel {
    public static Response executeCreateChannelTestBuilderAPI(Map<String, String> testData) throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        TestBuilder createChannelTestBuilderObject = new TestBuilder();
        RequestPOJO createChannelRequestPOJO = new RequestPOJO();
        createChannelRequestPOJO.setName(testData.get("channelName"));
        headers.put("Authorization", PropertiesManager.getProperty("Api_Token"));
        try {
            return createChannelTestBuilderObject.getInstance().setBaseUri(PropertiesManager.getProperty("baseURI")).setContentType("application/json").setHeader(headers).setResource(PropertiesManager.getProperty("createChannel")).setBody(createChannelRequestPOJO).build().execute();
        } catch (AssertionError error) {
            throw error;
        }
    }
}
