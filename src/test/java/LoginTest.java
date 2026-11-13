import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void validLoginShowsProductsPage() {
        new LoginPage(page).login("standard_user", "secret_sauce");
        assertThat(new ProductsPage(page).title()).hasText("Products");
    }

    @Test(groups = {"regression"})
    public void wrongPasswordShowsError() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.login("standard_user", "wrong_pass");
        assertThat(loginPage.errorMessage()).hasText(
                "Epic sadface: Username and password do not match any user in this service");
    }
    @DataProvider(name = "badLogins")
    public Object[][] badLogins() {
        String generic = "Epic sadface: Username and password do not match any user in this service";
        return new Object[][] {
                {"",                "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user",   "",             "Epic sadface: Password is required"},
                {"standard_user",   "wrong_pass",   generic},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"#$%^ahv145",      "secret_sauce", generic},
                {"abc123",          "secret_sauce", generic},
                {"STANDARD_USER",   "secret_sauce", generic}
        };
    }

    @Test(dataProvider = "badLogins", groups = {"regression"})
    public void badLoginShowsError(String user, String pass, String expectedError) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.login(user, pass);
        assertThat(loginPage.errorMessage()).hasText(expectedError);
    }
}