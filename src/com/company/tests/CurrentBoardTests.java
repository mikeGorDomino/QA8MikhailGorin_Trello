package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase {

    @BeforeMethod
    public void initTests() throws InterruptedException {
        // ---------Press login button  ---
        WebElement loginIcon = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        //Thread.sleep(7000);
        waitUntilElementIsClick(By.xpath("//a[contains(text(),'Log in')]"),10);
        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField, "m.gorin.airlife@gmail.com");
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.id("user"),10);
        driver.findElement(By.id("login")).click();
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.id("login"),10);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("4Gorini4");
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.id("password"),10);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the Home page loading and print 'Boards' button -------
        //Thread.sleep(20000);
        waitUntilElementIsClick(By.xpath("//button[@aria-label = 'Open Boards Menu']"),20);
        System.out.println("Name of the button 'Boards': " + driver
                .findElement(By.xpath("//button[@aria-label = 'Open Boards Menu']")).getText());

        //----Open QA Haifa8 board ----
        WebElement qaHaifa8Board = driver.findElement(By.xpath("//div[@title = 'QA Haifa8']"));
                        // ("//a[@class = 'board-tile'][.//@title='QA Haifa8']"));
        qaHaifa8Board.click();
        Thread.sleep(3000);
        //waitUntilElementIsClick(By.xpath("//div[@title = 'QA Haifa8']"),20); - CHTOTO on meshaet, ne mogu ponyat prichinu


    }

    @Test
    public void createNewList() throws InterruptedException {
        WebElement addListButton = driver.findElement(By.xpath("//span[@class='placeholder']/.."));
        addListButton.click();
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.xpath("//span[@class='placeholder']/.."),5);
        WebElement titleListField = driver.findElement(By.xpath("//input[@name = 'name']"));
        fillField(titleListField, "titeList");
        WebElement submitButton = driver.findElement(By.xpath("//input[@value = 'Add List']"));
        submitButton.click();
        WebElement cancelEditList = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelEditList.click();
    }

   @Test
    public void changeNameList() throws InterruptedException{

        WebElement addList = driver.findElement(By.xpath("//span[@class = 'placeholder']"));
        if(addList.getText().equals("Add a list")){
            addList.click();

            WebElement newNameList = driver.findElement(By.xpath("//input[@class = 'list-name-input']"));
            fillField(newNameList, "Gorin1-QA8");
            //Thread.sleep(2000);
            waitUntilElementIsClick(By.xpath("//input[@class = 'list-name-input']"),5);

            WebElement saveNameList = driver.findElement(By.cssSelector("input[value='Add List']"));
            saveNameList.click();
            //Thread.sleep(2000);
            waitUntilElementIsClick(By.cssSelector("input[value='Add List']"),5);

            WebElement closeCreateNewList = driver.findElement(By.xpath("//a[@class = 'icon-lg icon-close" +
                    " dark-hover js-cancel-edit']"));
            closeCreateNewList.click();
            //Thread.sleep(2000);
            waitUntilElementIsClick(By.xpath("//a[@class = 'icon-lg icon-close" +
                    " dark-hover js-cancel-edit']"), 10);
        }

        // ----- I find index of the last list in Board --------------
        int lastList = driver.findElements(By.cssSelector(".list-header")).size()-1;

        //-------- Click on the header--------------
        WebElement lastHeader = driver.findElements(By.cssSelector(".list-header")).get(lastList);
        lastHeader.click();
       //Thread.sleep(2000);
       waitUntilElementIsClick(By.cssSelector(".list-header"),10);

        //------- Change the header -----------------
        String newHeader = "newGorinList";
        WebElement lastNameList = driver.findElements(By.cssSelector(".js-list-name-input")).get(lastList);
        lastNameList.sendKeys(newHeader);
        //Thread.sleep(2000);
       waitUntilElementIsClick(By.cssSelector(".js-list-name-input"),10);
        lastNameList.sendKeys(Keys.ENTER);
        //Thread.sleep(2000);
       waitUntilElementIsClick(By.cssSelector(".js-list-name-input"),10);
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
            waitUntilElementIsClick(By.xpath("//span[@class = 'js-add-a-card']"),10);
            String name = "FirstCard";
            WebElement cardName = driver.findElement(By.cssSelector(".js-card-title"));
            cardName.sendKeys(name);
            //Thread.sleep(2000);
            waitUntilElementIsClick(By.cssSelector(".js-card-title"),10);
            cardName.sendKeys(Keys.ENTER);
            //Thread.sleep(2000);
            waitUntilElementIsClick(By.cssSelector(".js-card-title"),10);
        }
        else {
            WebElement addAnotherCard = driver.findElement(By.xpath("//span[@class='js-add-another-card']"));
            addAnotherCard.click();
            String newName = "FirstCard1234";
            WebElement cardName = driver.findElement(By.cssSelector(".js-card-title"));
            cardName.sendKeys(newName);
            //Thread.sleep(2000);
            waitUntilElementIsClick(By.cssSelector(".js-card-title"), 10);
            cardName.sendKeys(Keys.ENTER);
            //Thread.sleep(2000);
            waitUntilElementIsClick(By.cssSelector(".js-card-title"), 10);
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
        waitUntilElementIsClick(By.cssSelector(".list-header-extras-menu"),10);
        WebElement archiveTheList = driver.findElement(By.cssSelector(".js-close-list"));
        archiveTheList.click();
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.cssSelector(".js-close-list"),10);

    }
}