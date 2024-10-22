from selenium import webdriver

#chrome driver
from selenium.webdriver.chrome.service import Service
#-- Chrome
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
options = Options()
options.add_argument('--disable-dev-shm-usage')
options.add_argument('--ignore-certificate-errors')  # Ignore SSL certificate errors
options.add_argument('--ignore-ssl-errors')          # Ignore SSL errors
options.add_argument('--no-sandbox')                 # Add no-sandbox to avoid issues in headless environments

# Initialize WebDriver with Chrome options
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)

driver.implicitly_wait(4)
driver.get("https://rahulshettyacademy.com/angularpractice/")
#  //a[contains(@href,'shop')]    a[href*='shop']
driver.find_element(By.CSS_SELECTOR," a[href*='shop']").click()
products = driver.find_elements(By.XPATH,"//div[@class='card h-100']")

for product in products :
    productName = product.find_element(By.XPATH, "div/h4/a").text
    if productName == "Blackberry":
        product.find_element(By.XPATH, "div/button").click()

driver.find_element(By.CSS_SELECTOR,"a[class*='btn-primary']").click()
driver.find_element(By.XPATH,"//button[@class='btn btn-success']").click()
driver.find_element(By.ID,"country").send_keys("ind")
wait = WebDriverWait(driver,10)
wait.until(expected_conditions.presence_of_element_located((By.LINK_TEXT,"India")))
driver.find_element(By.LINK_TEXT,"India").click()
driver.find_element(By.XPATH,"//div[@class='checkbox checkbox-primary']").click()
driver.find_element(By.CSS_SELECTOR,"[type='submit']").click()
successText = driver.find_element(By.CLASS_NAME,"alert-success").text
assert "Success! Thank you!" in successText
driver.close()




















