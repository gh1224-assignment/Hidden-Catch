import java.io.InputStream;
import java.net.URL;

public class ResourceUtil {
    private static ResourceUtil inst = new ResourceUtil();

    private ResourceUtil() {
    }

    public static URL getUrl(String path) {
        return inst.getClass().getResource(path);
    }

    public static InputStream getStream(String path) {
        return inst.getClass().getClassLoader().getResourceAsStream(path);
    }
}
