package com.example.turistickaagencija;

import com.example.turistickaagencija.seleniumClasses.DestinaciiPage;
import com.example.turistickaagencija.seleniumClasses.KompaniiPage;
import com.example.turistickaagencija.seleniumClasses.LiniiPage;
import com.example.turistickaagencija.seleniumClasses.LoginPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class KompaniiTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/stoja/OneDrive/Documents/GitHub/turistickaagencija/src/main/resources/drivers/chromedriver.exe");
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    private void assertTrue(boolean loaded) {
    }
    @Test
    public void AdminAddNewCompanyTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.isLoaded();
        loginPage.login("tijana", "Test123!");
        KompaniiPage kompaniiPage=new KompaniiPage(driver);
        kompaniiPage.isLoaded();
        kompaniiPage.dodadi();
        Thread.sleep(5000);
        assertTrue(kompaniiPage.isLoaded());
    }
    @Test
    public void AdminEditNewCompanyTest() throws InterruptedException {
        KompaniiPage kompaniiPage=new KompaniiPage(driver);
        kompaniiPage.isLoaded();
        Thread.sleep(5000);
        kompaniiPage.edit();
        Thread.sleep(5000);
        assertTrue(kompaniiPage.isLoaded());
    }

    @Test
    public void DeleteCompanyAdmin() throws InterruptedException {

        KompaniiPage kompaniiPage=new KompaniiPage(driver);
        assertTrue(kompaniiPage.isLoaded());
        Thread.sleep(5000);
        kompaniiPage.deleteTest();
        Thread.sleep(5000);
        assertThrows(NoSuchElementException.class, () ->{
            kompaniiPage.daliSePrikazuva();
        });
    }
    @Test

    public void UserDeleteTest() throws InterruptedException {
        LiniiPage liniiPage = new LiniiPage(driver);
        KompaniiPage kompaniiPage= new KompaniiPage(driver);
        liniiPage.logout();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isLoaded();
        loginPage.login("user", "user");

        assertThrows(NoSuchElementException.class, () ->{
            kompaniiPage.deleteTest();
        });

    }
    @Test

    public void UserEditTest() throws InterruptedException {
        KompaniiPage kompaniiPage= new KompaniiPage(driver);
        assertThrows(NoSuchElementException.class, () ->{
            kompaniiPage.edit();
        });

    }
}
