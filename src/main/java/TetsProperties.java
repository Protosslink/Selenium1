import javax.imageio.IIOException;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class TetsProperties {

    private final Properties properties = new Properties();

    private static TetsProperties INSTANCE = null;

    private TetsProperties() {
        try {
            properties.load(new FileInputStream(new File("./" + System.getProperty("environment") + ".properties")));
        } catch (IIOException e) {
            e.printStackTrace();
        }
    }

    public static TetsProperties getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TetsProperties();
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }


}
