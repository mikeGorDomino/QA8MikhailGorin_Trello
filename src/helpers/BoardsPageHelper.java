package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class BoardsPageHelper extends PageBase {


    public BoardsPageHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        //------Wait the Home page loading and print 'Boards' button -------

        waitUntilElementIsClickable(By.xpath("//button[@aria-label = 'Open boards menu']"), 20);
        System.out.println("Name of the button 'Boards': " + driver
                .findElement(By.xpath("//button[@aria-label = 'Open boards menu']")).getText());
    }

    public String getNameBoardsButton() {
        waitUntilElementIsClickable(By.xpath("//button[@aria-label = 'Open boards menu']"), 10);
        WebElement boardsButton = driver.findElement(By.xpath("//button[@aria-label = 'Open boards menu']"));
        return boardsButton.getText();

    }

    public void qa8HaifaBoardOpen() {
        WebElement qaHaifa8BoardOpen = driver.findElement(By
                .xpath("//a[@class = 'board-tile'][.//@title='QA Haifa8']"));
        qaHaifa8BoardOpen.click();
        waitUntilElementIsClickable(By.cssSelector(".mod-show-menu"),10);

    }
}
