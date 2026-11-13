import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.nio.file.Paths;

public class BaseTest {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    protected Page page;

    @BeforeClass(alwaysRun = true)
    public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeMethod(alwaysRun = true)
    public void createContext() {
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));
        page = context.newPage();
        page.navigate("https://www.saucedemo.com");
    }

    @AfterMethod(alwaysRun = true)
    public void closeContext(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            context.tracing().stop(new Tracing.StopOptions().setPath(
                    Paths.get("traces/" + result.getName() + "-" + System.currentTimeMillis() + ".zip")));
        } else {
            context.tracing().stop();
        }
        context.close();
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        browser.close();
        playwright.close();
    }
}