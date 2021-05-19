package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

public class App {

    private static final String CRM_URL = "https://crm.geekbrains.space/user/login";

    public static void main( String[] args ) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get(CRM_URL);

        login(driver);

        NewProjectCreator creator = new NewProjectCreator(driver);
        creator.createProject();

        driver.quit();
    }

    private static void login(WebDriver driver) {
        findElement(driver, "prependedInput", "ApplanaTest1");

        findElement(driver, "prependedInput2", "Student2020!");

        WebElement loginButton = driver.findElement(By.id("_submit"));
        loginButton.click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.urlToBe("https://crm.geekbrains.space/"));
    }

    private static void findElement(WebDriver driver, String byId, String andSendKeys) {
        WebElement loginField = driver.findElement(By.id(byId));
        loginField.click();
        loginField.sendKeys(andSendKeys);
    }
}
