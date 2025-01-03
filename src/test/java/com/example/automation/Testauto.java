@Test
void testLocalLoginServlet() {
    System.setProperty("webdriver.chrome.driver", "C:\\\\Program Files\\\\chromedriver-win64\\\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    WebDriver driver = new ChromeDriver(options);

    try {
        // Navigate to the locally hosted servlet
        driver.get("http://localhost:8080/YourAppName/login");

        // Interact with the login form
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        // Test valid login
        usernameField.sendKeys("testUser");
        passwordField.sendKeys("testPassword");
        loginButton.click();

        // Verify successful redirection
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:8080/YourAppName/dashboard", currentUrl);

        // Add other tests for invalid login, missing fields, etc.

    } finally {
        driver.quit();
    }
}
