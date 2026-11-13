import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage {
    private final Locator firstName;
    private final Locator lastName;
    private final Locator postalCode;
    private final Locator continueButton;
    private final Locator finishButton;
    private final Locator confirmation;

    public CheckoutPage(Page page) {
        firstName = page.locator("#first-name");
        lastName = page.locator("#last-name");
        postalCode = page.locator("#postal-code");
        continueButton = page.locator("#continue");
        finishButton = page.locator("#finish");
        confirmation = page.locator(".complete-header");
    }

    public void fillDetails(String first, String last, String zip) {
        firstName.fill(first);
        lastName.fill(last);
        postalCode.fill(zip);
        continueButton.click();
    }

    public void finish() {
        finishButton.click();
    }

    public Locator confirmation() {
        return confirmation;
    }
}