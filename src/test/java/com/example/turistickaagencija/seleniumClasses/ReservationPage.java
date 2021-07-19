package com.example.turistickaagencija.seleniumClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReservationPage extends BasePage{

    public ReservationPage(WebDriver driver) {
        super(driver);
    }
    public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/div/div/table"))).isDisplayed();
    }
}
