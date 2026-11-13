import com.microsoft.playwright.*;
import java.nio.file.Paths;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FirstPlaywright {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true));

            try {
                page.navigate("https://www.saucedemo.com");
                page.locator("#user-name").fill("standard_user");
                page.locator("#password").fill("secret_sauce");
                page.locator("#login-button").click();
                assertThat(page.locator(".title")).hasText("Products");

                page.locator("#add-to-cart-sauce-labs-backpack").click();
                page.locator("#add-to-cart-sauce-labs-bike-light").click();
                assertThat(page.locator(".shopping_cart_badge")).hasText("2");

                page.locator(".shopping_cart_link").click();
                page.locator("#checkout").click();

                page.locator("#first-name").fill("Ankita");
                page.locator("#last-name").fill("Rajput");
                page.locator("#postal-code").fill("160062");
                page.locator("#continue").click();
                page.locator("#finish").click();

                assertThat(page.locator(".complete-header")).hasText("Thank you for your order!");
                System.out.println("PASS - order completed");
            } finally {
                context.tracing().stop(new Tracing.StopOptions()
                        .setPath(Paths.get("trace.zip")));
                browser.close();
            }
        }
    }
}