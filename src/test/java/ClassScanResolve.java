import org.luka.framework.core.io.ResourceResolver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


public class ClassScanResolve extends ResourceResolver {


    public ChromeDriver driver;

    public ClassScanResolve(String packagePath) {
        super(packagePath);
    }

    public List<String> scan() {
        return scan(f -> {
            if (f.getName().endsWith(".class")) {
                return f.getName()
                        .replaceAll(".class", "")
                        .replaceAll("/", ".")
                        .replaceAll("\\\\", ".");
            }
            return null;
        });
    }

}
