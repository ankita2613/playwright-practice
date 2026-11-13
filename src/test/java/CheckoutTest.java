import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void userCanCompleteAnOrder() {
        new LoginPage(page).login("standard_user", "secret_sauce");

        ProductsPage products = new ProductsPage(page);
        products.addToCart("sauce-labs-backpack");
        products.addToCart("sauce-labs-bike-light");
        assertThat(products.cartBadge()).hasText("2");
        products.openCart();

        CartPage cart = new CartPage(page);
        assertThat(cart.itemNames()).hasText(
                new String[]{"Sauce Labs Backpack", "Sauce Labs Bike Light"});
        cart.checkout();

        CheckoutPage checkout = new CheckoutPage(page);
        checkout.fillDetails("Ankita", "Rajput", "160062");
        checkout.finish();
        assertThat(checkout.confirmation()).hasText("Thank you for your order!");
    }
}