package APIs.joinChannel;

import io.restassured.response.Response;
import request.RequestPOJO;
import request.TestBuilder;
import tools.PropertiesManager;
import java.util.HashMap;
import java.util.Map;

public class JoinChannel {
    public static Response executeJoinChannelTestBuilderAPI(Map<String, String> testData) throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        TestBuilder joinChannelTestBuilderObject = new TestBuilder();
        RequestPOJO joinChannelRequestPOJO = new RequestPOJO();
        joinChannelRequestPOJO.setName("#" + testData.get("channelName"));
        headers.put("Authorization", PropertiesManager.getProperty("Api_Token"));
        try {
            return joinChannelTestBuilderObject.getInstance().setBaseUri(PropertiesManager.getProperty("baseURI")).setContentType("application/json").setHeader(headers).setResource(PropertiesManager.getProperty("joinChannel")).setBody(joinChannelRequestPOJO).build().execute();
        } catch (AssertionError error) {
            throw error;
        }
    }
}
