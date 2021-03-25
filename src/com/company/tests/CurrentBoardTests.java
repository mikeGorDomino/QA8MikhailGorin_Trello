package com.company.tests;

import helpers.BoardsPageHelper;
import helpers.CurrentBoardHelper;
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
    CurrentBoardHelper listPage;

    @BeforeMethod
    public void initTests() {
        // ---------Press login button and enter to the Board QA Haifa8 ---
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        listPage = new CurrentBoardHelper(driver);
        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterLoginPasswordAttl(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.getNameBoardsButton();
        boardsPage.qa8HaifaBoardOpen();

    }

    @Test
    public void createNewList() {
       // ---- count numbers of list in the board before add ----
        int listBeforeCreate = listPage.countLists();
        listPage.pushButtonAddList();
        listPage.getNameNewList();
        listPage.finishAddList();
        // ------- count quantity lists in the board after create newList -----
       int listAfterCreate = listPage.countLists();
       Assert.assertEquals(listBeforeCreate+1, listAfterCreate, "The quantity lists after" +
               " newCreate is equals quantity before newCreate + 1");

    }

   @Test
    public void changeNameList() {

        if (listPage.checkAvailabilList().equals("Add a list")) {
            listPage.pushButtonAddList();
            listPage.getNameNewList();
            listPage.finishAddList();
        }
       listPage.clickOnHeader();
       String oldName = listPage.nameOfHeaderList();

       listPage.getNewName();
       String newName = listPage.nameOfHeaderList();

       Assert.assertNotEquals(oldName, newName);
    }

    @Test
    public void addNewCardAtList() {
        int cardsBefore = listPage.countCard();

        if (listPage.checkAvailabilList().equals("Add a list")) {
            listPage.pushButtonAddList();
            listPage.getNameNewList();
            listPage.finishAddList();
        }
        else {
            listPage.getNameNewCard();
        }

        int cardsAfterCreate = listPage.countCard();
        Assert.assertEquals(cardsBefore+1,cardsAfterCreate,
                "The quantity of cards after adding is not equal to cards before adding plus one");

    }

    @Test
    public void deleteList() {

        if (listPage.checkAvailabilList().equals("Add a list")) {
            listPage.pushButtonAddList();
            listPage.getNameNewList();
            listPage.finishAddList();
        }
        int listBeforeDelete = listPage.countLists();
        listPage.listDelete();
        int listAfterDelete = listPage.countLists();

        Assert.assertEquals(listBeforeDelete-1, listAfterDelete,
                "The quantity of lists after deletion is not list before deletion minus one");
    }
}