package nz.co.airnz.cucumber;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * Created by jingbai on 12/12/17.
 */
public class BookFlight {

    WebDriver driver = null;
    WebDriverWait dw = null;

    @Before
    public void testSetUp() {

        String projectLocation = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectLocation + "/libs/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        dw = new WebDriverWait(driver, 10);

    }

    @After
    public void testShutDown() {
        driver.quit();
    }

    @Given("^I browser to the website \"([^\"]*)\"$")
    public void I_browser_to_the_website(String url) throws Throwable {

        driver.get(url);

    }

    @When("^I click \"([^\"]*)\"$")
    public void I_click(String arg1) throws Throwable {

        dw.until(ExpectedConditions.elementToBeClickable(By.linkText(arg1)));
        driver.findElement(By.linkText(arg1)).click();

    }

    @Then("^I should see the \"([^\"]*)\" page$")
    public void I_should_see_the_page(String arg1) throws Throwable {

        dw.until(ExpectedConditions.titleContains(arg1));
        Assert.assertTrue(driver.getTitle().contains(arg1));

    }

    @Then("^A \"([^\"]*)\" new page should be open$")
    public void A_new_page_should_be_open(String arg1) throws Throwable {

        dw.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windowsIds = driver.getWindowHandles();
        Iterator<String> windowsIt = windowsIds.iterator();

        String targetWindow = null;

        while ( windowsIt.hasNext())
        {
            targetWindow = windowsIt.next();
        }

        driver.switchTo().window(targetWindow);
        System.out.println(driver.getTitle());

    }

    @When("^I fill value \"([^\"]*)\" in the \"([^\"]*)\" form$")
    public void I_fill_in_the_form_with_value(String value, String form) throws Throwable {

        dw.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Search for flights']")));
        driver.findElement(By.id(form)).sendKeys(value);

    }

    @When("^Select day \"([^\"]*)\" of next month in the \"([^\"]*)\" form$")
    public void Select_day_of_next_month_in_the_form(String strDay, String form) throws Throwable {

        driver.findElement(By.id(form)).click();

        Select monthSelector = null;

        //the 0 calendarpanel-0 will become invisible once it is used once
        if (driver.findElement(By.xpath("//*[@id='calendarpanel-0']/div[1]/div/select")).isDisplayed())
        {
            monthSelector = new Select(driver.findElement(By.xpath("//*[@id='calendarpanel-0']/div[1]/div/select")));
        }
        else {
            monthSelector = new Select(driver.findElement(By.xpath("//*[@id='calendarpanel-1']/div[1]/div/select")));
        }

        monthSelector.selectByIndex(1);

        WebElement daysElement = driver.findElement(By.xpath("//*[@id='calendarpanel-1']"));

        int count = daysElement.findElements(By.className("datemarker")).size();
        List<WebElement> days =  daysElement.findElements(By.className("datemarker"));

        for (int i = 0; i < count; i++)
        {
            String text = days.get(i).getText();
            if(text.equalsIgnoreCase(strDay))
            {
                days.get(i).click();
                break;
            }
        }
    }


    @When("^I click Search button$")
    public void I_click_Search_button() throws Throwable {

        // Here should use submit instead
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).submit();

    }

    @Then("^The page content contains$")
    public void The_page_content_contains(DataTable dataTable) throws Throwable {

        for (DataTableRow row : dataTable.getGherkinRows()) {
            Assert.assertTrue(driver.getPageSource().contains(row.getCells().get(0)));
            Assert.assertTrue(driver.getPageSource().contains(row.getCells().get(1)));
        }

    }



}
