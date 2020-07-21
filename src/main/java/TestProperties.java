import javax.imageio.IIOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    private final Properties properties = new Properties();

    private static TestProperties INSTANCE = null;

    private TestProperties() throws IOException {
        try {
            properties.load(new FileInputStream(new File("./" + System.getProperty("environment") + ".properties")));
        } catch (IIOException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static TestProperties getInstance() throws IOException {
        if (INSTANCE == null) {
            INSTANCE = new TestProperties();
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }


}
