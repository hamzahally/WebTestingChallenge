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

    // Used a lot of xpaths here only because via the production website this was only available,
    // ideally Preferred selector order : id > name > css > xpath
    private static final String EXCHANGE_MARKET_PAGE = "https://crypto.com/exchange/markets";
    private static final String DISCLAIMER_BUTTON = "/html/body/div[5]/div/div/div[2]/div/button";
    private static final String COOKIE_ACCEPT_BUTTON = "/html/body/div[1]/div[2]/div[4]/div[2]/div";
    private static final String BOTTOM_OF_PAGE = "//*[@id=\"app\"]/div[2]/div[1]/div/span";
    private static final String ZIL_USDT_PAGE = "//*[@id=\"app\"]/div[1]/div[1]/div/div[1]/div/div[1]/div/div/div/div[1]/div";
    private static final String ZIL_USDT_TRADE_BUTTON = "//*[@id=\"app\"]/div[1]/div[1]/div[3]/div[3]/table/tbody/tr[182]";
    private static final String TOP_OF_PAGE = "//*[@id=\"app\"]/div[1]/div[1]/div/div[1]/div";

    @After
    public void teardown(){
        driver.quit();
    }

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
        // Thread.sleep, used here as issues with implicit wait functions and errors being found for automation
        Thread.sleep(3000);
        driver.findElement(By.xpath(ZIL_USDT_TRADE_BUTTON)).click();
    }

    @Then("^the ZIL/USDT trade page will show$")
    public void zilUsdtTradePageWillShow() throws InterruptedException {
        // Thread.sleep, used here as issues with implicit wait functions and errors being found for automation
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(TOP_OF_PAGE));
        js.executeScript("arguments[0].scrollIntoView();",element);
        Assert.assertEquals(driver.findElement(By.xpath(ZIL_USDT_PAGE)).getText(), "ZIL/USDT");
    }

}

