package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    public static Properties properties;
    public static void initializePropertyFile()
    {
        try(InputStream fin= new FileInputStream("testsettings.properties"))
        {
            properties= new Properties();
            properties.load(fin);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyName)
    {
        PropertiesManager.initializePropertyFile();
        return properties.getProperty(propertyName);
    }
}