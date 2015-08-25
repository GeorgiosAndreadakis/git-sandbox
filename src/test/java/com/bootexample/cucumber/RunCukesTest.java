/* Copyright 2015 by Andreadakis Consulting */
package com.bootexample.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Run definition for cucumber tests.
 * <p>
 *     Attention:<br/>
 *     the application must be running to pass the tests.
 * </p>
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources")
public class RunCukesTest {
}