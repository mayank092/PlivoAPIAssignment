package APIs.renameChannel;

import io.restassured.response.Response;
import request.RequestPOJO;
import request.TestBuilder;
import tools.PropertiesManager;
import java.util.HashMap;
import java.util.Map;

public class RenameChannel {
    public static Response executeRenameChannelTestBuilderAPI(Map<String, String> testData, String channelIdToBeRenamed) throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        TestBuilder renameChannelTestBuilderObject = new TestBuilder();
        RequestPOJO renameChannelRequestPOJO = new RequestPOJO();
        renameChannelRequestPOJO.setChannel(channelIdToBeRenamed);
        renameChannelRequestPOJO.setName(testData.get("newChannelName"));
        headers.put("Authorization", PropertiesManager.getProperty("Api_Token"));
        try {
            return renameChannelTestBuilderObject.getInstance().setBaseUri(PropertiesManager.getProperty("baseURI")).setContentType("application/json").setHeader(headers).setResource(PropertiesManager.getProperty("renameChannel")).setBody(renameChannelRequestPOJO).build().execute();
        } catch (AssertionError error) {
            throw error;
        }
    }
}
