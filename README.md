# OpenCart Automation Project

This repository contains my OpenCart UI automation project.

This project automates an OpenCart e-commerce website using:

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model

I created this project step by step as an SDET automation practice project. This README explains what I did, why I did it, and how someone else can follow the same process.

## Local OpenCart Website

I installed OpenCart locally on my laptop using XAMPP.

The website URL used in this project is:

```text
http://localhost/opencartsite/
```

Before running the automation tests, Apache and MySQL should be running in XAMPP.

## How I Downloaded And Installed OpenCart Locally

This part is important because anyone who wants to run this automation project first needs the OpenCart website running on their own laptop.

### Step 1: Downloaded And Installed XAMPP

I installed XAMPP because OpenCart needs a local server.

XAMPP gives us:

- Apache: runs the website in the browser
- MySQL/MariaDB: stores website data
- phpMyAdmin: helps manage the database
- PHP: needed because OpenCart is a PHP application

I installed XAMPP in:

```text
C:\xampp
```

### Step 2: Started Apache And MySQL

I opened XAMPP Control Panel and started:

- Apache
- MySQL

Then I checked:

```text
http://localhost/
http://localhost/phpmyadmin/
```

If `http://localhost/` opens, Apache is working.

If `http://localhost/phpmyadmin/` opens, MySQL and phpMyAdmin are working.

### Step 3: Downloaded OpenCart ZIP File

I downloaded OpenCart from the official OpenCart website.

After downloading, I got a ZIP file.

Then I extracted the ZIP file.

Inside the extracted OpenCart folder, there is usually an `upload` folder.

That `upload` folder contains the actual OpenCart website files.

### Step 4: Created Project Folder Inside htdocs

I created this folder inside XAMPP:

```text
C:\xampp\htdocs\opencartsite
```

The folder name `opencartsite` is important because my automation code opens this URL:

```text
http://localhost/opencartsite/
```

If someone uses a different folder name, they must also change the URL in the automation code.

### Step 5: Copied OpenCart Files Into htdocs

I copied the OpenCart files into:

```text
C:\xampp\htdocs\opencartsite
```

Important:

- Copy the files from inside the `upload` folder.
- Do not copy only the ZIP file.
- Do not keep the files only in Downloads.
- The file `index.php` should be directly inside `opencartsite`.

Correct example:

```text
C:\xampp\htdocs\opencartsite\index.php
```

Because of this folder path, my local OpenCart URL became:

```text
http://localhost/opencartsite/
```

### Step 6: Created OpenCart Config Files

Before or during installation, OpenCart needs config files.

I renamed:

```text
C:\xampp\htdocs\opencartsite\config-dist.php
```

to:

```text
C:\xampp\htdocs\opencartsite\config.php
```

I also renamed:

```text
C:\xampp\htdocs\opencartsite\admin\config-dist.php
```

to:

```text
C:\xampp\htdocs\opencartsite\admin\config.php
```

If these files are already created by the installer, then no need to rename again.

### Step 7: Created Database In phpMyAdmin

I opened phpMyAdmin:

```text
http://localhost/phpmyadmin/
```

Then I created a database:

```text
opencartsite
```

This database stores OpenCart data like:

- customers
- products
- cart data
- orders
- admin settings

### Step 8: Completed OpenCart Installation

I opened:

```text
http://localhost/opencartsite/
```

Then I followed the OpenCart installer steps and entered the database details:

```text
Database Host: localhost
Database Name: opencartsite
Database User: root
Database Password: blank
Database Port: 3306
```

Then I created the OpenCart admin username, password, and email.

### Step 9: Removed The install Folder

After installation, I removed the `install` folder:

```text
C:\xampp\htdocs\opencartsite\install
```

This is required because OpenCart shows a warning if the `install` folder is still present.

### Step 10: Checked Storefront And Admin URLs

Now the OpenCart site opens locally.

Storefront URL:

```text
http://localhost/opencartsite/
```

Admin URL:

```text
http://localhost/opencartsite/admin/
```

These are the URLs needed before running automation.

## How Someone Else Can Install The Same OpenCart Site

If another person downloads this project from GitHub, they should do this first:

1. Install XAMPP in `C:\xampp`.
2. Start Apache and MySQL from XAMPP Control Panel.
3. Download OpenCart ZIP from the official OpenCart website.
4. Extract the ZIP file.
5. Copy OpenCart files into:

```text
C:\xampp\htdocs\opencartsite
```

6. Create database in phpMyAdmin:

```text
opencartsite
```

7. Open installer:

```text
http://localhost/opencartsite/
```

8. Use local database details:

```text
Host: localhost
User: root
Password: blank
Database: opencartsite
Port: 3306
```

9. Finish installation.
10. Delete the `install` folder.
11. Open:

```text
http://localhost/opencartsite/
```

After this, they can run this automation project.

## How I Created This Automation Project

### Step 1: Created Maven Project

I created a Maven project in Eclipse.

Project name:

```text
opencart-automation
```

Maven is used because it makes dependency management easy. Instead of manually downloading Selenium and TestNG JAR files, Maven downloads them automatically from the `pom.xml` file.

### Step 2: Added Dependencies In pom.xml

I added the required dependencies in `pom.xml`.

The main dependencies are:

- Selenium Java
- TestNG
- DataFaker

Selenium is used for browser automation.

TestNG is used for running test cases and adding assertions.

DataFaker is used for creating random test data like first name, last name, email, and password.

### Step 3: Created Framework Folder Structure

I created the project structure like this:

```text
src/main/java
  base
  pages

src/test/java
  base
  tests
```

Meaning:

- `src/main/java/base`: common reusable page methods
- `src/main/java/pages`: page classes
- `src/test/java/base`: browser setup and teardown
- `src/test/java/tests`: actual test cases

This structure keeps the project clean and easy to understand.

## Framework Design

This project follows Page Object Model.

Page Object Model means:

- Each web page has its own Java class.
- Web elements are stored in page classes.
- Actions are written as methods in page classes.
- Test classes call those methods.

This helps avoid duplicate code.

For example, registration page elements and actions are written in:

```text
src/main/java/pages/RegistrationPage.java
```

The registration test cases are written in:

```text
src/test/java/tests/RegistrationTest.java
```

## Base Classes

### BaseTest.java

I created `BaseTest.java` for browser setup and closing the browser.

What it does:

1. Opens Microsoft Edge browser.
2. Opens the OpenCart local URL.
3. Adds implicit wait.
4. Maximizes the browser window.
5. Closes the browser after each test.

This avoids writing browser setup code again and again in every test class.

### BasePage.java

I created `BasePage.java` for common reusable Selenium methods.

It contains reusable methods like:

- wait for element
- click element
- scroll to element
- enter text
- check element is displayed
- JavaScript scroll and click helpers

This helps all page classes use the same common actions.

## Page Classes Created

### HeaderSection.java

I created `HeaderSection.java` because the OpenCart header is used on many pages.

The header contains menu options like:

- My Account
- Register
- Login
- Logout

Instead of writing header logic inside every page class, I created a separate header class.

### RegistrationPage.java

I created `RegistrationPage.java` for registration page elements and actions.

This class handles:

- verifying home page
- navigating to Register page
- verifying registration page
- entering first name
- entering last name
- entering email
- entering password
- selecting privacy policy checkbox
- clicking Continue button
- reading account success message
- reading duplicate email warning message

## Test Classes Created

I created test classes for the main OpenCart modules.

The full OpenCart user journey is automated module by module.

Main automated areas:

- Registration
- Login and logout
- Search
- Product details
- Shopping cart
- Checkout

## Automated Test Case 1: Register Valid Account

Test case ID:

```text
TC_RG_001
```

Test case name:

```text
Register Valid Account
```

### What I Did Step By Step

1. Opened the OpenCart home page.
2. Verified that the home page loaded properly.
3. Clicked `My Account`.
4. Clicked `Register`.
5. Verified that the Register Account page opened.
6. Generated random test data using DataFaker:
   - first name
   - last name
   - email
   - password
7. Entered first name.
8. Entered last name.
9. Entered email.
10. Entered password.
11. Selected the Privacy Policy checkbox.
12. Clicked the Continue button.
13. Verified the success message:

```text
Your Account Has Been Created!
```

### Why I Used Random Email

OpenCart does not allow the same email to register again.

So for valid registration, I used DataFaker to create a new random email every time.

## Automated Test Case 2: Register Duplicate Email

Test case ID:

```text
TC_RG_002
```

Test case name:

```text
Register Duplicate Email
```

### What I Did Step By Step

1. First, I registered a new user successfully.
2. I stored the same first name, last name, email, and password.
3. After account creation, I logged out.
4. I went back to `My Account`.
5. I clicked `Register` again.
6. I entered the same details again.
7. I selected the Privacy Policy checkbox.
8. I clicked the Continue button.
9. I verified the warning message:

```text
Warning: E-Mail Address is already registered!
```

### Why This Test Works

This test works because OpenCart should not allow two accounts with the same email address.

So the first registration creates the account, and the second registration checks whether OpenCart blocks duplicate email registration.

## Automated Test Case 3: Register Empty Submission

Test case ID:

```text
TC_RG_003
```

Test case name:

```text
Register Empty Submission
```

### What I Did Step By Step

1. Opened the OpenCart home page.
2. Clicked `My Account`.
3. Clicked `Register`.
4. Verified that the Register Account page opened.
5. Left all mandatory fields empty.
6. Clicked the Continue button.
7. Verified validation messages for required fields.

### Why This Test Is Important

This test checks that OpenCart does not allow account creation with blank required fields.

## Automated Test Case 4: Login With Valid Credentials

Test case ID:

```text
TC_LG_001
```

Test case name:

```text
Login Valid Credentials
```

### What I Did Step By Step

1. Opened the OpenCart home page.
2. Clicked `My Account`.
3. Clicked `Login`.
4. Entered a valid registered email.
5. Entered the correct password.
6. Clicked the Login button.
7. Verified that the user was redirected to the `My Account` page.

### Why This Test Is Important

This test confirms that a real registered user can login successfully.

## Automated Test Case 5: Login With Invalid Credentials

Test case ID:

```text
TC_LG_002
```

Test case name:

```text
Login Invalid Credentials
```

### What I Did Step By Step

1. Opened the login page.
2. Entered an unregistered email or wrong password.
3. Clicked the Login button.
4. Verified the warning message:

```text
Warning: No match for E-Mail Address and/or Password.
```

### Why This Test Is Important

This test checks that OpenCart blocks invalid login attempts.

## Automated Test Case 6: Logout Functionality

Test case ID:

```text
TC_LG_003
```

Test case name:

```text
Logout Functionality
```

### What I Did Step By Step

1. Logged in with valid credentials.
2. Clicked `My Account`.
3. Clicked `Logout`.
4. Verified that the Account Logout page opened.

### Why This Test Is Important

This test confirms that users can logout correctly after login.

## Automated Test Case 7: Search Existing Product

Test case ID:

```text
TC_SH_001
```

Test case name:

```text
Search Existing Product
```

### What I Did Step By Step

1. Opened the OpenCart home page.
2. Entered `MacBook` in the search box.
3. Clicked the search icon.
4. Verified that MacBook products were displayed in the search results.

### Why This Test Is Important

This test checks that search works for products that exist in the store.

## Automated Test Case 8: Search Non-Existing Product

Test case ID:

```text
TC_SH_002
```

Test case name:

```text
Search Non-Existing Product
```

### What I Did Step By Step

1. Opened the OpenCart home page.
2. Entered `InvalidProduct123` in the search box.
3. Clicked the search icon.
4. Verified the no-product message:

```text
There is no product that matches the search criteria.
```

### Why This Test Is Important

This test checks that OpenCart shows the correct message when no product matches the search.

## Automated Test Case 9: Product Details Verification

Test case ID:

```text
TC_SH_003
```

Test case name:

```text
Product Details Verification
```

### What I Did Step By Step

1. Searched for `iPhone`.
2. Clicked the iPhone product card.
3. Verified that the product details page opened.
4. Verified product title.
5. Verified product price.
6. Verified the Add to Cart button.

### Why This Test Is Important

This test checks that the product details page shows important information correctly.

## Automated Test Case 10: Add Product To Cart

Test case ID:

```text
TC_CT_001
```

Test case name:

```text
Add Product to Cart
```

### What I Did Step By Step

1. Opened a product details page.
2. Entered quantity as `1`.
3. Clicked `Add to Cart`.
4. Verified the success message that the product was added to the cart.

### Why This Test Is Important

This test checks that a customer can add a product to the shopping cart.

## Automated Test Case 11: Cart Total Verification

Test case ID:

```text
TC_CT_002
```

Test case name:

```text
Cart Total Verification
```

### What I Did Step By Step

1. Added a product to the cart.
2. Clicked the cart button in the header.
3. Verified that the cart dropdown opened.
4. Verified product name.
5. Verified quantity.
6. Verified subtotal.

### Why This Test Is Important

This test checks that cart details are calculated and displayed correctly.

## Automated Test Case 12: Remove Product From Cart

Test case ID:

```text
TC_CT_003
```

Test case name:

```text
Remove Product from Cart
```

### What I Did Step By Step

1. Added a product to the cart.
2. Opened the cart dropdown.
3. Clicked the remove button.
4. Verified that the product was removed.
5. Verified that the cart total updated to `$0.00`.

### Why This Test Is Important

This test checks that customers can remove products from the cart.

## Automated Test Case 13: End-To-End Logged-In Checkout

Test case ID:

```text
TC_CK_001
```

Test case name:

```text
E2E Logged-In Checkout
```

### What I Did Step By Step

1. Logged in with a valid account.
2. Added a product to the cart.
3. Opened the checkout page.
4. Completed billing details.
5. Completed shipping details.
6. Selected shipping method.
7. Selected payment method.
8. Confirmed the order.
9. Verified the order success message:

```text
Your order has been placed!
```

### Why This Test Is Important

This is the full customer journey test. It checks that a user can login, buy a product, and place an order successfully.

## How I Automated The Entire Site

I followed the same framework pattern for every module.

For each module, I did this:

1. Create a page class in `src/main/java/pages`.
2. Store web elements using locators.
3. Write reusable page action methods.
4. Create a test class in `src/test/java/tests`.
5. Write test steps using page methods.
6. Add assertions using TestNG.
7. Run the test using Maven/TestNG.
8. Check the report in `test-output`.

## How To Run This Project

### Step 1: Start OpenCart Locally

Start Apache and MySQL in XAMPP.

Then open:

```text
http://localhost/opencartsite/
```

If the site opens, continue.

### Step 2: Run Maven Test

From the project folder, run:

```powershell
mvn test
```

### Step 3: Check TestNG Report

After running tests, check:

```text
test-output/index.html
test-output/emailable-report.html
```

## Important Files

```text
pom.xml
```

Contains Maven dependencies.

```text
src/test/java/base/BaseTest.java
```

Contains browser setup and teardown.

```text
src/main/java/base/BasePage.java
```

Contains reusable Selenium actions.

```text
src/main/java/pages/HeaderSection.java
```

Contains header navigation logic.

```text
src/main/java/pages/RegistrationPage.java
```

Contains registration page elements and methods.

```text
src/test/java/tests/RegistrationTest.java
```

Contains registration test cases.

## Simple Explanation Of Tools

### Java

Java is the programming language used to write automation code.

### Selenium WebDriver

Selenium controls the browser like a real user.

It can click buttons, enter text, select checkboxes, and read messages from the page.

### TestNG

TestNG runs test cases and gives pass/fail results.

It also helps with assertions.

### Maven

Maven manages project dependencies and runs tests.

### DataFaker

DataFaker creates random test data.

I used it to create random names, emails, and passwords.

### Page Object Model

Page Object Model is a framework design pattern.

It keeps page actions separate from test cases.

Because of this, the project becomes easier to read, maintain, and expand.

## Notes Before Pushing To GitHub

Before pushing this project to GitHub:

1. Make sure the code is working locally.
2. Make sure `README.md` explains the project clearly.
3. Do not push unnecessary large files if not needed.
4. Check project status:

```powershell
git status
```

5. Add files:

```powershell
git add .
```

6. Commit files:

```powershell
git commit -m "Add OpenCart automation framework"
```

7. Push to GitHub:

```powershell
git push
```

## Current Status

Completed:

- Maven project created
- Selenium dependency added
- TestNG dependency added
- DataFaker dependency added
- BaseTest created
- BasePage created
- HeaderSection page object created
- RegistrationPage page object created
- Registration module automated
- Login module automated
- Search module automated
- Product details module automated
- Shopping cart module automated
- Checkout module automated
- TestNG report generation configured
- Project ready to push to GitHub
