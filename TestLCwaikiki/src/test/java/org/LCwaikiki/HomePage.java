package org.LCwaikiki;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends Home {

    WebElement wElement = null;

    public By homePage = By.xpath("//span[@class='dropdown-label']");
    public By ProductPriceBy  = By.xpath("//*[@id=\"rightInfoBar\"]/div[1]/div/div[2]/div/div/div/span[2]");
    public By ProductBasketPrice  = By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/span");
    public By productQuantity = By.cssSelector("#ShoppingCartContent > div.row.mt-20.main-content-row > div.col-md-8 > div:nth-child(1) > div > span");

    public WebElement findWebElement(String byKey, String value){
        if(byKey == "id"){
            wElement = driver.findElement(By.id(value));
            wElement.click();
        }
        if(byKey == "xpath"){
            wElement = driver.findElement(By.xpath(value));
            wElement.click();
        }
        if(byKey == "classname"){
            wElement = driver.findElement(By.className(value));
            wElement.click();
        }
        return wElement;
    }

    public void sendKeysElement(String byKey, String value, String sValue ){
        wElement = findWebElement(byKey,value);
        wElement.sendKeys(sValue);
    }

    public String getTextValue(String path){
        String text = driver.findElement(By.xpath(path)).getText();
        return text;
    }

    public String homePage(){return getText(homePage); }

    public String getText(By homePage) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(homePage)).getText();
    }

    public String priceBasket(){
        return driver.findElement(ProductBasketPrice).getText();
    }

    public String productPrice(){
        return driver.findElement(ProductPriceBy).getText();
    }

    public String productQuantity(){
        return driver.findElement(productQuantity).getText();
    }

    public int randomNumber(int min, int max){
        int n = (int) ((Math.random()*((max-min)+1))+min);
        return n;
    }
}
