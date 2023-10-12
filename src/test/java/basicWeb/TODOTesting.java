package basicWeb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class TODOTesting {
    ChromeDriver chrome;
    Actions actions;
    @BeforeEach
    public void openBrowser(){
        //ACLARACION! MI COMPUTADORA ES MAC OS, POR LO QUE LA RUTA Y EL DRIVER DEBEN SER MODIFICADOS
        System.setProperty("webdriver.chrome.driver","src/test/resources/chrome/chromedriver");
        chrome = new ChromeDriver();
        actions = new Actions(chrome);
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        chrome.get("http://todo.ly/");
    }

    @Test
    public void verifyLoginTest() throws InterruptedException {
        String proyecto_nombre = "proyecto";
        // click login button
        chrome.findElement(By.xpath("//img[@src=\"/Images/design/pagelogin.png\"]")).click();
        // set email
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("tuerto@tuerto.com");
        // set password
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("12345");
        // click login
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        // verificar si existe el control del logout

        Assertions.assertTrue((chrome.findElements(By.xpath("//a[text()='Logout']")).size() == 1),
                "ERROR no se pudo ingresar a la sesion");

        chrome.findElement(By.xpath("//div[@class=\"AddProjectLiDiv\"]")).click();


        chrome.findElement(By.id("NewProjNameInput")).sendKeys(proyecto_nombre);

        chrome.findElement(By.id("NewProjNameButton")).click();

        Assertions.assertTrue((chrome.findElements(By.xpath("//td[text()='"+proyecto_nombre+"']")).size() == 1),
                "ERROR no se pudo crear proyecto");
        Thread.sleep(2000);

        WebElement project_element = chrome.findElement(By.xpath("//td[text()='"+proyecto_nombre+"']"));
        String project_itemid = project_element.getAttribute("itemid");
        System.out.println("ITEM ID: "+project_itemid);

        actions.moveToElement(chrome.findElement(By.xpath("//td[text()='"+proyecto_nombre+"']"))).perform();
        chrome.findElement(By.xpath("//div[@class=\"ProjItemMenu\" and @itemid=\""+project_itemid+"\"]")).click();
        chrome.findElement(By.xpath("//a[@href=\"#edit\"]")).click();

        String proyecto_nombre_editado = " editado";

        chrome.findElement(By.id("ItemEditTextbox")).sendKeys(proyecto_nombre_editado);

        chrome.findElement(By.id("ItemEditSubmit")).click();
        chrome.findElement(By.xpath("//div[@class=\"AddProjectLiDiv\"]")).click();

        Assertions.assertTrue((chrome.findElements(By.xpath("//td[text()='"+proyecto_nombre+proyecto_nombre_editado+"']")).size() == 1),
                "ERROR no se pudo editar bien el proyecto");
    }




}
