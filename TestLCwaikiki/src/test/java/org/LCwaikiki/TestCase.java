package org.LCwaikiki;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.logging.Logger;
import static org.junit.Assert.assertTrue;


public class TestCase extends HomePage {
    @Test
    public void LCwaikikiTest() throws InterruptedException {

        Logger logger = Logger.getLogger(TestCase.class.getName());
        // Ana sayfanın açıldığı kontrol edilir.
        Assert.assertEquals("LC Waikiki | İlk Alışverişte Kargo Bedava! - LC Waikiki",driver.getTitle());
        logger.info("Ana sayfa açıldı ve kontrol edildi.");

        // Login ekranı açılır.
        findWebElement("classname","dropdown-toggle");
        logger.info("Login ekranı açıldı.");
        Thread.sleep(1*1000);

        //Cookies kapatılır.
        findWebElement("xpath", "//span[@class='closeBtn']");
        Thread.sleep(1*1000);

        //Login için gerekli bilgiler doldurulur.(Email ve Şifre)
        findWebElement("id", "LoginEmail");
        sendKeysElement("id","LoginEmail","sadekew829@niekie.com");
        findWebElement("id", "Password");
        sendKeysElement("id","Password","Alperkaan123!");
        logger.info("Email ve Password dolduruldu.");
        Thread.sleep(1*1000);

        // Login olunur.
        findWebElement("id","loginLink");
        Thread.sleep(1*1000);
        assertTrue(homePage().contains("Hesabım"));
        logger.info("Giriş yapıldığı kontrol edildi.");
        Thread.sleep(1*1000);

        //Arama kutucuğuna “Pantolon” kelimesi girilir.
        findWebElement("id","search_input");
        sendKeysElement("id","search_input","Pantolon");
        findWebElement("xpath","//button[@class='searchButton']");
        logger.info("Pantolon kelimesi aratıldı.");

        //Sayfa sonuna scroll etme ve daha fazla ürün görme butonuna basma işlemi yapılır.
        Actions action = new Actions(driver);
        WebElement w = driver.findElement(By.className("lazy-load-button"));
        action.moveToElement(w).perform();
        Thread.sleep(1*1000);
        findWebElement("classname","lazy-load-button");
        logger.info("Daha fazla ürün görme butonuna basıldı.");

        //Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
        int n = randomNumber(1,25);
        findWebElement("xpath","//div[@data-key='2']["+n+"]");
        logger.info("Ürün seçildi.");

        //Ürünü sepete ekleme işlemi gerçekleştirilir.
        findWebElement("xpath","(//a[@key='1'][@data-tracking-category='UrunDetay'])[1]");
        String productPrice = productPrice();
        findWebElement("id","pd_add_to_cart");
        Thread.sleep(1*1000);
        logger.info("Ürün sepete eklendi.");
        findWebElement("xpath","//i[@class='header-bag-icon bndl-shopping-bag bndl-shopping-bag-dims']");

        //Ürün detay fiyatı ile sepetteki fiyatı karşılaştırma işlemi yapılır.
        String basketPrice = priceBasket();
        Assert.assertEquals(productPrice,basketPrice);
        Thread.sleep(1*1000);
        logger.info("Fiyatlar aynı.");

        //Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.
        findWebElement("xpath","//a[@class='oq-up plus']");
        Thread.sleep(1*1000);
        assertTrue(productQuantity().contains("Sepetim (2 Ürün)"));
        logger.info("Ürün adedi 2 olarak artırıldı ve kontrol edildi.");
        Thread.sleep(1*1000);

        //Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.
        findWebElement("xpath","//i[@class='fa fa-trash-o']");
        Thread.sleep(1*1000);
        findWebElement("xpath","//a[@class='inverted-modal-button sc-delete ins-init-condition-tracking']");
        String basketText = getTextValue("//p[@class='cart-empty-title']");
        Assert.assertEquals("Sepetinizde ürün bulunmamaktadır.",basketText);
        logger.info("Ürün silindi ve sepetin boş olup olmadığı kontrol edildi");
    }
}
