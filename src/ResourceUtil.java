import java.io.InputStream;
import java.net.URL;

public abstract class ResourceUtil {
    public static URL getUrl(String path) {
        return ResourceUtil.class.getResource(path);
    }

    public static InputStream getStream(String path) {
        return ResourceUtil.class.getClassLoader().getResourceAsStream(path);
    }
}
