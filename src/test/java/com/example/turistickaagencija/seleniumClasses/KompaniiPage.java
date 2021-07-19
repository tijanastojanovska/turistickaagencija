package com.example.turistickaagencija.seleniumClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class KompaniiPage extends BasePage{
    public KompaniiPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        driver.findElement(By.name("komp")).click();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div[2]/table/thead/tr/th"))).isDisplayed();
    }
    public void dodadi(){
        driver.findElement(By.name("dodadiKompanija")).click();
        driver.findElement(By.name("ime_kompanija")).sendKeys("vtora");
        driver.findElement(By.name("submit")).click();
    }
    public void edit(){
        driver.findElement(By.xpath("//td[contains(text(), 'vtora')]/following::a[contains(@title,'Edit')]")).click();
            driver.findElement(By.name("ime_kompanija")).clear();
            driver.findElement(By.name("ime_kompanija")).sendKeys("Vtora");
            driver.findElement(By.name("submit")).click();
    }
    public void deleteTest(){
        driver.findElement(By.xpath("//td[contains(text(), 'Vtora')]/following::form//button[contains(@title,'Delete')]")).click();
    }
    public boolean daliSePrikazuva(){
        boolean dali= driver.findElement(By.xpath("//td[contains(text(), 'Vtora')]")).isDisplayed();
        return dali;
    }
}
