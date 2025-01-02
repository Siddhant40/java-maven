package com.example.automation;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testauto {
    @Test
    public void testLogin() {
        // Set up the WebDriver
        // System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        // WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\\\Program Files\\\\chromedriver-win64\\\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Add this to handle WebSocket issues
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the login page
            driver.get("https://practicetestautomation.com/practice-test-login/");
            // Locate the username and password fields
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("submit"));
            // Perform login
            usernameField.sendKeys("student");
            passwordField.sendKeys("Password123");
            loginButton.click();
            // Validate successful login
            String expectedTitle = "Logged In Successfully | Practice Test Automation";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle);
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
