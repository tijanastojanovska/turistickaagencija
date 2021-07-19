package com.example.turistickaagencija.seleniumClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DestinaciiPage extends BasePage{
    public DestinaciiPage(WebDriver driver) {
        super(driver);
    }
    public boolean isLoaded() {
        driver.findElement(By.name("destin")).click();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("searchDest"))).isDisplayed();
    }
//    public void dodadiDestinacija(){
//        driver.findElement(By.name("dodadiDestinacija")).click();
//        driver.findElement(By.name("ime_destinacija")).sendKeys("Zagreb");
//        driver.findElement(By.name("drzhava")).sendKeys("Hrvatska");
//        driver.findElement(By.name("latitude")).sendKeys("45.815399");
//        driver.findElement(By.name("longitude")).sendKeys("15.966568");
//        driver.findElement(By.name("submit")).click();
//    }
    public void dodadi(){
        driver.findElement(By.name("dodadiDestinacija")).click();
        driver.findElement(By.name("ime_destinacija")).sendKeys("Split");
        driver.findElement(By.name("drzhava")).sendKeys("Turcija");
        driver.findElement(By.name("latitude")).sendKeys("41.0082");
        driver.findElement(By.name("longitude")).sendKeys("28.9784");
        driver.findElement(By.name("submit")).click();
    }
    public void edit(){
        driver.findElement(By.xpath("//td[contains(text(), 'Split')]/following::a[contains(@title,'Edit')]")).click();
  driver.findElement(By.name("ime_destinacija")).clear();
  driver.findElement(By.name("ime_destinacija")).sendKeys("Istanbul");
        driver.findElement(By.name("submit")).click();
    }
        public void search(){
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Франција");
        driver.findElement(By.name("searchDest")).click();
}
    public boolean uspesenSearch(){
       return driver.findElement(By.xpath("//td[contains(text(), 'Македонија')]")).isDisplayed();
    }
    public void deleteTest(){
        driver.findElement(By.xpath("//td[contains(text(), 'Istanbul')]/following::form//button[contains(@title,'Delete')]")).click();
    }
    public boolean daliSePrikazuva(){
        boolean dali= driver.findElement(By.xpath("//td[contains(text(), 'Istanbul')]")).isDisplayed();
        return dali;
    }
}
