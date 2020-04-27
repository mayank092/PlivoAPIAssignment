package APIs.archiveChannel;

import io.restassured.response.Response;
import request.RequestPOJO;
import request.TestBuilder;
import tools.PropertiesManager;
import java.util.HashMap;
import java.util.Map;

public class ArchiveChannel {
    public static Response executeArchiveChannelTestBuilderAPI(Map<String, String> testData, String channelIdToBeArchivedAtTheEnd) throws Exception {

        Map<String, String> headers = new HashMap<String, String>();
        TestBuilder archiveChannelTestBuilderObject = new TestBuilder();
        RequestPOJO archiveChannelRequestPOJO = new RequestPOJO();
        archiveChannelRequestPOJO.setChannel(channelIdToBeArchivedAtTheEnd);
        headers.put("Authorization", PropertiesManager.getProperty("Api_Token"));
        try {
            return archiveChannelTestBuilderObject.getInstance().setBaseUri(PropertiesManager.getProperty("baseURI")).setContentType("application/json").setHeader(headers).setResource(PropertiesManager.getProperty("archiveChannel")).setBody(archiveChannelRequestPOJO).build().execute();
        } catch (AssertionError error) {
            throw error;
        }
    }
}
