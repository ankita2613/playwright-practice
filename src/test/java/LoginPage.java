import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        usernameField = page.locator("#user-name");
        passwordField = page.locator("#password");
        loginButton = page.locator("#login-button");
        errorMessage = page.locator("[data-test='error']");
    }

    public void login(String user, String pass) {
        usernameField.fill(user);
        passwordField.fill(pass);
        loginButton.click();
    }

    public Locator errorMessage() {
        return errorMessage;
    }
}