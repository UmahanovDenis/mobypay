import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class mobypay {

    @Test
    public void checkMobiPay(){

        System.setProperty("webdriver.chrome.driver","C:/Users/arche/Desktop/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

//Установка жесткого ожидания элемента в течение 30 сек, прежде чем с ним взаимодействовать
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//Переход на страницу
        driver.get("https://next.privat24.ua/mobile");

//Ввод телефонного номера для пополнения
        driver
                .findElement(By.xpath("//input[@data-qa-node='phone-number']"))
                .sendKeys("733407639");

 //Проверка популярной суммы пополнения популярный суммы на 50 UAH
        By hotSpotAmount50 = By.xpath("(//button[@data-qa-node='amount-hot-spot'])[1]");
        Assert.assertEquals("50", driver.findElement(hotSpotAmount50).getText());

 //Ввод номера карты
        driver
                .findElement(By.xpath("//input[@data-qa-node='numberdebitSource']"))
                .sendKeys("5309233034765085");

//Ввод срока действия карты
        driver
                .findElement(By.xpath("//input[@data-qa-node='expiredebitSource']"))
                .sendKeys("0124");

//Ввод CVV
        driver
                .findElement(By.xpath("//input[@data-qa-node='cvvdebitSource']"))
                .sendKeys("891");

//Ввод Имени владельца карты
        driver
                .findElement(By.xpath("//input[@data-qa-node='firstNamedebitSource']"))
                .sendKeys("Juliana");

//Ввод Фамилии владельца карты
        driver
                .findElement(By.xpath("//input[@data-qa-node='lastNamedebitSource']"))
                .sendKeys("Janssen");

//Нажатие кнопки "Продолжить"
        driver
                .findElement(By.xpath("//button[@data-qa-node='submit']")).click();

//Проверка номера телефона
        By checkTelNumb = By.xpath("//span[@data-qa-node='details']");
        Assert.assertEquals("Поповнення телефону. На номер +380733407639", driver.findElement(checkTelNumb).getText());

//Проверка оператора
        By chekOperator = By.xpath("//span[@data-qa-node='nameB']");
        Assert.assertEquals("Lifecell Ukraine", driver.findElement(chekOperator).getText());

//Проверка суммы пополнения
        By chekAmount = By.xpath("//span[@data-qa-node='amount']");
        Assert.assertEquals("50", driver.findElement(chekAmount).getText());

//Проверка комиссии
        By chekCommission = By.xpath("//span[@data-qa-node='commission']");
        Assert.assertEquals("2", driver.findElement(chekCommission).getText());

//Проверка номеракарты
        By chekCard = By.xpath("//td[@data-qa-node='card']");
        Assert.assertEquals("5309 **** **** 5085", driver.findElement(chekCard).getText());

//Проверка итоговой суммы списания
        By chekTotal = By.xpath("//div[@data-qa-node='total']");
        Assert.assertEquals("52", driver.findElement(chekTotal).getText());
    }

}
