package nz.co.airnz.testng;

import nz.co.airnz.testng.object.FlightBook;
import nz.co.airnz.testng.object.FlightSearch;
import nz.co.airnz.testng.object.Home;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class TestBooking {


    WebDriver driver;
    WebDriverWait dw = null;

    @BeforeTest(groups = "pageFactory")
            //BeforeTest(groups = "pageFactory")
    public void testSetup() {
        String projectLoc = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",projectLoc + "/libs/chromedriver");
        driver = new ChromeDriver();
        dw = new WebDriverWait(driver, 10);
    }


    @Test(groups = "pageFactory")
    public void testBooking(){

        driver.get("https://www.airnewzealand.co.nz/");

        Home anzHome = new Home(driver);
        anzHome.bookLink().click();

        FlightBook anzBook_F = new FlightBook(driver);
        anzBook_F.bookFlight().click();

        dw.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windowsIds = driver.getWindowHandles();
        Iterator<String> windowsIt = windowsIds.iterator();
        String targetWindow = null;

        while ( windowsIt.hasNext())
        {
            targetWindow = windowsIt.next();
        }
        driver.switchTo().window(targetWindow);


        FlightSearch anzSearch_F = new FlightSearch(driver);

        dw.until(ExpectedConditions.visibilityOfElementLocated(By.id("depart-to")));
        anzSearch_F.departTo().sendKeys("Queenstown");

        anzSearch_F.searchButton().click();

        dw.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Select your flights']")));
        Assert.assertTrue(driver.getPageSource().contains("Select your flight"));

    }

    @AfterMethod(groups = "pageFactory")
    public void testShutDown() {
        driver.quit();
    }

}
