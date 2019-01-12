package com.bodaboda.bodaboda;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bodaboda.bodaboda.activities.CustomerMainActivity;
import com.bodaboda.bodaboda.activities.MainActivity;
import com.bodaboda.bodaboda.activities.RegisterAccountActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(RegisterAccountActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor cmonitor = getInstrumentation().addMonitor(CustomerMainActivity.class.getName(),null,false);
    //Instrumentation.ActivityMonitor dmonitor = getInstrumentation().addMonitor(DriverMainActivity.class.getName(),null,false);


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLoginErrorMessageValidation() throws InterruptedException {

        //Find views with id user_name & password and enter least characters and Close the keyboard.
        onView(withId(R.id.main_enter_username_editText)).perform(typeText("Cus"), closeSoftKeyboard());
        onView(withId(R.id.main_enter_password_editText)).perform(typeText("abcd"), closeSoftKeyboard());

        //Find the LOGIN button and perform click().
        onView(withId(R.id.main_login_button)).perform(click());

        //Display the error message when Username is not matched with the minimum no.of characters
        onView(allOf(withId(R.id.main_error_textView), withText("Username must be at least 3 characters!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

        //Clear the Text entered in username and Password fields
        onView(withId(R.id.main_enter_username_editText)).perform(clearText());
        onView(withId(R.id.main_enter_password_editText)).perform(clearText());

        onView(withId(R.id.main_enter_username_editText)).perform(typeText("Customer"), closeSoftKeyboard());
        onView(withId(R.id.main_enter_password_editText)).perform(typeText("abcd"), closeSoftKeyboard());

        //Find the LOGIN button and perform click().
        onView(withId(R.id.main_login_button)).perform(click());

        //Display the error message when password is not matched with the minimum no.of characters
        onView(allOf(withId(R.id.main_error_textView), withText("Password must be at least 6 characters!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

        //Clear the Text entered in username and Password fields

        onView(withId(R.id.main_enter_password_editText)).perform(clearText());

        onView(withId(R.id.main_enter_password_editText)).perform(typeText("software@"), closeSoftKeyboard());
        onView(withId(R.id.main_login_button)).perform(click());

        //Display the error message when wrong username or password is entered
        onView(allOf(withId(R.id.main_error_textView), withText("Wrong Username or Password!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

    }
    @Test
    public void testCustomerLogin() throws InterruptedException {

        //Enter Customer credentials
        onView(withId(R.id.main_enter_username_editText)).perform(typeText("Customernew"),closeSoftKeyboard());
        onView(withId(R.id.main_enter_password_editText)).perform(typeText("abcd1234@"),closeSoftKeyboard());

        // Upon clicking Login button, Page is redirecting to Customer main page
        assertNotNull(mActivity.findViewById(R.id.main_login_button));
        onView(withId(R.id.main_login_button)).perform(click());
        Activity CustomerMainActivity = getInstrumentation().waitForMonitorWithTimeout(cmonitor,2000);
        assertNotNull(CustomerMainActivity);
        Thread.sleep(2000);

    }



    @Test
    public void testRegisterErrorMessageValidation() throws InterruptedException {

        assertNotNull(mActivity.findViewById(R.id.main_register_button));
        onView(withId(R.id.main_register_button)).perform(click());
        Activity RegisterAccountActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 2000);
        assertNotNull(RegisterAccountActivity);

        onView(withId(R.id.reg_username_editText)).perform(typeText("tes"));
        onView(withId(R.id.reg_firstname_editText)).perform(typeText("Customer"));
        onView(withId(R.id.reg_lastname_editText)).perform(typeText("Test"));
        onView(withId(R.id.reg_phoneNo_editText)).perform(scrollTo(), typeText("795158087"));
        onView(withId(R.id.reg_email_editText)).perform(scrollTo(), typeText("abcd1234gmail.com"));
        onView(withId(R.id.reg_password_editText)).perform(scrollTo(), typeText("abcd12"));
        onView(withId(R.id.reg_confirm_password_editText)).perform(scrollTo(), typeText("abcd123"), closeSoftKeyboard());
        onView(withId(R.id.reg_register_button)).perform(scrollTo()).perform(click());

        //Validating incorrect username error message
        onView(allOf(withId(R.id.reg_error_textView), withText("Username must have atleast 3 characters!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

        //Validating incorrect password error message
        onView(withId(R.id.reg_username_editText)).perform(scrollTo(),clearText());
        onView(withId(R.id.reg_username_editText)).perform(scrollTo(),typeText("Customernew"), closeSoftKeyboard());
        onView(withId(R.id.reg_register_button)).perform(scrollTo()).perform(click());
        onView(allOf(withId(R.id.reg_error_textView), withText("Password must have atleast 6 characters!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

        //Validating Incorrect email format error message
        onView(withId(R.id.reg_password_editText)).perform(clearText());
        onView(withId(R.id.reg_confirm_password_editText)).perform(clearText());
        onView(withId(R.id.reg_password_editText)).perform(scrollTo(), typeText("abcd1234@"));
        onView(withId(R.id.reg_confirm_password_editText)).perform(scrollTo(), typeText("abcd123"), closeSoftKeyboard());
        onView(withId(R.id.reg_register_button)).perform(scrollTo()).perform(click());
        Espresso.onView(allOf(withId(R.id.reg_error_textView), withText("Incorrect email format!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

        //Validating Password does not match error message
        onView(withId(R.id.reg_email_editText)).perform(clearText());
        onView(withId(R.id.reg_password_editText)).perform(clearText());
        onView(withId(R.id.reg_confirm_password_editText)).perform(clearText());
        onView(withId(R.id.reg_email_editText)).perform(scrollTo(), typeText("Customernew@gmail.com"));
        onView(withId(R.id.reg_password_editText)).perform(scrollTo(), typeText("abcd1234@"));
        onView(withId(R.id.reg_confirm_password_editText)).perform(scrollTo(), typeText("abcd123"), closeSoftKeyboard());
        onView(withId(R.id.reg_register_button)).perform(scrollTo()).perform(click());
        onView(allOf(withId(R.id.reg_error_textView), withText("Password does not match!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

        //Validating the duplicate username with errer message
        onView(withId(R.id.reg_password_editText)).perform(clearText());
        onView(withId(R.id.reg_confirm_password_editText)).perform(clearText());
        onView(withId(R.id.reg_password_editText)).perform(scrollTo(), typeText("abcd1234@"), closeSoftKeyboard());
        onView(withId(R.id.reg_confirm_password_editText)).perform(scrollTo(), typeText("abcd1234@"), closeSoftKeyboard());
        onView(withId(R.id.reg_register_button)).perform(scrollTo()).perform(click());
        onView(allOf(withId(R.id.reg_error_textView), withText("Could not create account! Try another username!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}




