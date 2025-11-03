package com.practice;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartAutomation {

    public static void main(String[] args) {

        // Initialize Chrome browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to Flipkart
        driver.get("https://www.flipkart.com");

        // Locate the search bar and search for a product
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("iPhone 15 Pro Max");
        searchInput.submit();

        // Store parent window handle
        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window ID: " + parentWindow);

        // Click on the first matching product
        WebElement selectedItem = driver.findElement(
                By.xpath("//div[contains(text(),'Apple iPhone 15 Pro Max') and contains(text(),'256 GB')]"));
        selectedItem.click();

        // Switch to the newly opened window
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Print current page URL
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Fetch product details
        List<WebElement> productDetails = driver.findElements(By.className("_21Ahn-"));
        System.out.println("Total elements found: " + productDetails.size());
        for (WebElement detail : productDetails) {
            System.out.println(detail.getText());
        }

        // Add product to cart
        WebElement addToCartBtn = driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"));
        addToCartBtn.click();

        // Optionally close browser after the operation
        driver.quit();
    }
}
