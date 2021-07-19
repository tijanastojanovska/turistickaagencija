package com.example.turistickaagencija.seleniumClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        //home page
        driver.get("http://localhost:8080");
    }

    //login stranata
    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(5000);
       driver.findElement(By.name("login")).click();
       return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).isDisplayed();

    }
//vnesuvanje podatoci
    public void login(String user, String password) throws InterruptedException {
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(user);
        Thread.sleep(5000);
        driver.findElement(By.name("password")).sendKeys(password);
        Thread.sleep(5000);
        driver.findElement(By.name("submit")).click();
        Thread.sleep(5000);
    }

//poraka pri vnes na gresni podatoci
    public String getErrorMessage() {

        WebElement errorPage = driver.findElement(By.name("error"));
        return errorPage.getText();
    }
}

