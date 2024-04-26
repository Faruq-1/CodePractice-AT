package project.bb;
import java.io.IOException;
import java.time.Duration;

import java.util.List;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class footer_verificatin {
    

	public static void main(String[] args) throws IOException {

		String mainPage = "B&B HOTELS: book a hotel in France and in Europe | Official website";
		String faqPage = "Frequently asked questions | B&B HOTELS";
		String contactPage = "Contact";
		String myBookingsPAge = "My bookings | B&B HOTELS";
		String Bme_loyalty_program = "B&me, the free and rewardful loyalty program from B&B HOTELS | B&B HOTELS";
		String BB_HOTELS_Club = "B&B HOTELS Club";
		String Corporate_website = "Homepage - B&B HOTELS";
		String Become_a_mandate_manager = "Become a Mandate Manager - B&B HOTELS";
		String Become_a_franchisee = "Franchise - B&B HOTELS";
		String Careers = "Richesses humaines | Recrutement B&B Hôtels";
		String OurCommunity_image1 = "Affordable hotels in Europe and in the world | B&B HOTELS";
		String OurCommunity_image2 = "Affordable hotels in Europe and in the world | B&B HOTELS";
		String About_CSR = "Our CSR strategy - B&B HOTELS";
		String OurCommunity_image3 = "Affordable hotels in Europe and in the world | B&B HOTELS";
		String AppStore = "Download our free app and stay connected to B&B Hotels! | B&B HOTELS";
		String googlePlayStore = "Download our free app and stay connected to B&B Hotels! | B&B HOTELS";
		String facebook = "www.facebook.com";
		String instagram = "www.instagram.com";
		String youtube = "B&B HOTELS France - YouTube";
		String tiktok = "www.tiktok.com";
		String linkdin = "B&B HOTELS France | LinkedIn";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		String url = "https://bbhotels:Q1TZWshVS7dQFbfnC@preprod-akamai.moveon-hotelbb.com/en/fr";
		driver.get(url);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Accept cookies if present
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler"))).click();
		} catch (Exception e) {
			System.out.println("No cookies popup found.");
		}

		String footer_path = "/html/body/div[3]/div/div[1]/footer/div/div[1]/div[2]/div[1]/..//a";

		List<WebElement> footerLinks = driver.findElements(By.xpath(footer_path));

		for (WebElement linkElement : footerLinks) {
			String link = linkElement.getAttribute("href");

			// Open the link in a new tab
			((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", link);

			// Switch back to the main tab
			driver.switchTo().window(driver.getWindowHandles().iterator().next());

		}

		Set<String> newtab = driver.getWindowHandles();

		for (String string : newtab) {
			driver.switchTo().window(string);
			String pageTitle = driver.getTitle();

			// Faq page verification
			if (pageTitle.contains(faqPage)) {

				// topimage section
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(" //*[@class='faq-groups-country']")));
					System.out.println(" `Select the country where your stay takes place` " + "section is Displayed");
				} catch (Exception e) {
					System.out
							.println(" `Select the country where your stay takes place` " + "section is Not Displayed");
				}

				try { // fetching the image links
					List<WebElement> topImageSection = driver
							.findElements(By.xpath("//*[@class='faq-groups-country__list']//a"));

					for (WebElement imageSection : topImageSection) {

						boolean imageValue = imageSection.isDisplayed();
						if (imageValue == true) {
							System.out.println(imageSection.getText() + " image is displayed");
						}
						try {
							wait.until(ExpectedConditions.elementToBeClickable(imageSection));

							System.out.println(imageSection.getText() + " image is clickable");

						} catch (Exception e) {
							System.out.println(imageSection.getText() + " is not clickable");
						}
					}
				} catch (Exception e) {
					System.out.println("Top image section are not fetched " + " verify the xpath");
				}

				// navbar
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='faq-groups']")));
					System.out.println("Faq nav bar is displayed");
					try {
						List<WebElement> navElements = driver.findElements(By.xpath("//*[@class='faq-groups']//a"));
						for (WebElement navElement : navElements) {
							boolean navElementdisplayed = navElement.isDisplayed();
							if (navElementdisplayed == true) {
								try {
									wait.until(ExpectedConditions.elementToBeClickable(navElement));
									System.out.println(navElement.getText() + " is clickable");
								} catch (Exception e) {
									System.out.println(navElement.getText() + " is not clickable");
								}

							} else {
								System.out.println("Error: navbar element is not displayed");
							}
						}
					} catch (Exception e) {
						System.out.println("Navbar Elements are not fetched" + " verify the xpath");
					}

				} catch (Exception e) {
					System.out.println("Faq nav bar is not displayed");
				}

				// navbar content

				try {
					wait.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='faq-content__group']/..")));
					System.out.println("Faq content section is displayed");

					try {
						List<WebElement> faqContent = driver
								.findElements(By.xpath("//*[@class='faq-list__opener opener']"));
						int refCount = 1;
						for (WebElement content : faqContent) {

							try {
								wait.until(ExpectedConditions.elementToBeClickable(content));
								System.out.println(refCount++ + " block is Clickable");
							} catch (Exception e) {
								System.out.println(refCount++ + " block is not clickable");
							}
						}
					} catch (Exception e) {
						System.out.println("Faq content block is not fetched" + " verify the xpath");
					}

				} catch (Exception e) {

					System.out.println("Faq Content section is not displayed");
				}

				// Contact us block
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='aside-block']")));
					System.out.println("Contact us block is displayed");

					try {
						WebElement ContactusButton = driver.findElement(By.xpath("//*[@id='BTNContactUSFaq']"));

						try {
							wait.until(ExpectedConditions.elementToBeClickable(ContactusButton));
							System.out.println(ContactusButton.getText() + " is clickable");
						} catch (Exception e) {
							System.out.println(ContactusButton.getText() + " is not clickable");
						}

					} catch (Exception e) {
						System.out.println("Conactus button is not fetched" + " verify the xpath");
					}

				} catch (Exception e) {
					System.out.println("Contact us block is not displayed");
				}

				// breadcrumd
				try {

					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[@class='breadcrumbs-wrap desktop-show']")));
					System.out.println("Breadcrum section is displayed");
					try {
						List<WebElement> breadcrumb = driver
								.findElements(By.xpath("//*[@class='breadcrumbs-wrap desktop-show']//li"));

						for (WebElement breadcrumbElement : breadcrumb) {

							boolean breadcrumbEle = breadcrumbElement.isDisplayed();

							if (breadcrumbEle == true) {
								System.out.println(breadcrumbElement.getText() + " breadcrumb is displayed");

								try {
									wait.until(ExpectedConditions.elementToBeClickable(breadcrumbElement));
									System.out.println(breadcrumbElement.getText() + " breadcrumb is clickable");
								} catch (Exception e) {
									System.out.println(breadcrumbElement.getText() + " breadcrumb is not clickable");
								}
							} else {
								System.out.println(breadcrumbElement.getText() + " breadcrumb is not displayed");
							}
						}

					} catch (Exception e) {
						System.out.println("BreadCrumb Elements are not fetched" + " verify the xpath");
					}

				} catch (Exception e) {
					System.out.println("Breadcrumb section is not displayed");
				}

			}

			// contact page verification --> processing
			else if (pageTitle.contains(contactPage)) {
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='tabs']")));
					System.out.println("Tab section is visible");

					try {
						List<WebElement> contactTabsection = driver.findElements(By.xpath("//*[@class='tabs']//a"));

						for (WebElement tabSection : contactTabsection) {
							boolean TabElement = tabSection.isDisplayed();
							if (TabElement == true) {
								System.out.println(tabSection.getText() + " tab is displayed");
								try {
									wait.until(ExpectedConditions.elementToBeClickable(tabSection));
									System.out.println(tabSection.getText() + " tab is clickable");
								} catch (Exception e) {
									System.out.println(tabSection.getText() + " tab is not clcikable");
								}

							} else {
								System.out.println(tabSection.getText() + "tab is not displayed");
							}
						}

					} catch (Exception e) {
						System.out.println("Tab section Elements not fetched" + " verify the xpath");
					}

				} catch (Exception e) {
					System.out.println("Tab section is not visible");
				}

				try {

					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[@class='support__select-category-row']")));
					System.out.println("Dropdown section is visible");
					try {

						WebElement dropdownElement = driver
								.findElement(By.xpath("//span[text()='Select your country']"));

						boolean dropdown = dropdownElement.isDisplayed();
						if (dropdown == true) {

							System.out.println(dropdownElement.getText() + " is displayed");
							try {
								wait.until(ExpectedConditions.elementToBeClickable(dropdownElement));
								System.out.println(dropdownElement.getText() + " is clickable");
							} catch (Exception e) {
								System.out.println(dropdownElement.getText() + " is not clickable");
							}
						} else {
							System.out.println(dropdownElement.getText() + " is not displayed");
						}

					} catch (Exception e) {
						System.out.println("Dropdown Element is not fetched" + " verify the xpath");
					}

				} catch (Exception e) {
					System.out.println("Dropdown section is not visible");
				}

				// here

				// Contact page breadcrumd
				try {

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='desktop-show']")));
					System.out.println("Breadcrum section is displayed");
					try {
						List<WebElement> breadcrumb = driver.findElements(By.xpath("//*[@class='desktop-show']//li"));

						for (WebElement breadcrumbElement : breadcrumb) {

							boolean breadcrumbEle = breadcrumbElement.isDisplayed();

							if (breadcrumbEle == true) {
								System.out.println(breadcrumbElement.getText() + " breadcrumb is displayed");

								try {
									wait.until(ExpectedConditions.elementToBeClickable(breadcrumbElement));
									System.out.println(breadcrumbElement.getText() + " breadcrumb is clickable");
								} catch (Exception e) {
									System.out.println(breadcrumbElement.getText() + " breadcrumb is not clickable");
								}
							} else {
								System.out.println(breadcrumbElement.getText() + " breadcrumb is not displayed");
							}
						}

					} catch (Exception e) {
						System.out.println("BreadCrumb Elements are not fetched" + " verify the xpath");
					}

				} catch (Exception e) {
					System.out.println("Breadcrumb section is not displayed");
				}
				// here
			} else if (pageTitle.contains(myBookingsPAge)) {

				// My booking section
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[contains(@class,'sign-in__block')]")));
					System.out.println("My booking section is visible");

					try {

						// Country dropdown
						WebElement dropdown = driver
								.findElement(By.xpath("//*[contains(@class,'jcf-select-form-select')]"));

						boolean dropdownVerify = dropdown.isDisplayed();

						if (dropdownVerify == true) {

							System.out.println(dropdown.getText() + " dropdown is displayed");

							try {
								wait.until(ExpectedConditions.elementToBeClickable(dropdown));
								System.out.println(dropdown.getText() + " dropdown is clickable");

							} catch (Exception e) {
								System.out.println(dropdown.getText() + " dropdown is not clickable");
							}
						} else {
							System.out.println(dropdown.getText() + " dropdown is not displayed");
						}

						// Confirm button
						try {
							wait.until(
									ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit-submit']")));

							System.out.println("Confirm button is visible");

							try {
								WebElement confirmButton = driver.findElement(By.xpath("//*[@id='edit-submit']"));

								if (confirmButton.isEnabled() == true) {

									// confirmButton.getText()--> is getting the text
									System.out.println("Confirm button is clickable");
								} else {
									System.out.println("Confirm button is not clickable");
								}

							} catch (Exception e) {
								System.out.println("Confirm button is not clickable " + "Verify the xpath ");
							}

						} catch (Exception e) {
							System.out.println("Confirm button is not visible");
						}

						// here....

					} catch (Exception e) {
						System.out.println("My booking section elements are not fetched " + "Verify the xpath");
					}

				} catch (Exception e) {

					System.out.println("My booking section is not visible ");
				}

				// sign-in section
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[@class='content-block__heading']/..")));
					System.out.println("Sign-in section is visible");

					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Sign in')]")));

						System.out.println("sign-in button is visible");

						try {
							WebElement signInButton = driver
									.findElement(By.xpath("//button[contains(text(),'Sign in')]"));

							if (signInButton.isEnabled() == true) {

								System.out.println(signInButton.getText() + " button is clickable");
							} else {
								System.out.println(signInButton.getText() + " button is not clickable");
							}
						} catch (Exception e) {
							System.out.println("sign-in button is not displayed" + " verify the xpath");
						}
					} catch (Exception e) {
						System.out.println("sign-in button is not visible");
					}

				} catch (Exception e) {
					System.out.println("sign-in section is not visible");
				}

			}

			//

			else {

				System.out.println("Validation under Process ");
			}

		}

		driver.quit();
	}
}
