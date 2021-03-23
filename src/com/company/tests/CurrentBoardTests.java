package com.company.tests;

import helpers.BoardsPageHelper;
import helpers.LoginPageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase{

    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests() {
        // ---------Press login button  ---
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterLoginPasswordAttl(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.getNameBoardsButton();
        boardsPage.qa8HaifaBoardOpen();

    }

    @Test
    public void createNewList() throws InterruptedException{
       // ---- count numbers of list in the board ----
        int listBefore = driver.findElements(By.cssSelector(".list-header")).size();

        WebElement addListButton = driver.findElement(By.xpath("//span[@class='placeholder']/.."));
        addListButton.click();
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.xpath("//span[@class='placeholder']/.."),5);
        WebElement titleListField = driver.findElement(By.xpath("//input[@name = 'name']"));
        fillField(titleListField, "QWDFKL");
        WebElement submitButton = driver.findElement(By.xpath("//input[@value = 'Add list']"));
        submitButton.click();
        WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelEditList.click();

        // ------- count quantity lists in the board after create newList -----
        int listAfterCreate = driver.findElements(By.cssSelector(".list-header")).size();

       Assert.assertEquals(listBefore+1, listAfterCreate, "The quantity lists after" +
                " newCreate is equals quantity before newCreate + 1");

    }

   @Test
    public void changeNameList() throws InterruptedException{

        WebElement addList = driver.findElement(By.xpath("//span[@class = 'placeholder']"));
        if(addList.getText().equals("Add a list")){
            addList.click();

            WebElement newNameList = driver.findElement(By.xpath("//input[@class = 'list-name-input']"));
            fillField(newNameList, "Gorin1-QA8");
            //Thread.sleep(2000);
            waitUntilElementIsClickable(By.xpath("//input[@class = 'list-name-input']"),10);

            WebElement saveNameList = driver.findElement(By.cssSelector("input[value='Add List']"));
            saveNameList.click();
            //Thread.sleep(2000);
            waitUntilElementIsClickable(By.cssSelector("input[value='Add list']"),10);

            WebElement closeCreateNewList = driver.findElement(By.xpath("//a[@class = 'icon-lg icon-close" +
                    " dark-hover js-cancel-edit']"));
            closeCreateNewList.click();
            //Thread.sleep(2000);
            waitUntilElementIsClickable(By.xpath("//a[@class = 'icon-lg icon-close" +
                    " dark-hover js-cancel-edit']"), 10);
        }

        // ----- I find index of the last list in Board --------------
        int lastList = driver.findElements(By.cssSelector(".list-header")).size()-1;

        //-------- Click on the header--------------
        WebElement lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);
        lastHeader.click();
       //Thread.sleep(2000);
       waitUntilElementIsClickable(By.cssSelector(".list-header"),10);

        //------- Change the header -----------------
        String newHeader = "newGorinList";
        WebElement lastNameList = driver.findElements(By.cssSelector(".js-list-name-input")).get(lastList);
        lastNameList.sendKeys(newHeader);
        //Thread.sleep(2000);
       waitUntilElementIsClickable(By.cssSelector(".js-list-name-input"),10);
        lastNameList.sendKeys(Keys.ENTER);
        //Thread.sleep(2000);
       waitUntilElementIsClickable(By.cssSelector(".js-list-name-input"),10);
        driver.navigate().refresh();
        Thread.sleep(2000);


        lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);

        Assert.assertEquals(lastHeader.getText(), newHeader);
    }

    @Test
    public void addNewCardAtList() throws InterruptedException{

        WebElement addList = driver.findElement(By.xpath("//span[@class = 'placeholder']"));
        if(addList.getText().equals("Add a list")) {
            createNewList();
        }
        WebElement addCard = driver.findElement(By.xpath("//a[@class='open-card-composer" +
                " js-open-card-composer']"));
        if(addCard.getText().equals("Add a card")) {
            WebElement newCard = driver.findElement(By.xpath("//span[@class = 'js-add-a-card']"));
            newCard.click();
            //Thread.sleep(2000);
            waitUntilElementIsClickable(By.xpath("//span[@class = 'js-add-a-card']"),10);
            String name = "FirstCard";
            WebElement cardName = driver.findElement(By.cssSelector(".js-card-title"));
            cardName.sendKeys(name);
            //Thread.sleep(2000);
            waitUntilElementIsClickable(By.cssSelector(".js-card-title"),10);
            cardName.sendKeys(Keys.ENTER);
            //Thread.sleep(2000);
            waitUntilElementIsClickable(By.cssSelector(".js-card-title"),10);
        }
        else {
            WebElement addAnotherCard = driver.findElement(By.xpath("//span[@class='js-add-another-card']"));
            addAnotherCard.click();
            String newName = "NoFirstCard1234";
            WebElement cardName = driver.findElement(By.cssSelector(".js-card-title"));
            cardName.sendKeys(newName);
            //Thread.sleep(2000);
            waitUntilElementIsClickable(By.cssSelector(".js-card-title"), 10);
            cardName.sendKeys(Keys.ENTER);
            //Thread.sleep(2000);
            waitUntilElementIsClickable(By.cssSelector(".js-card-title"), 10);
        }

    }

    @Test
    public void deleteList() throws InterruptedException{
        WebElement addList = driver.findElement(By.xpath("//span[@class = 'placeholder']"));
        if(addList.getText().equals("Add a list")) {
            createNewList();
        }

        WebElement listMenu = driver.findElement(By.cssSelector(".list-header-extras-menu"));
        listMenu.click();
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.cssSelector(".list-header-extras-menu"),10);
        WebElement archiveTheList = driver.findElement(By.cssSelector(".js-close-list"));
        archiveTheList.click();
        //Thread.sleep(2000);
        waitUntilElementIsClickable(By.cssSelector(".js-close-list"),10);

    }
}