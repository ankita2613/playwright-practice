import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {
    private final Locator itemNames;
    private final Locator checkoutButton;

    public CartPage(Page page) {
        itemNames = page.locator(".inventory_item_name");
        checkoutButton = page.locator("#checkout");
    }

    public Locator itemNames() {
        return itemNames;
    }

    public void checkout() {
        checkoutButton.click();
    }
}