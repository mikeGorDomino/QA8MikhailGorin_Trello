package com.company.tests;


import helpers.BoardsPageHelper;
import helpers.LoginPageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests() {
        // ---------Press login button  ---
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        loginPage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();

    }

    @Test
    public void loginNegativeLoginIncorrect()  {
        // -------- Enter login/password -------------
        loginPage.enterLoginPassNotAttl("123", "login");
        Assert.assertTrue(loginPage.getErrorNotAttlMessage().
                contains("There isn't an account"), "The error-message" +
                "doesn't contain 'There isn't an account'");

    }

    @Test
    public void loginPositive() {
        loginPage.enterLoginPasswordAttl(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

        Assert.assertEquals("Boards", boardsPage.getNameBoardsButton());
    }


    @Test
    public void negativePasswordIncorrect() {
        //---- Fill in login-field and press "login with Attlassian"----
        loginPage.enterLoginPasswordAttl(LOGIN, "incorrectPass");
        loginPage.getErrorAttlMessage();

        Assert.assertTrue(loginPage.getErrorAttlMessage(). contains("email"));
    }
}
