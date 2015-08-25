/* Copyright 2015 by Andreadakis Consulting */
package com.bootexample.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.startsWith;

/**
 * Cucumber step class for cucumber-login-test.feature
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
public class ExampleSteps implements En {

    private final WebDriver driver = new FirefoxDriver();

    @Given("^User enters \"(.*)\" as username$")
    public void userEntersUsername(String username) {
        driver.get("http://localhost:8080");
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
    }

    @Given("^he also enters \"(.*)\" as password$")
    public void userEntersPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    @Then("^he has successfully logged in$")
    public void he_has_successfully_logged_in() throws Throwable {
        assertThat(driver.getTitle(), startsWith("Start Page"));
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}