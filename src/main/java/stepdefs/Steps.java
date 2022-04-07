package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Steps {

    WebDriver driver = null;

    private static final String EXCHANGE_MARKET_PAGE = "https://crypto.com/exchange/markets";
    private static final String DISCLAIMER_BUTTON = "/html/body/div[5]/div/div/div[2]/div/button";
    private static final String COOKIE_ACCEPT_BUTTON = "/html/body/div[1]/div[2]/div[4]/div[2]/div";
    private static final String BOTTOM_OF_PAGE = "//*[@id=\"app\"]/div[2]/div[1]/div/span";
    private static final String ZIL_USDT_PAGE = "//*[@id=\"app\"]/div[1]/div[1]/div/div[1]/div/div[1]/div/div/div/div[1]/div";
    private static final String ZIL_USDT_TRADE_BUTTON = "//*[@id=\"app\"]/div[1]/div[1]/div[3]/div[3]/table/tbody/tr[180]/td[7]/div/button";

//    @After
//    public void teardown(){
//        driver.quit();
//    }

    @Given("^the browser is open$")
    public void theBrowserIsOpen() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("^user navigates to the exchange market page$")
    public void navigateToExchangeMarket() {
        driver.get(EXCHANGE_MARKET_PAGE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath(DISCLAIMER_BUTTON)).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.findElement(By.xpath(DISCLAIMER_BUTTON)).click();
        driver.findElement(By.xpath(COOKIE_ACCEPT_BUTTON)).click();
    }


    @When("^user clicks on ZIL/USDT pair$")
    public void userClicksOnZilUsdtPair() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(BOTTOM_OF_PAGE));
        js.executeScript("arguments[0].scrollIntoView();",element);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[3]/div[3]/table/tbody/tr[182]")).click();
    }

    @Then("^the ZIL/USDT trade page will show$")
    public void zilUsdtTradePageWillShow() throws InterruptedException {
        Thread.sleep(5000);
        // scroll to view zil/usdt and assert words
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div/div[1]/div/div[1]/div/div/div/div[1]/div")).isDisplayed());
    }

}
