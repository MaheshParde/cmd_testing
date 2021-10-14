package in.ecgc.smile.erp.accounts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class SkeletonTest {
	
   /* The annotated method is a part of a test case */
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }
  
  /* The annotated method will be run after each test method */
  @BeforeMethod
  public void beforeMethod() {
  }

  /* The annotated method will be run before each test method */
  @AfterMethod
  public void afterMethod() {
  }

  /*
   * It helps you to write data-driven tests which essentially means that same
   * test method can be run multiple times with different data-sets.
   */
  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  
  /*
   * The annotated method will be run before the first test method in the current
   * class is invoked
   */
  @BeforeClass
  public void beforeClass() {
  }

  /*
   * The annotated method will be run after all the test methods in the current
   * class have been run
   */
  @AfterClass
  public void afterClass() {
  }

  /*
   * The annotated method will be run before any test method belonging to the
   * classes inside the tag is run
   */
  @BeforeTest
  public void beforeTest() {
  }
  
  /* The annotated method will be run after all the test methods belonging to the 
   * classes inside the tag have run */
  @AfterTest
  public void afterTest() {
  }

  /* The annotated method will be run before all tests in this suite have run */
  @BeforeSuite
  public void beforeSuite() {
  }
  
  /* The annotated method will be run after all tests in this suite have run */  
  @AfterSuite
  public void afterSuite() {
  }

}
