import io.restassured.response.Response;
import listChannels.ListChannelsResponsePOJO;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import request.RequestPOJO;
import request.TestBuilder;
import response.ResponsePOJO;
import tools.GenericDataProvider;
import tools.PropertiesManager;
import tools.CSVAnnotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scenario {
    private static final Logger LOGGER = Logger.getLogger(Scenario.class);

    @Test(dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class, description = " ", groups = {"CreateChannelTest"})
    @CSVAnnotation.CSVFileParameters(delimiter = "#", path = "test-data/Scenario.csv")
    public void APIAutomationScenario(int rowNo, Map<String, String> testData) throws Exception {
        try {

            String channelName = Scenario.getRandomAlphaNumericString(30);
            String newChannelName = Scenario.getRandomAlphaNumericString(30);
            testData.put("channelName", channelName);
            testData.put("newChannelName", newChannelName);

            // Executing CreateChannel API
            Response createChannelResponse = executeCreateChannelTestBuilderAPI(testData);
            //Deserializing Response into ResponsePOJO class
            ResponsePOJO createChannelResponsePOJOObject = createChannelResponse.as(ResponsePOJO.class);
            //doing assertion on status code
            if (createChannelResponse.statusCode() == HttpStatus.SC_OK && createChannelResponsePOJOObject.getOk().equals(true)) {

                LOGGER.info("New Channel successfully created. Name:" + createChannelResponsePOJOObject.getChannel().getName() + ", Id: " + createChannelResponsePOJOObject.getChannel().getId());

                //Deliberately commented below working piece of JoinChannelResponse code because channel created by a user is bydefault joined by the same user
                // and execution of JoinChannel API gives error if called by the same user.

/*                Response joinChannelResponse = executeJoinChannelTestBuilderAPI(testData);
                ResponsePOJO joinChannelResponsePOJOObject = joinChannelResponse.as(ResponsePOJO.class);
                if (joinChannelResponse.statusCode() == HttpStatus.SC_OK && joinChannelResponsePOJOObject.getOk().equals(true)) {

                    LOGGER.info("Newly created Channel joined by Users:" + joinChannelResponsePOJOObject.getChannel().getMembers());
*/                  String channelIdToBeRenamed = createChannelResponsePOJOObject.getChannel().getId();
                    Response renameChannelResponse = executeRenameChannelTestBuilderAPI(testData, channelIdToBeRenamed);
                    ResponsePOJO renameChannelResponsePOJOObject = renameChannelResponse.as(ResponsePOJO.class);
                    String channelIdToBeArchivedAtTheEnd = renameChannelResponsePOJOObject.getChannel().getId();
                    if (renameChannelResponse.statusCode() == HttpStatus.SC_OK && renameChannelResponsePOJOObject.getOk().equals(true)) {

                        LOGGER.info("Channel: " + channelName + " succesfully renamed to " + newChannelName);
                        Response listChannelsResponse = executeListChannelsTestBuilderAPI(testData);
                        ListChannelsResponsePOJO listChannelsResponsePOJOObject = listChannelsResponse.as(ListChannelsResponsePOJO.class);
                        ArrayList<String> channelNames = new ArrayList<String>();
                        for (int i = 0; i < listChannelsResponsePOJOObject.getChannels().size(); i++) {
                            channelNames.add(listChannelsResponsePOJOObject.getChannels().get(i).getName());
                        }
                        if (listChannelsResponse.statusCode() == HttpStatus.SC_OK && listChannelsResponsePOJOObject.getOk().equals(true) && channelNames.contains(newChannelName.toLowerCase()) && !channelNames.contains(channelName.toLowerCase())) {

                            LOGGER.info("All expected channels were found out in the list");
                            Response archiveChannelResponse = executeArchiveChannelTestBuilderAPI(testData, channelIdToBeArchivedAtTheEnd);
                            ResponsePOJO archiveChannelResponsePOJOObject = archiveChannelResponse.as(ResponsePOJO.class);
                            if (archiveChannelResponse.statusCode() == HttpStatus.SC_OK && archiveChannelResponsePOJOObject.getOk().equals(true)) {

                                 LOGGER.info("End to end Scenario Automated");
                            }
                            else{
                                LOGGER.error("Invalid Response from archiveChannelAPI. " + archiveChannelResponsePOJOObject.getError());
                            }
                        }
                        else
                            LOGGER.error("Invalid Response from listChannelsAPI. " + listChannelsResponsePOJOObject.getError());
                    }
                    else
                        LOGGER.error("Invalid Response from renameChannelAPI. " + renameChannelResponsePOJOObject.getError());
 /*               }
                else
                    LOGGER.error("Invalid Response from joinChannelAPI : " + joinChannelResponsePOJOObject.getError());
*/            } else
                LOGGER.error("Invalid Response from createChannelAPI. " + createChannelResponsePOJOObject.getError() + " " + createChannelResponsePOJOObject.getDetail());
        } catch (Exception e) {
            LOGGER.info(e);
            Assert.fail("Scenario has failed..");
        }
    }

    public Response executeCreateChannelTestBuilderAPI(Map<String, String> testData) throws Exception {

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

    public Response executeJoinChannelTestBuilderAPI(Map<String, String> testData) throws Exception {

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

    public Response executeRenameChannelTestBuilderAPI(Map<String, String> testData, String channelIdToBeRenamed) throws Exception {

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

    public Response executeListChannelsTestBuilderAPI(Map<String, String> testData) throws Exception {

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

    public Response executeArchiveChannelTestBuilderAPI(Map<String, String> testData, String channelIdToBeArchivedAtTheEnd) throws Exception {

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

    public static String getRandomAlphaNumericString(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
