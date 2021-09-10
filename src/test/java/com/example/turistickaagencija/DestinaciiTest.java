package com.example.turistickaagencija;

import com.example.turistickaagencija.seleniumClasses.DestinaciiPage;
import com.example.turistickaagencija.seleniumClasses.LiniiPage;
import com.example.turistickaagencija.seleniumClasses.LoginPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DestinaciiTest {
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
    public void AdminAddNewDestinationTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.isLoaded();
        loginPage.login("tijana", "Test123!");
        DestinaciiPage destinaciiPage=new DestinaciiPage(driver);
        destinaciiPage.isLoaded();
        destinaciiPage.dodadi();
        assertTrue(destinaciiPage.isLoaded());
    }
    @Test
    public void AdminEditNewDestinationTest() throws InterruptedException {
        DestinaciiPage destinaciiPage=new DestinaciiPage(driver);
        destinaciiPage.isLoaded();
        destinaciiPage.edit();
        assertTrue(destinaciiPage.isLoaded());
    }

    @Test
    public void DeleteDestinacija() throws InterruptedException {

        DestinaciiPage destinaciiPage=new DestinaciiPage(driver);
        assertTrue(destinaciiPage.isLoaded());
       // destinaciiPage.dodadiDestinacija();
        Thread.sleep(5000);
        destinaciiPage.deleteTest();
        Thread.sleep(5000);
        assertThrows(NoSuchElementException.class, () ->{
            destinaciiPage.daliSePrikazuva();
        });
    }
    @Test
    public void SearchDestinationTest() throws InterruptedException {
        DestinaciiPage destinaciiPage=new DestinaciiPage(driver);
        destinaciiPage.isLoaded();
        destinaciiPage.search();
        assertThrows(NoSuchElementException.class, () ->{
            destinaciiPage.uspesenSearch();
        });
    }
    @Test
    public void UserDeleteTest() throws InterruptedException {
        LiniiPage liniiPage = new LiniiPage(driver);
        DestinaciiPage destinaciiPage = new DestinaciiPage(driver);
        liniiPage.logout();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isLoaded();
        loginPage.login("user", "user");

        assertThrows(NoSuchElementException.class, () ->{
            destinaciiPage.deleteTest();
        });

    }
    @Test

    public void UserEditTest() throws InterruptedException {
        DestinaciiPage destinaciiPage= new DestinaciiPage(driver);
        assertThrows(NoSuchElementException.class, () ->{
            destinaciiPage.edit();
        });

    }
}
