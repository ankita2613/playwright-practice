import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductsPage {
    private final Page page;
    private final Locator title;
    private final Locator cartBadge;
    private final Locator cartLink;

    public ProductsPage(Page page) {
        this.page = page;
        title = page.locator(".title");
        cartBadge = page.locator(".shopping_cart_badge");
        cartLink = page.locator(".shopping_cart_link");
    }

    public void addToCart(String productId) {
        page.locator("#add-to-cart-" + productId).click();
    }

    public void openCart() {
        cartLink.click();
    }

    public Locator title() {
        return title;
    }

    public Locator cartBadge() {
        return cartBadge;
    }
}