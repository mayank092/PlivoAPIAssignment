import APIs.archiveChannel.ArchiveChannel;
import APIs.createChannel.CreateChannel;
import APIs.joinChannel.JoinChannel;
import APIs.listChannels.ListChannels;
import APIs.renameChannel.RenameChannel;
import io.restassured.response.Response;
import listChannels.ListChannelsResponsePOJO;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import response.ResponsePOJO;
import tools.GenericDataProvider;
import tools.CSVAnnotation;
import tools.PropertiesManager;
import tools.Utilities;
import tools.exceptions.InvalidAuthorizationTokenException;
import java.util.ArrayList;
import java.util.Map;

/*
 * Used rest assured here so designing according to rest assured request
*/
public class SlackChannel_TestCases {

    @BeforeTest
    public void before_test()
    {
        if(PropertiesManager.getProperty("Api_Token").contains("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx") || PropertiesManager.getProperty("Api_Token").isEmpty() || PropertiesManager.getProperty("Api_Token") == null)
            throw new InvalidAuthorizationTokenException("Api_Token in testsettings.properties file is either invalid or blank. Kindly generate a valid one and save it in testsettings.properties file with prefix as Bearer<space>token");
    }

    private static final Logger LOGGER = Logger.getLogger(SlackChannel_TestCases.class);


    @Test(dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class, description = "This is the Complete end-to-end scenario Automation mentioned in the Assignment. ", groups = {"Scenario"})
    @CSVAnnotation.CSVFileParameters(delimiter = "#", path = "test-data/Scenario.csv")
    public void APIAutomationScenario(int rowNo, Map<String, String> testData) throws Exception {
        try {

            String channelName_RandomString = Utilities.getRandomAlphaNumericString(30);
            String newChannelName_RandomString = Utilities.getRandomAlphaNumericString(30);
            testData.put("channelName", channelName_RandomString);
            testData.put("newChannelName", newChannelName_RandomString);

            System.out.println("Executing createChannelAPI..");
            Response createChannelResponse = CreateChannel.executeCreateChannelTestBuilderAPI(testData);
            ResponsePOJO createChannelResponsePOJOObject = createChannelResponse.as(ResponsePOJO.class);
            System.out.println("Succesfully Desearilized createChannelAPI response into Response POJO");
            if (createChannelResponse.statusCode() == HttpStatus.SC_OK && createChannelResponsePOJOObject.getOk().equals(true)) {

                System.out.println("New Channel successfully created. Name:" + createChannelResponsePOJOObject.getChannel().getName() + ", Id: " + createChannelResponsePOJOObject.getChannel().getId());
                System.out.println("Now Executing joinChannelAPI..");
                Response joinChannelResponse = JoinChannel.executeJoinChannelTestBuilderAPI(testData);
                ResponsePOJO joinChannelResponsePOJOObject = joinChannelResponse.as(ResponsePOJO.class);
                System.out.println("Succesfully Desearilized joinChannelAPI response into Response POJO");
                if (joinChannelResponse.statusCode() == HttpStatus.SC_OK && joinChannelResponsePOJOObject.getOk().equals(true) && joinChannelResponsePOJOObject.getAlready_In_Channel().equals(true)) {

                    System.out.println("Newly created Channel joined by Users:" + joinChannelResponsePOJOObject.getChannel().getMembers());
                    String channelIdToBeRenamed = createChannelResponsePOJOObject.getChannel().getId();
                    System.out.println("Now Executing renameChannelAPI..");
                    Response renameChannelResponse = RenameChannel.executeRenameChannelTestBuilderAPI(testData, channelIdToBeRenamed);
                    ResponsePOJO renameChannelResponsePOJOObject = renameChannelResponse.as(ResponsePOJO.class);
                    System.out.println("Succesfully Desearilized renameChannelAPI response into Response POJO");
                    String channelIdToBeArchivedAtTheEnd = renameChannelResponsePOJOObject.getChannel().getId();
                    if (renameChannelResponse.statusCode() == HttpStatus.SC_OK && renameChannelResponsePOJOObject.getOk().equals(true)) {

                        System.out.println("Channel: " + channelName_RandomString + " succesfully renamed to " + newChannelName_RandomString);
                        System.out.println("Now Executing executeListChannelsAPI..");
                        Response listChannelsResponse = ListChannels.executeListChannelsTestBuilderAPI(testData);
                        ListChannelsResponsePOJO listChannelsResponsePOJOObject = listChannelsResponse.as(ListChannelsResponsePOJO.class);
                        System.out.println("Succesfully Desearilized listChannelsAPI response into Response POJO");
                        ArrayList<String> channelNames = new ArrayList<String>();
                        for (int i = 0; i < listChannelsResponsePOJOObject.getChannels().size(); i++) {
                            channelNames.add(listChannelsResponsePOJOObject.getChannels().get(i).getName());
                        }
                        if (listChannelsResponse.statusCode() == HttpStatus.SC_OK && listChannelsResponsePOJOObject.getOk().equals(true) && channelNames.contains(newChannelName_RandomString.toLowerCase()) && !channelNames.contains(channelName_RandomString.toLowerCase())) {

                            System.out.println("All expected channels were found out in the list");
                            System.out.println("Now Executing archiveChannelAPI..");
                            Response archiveChannelResponse = ArchiveChannel.executeArchiveChannelTestBuilderAPI(testData, channelIdToBeArchivedAtTheEnd);
                            ResponsePOJO archiveChannelResponsePOJOObject = archiveChannelResponse.as(ResponsePOJO.class);
                            System.out.println("Succesfully Desearilized archiveChannelAPI response into Response POJO");
                            if (archiveChannelResponse.statusCode() == HttpStatus.SC_OK && archiveChannelResponsePOJOObject.getOk().equals(true)) {
                                System.out.println("End to end Scenario Automated");
                            }
                            else{
                                Assert.fail("Invalid Response from archiveChannelAPI. " + archiveChannelResponsePOJOObject.getError());
                            }
                        }
                        else
                            Assert.fail("Invalid Response from listChannelsAPI. " + listChannelsResponsePOJOObject.getError());
                    }
                    else
                        Assert.fail("Invalid Response from renameChannelAPI. " + renameChannelResponsePOJOObject.getError());
                }
                else
                    Assert.fail("Invalid Response from joinChannelAPI : " + joinChannelResponsePOJOObject.getError());
            } else
                Assert.fail("Invalid Response from createChannelAPI. " + createChannelResponsePOJOObject.getError() + " " + createChannelResponsePOJOObject.getDetail());
        } catch (Exception e) {
            LOGGER.debug(e);
            Assert.fail("Scenario has failed..");
        }
    }
}
