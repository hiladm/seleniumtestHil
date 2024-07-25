package saucedemo.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Endtoendtest {

	    private WebDriver driver;

	    public Endtoendtest(WebDriver driver) {

			this.driver = driver;
		}

	    public void completePurchaseProcess() {

	        // Login with valid credentials
	        driver.findElement(By.id("user-name")).sendKeys("standard_user");
	        driver.findElement(By.id("password")).sendKeys("secret_sauce");
	        driver.findElement(By.id("login-button")).click();

	        // Add items to the cart
	        List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));
	        inventoryItems.get(0).findElement(By.className("btn_inventory")).click();
	        inventoryItems.get(1).findElement(By.className("btn_inventory")).click(); // Adding more than one item

	        // Verify items in the cart
	        driver.findElement(By.className("shopping_cart_link")).click();
	        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
	        assert(cartItems.size() > 0);

	        // Proceed to checkout
	        driver.findElement(By.cssSelector(".checkout_button")).click();

	        // Fill out checkout form incorrectly and verify error
	        driver.findElement(By.id("first-name")).sendKeys("");
	        driver.findElement(By.id("last-name")).sendKeys("");
	        driver.findElement(By.id("postal-code")).sendKeys("");
	        driver.findElement(By.cssSelector(".cart_button")).click();
	        String errorMsg = driver.findElement(By.cssSelector(".error-message-container")).getText();
	        assert(errorMsg.contains("Error: First Name is required"));

	        // Fill out checkout form correctly and proceed
	        driver.findElement(By.id("first-name")).sendKeys("Hilary");
	        driver.findElement(By.id("last-name")).sendKeys("Delgado");
	        driver.findElement(By.id("postal-code")).sendKeys("12345");
	        driver.findElement(By.cssSelector(".cart_button")).click();

	        // Verify items and total in the order summary
	        List<WebElement> orderItems = driver.findElements(By.className("cart_item"));
	        assert(orderItems.size() > 0);
	        // You may add assertions to verify the total amount including tax

	        // Complete the purchase
	        driver.findElement(By.cssSelector(".cart_button")).click();

	        // Verify the completion messages
	        String thankYouMsg = driver.findElement(By.className("complete-header")).getText();
	        assert(thankYouMsg.contains("Thank you for your order!"));
	        String dispatchMsg = driver.findElement(By.className("complete-text")).getText();
	        assert(dispatchMsg.contains("Your order has been dispatched"));

	        // Navigate back home
	        driver.findElement(By.cssSelector("#back-to-products")).click();

	        // Verify cart is empty
	        List<WebElement> cartBadges = driver.findElements(By.className("shopping_cart_badge"));
	        assert(cartBadges.size() == 0);

	        // Logout
	        driver.findElement(By.cssSelector("#react-burger-menu-btn")).click();

	        // Verify successful logout and return to login page
	        assert(driver.getCurrentUrl().contains("inventory.html"));
	        
	        driver.findElement(By.id("logout_sidebar_link")).click();

	    }


	}
