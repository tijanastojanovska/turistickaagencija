package com.example.turistickaagencija;

import com.example.turistickaagencija.seleniumClasses.LiniiPage;
import com.example.turistickaagencija.seleniumClasses.LoginPage;
import com.example.turistickaagencija.seleniumClasses.ReservationPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LiniiTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }



    private void assertTrue(boolean loaded) {
    }

 @Test
    public void AdminAddNewTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.isLoaded();
       // assertTrue(loginPage.isLoaded());
        loginPage.login("tijana", "Test123!");
        LiniiPage liniiPage=new LiniiPage(driver);
        liniiPage.dodadiLinija();
        Thread.sleep(5000);
        assertTrue(liniiPage.isLoaded());
    }
    @Test
    public void AdminEditNewTest() throws InterruptedException {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.open();
//        loginPage.isLoaded();
//        // assertTrue(loginPage.isLoaded());
//        loginPage.login("tijana", "Test123!");
        LiniiPage liniiPage=new LiniiPage(driver);
        liniiPage.editLinija();
        liniiPage.promeniCena();
        Thread.sleep(5000);
        liniiPage.deleteNovata();
        assertTrue(liniiPage.isLoaded());
    }
    @Test
    public void AdminDelete() throws InterruptedException {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.open();
//        loginPage.isLoaded();
//        // assertTrue(loginPage.isLoaded());
//        loginPage.login("tijana", "Test123!");
        LiniiPage liniiPage=new LiniiPage(driver);
        liniiPage.zaBrisenje();
        assertTrue(liniiPage.isLoaded());
        assertTrue(liniiPage.daliSePrikazuva());
        Thread.sleep(5000);
        liniiPage.deleteTest();
        Thread.sleep(5000);
        assertThrows(NoSuchElementException.class, () ->{
            liniiPage.daliSePrikazuva();
        });
    }

    @Test
    public void AdminReserveTest() throws InterruptedException {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.open();
//        loginPage.isLoaded();
//        // assertTrue(loginPage.isLoaded());
//        loginPage.login("tijana", "Test123!");
        LiniiPage liniiPage=new LiniiPage(driver);
        ReservationPage reservationPage=new ReservationPage(driver);
        liniiPage.reserve();
        assertTrue(reservationPage.isLoaded());


    }
    @Test

    public void UserDeleteTest() throws InterruptedException {
        LiniiPage liniiPage = new LiniiPage(driver);
        liniiPage.logout();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isLoaded();
        loginPage.login("user", "user");

        assertThrows(NoSuchElementException.class, () ->{
            liniiPage.deleteTest();
        });

    }
    @Test

    public void UserEditTest() throws InterruptedException {
        LiniiPage liniiPage = new LiniiPage(driver);
        assertThrows(NoSuchElementException.class, () ->{
            liniiPage.edit();
        });

    }
    @Test

    public void UserFilterTest() throws InterruptedException {
        LiniiPage liniiPage = new LiniiPage(driver);
       liniiPage.filter();
       assertTrue(liniiPage.isFiltered());

    }
@Test
public void UserReserveTest() throws InterruptedException {
    LiniiPage liniiPage=new LiniiPage(driver);

    ReservationPage reservationPage=new ReservationPage(driver);
    liniiPage.reserve();
    assertTrue(reservationPage.isLoaded());

}



    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/stoja/OneDrive/Documents/GitHub/turistickaagencija/src/main/resources/drivers/chromedriver.exe");
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}
