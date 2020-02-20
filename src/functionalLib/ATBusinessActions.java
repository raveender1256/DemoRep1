package functionalLib;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Reporting.Report;
//import Reporting.Report;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class ATBusinessActions {
	WebDriver BDriver;
	String sTDFileName;
	String Navtextfiles;
	uiObjInfo.UILocators oUIObj = new uiObjInfo.UILocators();
	public String oParentBrw;
	String Screenshotpath;
	File scrFile;
	String appURL;
	String browser;
	String devicename;
	String platformname;
	String devc;
	String platformversion;
	Report selectReport;
	String Navigationtextfiles;
	public String[] arrMenuPag;
	public String[] arrMenuFilt;
	public String[] arrSortByFilt;
	public static int ChtestcaseNo;

	//Constructor
	public ATBusinessActions(WebDriver BDriver, String strURL, String devicename, String platformname, String devc,
			String platformversion, String browser, Report report) throws IOException {
		sTDFileName = ExcelRead.exlPath();
		Navtextfiles = report.Navigationtextfiles;
		Screenshotpath = report.strScreenshotPath;
		this.BDriver = BDriver;
		this.appURL = strURL;
		this.devicename = devicename;
		this.platformname = platformname;
		this.devc = devc;
		this.platformversion = platformversion;
		this.browser = browser;
		selectReport = report;
	}

	/*
	 * TC_openApplication Created By GRK Usage: Opening Spirit app in UAT
	 * environment from Excel sheet
	 * 
	 * @Information will read form Excel sheet
	 */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void openApplication() throws IOException, InterruptedException, BiffException, WriteException {
		System.out.println("---" + appURL + "---");

		if (browser.equalsIgnoreCase("Firefox")) {
			System.out.println("Inside firefox");
			selectReport.iSleep = 500;
			Thread.sleep(500);
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\Resources\\geckodriver.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			// capabilities.setVersion("45.0.2");
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setCapability("marionette", false);
			try {
				BDriver = new FirefoxDriver(capabilities);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			BDriver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.out.println("Inside Chrome");
			selectReport.iSleep = 500;
			Thread.sleep(500);
			System.out.println("YRR1");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/Resources/chromedriver.exe");
			System.out.println("YRR2");
			
			//ChromeOptions Options = new ChromeOptions();
			//Options.addArguments("Headless");		
			//BDriver =    new ChromeDriver(Options);	
			
			BDriver = new ChromeDriver();
			System.out.println("YRR3");
			BDriver.manage().window().maximize();
			System.out.println("YRR4");
			System.out.println("Browser open in chrome but not executed");

		} else if (browser.equalsIgnoreCase("IE")) {
			selectReport.iSleep = 2000;
			Thread.sleep(2000);
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Resources/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			// this line of code is to resolve protected mode issue
			// capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			// true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("unexpectedAlertBehaviour", "accept");
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setCapability("enablePersistentHover", false);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("nativeEvents", false);
			BDriver = new InternetExplorerDriver(capabilities);
			BDriver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("Safari")) {
			selectReport.iSleep = 2000;
			Thread.sleep(2000);
			SafariOptions safariOpts = new SafariOptions();
			DesiredCapabilities cap = DesiredCapabilities.safari();

			// ((Object) safariOpts).setUseCleanSession(true);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			// cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "dismiss");
			cap.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
			cap.setCapability(SafariOptions.CAPABILITY, safariOpts);
			cap.setBrowserName("safari");
			cap.setPlatform(Platform.MAC);

			BDriver = new SafariDriver();

		}

		selectReport.gbDriver = BDriver;
		System.out.println("Before android");
		BDriver.get(appURL);
		System.out.println(BDriver.getTitle());
		waitforpageload();
		Thread.sleep(5000);
		if (browser.contains("iOS"))
			Thread.sleep(12000);
		selectReport.ReportStep("Pass", "Open Application", "Application should open",
				"Opened " + appURL + " application successfully");
		System.out.println("after report step");
		if (browser.equalsIgnoreCase("IE")) {
			System.out.println("inside IE");
			try {
				BDriver.findElement(By.xpath("//a[contains(@id,'overridelink')]")).click();
				Thread.sleep(4000);
			} catch (Exception ex) {
				System.out.println("");
			}
		}
		new File(Navtextfiles).mkdir();
	}

	/*
	 * waitforpageload Created By GRK Usage: Wait up to load complete page
	 * 
	 * @Information will save into database
	 */
	public void waitforpageload() {
		System.out.println("inside wait load");
		JavascriptExecutor js = (JavascriptExecutor) BDriver;
		for (int i = 0; i < 5000; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	// Implicit wait
	public void pImplicitWait() {
		System.out.println("applying Implict wait");
		BDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	/*
	 * TC_closeApplication Created by GRK Usage: Closing all browsers
	 * 
	 * @Information will read form Excel sheet
	 */
	public void closeApplication() throws InterruptedException {
		Thread.sleep(2000);
		BDriver.quit();
		if (selectReport.curntTestStatus.equalsIgnoreCase("pass")
				&& selectReport.curntVerifysteptStatus.equalsIgnoreCase("pass")) {
			selectReport.noTestsPassed++;
		} else {
			selectReport.noTestsFailed++;
		}
		try {
			selectReport.CloseTestReport();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/*
	 * TC_captureScreenshotall Created by GRK Usage: Capturing Screenshots of the
	 * page
	 * 
	 * @Information For the validation purpose
	 */

	public void captureScreenshotall(String img) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy_HHmmss");
		TakesScreenshot oScn = (TakesScreenshot) BDriver; // Type casting

		//screenshot info will be saved in oScnshot variable (of type file)
		File oScnshot = oScn.getScreenshotAs(OutputType.FILE);

		//Creating a empty image file
		File oDest = new File(Screenshotpath, img + "_" + sdf.format(new Date()) + ".png");
		//Save in a Physical location
		org.apache.maven.surefire.shade.org.codehaus.plexus.util.FileUtils.copyFile(oScnshot, oDest);

	}

	/*
	 * TC_setText Created By GRK Usage: Generaic function for Entering value in
	 * textbox
	 * 
	 * @Information: Reading the value from the Excel Sheet
	 **/
	public void setText(String strLocator, String strvalue) {
		try {
			getElement(strLocator).clear();
			highLight(BDriver, strLocator);
			getElement(strLocator).click();
			getElement(strLocator).sendKeys(strvalue);
			System.out.println("After enter value");
			selectReport.ReportStep("Pass", "Input Text", "Set Text", "Value Entered as " + strvalue);
		} catch (Exception e) {
			System.out.println("Failed to find element " + strLocator); // + strLocator);
			selectReport.ReportStep("Fail", "Input Text", "Set Text", "Text not entered " + strvalue);
			//sreenshotSoftLine(Global.gstrResultPath + "\\setText" + strvalue);
		}
	}

	/*
	 * Error Validation Created By GRK Usage: Generaic function for validation
	 * errors
	 **/
	public void ErrorValidation(String strLocator, String ErrorText) {
		try {
			WebElement ErrorMsg = getElement(strLocator);
			ErrorMsg.isDisplayed();
			//			if (ErrorMsg.isDisplayed()) {
			//				System.out.println("Error validation displayed for given value");
			//				selectReport.ReportStep("Fail", "Validating Message", "Error Text", "Validation as " + ErrorText);
			//				Thread.sleep(3000);
			//				BDriver.close();
			//			} else {
			//				selectReport.ReportStep("Pass", "Validating Message", "No Validation Text",
			//						"Validation Successful ");
			//			}
			//			
			System.out.println("Error validation displayed for given value");
			selectReport.ReportStep("Fail", "Validating Message", "Error Text", "Validation displayed as " + ErrorText);
			//BDriver.close();
			closeApplication();

		} catch (Exception e) {
			System.out.println("No validation displayed and provided data is valid"); // + strLocator);
			selectReport.ReportStep("Pass", "Validating Message", "Error Text", "Validation displayed as " + ErrorText);
		}
	}

	/*
	 * controlClick Created By GRK Usage: Generic function for clicking on the
	 * control
	 * 
	 * @Information:
	 **/
	public void controlClick(String strLocator, String strObjName) {
		try {
			highLight(BDriver, strLocator);
			getElement(strLocator).click();
			Thread.sleep(1000);
			System.out.println("Clicked on element " + strObjName);
			selectReport.ReportStep("Pass", "Click Control", "Click Control", "Clicked on control " + strObjName);
		} catch (Exception e) {
			System.err.println("Failed to find element " + e.getMessage());
			selectReport.ReportStep("Fail", "Click Control", "Click Control", "Unable to click(element not found) " + strObjName);
		}
	}

	/*
	 * TC_movetoElement Created By GRK
	 */
	public void MoveToElement(String strLocator, String strObjName) {
		Actions a = new Actions(BDriver);
		try {
			a.moveToElement(getElement(strLocator)).build().perform();
			System.out.println("Hover on the Element " + strObjName);
			selectReport.ReportStep("Pass", "Mouse Hover", "Mouse Hover", "Moved to Element" + strObjName);
		} catch (Exception e) {
			System.err.println("Failed to hover on element " + e.getMessage());
		}
	}

	/*
	 * selectValueforAutoSearch Created By GRK Usage: Generaic function for clicking
	 * on the desired value for autosearch dropdown
	 */
	public void selectFromAutosearch(String strLocator, String value) {
		WebDriverWait wait = new WebDriverWait(BDriver, 30);
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(strLocator)));
			List<WebElement> autoresult = BDriver.findElements(By.xpath(strLocator));
			int size = autoresult.size();
			String eleValue = null;
			for (int i = 0; i <= size; i++) {
				eleValue = autoresult.get(i).getText();
				if (eleValue.equalsIgnoreCase(value)) {
					autoresult.get(i).click();
					break;
				}
				System.out.println(eleValue);
				selectReport.ReportStep("Pass", "autosearchlist", "selected a value", value);
			}
		} catch (Exception e) {
			System.err.println("Failed to select the value from autosearchlist" + e.getMessage());
			selectReport.ReportStep("Fail", "autosearchlist", "Not found", value);
		}

	}

	/*
	 * Dropdown Created by GRK Usage: Generic function to dropdown
	 * 
	 * @Information:
	 **/
	@SuppressWarnings("unused")
	public void SelectValueFromDropdown(String drpdown_xp, String strSelectVal) {
		System.out.println(drpdown_xp);
		System.out.println(strSelectVal);
		Select xsSelectafter = null;
		int iVal = 0;
		try {
			if (BDriver.findElement(By.xpath(drpdown_xp)).isDisplayed()) {
				System.out.println("inside");
				xsSelectafter = new Select(BDriver.findElement(By.xpath(drpdown_xp)));
				List<WebElement> selectitemValues = xsSelectafter.getOptions();
				System.out.println("List of value get from the dropdown");
				System.out.println(selectitemValues);
				for (WebElement itemValues : selectitemValues) {
					if (itemValues.getText().contains(strSelectVal)) {
						//xsSelectafter.selectByValue(itemValues.getText() );
						xsSelectafter.selectByIndex(iVal);
						break;
					}
					iVal = iVal + 1;
				}
				if (false) {
					System.out.println("Specified Value " + strSelectVal + " is not found in the dropdown");
				}

			}
		} catch (Exception e) {
			System.out.println("No dropdown to select " + strSelectVal + "is available");
			selectReport.ReportStep("Fail", "Dropdown selection", "Dropdown found", drpdown_xp);
		}

	}

	/*
	 * Dropdownselection Created By GRK Usage: Generaic function for clicking
	 * on the desired value for autosearch dropdown
	 */

	public void DropdownSelection(String strObject, String listvalue) {
		try {
			Select dropdown= new Select (getElement(strObject));
			System.out.println(dropdown);
			dropdown.selectByVisibleText(listvalue);
			selectReport.ReportStep("Pass", "DropdownSelection", "selected a value", listvalue);

		} catch(Exception e) {
			System.out.println("No dropdown to select " + listvalue + "is available");
			selectReport.ReportStep("Fail", "DropdownSelection", "Value not matched to list", listvalue);
		}

	}

	/*
	 * getElement Created By GRK Usage: Generaic function for finding element bases
	 * on xpath sent
	 * 
	 * @Information:Capturing element
	 **/
	private WebElement getElement(String strLocator) {
		WebElement element = null;
		if (strLocator.startsWith("//")) {
			element = BDriver.findElement(By.xpath(strLocator));
		}
		if (strLocator.startsWith("id")) {
			strLocator = strLocator.substring(strLocator.indexOf('=') + 1, strLocator.length());
			element = BDriver.findElement(By.id(strLocator));
		}
		if (strLocator.toLowerCase().startsWith("name")) {
			strLocator = strLocator.substring(strLocator.indexOf('=') + 1, strLocator.length());
			element = BDriver.findElement(By.name(strLocator));
		}

		if (strLocator.toLowerCase().startsWith("css")) {
			strLocator = strLocator.substring(strLocator.indexOf('=') + 1, strLocator.length());
			element = BDriver.findElement(By.cssSelector(strLocator));
		}

		if (strLocator.toLowerCase().startsWith("link")) {
			strLocator = strLocator.substring(strLocator.indexOf('=') + 1, strLocator.length());
			element = BDriver.findElement(By.linkText(strLocator));
		}

		return element;

	}

	/*
	 * switchchildBrowser Created By GRK Usage: Validate the Switch to another
	 * Browser
	 * 
	 * @Information will read form Excel sheet
	 */
	public void switchchildBrowser() throws Exception {
		oParentBrw = BDriver.getWindowHandle();
		// Get the All Browsers Info
		Set<String> oAllBrws = BDriver.getWindowHandles();
		for (String oEachBrw : oAllBrws) {
			if (!(oEachBrw.equals(oParentBrw))) {
				BDriver.switchTo().window(oEachBrw);
				Thread.sleep(1000);
				System.out.println(BDriver.switchTo().window(oEachBrw).getTitle());

				// BDriver.close();
			}
		}

	}


	/*
	 * Scrolling to element Created By GRK Usage: Generic function for an element to
	 * bring in visible screen
	 */
	public void scrollToEle(String strlocator, String strObjName) {
		JavascriptExecutor js = (JavascriptExecutor) BDriver;
		WebElement element = getElement(strlocator);
		try {
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			System.err.println("Failed to find element " + strObjName + e.getMessage());
		}
	}

	/*
	 * * closeerrorpopup Created By GRK Usage: Entering user name and password from
	 * Excel spread sheet
	 */
	public void closeerrorpopup() throws Exception {
		List<WebElement> we1 = BDriver.findElements(By.xpath("//button[@ng-show='!message.disableCloseButton']"));
		for (int i = 0; i < we1.size(); i++) {
			we1.get(i).click();
			Thread.sleep(500);
		}
	}

	/*
	 * switchToIframe Created By GRK Usage: Checking the ifames and switch to the
	 * iframe
	 */
	public void switchFrame(String strLocator) throws Exception {
		// Fetching iframes count
		int iframes = BDriver.findElements(By.tagName("iframe")).size();
		// Iterating through switching frames o find the required element
		for (int i = 0; i <= iframes; i++) {
			String ftitle = BDriver.switchTo().frame(i).getTitle();
			System.out.println(ftitle);
			BDriver.switchTo().frame(i);
			if (getElement(strLocator).isDisplayed()) {
				System.out.println("Switch to ifame " + "IfameTitle:" + ftitle);
				break;
			} else {
				continue;
			}
		}

	}

	/*
	 * Highlight the element
	 * Created by GRK 
	 * Usage: A generic function for highlighting the control where action is going to perform
	 */
	public void highLight(WebDriver driver, String StrLocator) {
		// Javascriptexecutor to highlight the focused element
		WebElement ele = getElement(StrLocator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: lightyellow; border: 1px solid red;');", ele);

	}

	/*
	 * selectCheckbox Created By GRK 
	 * Usage: Generic function for selecting checkbox
	 **/
	public void selectchk(String strLocator, String strObjName) {
		try {
			if (!getElement(strLocator).isSelected()) {
				Thread.sleep(1000);
				System.out.println("Clicked on element " + strObjName);
				selectReport.ReportStep("Pass", "Click Control", "Click Control", "Clicked on control " + strObjName);
			}
			highLight(BDriver, strLocator);
			getElement(strLocator).click();
		} catch (Exception e) {
			System.err.println("Failed to find element " + strObjName + e.getMessage());
			selectReport.ReportStep("Fail", "Click Control", "Click Control", "Check box not selected " + strObjName);
		}
	}


	/*
	 * selectCheckbox Created By Raveender
	 * Usage: Generic function for selecting checkbox
	 **/
	public void selectUnchk(String strLocator, String strObjName) {
		try {
			if (getElement(strLocator).isSelected()) {
				Thread.sleep(1000);
				System.out.println("Clicked on element " + strObjName);
				selectReport.ReportStep("Pass", "Click Control", "Click Control", "Clicked on control " + strObjName);
			}
			highLight(BDriver, strLocator);
			getElement(strLocator).click();
		} catch (Exception e) {
			System.err.println("Failed to find element " + strObjName + e.getMessage());
			selectReport.ReportStep("Fail", "Click Control", "Click Control", "Check box not selected " + strObjName);
		}
	}

	//Generic method to select from list
	public void selectFromList(String strLocator, String value) {

		try {

			List<WebElement> autoresult = BDriver.findElements(By.xpath(strLocator));
			int size = autoresult.size();
			String eleValue = null;
			for (int i = 0; i <= size; i++) {
				eleValue = autoresult.get(i).getText();
				if (eleValue.equalsIgnoreCase(value)) {
					autoresult.get(i).click();
					break;
				}
				System.out.println(eleValue);
				selectReport.ReportStep("Pass", "autosearchlist", "selected a value", value);
			}
		}catch (Exception e) {
			System.err.println("Failed to select the value from autosearchlist"+e.getMessage());
			selectReport.ReportStep("Fail", "autosearchlist", "Value not matched", value);
		}
	}	

	/*
	 * * TC_Login Created By GRK Usage: Getting user name and password from Excel
	 * spread sheet
	 */
	public void LogIn() throws Exception {
		pImplicitWait();
		ExcelRead oExcelLogin = new ExcelRead(sTDFileName, "Login");
		System.out.println("ATTestSC sheet read");
		setText(oUIObj.username_id, oExcelLogin.getCellData("UserName", 1));
		System.out.println("User name entered in username field");
		setText(oUIObj.password_id, oExcelLogin.getCellData("Password", 1));
		System.out.println("Password entered in Password field");
		System.out.println("ATTestSC sheet read");
		controlClick(oUIObj.signin_id, "Sign In");
		System.out.println("clicked Signin button");

	}

	/*
	 * * TC_Logout Created By GRK Usage: Logging out from the site and closing the
	 * driver instance spread sheet
	 */
	public void LogoutClose() throws Exception {
		pImplicitWait();
		controlClick(oUIObj.Logout_xp, "User arrow down");
		controlClick(oUIObj.Signout_xp, "Sign Out");
		System.out.println("clicked Signout button");
		controlClick(oUIObj.Confirm_xp, "Signing Out confirmation");
		System.out.println("Confirmed Signout");
		BDriver.close();
	}





	/*
	 * TC_Navigation to WORK Definition page after login to applictaion
	 * Usage: Navigation to WORK Definition page from Home page
	 * Created By Raveender 
	 */

	//Navigate2WorkDef
	
	public void Navigate2WorkDef()
	{
		pImplicitWait();
		waitforpageload();
		//ExcelRead WDTestSc = new ExcelRead(sTDFileName, "WorkDef");
		//System.out.println("WDTestSc sheet read"); 
		controlClick(oUIObj.home_id, "Home");
		controlClick(oUIObj.SCE_xp, "Supplu Chain Execution");   
		controlClick(oUIObj.workdef_xp, "Work Definition");   		
		waitforpageload();
		controlClick(oUIObj.Task_xp, "Task bar");
		waitforpageload();
		waitforpageload();
		controlClick(oUIObj.MWDLink_xp, "Manage Work Definition link");         	    		
	}


	/*
	 * TC_Add New Supplier Operation to a WORK Definition by searching with an Item  
	 * Usage: for  bulk list of test data --Add New Supplier Operation to a WORK Definition by searching with an Item 
	 * Created By Raveender 
	 */
	
	//AddNewSupOperation
	public void AddNewSupOperation() throws InterruptedException
	{
		pImplicitWait();
		waitforpageload();
		ExcelRead WDTestSc = new ExcelRead(sTDFileName, "WorkDef");
		System.out.println("WorkDef excel sheet read");
		int eRowCount = WDTestSc.rowCount();
		System.out.println("Total Number of test Records given in Excel: " +eRowCount);
		for (int j=1;j<eRowCount;j++) {

			System.out.println("********************************************************************");	 		
			System.out.println("  ");
			System.out.println(j +". Adding Operation for a WD with Item :  "+ WDTestSc.getCellData("Item", j));
			System.out.println("  ");
			System.out.println("**********************************************************************");		


			setText(oUIObj.ItemInput_xp,WDTestSc.getCellData("Item", j));
			waitforpageload();
			scrollToEle(oUIObj.AddWDIcon_xp,"Scroll to down");	
			waitforpageload();
			controlClick(oUIObj.WorkDefLOV_xp, "WorkDefName LOV");
			waitforpageload(); 	
			controlClick(oUIObj.WDSearchLink_xp, "WorkDefLOV search link");
			waitforpageload(); 	
			setText(oUIObj.WDNameInput_xp,WDTestSc.getCellData("WDName", j));
			waitforpageload(); 	
			controlClick(oUIObj.WDNameSerch_xp, "WorkDefLOV search button");
			waitforpageload(); 	
			controlClick(oUIObj.WDNameresult_xp, "WorkDefName select record");
			waitforpageload(); 	
			controlClick(oUIObj.WDNameOK_xp, "WorkDefLOV OK button");
			Thread.sleep(3000);
			controlClick(oUIObj.searchButton_xp, "WD Search Button");
			waitforpageload(); 		
			BDriver.findElement(By.xpath(oUIObj.ResultTable_xp)).getSize();	
			List<WebElement> TableList = BDriver.findElements(By.xpath(oUIObj.ResultTable_xp));
			int size = TableList.size();	
			System.out.println("Total Number of WD versions are: " +size);		
			String ReqVersionElement_xp =oUIObj.VersionIconpart1_xp+size+oUIObj.VersionIconpart2_xp;
			BDriver.findElement(By.xpath(ReqVersionElement_xp)).click();  
			waitforpageload(); 
			controlClick(oUIObj.AddVersion_xp, " Add + icon");
			waitforpageload(); 
			controlClick(oUIObj.SaveAndEdit_xp, "Save and Edit button");
			waitforpageload(); 
			controlClick(oUIObj.AddOperationsIcon_xp, "Add Operations Icon");
			waitforpageload(); 
			controlClick(oUIObj.AddNewSupplierOperataion_xp, "Add New Supplier Operataion");
			waitforpageload(); 
			controlClick(oUIObj.ActionsArrow_xp, "Actions Arrow");		
			waitforpageload(); 
			Thread.sleep(3000);
			controlClick(oUIObj.ClickAssign_xp, "Click Assign");
			waitforpageload(); 
			int SeqNum = 10*size;
			setText(oUIObj.SeqNum_xp,""+SeqNum);
			waitforpageload(); 
			setText(oUIObj.OpName_xp,WDTestSc.getCellData("OPName", j)); 
			waitforpageload(); 
			setText(oUIObj.OpDesc_xp,WDTestSc.getCellData("Description", j)); 
			waitforpageload(); 
			setText(oUIObj.OpWorkCenter_xp,WDTestSc.getCellData("WorkCenter", j));  			
			waitforpageload();
			setText(oUIObj.OpLeadTime_xp,WDTestSc.getCellData("Lead", j)); 
			waitforpageload(); 
			setText(oUIObj.OSPItem_xp,WDTestSc.getCellData("OSPItem", j));
			waitforpageload(); 
			SelectValueFromDropdown(oUIObj.UOM_xp,WDTestSc.getCellData("UOM", j));  
			waitforpageload(); 
			setText(oUIObj.OSPSupplier_xp,WDTestSc.getCellData("Supplier", j)); 			
			waitforpageload(); 
			setText(oUIObj.Fixed_xp,WDTestSc.getCellData("Fixed", j));  			

			if(size>1) {
				waitforpageload(); 
				selectUnchk(oUIObj.GenShipment_xp,"Unchekc the GetSHipment checkbox"); 
			}
			waitforpageload(); 
			Thread.sleep(3000);
			controlClick(oUIObj.OKButton_xp, "OK Button");			
			waitforpageload(); 
			Thread.sleep(3000);
			controlClick(oUIObj.SaveAndClose_xp, "Save And Close Operations page");			
			waitforpageload(); 
			//Thread.sleep(3000);
			/*
			 * if(BDriver.findElement(By.xpath(oUIObj.SaveandCloseBtn_xp)).isDisplayed()) {
			 * controlClick(oUIObj.SaveandCloseBtn_xp, "Save And Close Operations page");
			 * waitforpageload(); }
			 */

			System.out.println("*****************************************************************************************");
			System.out.println(" ");
			System.out.println("Successfully Added New Supplier Operation to the WD with Item  "+ WDTestSc.getCellData("Item", j));
			selectReport.ReportStep("Pass", "Add NewSupplierOperation", "Should able add this record",
					"Successfully Added New Supplier Operation to the WD with Item  "+ WDTestSc.getCellData("Item", j));
			System.out.println(" ");
			System.out.println("*****************************************************************************************");
			
		}  // close for loop

		controlClick(oUIObj.DoneButton_xp, "click Done on the WD page");
	} // close method









	/*
	 * TC_Add New Supplier Operation to a WORK Definition by searching with an Item  
	 * Usage: for  single row of test data --Add New Supplier Operation to a WORK Definition by searching with an Item 
	 * Created By Raveender 
	 */
	
	/*  //commented to be reoved

 	//AddNewSupOperation
	public void AddNewSupOperation() throws InterruptedException
	{
		pImplicitWait();
   		waitforpageload();
   		ExcelRead WDTestSc = new ExcelRead(sTDFileName, "WorkDef");
   		System.out.println("WDTestSc sheet read");
   		setText(oUIObj.ItemInput_xp,WDTestSc.getCellData("Item", 1));
   		waitforpageload();
   		scrollToEle(oUIObj.AddWDIcon_xp,"Scroll to down");	
   		waitforpageload();
   		//setText(oUIObj.WorkDef_xp,WDTestSc.getCellData("WDName", 1)); //"Main");
   		//Thread.sleep(3000);
   		//BDriver.findElement(By.xpath(oUIObj.WorkDef_xp)).sendKeys(Keys.ARROW_DOWN);
		//BDriver.findElement(By.xpath(oUIObj.WorkDef_xp)).sendKeys(Keys.ENTER);
   		controlClick(oUIObj.WorkDefLOV_xp, "WorkDefName LOV");
   		waitforpageload(); 	
   		controlClick(oUIObj.WDSearchLink_xp, "WorkDefLOV search link");
   		waitforpageload(); 	
   		setText(oUIObj.WDNameInput_xp,WDTestSc.getCellData("WDName", 1));
   		waitforpageload(); 	
   		controlClick(oUIObj.WDNameSerch_xp, "WorkDefLOV search button");
   		waitforpageload(); 	
   		controlClick(oUIObj.WDNameresult_xp, "WorkDefName select record");
   		waitforpageload(); 	
   		controlClick(oUIObj.WDNameOK_xp, "WorkDefLOV OK button");
   		Thread.sleep(3000);
   		controlClick(oUIObj.searchButton_xp, "WD Search Button");
   		waitforpageload(); 		
   		BDriver.findElement(By.xpath(oUIObj.ResultTable_xp)).getSize();	
   		List<WebElement> TableList = BDriver.findElements(By.xpath(oUIObj.ResultTable_xp));
		int size = TableList.size();	
		System.out.println("Total Number of WD versions are: " +size);
		int i = size-1;
		String ReqVersionElement_xp =oUIObj.VersionIconpart1_xp+i+oUIObj.VersionIconpart2_xp;
		BDriver.findElement(By.xpath(ReqVersionElement_xp)).click();  
		waitforpageload(); 
		controlClick(oUIObj.AddVersion_xp, "Click Add + icon");
		waitforpageload(); 
		controlClick(oUIObj.SaveAndEdit_xp, "Save and Edit button");
		waitforpageload(); 
		controlClick(oUIObj.AddOperationsIcon_xp, "Add Operations Icon");
		waitforpageload(); 
		controlClick(oUIObj.AddNewSupplierOperataion_xp, "Add New Supplier Operataion");
		waitforpageload(); 
		controlClick(oUIObj.ActionsArrow_xp, "Actions Arrow");		
		waitforpageload(); 
		controlClick(oUIObj.ClickAssign_xp, "Click Assign");
		waitforpageload(); 
		int SeqNum = 10*size;
		setText(oUIObj.SeqNum_xp,""+SeqNum);
		setText(oUIObj.OpName_xp,WDTestSc.getCellData("OPName", 1)); //"OSP Services");
		setText(oUIObj.OpDesc_xp,WDTestSc.getCellData("Description", 1));  //"OSP Services");
		setText(oUIObj.OpWorkCenter_xp,WDTestSc.getCellData("WorkCenter", 1));  //"Vendor");
		waitforpageload();
		setText(oUIObj.OpLeadTime_xp,WDTestSc.getCellData("Lead", 1));   //"10");
		setText(oUIObj.OSPItem_xp,WDTestSc.getCellData("OSPItem", 1));   //"OSP SERVICES");
		setText(oUIObj.OSPSupplier_xp,WDTestSc.getCellData("Supplier", 1));  //"JASS RESOURCES INC");
		SelectValueFromDropdown(oUIObj.UOM_xp,WDTestSc.getCellData("UOM", 1));  //"HOUR");
		setText(oUIObj.Fixed_xp,WDTestSc.getCellData("Fixed", 1));   //"120");
		controlClick(oUIObj.OKButton_xp, "OK Button");
		waitforpageload(); 
		controlClick(oUIObj.SaveAndClose_xp, "Save And Close Operations page");
		waitforpageload(); 
	} 


	 */ //commented to be reoved



}   // closing class