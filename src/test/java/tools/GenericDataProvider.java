package tools;

import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;
import org.testng.log4testng.Logger;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tools.CSVAnnotation.*;
public class GenericDataProvider {

    public GenericDataProvider() {
    }

    @DataProvider(
            name = "dataproviderForTestCase"
    )
    public Object[][] sampleDataProvider(Method testMethod) {
        Object[][] finalObject = null;
        Map<Integer, Map<String, String>> outerMap = new HashMap();
        CSVFileParameters parameters = (CSVFileParameters)testMethod.getAnnotation(CSVFileParameters.class);
        if (parameters != null) {
            String path = parameters.path();
            String delimiter = parameters.delimiter();
            String[][] csvArray = loadDataFromCSVFile(path);
            if (csvArray != null && csvArray.length > 0) {
                int length = csvArray[0][0].split(delimiter).length;

                int count;
                for(count = 0; count < csvArray.length; ++count) {
                    Map<String, String> innerMap = new HashMap();
                    String[] key = csvArray[count][0].split(delimiter);
                    String[] value = csvArray[count][1].split(delimiter);

                    for(int k = 0; k < length; ++k) {
                        innerMap.put(key[k], value[k]);
                    }

                    outerMap.put(count, innerMap);
                }

                finalObject = new Object[outerMap.size()][outerMap.size()];

                for(count = 0; count < outerMap.size(); ++count) {
                    finalObject[count] = new Object[]{count, outerMap.get(count)};
                }
            }
        }

        return finalObject;
    }

    private static String[][] loadDataFromCSVFile(String path) {
        CSVReader reader = null;
        String[][] returnArray = null;
        boolean var3 = true;

        try {
            Throwable var4 = null;
            Object var5 = null;

            try {
                FileReader fr = new FileReader(path);

                try {
                    reader = new CSVReader(fr, '\u0007', '\'');
                    List<String[]> csvList = reader.readAll();
                    if (!csvList.isEmpty()) {
                        returnArray = new String[csvList.size() - 1][csvList.size() - 1];
                        int iterator = 0;

                        for(int count = 1; count < csvList.size(); ++count) {
                            returnArray[iterator] = new String[]{((String[])csvList.get(0))[0], ((String[])csvList.get(count))[0]};
                            ++iterator;
                        }

                        reader.close();
                    }
                } finally {
                    if (fr != null) {
                        fr.close();
                    }

                }
            } catch (Throwable var19) {
                if (var4 == null) {
                    var4 = var19;
                } else if (var4 != var19) {
                    var4.addSuppressed(var19);
                }

                throw var4;
            }
        } catch (IOException var20) {
            System.out.println("IO Exception while reading CSV.." + var20.toString());
        } catch (Exception var21) {
            System.out.println(var21.toString());
        }
        catch (Throwable e )
        {
            System.out.println("Throwable Exception");
        }

        return returnArray;
    }

}