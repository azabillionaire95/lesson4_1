package org.example.lesson4_1.tests;

import org.example.lesson4_1.pages.MainPage;
import org.example.lesson4_1.pages.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTest {
    private WebDriver driver;
    MainPage mp;
    ResultsPage rp;
    private static final String input = "Selenium";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
        mp = new MainPage(driver);
        rp = new ResultsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchFieldTest() {
        mp.sendText(input);
        assertEquals(input, rp.getTextFromSearchField(), "Текст не совпал");
    }

    @Test
    public void clickElementTest() {
        mp.sendText(input);
        rp.clickElement(0);
        rp.switchToLastTab();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.selenium.dev/", currentUrl, "Первая ссылка ведет не на тот сайт");
    }
}
