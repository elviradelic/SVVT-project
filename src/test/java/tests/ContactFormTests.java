package tests;

import TestBase.BaseTest;
import TestBase.Factory.TestDataFactory;
import TestBase.Factory.TestUser;
import org.junit.jupiter.api.*;
import pages.ContactPage;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactFormTests extends BaseTest {


    private ContactPage contactPage;



    @BeforeEach
    public void openContactPage() {
        contactPage = new ContactPage(driver).open();
    }

    @Test
    @Order(1)
    public void testValidFormSubmission() {
        TestUser user = TestDataFactory.validTestUser();

        // Arrange & Act
        contactPage
                .setName(user.getFirstName() + " " + user.getLastName())
                .setEmail(user.getEmail())
                .setPhone("123456789")
                .setMessage("This is a test message.")
                .submit();

        // If captcha is present, we can't reliably auto-submit; treat as assumption
        if (contactPage.isCaptchaPresent()) {
            System.out.println("Captcha present - skipping assertion for automatic submission.");
            return; // effectively ignore in environments with captcha
        }

        contactPage.waitForSuccessOrError();

        // Assert
        assertTrue(contactPage.isSubmitted(), "Form submission was not successful.");
        assertNull(contactPage.getGenericError(), "Unexpected generic error after successful submission.");
    }

    @Test
    @Order(2)
    public void testMissingName() {
        TestUser user = TestUser.builder().build();

        contactPage
                .setName("")
                .setEmail(user.getEmail())
                .setPhone("123456789")
                .setMessage("This is a test message.")
                .submit();

        contactPage.waitForSuccessOrError();

        assertFalse(contactPage.isSubmitted(), "Form should not be submitted when name is missing.");
        String nameError = contactPage.getFieldError("name");
        assertNotNull(nameError, "Expected validation error for missing name.");
        assertFalse(nameError.isEmpty(), "Validation error for name should not be empty.");
    }

    @Test
    @Order(3)
    public void testMissingEmail() {
        TestUser user = TestUser.builder().build();

        contactPage
                .setName(user.getFirstName() + " " + user.getLastName())
                .setEmail("")
                .setPhone("123456789")
                .setMessage("This is a test message.")
                .submit();

        contactPage.waitForSuccessOrError();

        assertFalse(contactPage.isSubmitted(), "Form should not be submitted when email is missing.");
        String emailError = contactPage.getFieldError("email");
        assertNotNull(emailError, "Expected validation error for missing email.");
        assertFalse(emailError.isEmpty(), "Validation error for email should not be empty.");
    }

    @Test
    @Order(4)
    public void testInvalidEmail() {
        TestUser user = TestUser.builder().build();

        contactPage
                .setName(user.getFirstName() + " " + user.getLastName())
                .setEmail("invalid-email")
                .setPhone("123456789")
                .setMessage("This is a test message.")
                .submit();

        contactPage.waitForSuccessOrError();

        assertFalse(contactPage.isSubmitted(), "Form should not be submitted with invalid email.");
        String emailError = contactPage.getFieldError("email");
        assertNotNull(emailError, "Expected validation error for invalid email.");
        assertFalse(emailError.isEmpty(), "Validation error for invalid email should not be empty.");
    }

    @Test
    @Order(5)
    public void testMissingMessage() {
        TestUser user = TestUser.builder().build();

        contactPage
                .setName(user.getFirstName() + " " + user.getLastName())
                .setEmail(user.getEmail())
                .setPhone("123456789")
                .setMessage("")
                .submit();

        contactPage.waitForSuccessOrError();

        assertFalse(contactPage.isSubmitted(), "Form should not be submitted when message is missing.");
        String messageError = contactPage.getFieldError("comment");
        assertNotNull(messageError, "Expected validation error for missing message.");
        assertFalse(messageError.isEmpty(), "Validation error for message should not be empty.");
    }

    @Test
    @Order(6)
    public void testEmptyFields() {
        contactPage
                .setName("")
                .setEmail("")
                .setPhone("")
                .setMessage("")
                .submit();

        contactPage.waitForSuccessOrError();

        assertFalse(contactPage.isSubmitted(), "Form should not be submitted when all fields are empty.");

        String nameError = contactPage.getFieldError("name");
        String emailError = contactPage.getFieldError("email");
        String messageError = contactPage.getFieldError("comment");

        assertNotNull(nameError, "Expected validation error for name when all fields are empty.");
        assertNotNull(emailError, "Expected validation error for email when all fields are empty.");
        assertNotNull(messageError, "Expected validation error for message when all fields are empty.");
    }

    @Test
    @Order(7)
    public void emptyPhoneNumberField() {
        TestUser user = TestUser.builder().build();

        contactPage
                .setName(user.getFirstName() + " " + user.getLastName())
                .setEmail(user.getEmail())
                .setPhone("")
                .setMessage("This is a test message.")
                .submit();

        contactPage.waitForSuccessOrError();

        if (contactPage.isCaptchaPresent()) {
            System.out.println("Captcha present - skipping strict assertion on successful submission.");
            return;
        }

        assertTrue(contactPage.isSubmitted(), "Form submission should be successful even without a phone number.");
        String phoneError = contactPage.getFieldError("telephone");
        // Phone should be optional, so no error expected
        assertTrue(phoneError == null || phoneError.isEmpty(), "No validation error expected for missing phone number.");
    }

}