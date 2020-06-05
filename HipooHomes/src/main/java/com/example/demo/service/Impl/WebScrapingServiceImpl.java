package com.example.demo.service.Impl;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Images;
import com.example.demo.entities.Property;
import com.example.demo.service.PropertyService;
import com.example.demo.service.WebScrappingService;

@Service
public class WebScrapingServiceImpl implements WebScrappingService {

	@Autowired
	PropertyService propertyService;

	@Override
	public void getIdealistaImages() {
		List<Property> listProperty = new ArrayList<>();
		listProperty = propertyService.GetAll();

		for (Property property : listProperty) {
			String url = property.getUrl();
			int numPhotos = property.getNumPhotos();
			List<String> listImages = new ArrayList<>();
				
			
		
			
			for (int i = 2; i <= numPhotos; i++) {
				String fotoUrl = url + "foto/" + i + "/";

				try {
					Document doc = Jsoup.connect(fotoUrl)
							.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36")
							.ignoreHttpErrors(true)
							.timeout(5000)
							.referrer( url + "foto/" + (i-1) + "/")
							.header("accept-encoding"," gzip, deflate, br")
							.header("accept-language" ,"en-GB,en-US;q=0.9,en;q=0.8,fr;q=0.7")
							.cookie("ABTastySession", "mrasn=&lp=https://www.idealista.com/inmueble/85457865/foto/1/&sen=0")
							.cookie("utag_main","_sn:1$_se:1$_ss:1$_st:1590210329570$ses_id:1590208529570%3Bexp-session$_pn:1%3Bexp-session$_prevVtSource:portalSites%3Bexp-1590212129579$_prevVtCampaignCode:%3Bexp-1590212129579$_prevVtDomainReferrer:idealista.com%3Bexp-1590212129579$_prevVtSubdomaninReferrer:www.idealista.com%3Bexp-1590212129579$_prevVtUrlReferrer:https%3A%2F%2Fwww.idealista.com%2Finmueble%2F85457865%2Ffoto%2F1%2F%3Bexp-1590212129579$_prevVtprevPageName:11%3A%3Adetalle%3A%3Ahome%3Bexp-1590212129580$dc_visit:1$dc_event:1%3Bexp-session$dc_region:eu-central-1%3Bexp-session" )
							.timeout(12000)
							.followRedirects(true)
							.get();

					Elements metalinks = doc.select("meta[property=\"og:image\"]");
					String imageUrl = metalinks.attr("content");
					listImages.add(imageUrl);
					System.out.println(imageUrl);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}

		//propertyService.updateProperty(property.getId());

		}
	}

	@Override
	public void getFotocasaProperties() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
	    options.setExperimentalOption("useAutomationExtension", false);
	    WebDriver driver =  new ChromeDriver(options);
	    driver.get("https://www.fotocasa.es/");
	    
	    driver.findElement(By.xpath("//*[@id=\"App\"]/div[2]/div[1]/div[1]/div[1]/div[3]/div[2]/form/div/div/div/input")).sendKeys("madrid");
	    driver.findElement(By.xpath("//*[@id=\"App\"]/div[2]/div[1]/div[1]/div[1]/div[3]/div[2]/form/div/div/div/input")).sendKeys(Keys.ENTER);
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    WebElement Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("re-Searchresult-itemRow")));
	    
	    driver.findElement(By.xpath("//*[@id=\"App\"]/div[3]/div[1]/div[6]/div[2]/div[3]/div[1]/div/div[2]/a")).click();
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//*[@id=\"App\"]/div[3]/div[1]/div[1]/div[2]/a")).click();
	    
		
		
	}

	
}
