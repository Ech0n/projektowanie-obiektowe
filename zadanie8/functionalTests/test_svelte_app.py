import time
import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

BASE_URL = "http://127.0.0.1:8000/index.html"

class TestRouting(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.driver.maximize_window()
        cls.wait = WebDriverWait(cls.driver, 2)
    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()
        
    def test_should_correctly_route_to_home(self):
        self.driver.get(BASE_URL + "/#/") 
        self.wait.until(EC.element_to_be_clickable((By.XPATH, "//h2[contains(text(),'Home Page')]")))

    def test_should_correctly_route_to_products(self):
        self.driver.get(BASE_URL + "/#/products") 
        self.wait.until(EC.element_to_be_clickable((By.XPATH, "//h2[contains(text(),'Products')]")))

    def test_should_correctly_route_to_alghoritms(self):
        self.driver.get(BASE_URL + "/#/algos") 
        self.wait.until(EC.element_to_be_clickable((By.XPATH, "//h2[contains(text(),'Algorithms Page')]")))
        
    def test_should_correctly_route_to_buttons(self):
        self.driver.get(BASE_URL + "/#/buttons") 
        self.wait.until(EC.element_to_be_clickable((By.XPATH, "//h2[contains(text(),'Button Page')]")))
        
    def test_should_display_navbar(self):
        self.driver.get(BASE_URL + "/#/") 
        self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "nav")))
    def test_invalid_path_should_display_navbar(self):
        self.driver.get(BASE_URL + "/#/invalid_path") 
        self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "nav")))


class TestAlgos(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        
        cls.driver = webdriver.Chrome()
        cls.driver.maximize_window()
        cls.wait = WebDriverWait(cls.driver, 3)
        cls.driver.get(BASE_URL + "/#/algos")
        cls.prime_input = cls.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#prime-input")))
        cls.reverse_input = cls.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#reverse-input")))
        cls.sum_input = cls.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#sum-input")))
        
        

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    def test_factorial_should_return_correct_scores(self):

        fact_input = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#factorial-input")))
        numbers_to_test = ["5","6","3"]
        results = ["120","720","6"]
        for i in range(len(numbers_to_test)):
            fact_input.clear()
            fact_input.send_keys(numbers_to_test[i])

            fact_result = self.wait.until(EC.presence_of_element_located((By.XPATH, "//section[h3[text()='Factorial Calculator']]//p")))
            self.assertIn(results[i], fact_result.text)
    
        
    def test_factorial_should_return_1_for_0_and_1(self):
        self.driver.get(BASE_URL + "/#/algos")

        fact_input = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#factorial-input")))
        fact_input.clear()
        fact_input.send_keys("0")

        fact_result = self.wait.until(EC.presence_of_element_located((By.XPATH, "//section[h3[text()='Factorial Calculator']]//p")))
        self.assertIn("1", fact_result.text)
        
        fact_input.clear()
        fact_input.send_keys("1")

        fact_result = self.wait.until(EC.presence_of_element_located((By.XPATH, "//section[h3[text()='Factorial Calculator']]//p")))
        self.assertIn("1", fact_result.text)

    def test_factorial_should_return_Invalid_Input_for_negatives(self):
        self.driver.get(BASE_URL + "/#/algos")

        fact_input = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#factorial-input")))
        numbers_to_test = ["-5","-1","-9"]
        for i in range(len(numbers_to_test)):
            fact_input.clear()
            fact_input.send_keys(numbers_to_test[i])

            fact_result = self.wait.until(EC.presence_of_element_located((By.XPATH, "//section[h3[text()='Factorial Calculator']]//p")))
            self.assertIn("Invalid input", fact_result.text)
    
    def test_factorial_should_result_should_be_empty_for_non_numbers(self):
        self.driver.get(BASE_URL + "/#/algos")

        fact_input = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#factorial-input")))
        numbers_to_test = ["text","1a","t2","132.2"]
        for i in range(len(numbers_to_test)):
            fact_input.clear()
            fact_input.send_keys(numbers_to_test[i])

            fact_result = self.wait.until(EC.presence_of_element_located((By.XPATH, "//section[h3[text()='Factorial Calculator']]//p")))
            self.assertIn("", fact_result.text)
    
        
    def test_fibonacci_result_should_be_correct(self):
        self.driver.get(BASE_URL + "/#/algos")
        fib_input = self.driver.find_element(By.CSS_SELECTOR, "#fibo-input")

        numbers_to_test = ["9","3","8","2"]
        results = ["34","2","21","1"]
        for i in range(len(numbers_to_test)):
            fib_input.clear()
            fib_input.send_keys(numbers_to_test[i])

            fib_result = self.driver.find_element(By.XPATH, "//section[h3[text()='Fibonacci Calculator']]//p")
            self.assertIn(results[i], fib_result.text)
            
    def test_fibonacci_result_should_be_1_for_1(self):
        self.driver.get(BASE_URL + "/#/algos")
        fib_input = self.driver.find_element(By.CSS_SELECTOR, "#fibo-input")
        fib_input.clear()
        fib_input.send_keys("1")
        fib_result = self.driver.find_element(By.XPATH, "//section[h3[text()='Fibonacci Calculator']]//p")
        self.assertIn("1", fib_result.text)
        
    def test_fibonacci_result_should_be_0_for_0(self):
        self.driver.get(BASE_URL + "/#/algos")
        fib_input = self.driver.find_element(By.CSS_SELECTOR, "#fibo-input")
        fib_input.clear()
        fib_input.send_keys("0")
        fib_result = self.driver.find_element(By.XPATH, "//section[h3[text()='Fibonacci Calculator']]//p")
        self.assertIn("0", fib_result.text)
        
    def test_fibonacci_result_should_be_Invalid_Input_for_negatives(self):
        self.driver.get(BASE_URL + "/#/algos")
        fib_input = self.driver.find_element(By.CSS_SELECTOR, "#fibo-input")

        numbers_to_test = ["-9","-3","-8","-2"]
        for i in range(len(numbers_to_test)):
            fib_input.clear()
            fib_input.send_keys(numbers_to_test[i])

            fib_result = self.driver.find_element(By.XPATH, "//section[h3[text()='Fibonacci Calculator']]//p")
            self.assertIn("Invalid input", fib_result.text)
    def test_factorial_should_result_should_be_empty_for_non_integers(self):
        self.driver.get(BASE_URL + "/#/algos")

        fib_input = self.wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#fibo-input")))
        numbers_to_test = ["text","1a","t2","132.2"]
        for i in range(len(numbers_to_test)):
            fib_input.clear()
            fib_input.send_keys(numbers_to_test[i])

            fib_result = self.wait.until(EC.presence_of_element_located((By.XPATH, "//section[h3[text()='Factorial Calculator']]//p")))
            self.assertIn("", fib_result.text)
    
    def test_prime_should_return_correctly_identify_prime_numbers(self):
        numbers_to_test = [23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71]
        for i in range(len(numbers_to_test)):
            self.prime_input.clear()
            self.prime_input.send_keys(str(numbers_to_test[i]))
            prime_result = self.driver.find_element(By.XPATH, "//section[h3[text()='Prime Checker']]//p")
            self.assertIn("is a prime number", prime_result.text)
    def test_prime_should_return_correctly_identify_non_prime_numbers(self):
        numbers_to_test = [23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71]
        for i in range(len(numbers_to_test)):
            self.prime_input.clear()
            self.prime_input.send_keys(str(numbers_to_test[i]*2))
            prime_result = self.driver.find_element(By.XPATH, "//section[h3[text()='Prime Checker']]//p")
            self.assertIn("not a prime number", prime_result.text)  
    def test_reverse_input_should_reverse_strings_correctly(self):
        strings_to_reverse = ["dog", "cat", "bird", "fish"]
        results = ["god", "tac", "drib", "hsif"]
        for i in range(len(strings_to_reverse)):
            self.reverse_input.clear()
            self.reverse_input.send_keys(strings_to_reverse[i])
            reversed_text = self.driver.find_element(By.XPATH, "//section[h3[text()='String Reverser']]//p")
            self.assertIn(results[i], reversed_text.text)
    def test_reverse_input_should_handle_empty_string(self):
        self.reverse_input.clear()
        reversed_text = self.driver.find_element(By.XPATH, "//section[h3[text()='String Reverser']]//p")
        self.assertIn("\"\"", reversed_text.text)
        
    def test_reverse_input_should_reverse_numbers(self):
        strings_to_reverse = ["12","-3","11122"]
        results = ["21","3-","22111"]
        for i in range(len(strings_to_reverse)):
            self.reverse_input.clear()
            self.reverse_input.send_keys(strings_to_reverse[i])
            reversed_text = self.driver.find_element(By.XPATH, "//section[h3[text()='String Reverser']]//p")
            self.assertIn(results[i], reversed_text.text)
    
    def test_digit_sum_should_correctly_sum_digits(self):
        numbers_to_test = ["101","100","131","999","8102"]
        results = ["2","1","5","27","11"]
        for i in range(len(numbers_to_test)):
            self.sum_input.clear()
            self.sum_input.send_keys(numbers_to_test[i])

            sum_result = self.driver.find_element(By.XPATH, "//section[h3[text()='Sum of Digits']]//p")
            self.assertIn(results[i], sum_result.text)
    
    def test_digit_sum_should_correctly_sum_single_digit_numbers(self):
        numbers_to_test = ["1","2","3","0","9"]
        results = ["1","2","3","0","9"]
        for i in range(len(numbers_to_test)):
            self.sum_input.clear()
            self.sum_input.send_keys(numbers_to_test[i])

            sum_result = self.driver.find_element(By.XPATH, "//section[h3[text()='Sum of Digits']]//p")
            self.assertIn(results[i], sum_result.text)
            
    def test_digit_sum_should_correctly_sum_negative_numbers(self):
        numbers_to_test = ["-101","-100","-131","-999","-8102"]
        results = ["2","1","5","27","11"]
        for i in range(len(numbers_to_test)):
            self.sum_input.clear()
            self.sum_input.send_keys(numbers_to_test[i])

            sum_result = self.driver.find_element(By.XPATH, "//section[h3[text()='Sum of Digits']]//p")
            self.assertIn(results[i], sum_result.text)
            
    def test_digit_sum_should_not_sum_strings(self):
        self.sum_input.clear()
        self.sum_input.send_keys("text")

        sum_result = self.driver.find_element(By.XPATH, "//section[h3[text()='Sum of Digits']]//p")
        self.assertIn("", sum_result.text)
        
class TestButtons(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.driver.maximize_window()
        cls.wait = WebDriverWait(cls.driver, 10)
        cls.driver.get(BASE_URL + "/#/buttons") 
    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()
        
    def test_color_button_should_change_color_on_click(self):
        color_button = self.wait.until(EC.element_to_be_clickable((By.XPATH, "//button[contains(text(),'change color')]")))
        initial_color = color_button.value_of_css_property("background-color")
        color_button.click()
        time.sleep(0.5)
        new_color = color_button.value_of_css_property("background-color")
        self.assertNotEqual(initial_color, new_color)

    def toggle_button_should_toggle(self):
        toggle_button = self.driver.find_element(By.XPATH, "//button[contains(text(),'Toggle text')]")
        toggle_button.click()
        time.sleep(0.3)
        toggled_text = self.driver.find_element(By.XPATH, "//p[contains(text(),'toggled text')]")
        self.assertTrue(toggled_text.is_displayed())

        toggle_button.click()
        time.sleep(0.3)
        toggled_texts = self.driver.find_elements(By.XPATH, "//p[contains(text(),'toggled text')]")
        self.assertEqual(len(toggled_texts), 0)


    def test_image_toggle_button_should_show_and_hide_image(self):
        toggle_image_button = self.driver.find_element(By.XPATH, "//button[contains(text(),'Show image') or contains(text(),'Hide image')]")
        
        toggle_image_button.click()
        time.sleep(0.3)
        image = self.driver.find_element(By.XPATH, "//img[@alt='Placeholder']")
        self.assertTrue(image.is_displayed(), "Image should be visible after clicking 'Show image'.")

        toggle_image_button = self.driver.find_element(By.XPATH, "//button[contains(text(),'Show image') or contains(text(),'Hide image')]")
        toggle_image_button.click()
        time.sleep(0.3)
        images = self.driver.find_elements(By.XPATH, "//img[@alt='Placeholder']")
        self.assertEqual(len(images), 0, "Image should be hidden after clicking 'Hide image'.")

    def test_increment_button_should_increment_count(self):
        increment_button = self.driver.find_element(By.XPATH, "//button[contains(text(),'Increment counter')]")
        count_paragraph = self.driver.find_element(By.XPATH, "//p[contains(text(),'Current count')]")
        initial_count = int(count_paragraph.text.split(":")[1].strip())
        increment_button.click()
        time.sleep(0.3)
        updated_count = int(count_paragraph.text.split(":")[1].strip())
        self.assertEqual(updated_count, initial_count + 1, "Counter did not increment by 1.")
        
    def test_decrement_button_should_decrement_count(self):
        decrement_button = self.driver.find_element(By.XPATH, "//button[contains(text(),'Decrement counter')]")
        count_paragraph = self.driver.find_element(By.XPATH, "//p[contains(text(),'Current count')]")
        initial_count = int(count_paragraph.text.split(":")[1].strip())
        decrement_button.click()
        time.sleep(0.3)
        updated_count = int(count_paragraph.text.split(":")[1].strip())
        self.assertEqual(updated_count, initial_count - 1, "Counter did not decrement by 1.")
        
    def test_button_should_change_values_after_consecutive_increment_and_decrement_clicks(self):
        increment_button = self.driver.find_element(By.XPATH, "//button[contains(text(),'Increment counter')]")
        decrement_button = self.driver.find_element(By.XPATH, "//button[contains(text(),'Decrement counter')]")
        count_paragraph = self.driver.find_element(By.XPATH, "//p[contains(text(),'Current count')]")
        initial_count = int(count_paragraph.text.split(":")[1].strip())
        increment_button.click()
        increment_button.click()
        decrement_button.click()
        decrement_button.click()
        increment_button.click()
        increment_button.click()
        decrement_button.click()
        increment_button.click()
        decrement_button.click()
        increment_button.click()
        decrement_button.click()
        increment_button.click()
        increment_button.click()
        increment_button.click()
        time.sleep(0.3)
        updated_count = int(count_paragraph.text.split(":")[1].strip())
        self.assertEqual(updated_count, initial_count + 4, "Counter did not increment by 1.")
        

class TestProducts(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.driver.maximize_window()
        cls.wait = WebDriverWait(cls.driver, 10)
        cls.driver.get(BASE_URL + "/#/products") 
        
    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()
        
    def test_products_should_be_fetched_correctly(self):

        self.wait.until(EC.element_to_be_clickable((By.XPATH, "//h2[contains(text(),'Products')]")))

        expected_names = {"Laptop", "Smartphone", "Headphones"}

        def expected_products_visible(driver):
            strong_elements = driver.find_elements(By.XPATH, "//ul/li/strong")
            rendered_names = {el.text.strip() for el in strong_elements}
            return expected_names.issubset(rendered_names)

        self.wait.until(expected_products_visible)

        strong_elements = self.driver.find_elements(By.XPATH, "//ul/li/strong")
        rendered_names = {el.text.strip() for el in strong_elements}

        for name in expected_names:
            self.assertIn(name, rendered_names, f"Product '{name}' not found in the list.")
            
if __name__ == "__main__":
    unittest.main()
