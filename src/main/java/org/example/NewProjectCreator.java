package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewProjectCreator {

    private static final String CREATE_PROJECT_URL = "https://crm.geekbrains.space/project/create/";

    private final WebDriver driver;

    public NewProjectCreator(WebDriver driver) {
        this.driver = driver;
    }

    public void createProject() throws InterruptedException {
        this.driver.get(CREATE_PROJECT_URL);

        findInput("crm_project[name]", "Sarycheva auto test " + (int)(Math.random() * 1000 + 1));
        findSelect2("crm_project[company]", "kolobok");
        //selectRadioButton("Внутренний");
        findSelect("crm_project[priority]", "Высокий");
        findSelect("crm_project[financeSource]", "Инвестиции");
        findSelect("crm_project[businessUnit]", "Research & Development");
        findSelect("crm_project[curator]", "Applanatest Applanatest Applanatest");
        findSelect("crm_project[rp]", "Applanatest Applanatest Applanatest");
        findSelect("crm_project[administrator]", "Applanatest Applanatest Applanatest");
        findSelect("crm_project[manager]", "Applanatest Applanatest Applanatest");
        findSelect("crm_project[contactMain]", "Аникеев Федор");
        findEditor("//textarea[@name='crm_project[planning]']/parent::*//iframe", "test");
        findEditor("//textarea[@name='crm_project[requirementsManagement]']/parent::*//iframe", "test");
        findEditor("//textarea[@name='crm_project[development]']/parent::*//iframe", "test");
        findEditor("//textarea[@name='crm_project[testing]']/parent::*//iframe", "test");
        findInput("crm_project[configManagement]", "test");
        findCheckBox("crm_project[skipSpeedChecks]", true);
        findSelect("crm_project[reportInterval]", "Ежемесячно");

        WebElement saveButton = this.driver.findElement(By.xpath("//button[normalize-space(text()) = 'Сохранить']"));
        saveButton.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Проект сохранен']")));
    }

    private void findInput(String byName, String andSendKeys) {
        WebElement element = this.driver.findElement(By.name(byName));
        element.click();
        element.sendKeys(andSendKeys);
    }

    private void findSelect(String byName, String andSelectOption) {
        WebElement element = this.driver.findElement(By.name(byName));
        Select select = new Select(element);
        select.selectByVisibleText(andSelectOption);
    }

    private void findCheckBox(String byName, Boolean andCheck) {
        WebElement element = this.driver.findElement(By.name(byName));
        if (element.isSelected()) {
            if (!andCheck) element.click();
        } else {
            if (andCheck) element.click();
        }
    }

    private void findEditor(String byXPath, String andSendKeys) {
        this.driver.switchTo().frame(this.driver.findElement(By.xpath(byXPath)));
        this.driver.findElement(By.id("tinymce")).sendKeys(andSendKeys);
        this.driver.switchTo().defaultContent();
    }

    private void findSelect2(String byName, String andSendKeys) throws InterruptedException {
        String xpath = "//input[@name='"+byName+"']/parent::*//a";
        WebElement element = this.driver.findElement(By.xpath(xpath));
        element.click();
        WebElement input = this.driver.findElement(By.cssSelector(".select2-input.select2-focused"));
        input.sendKeys(andSendKeys);
        Thread.sleep(3000);
        input.sendKeys(Keys.ENTER);
    }

    private void selectRadioButton(String withText) {
        WebElement radioLabel = this.driver.findElement(By.xpath("//label[text() = '"+withText+"']"));
        radioLabel.click();
    }
}
