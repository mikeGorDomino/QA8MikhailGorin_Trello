package com.company.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void initTests() throws InterruptedException {
        // ---------Press login button  ---
        WebElement loginIcon = driver.findElement(By.xpath("//a[contains(text(),'Log in')]"));
        loginIcon.click();
        // Thread.sleep(7000);
       waitUntilElementIsClick(By.id("login"),10);
    }

    @Test
    public void loginNegativeLoginIncorrect() throws InterruptedException {
        // -------- Enter login/password -------------
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"123");
        WebElement passwordField = driver.findElement(By.id("password"));
        fillField(passwordField,"pass");
        //Thread.sleep(3000);


        // -------- Click login button ------------
        driver.findElement(By.id("login")).click();
        //Thread.sleep(3000);
        waitUntilElementIsVisible(By.cssSelector("#error >.error-message"),10);

        // --------- Print error message ----------
        WebElement errorMessage = driver.findElement(By.cssSelector("#error >.error-message"));
        System.out.println("Error-message: " + errorMessage.getText());

        Assert.assertTrue(errorMessage.getText().contains("There isn't an account"),"The error-message" +
                "doesn't contain 'There isn't an account'");

    }

    @Test
    public void loginPositive() throws InterruptedException {
        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"m.gorin.airlife@gmail.com");
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.xpath("//input[@value = 'Log in with Atlassian']"), 10);
        driver.findElement(By.id("login")).click();
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.id("login"),10);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("4Gorini4");
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.id("password"), 10);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the Home page loading and print 'Boards' button -------
        //Thread.sleep(20000);
        waitUntilElementIsClick(By.xpath("//button[@aria-label = 'Open Boards Menu']"),20);
        System.out.println("Name of the button 'Boards': " + driver
                .findElement(By.xpath("//button[@aria-label = 'Open Boards Menu']")).getText());
    }

    @Test
    public void negativePasswordIncorrect() throws InterruptedException {
        //---- Fill in login-field and press "login with Attlassian"----
        WebElement loginField = driver.findElement(By.id("user"));
        fillField(loginField,"m.gorin.airlife@gmail.com");
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.id("user"), 10);
        driver.findElement(By.id("login")).click();
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.id("login"),10);

        //----- Fill in password field and press login-submit button-----------
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("qwerrc%tT");
        //Thread.sleep(2000);
        waitUntilElementIsClick(By.id("password"), 10);
        driver.findElement(By.id("login-submit")).click();

        //------Wait the error-message and print it -------
        //Thread.sleep(5000);
        waitUntilElementIsVisible(By.id("login-error"), 15);
        System.out.println("Error-message: " + driver
                .findElement(By.id("login-error")).getText());
    }





}
