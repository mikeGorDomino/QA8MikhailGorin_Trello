package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CurrentBoardHelper extends PageBase{

    public CurrentBoardHelper (WebDriver driver) {
        super(driver);
    }

    // ----- I find index of the last list in Board --------------
    //int numberOfList = countLists();

    //@FindBy (css = ".list-header")
    //List<WebElement> countListsInBoards;

    //@FindBy (xpath = "//span[@class='placeholder']/..")
    //WebElement addListButton;

    // @FindBy (name = "name")
    // WebElement titleListField;

    public int countLists(){

        return driver.findElements(By.cssSelector(".list-header")).size()-1;
    }

    public void pushButtonAddList(){
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        waitUntilElementIsClickable(By.xpath("//span[@class='placeholder']/"),10);
    }

    public void getNameNewList() {
        WebElement titleListField = driver.findElement(By.xpath("//input[@name = 'name']"));
        fillField(titleListField, "abvgdyKa");
        driver.findElement(By.xpath("//input[@value = 'Add list']")).click();
        waitUntilElementIsClickable(By.xpath("//input[@value = 'Add list']"), 10);
    }

    public void finishAddList(){
        WebElement cancelEdit = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelEdit.click();
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
    }

    public String checkAvailabilList (){
        return driver.findElement(By.xpath("//span[@class = 'placeholder']")).getText();
    }

   public void getNameNewCard() {
        waitUntilElementIsClickable(By.cssSelector(".open-card-composer"),10);
        driver.findElement(By.cssSelector(".open-card-composer")).click();
        waitUntilElementIsClickable(By.cssSelector(".js-card-title"),10);
        WebElement cardName = driver.findElement(By.cssSelector(".js-card-title"));
        fillField(cardName, "AddedCard");
        driver.findElement(By.cssSelector(".js-add-card")).click();
        waitUntilElementIsClickable(By.cssSelector(".js-cancel"),10);
        driver.findElement(By.cssSelector(".js-cancel")).click();
    }

      public int countCard() {
        return driver.findElements(By.cssSelector(".js-card-details")).size();
    }

    public void listDelete() {
        waitUntilElementIsClickable(By.cssSelector(".list-header-extras-menu"),10);
        driver.findElement(By.cssSelector(".list-header-extras-menu")).click();
        waitUntilElementIsClickable(By.cssSelector(".js-close-list"),10);
        driver.findElement(By.cssSelector(".js-close-list")).click();

    }

    public void clickOnHeader() {
        int lastList = driver.findElements(By.cssSelector(".list-header")).size()-1;
        WebElement lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);
        lastHeader.click();
        waitUntilElementIsClickable(By.cssSelector(".js-list-name-input"),10);
    }

    public void getNewName() {
        String newHeader = "123DRgor";
        int lastList = driver.findElements(By.cssSelector(".list-header")).size()-1;
        WebElement lastNameList = driver.findElements(By.cssSelector(".js-list-name-input"))
                .get(lastList);
        lastNameList.sendKeys(newHeader);
        lastNameList.sendKeys(Keys.ENTER);
        driver.navigate().refresh();
        waitUntilAllElementsArePresent(By.cssSelector(".list-header"),15);
    }

    public String nameOfHeaderList() {
        int lastList = driver.findElements(By.cssSelector(".list-header")).size()-1;
        WebElement lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);
        return lastHeader.getText();

    }
}
