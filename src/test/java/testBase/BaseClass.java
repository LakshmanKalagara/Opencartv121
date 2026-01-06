package testBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
/*WebDriver is static because we created a driver and in ExtentReportUtility file we created called 
BaseClass method captureScreen In that we created other driver we created a new object of BaseClass
 for Takescreenshot to avoid the clash between these two drivers we create make Webdriver driver STATIC*/
	
public static WebDriver driver; 
public Logger logger; //loading log4j2
public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		logger=LogManager.getLogger(this.getClass());//log4j2
		
		//loading config.properties
		FileReader file=new FileReader("./src/test/resources//config.properties");
		p=new Properties();
		p.load(file);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("not matching os");
				return;
			}
			switch(br.toLowerCase()) 
			{
			case "chrome":cap.setBrowserName("chrome");break;
			case "edge":cap.setBrowserName("MicrosoftEdge");break;
			case "firefox":cap.setBrowserName("firefox");break;
			default :System.out.println("not matching browser");return;
			}
			
			driver=new RemoteWebDriver(new URL("http://192.168.2.26:4444"),cap);
			
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			
			switch(br.toLowerCase()) 
			{
			case "chrome":driver=new ChromeDriver();break;
			case "edge":driver=new EdgeDriver();break;
			case "firefox":driver =new FirefoxDriver();break;
			default:System.out.println("Not matchiing browser");return;
			}
			
		}
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
		driver.get(p.getProperty("appURL"));//reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();    
	}
	
	public String randomString() {
		String generatedstring=RandomStringUtils.insecure().nextAlphabetic(5);
		
		return generatedstring;
		
	}
	
	public String randomNumString() {
	
		String generatedTelephone=RandomStringUtils.insecure().nextNumeric(10);
		return generatedTelephone;
	}
	public String randomPwdString() {
		
		String generatedpNum=RandomStringUtils.insecure().nextNumeric(3);
		String generatedpString=RandomStringUtils.insecure().nextAlphabetic(3);
		return (generatedpString+generatedpNum);
	}
	public String captureScreen (String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs (OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp +".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		}


}
