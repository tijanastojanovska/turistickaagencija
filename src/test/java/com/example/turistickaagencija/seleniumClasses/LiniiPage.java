package com.example.turistickaagencija.seleniumClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LiniiPage extends BasePage {
    public LiniiPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Tabelalinii\"]/thead/tr/th[1]"))).isDisplayed();
    }
    public void dodadiLinija(){
        driver.findElement(By.name("dodadiLinija")).click();
        driver.findElement(By.name("vreme")).sendKeys(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        driver.findElement(By.name("cena")).sendKeys("233");
        Select pocetna=new Select(driver.findElement(By.name("pocetna")));
        pocetna.selectByValue("1");
        Select krajna=new Select(driver.findElement(By.name("krajna")));
        krajna.selectByValue("2");
        Select kompanii=new Select(driver.findElement(By.name("kompanii")));
        kompanii.selectByValue("1");
        driver.findElement(By.name("submit")).click();
    }
    public void zaBrisenje(){
        driver.findElement(By.name("dodadiLinija")).click();
        driver.findElement(By.name("vreme")).sendKeys(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        driver.findElement(By.name("cena")).sendKeys("111");
        Select pocetna=new Select(driver.findElement(By.name("pocetna")));
        pocetna.selectByValue("1");
        Select krajna=new Select(driver.findElement(By.name("krajna")));
        krajna.selectByValue("2");
        Select kompanii=new Select(driver.findElement(By.name("kompanii")));
        kompanii.selectByValue("1");
        driver.findElement(By.name("submit")).click();

    }
    public void reserve(){
        driver.findElement(By.xpath("//*[@id=\"Tabelalinii\"]/tbody/tr[1]/td[6]/form[1]/button")).click();
    }
    public void filter(){
        driver.findElement(By.xpath("//*[@id=\"destinacijaId\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"destinacijaId\"]/option[2]")).click();
        driver.findElement(By.name("filter")).click();
    }
    public boolean isFiltered(){
      return driver.findElement(By.xpath("//td[contains(text(),'Париз')]")).isDisplayed();

    }
    public void deleteTest(){
        driver.findElement(By.xpath("//td[contains(text(), '111')]/following::form//button[contains(@title,'Delete')]")).click();
    }
    public void editLinija(){
        driver.findElement(By.xpath("//td[contains(text(), '233')]/following::a[contains(@title,'Edit')]")).click();
    }
    public void promeniCena()
    {
        driver.findElement(By.name("cena")).clear();
        driver.findElement(By.name("cena")).sendKeys("333");
        driver.findElement(By.name("submit")).click();
    }
    public void deleteNovata(){
        driver.findElement(By.xpath("//td[contains(text(), '333')]/following::form//button[contains(@title,'Delete')]")).click();
    }
    public void edit(){
        driver.findElement(By.xpath("//*[@id=\"Tabelalinii\"]/tbody/tr[1]/td[6]/a/i")).click();
    }

    public boolean daliSePrikazuva(){
        boolean dali= driver.findElement(By.xpath("//td[contains(text(),'111')]")).isDisplayed();
   return dali;
    }
    public void logout(){
        driver.findElement(By.className("logout")).click();
    }
}