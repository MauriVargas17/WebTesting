package basicWeb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    ChromeDriver chrome;
    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.get("http://todo.ly/");
    }

    @Test
    public void verifySomeThing() throws InterruptedException {
        Thread.sleep(5000);
    }

    @AfterEach
    public void closeBrowser(){
        chrome.quit();
    }

}
