package core;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonTestNG  {
    static String baseUrl = "http://www.amazon.com/";
    static String expected = null;
    static String actual = null;
    Logic homePage = new Logic();
    // BeforeClass
    // AfterClass
    // @BeforeMethod
    // @AfterMethod
//-------------------------------------------
    // pre-conditions
    @BeforeTest
    public void launchBrowser() {
	System.out.println("launching firefox browser");
	homePage.getDriver("firefox", baseUrl);
    }

    @BeforeMethod
    public void verifyHomepageTitle() {
	expected = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
	actual = homePage.getTitle();
	Assert.assertEquals(actual, expected);
    }

    @Test(priority = 0)
    public void register() {
	// click(TestData.);
	// type("twotabsearchtextbox", "iphone");
	
	homePage.click("twotabsearchtextbox").type("bla").selectFirstSuggestedItem("issDiv0");
	expected = "Register: Mercury Tours";
	actual = homePage.getTitle();
	Assert.assertEquals(actual, expected);
    }

    // @Test(priority = 1)
    // public void support() {
    // click("SUPPORT");
    // expected = "Under Construction: Mercury Tours";
    // actual = getTitle();
    // Assert.assertEquals(actual, expected);
    // }

    // post-conditions
    @AfterMethod
    public void goBackToHomepage() {
	homePage.navigateToUrl(baseUrl);
    }

    @AfterTest
    public void terminateBrowser() {
	homePage.quit();
    }
}

//
//
// @BeforeSuite: The annotated method will be run before all tests in this suite
// have run.
//
// @AfterSuite: The annotated method will be run after all tests in this suite
// have run.
//
// @BeforeTest: The annotated method will be run before any test method
// belonging to the classes inside the tag is run.
//
// @AfterTest: The annotated method will be run after all the test methods
// belonging to the classes inside the tag have run.
//
// @BeforeGroups: The list of groups that this configuration method will run
// before. This method is guaranteed to run shortly before the first test method
// that belongs to any of these groups is invoked.
//
// @AfterGroups: The list of groups that this configuration method will run
// after. This method is guaranteed to run shortly after the last test method
// that belongs to any of these groups is invoked.
//
// @BeforeClass: The annotated method will be run before the first test method
// in the current class is invoked.
//
// @AfterClass: The annotated method will be run after all the test methods in
// the current class have been run.
//
// @BeforeMethod: The annotated method will be run before each test method.
//
// @AfterMethod: The annotated method will be run after each test method.
//
// @Test: The annotated method is a part of a test case