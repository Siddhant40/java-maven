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
    void testLoginFunctionality() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the login page
            driver.get("http://localhost:8080/java-maven/login");

            // Interact with the login form
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            // Test valid login
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPassword");
            loginButton.click();

            // Verify successful redirection to the dashboard
            String currentUrl = driver.getCurrentUrl();
            assertEquals("http://localhost:8080/java-maven/dashboard", currentUrl);

            // Add tests for invalid login scenarios here, such as empty fields or wrong credentials
            WebElement errorMessage = driver.findElement(By.id("errorMessage"));
            assertEquals("Invalid credentials, please try again.", errorMessage.getText());

        } finally {
            driver.quit();
        }
    }
}
