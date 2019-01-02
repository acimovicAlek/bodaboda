package com.bodaboda.bodaboda;

import com.bodaboda.bodaboda.activities.CustomerAccountSettingsActivity;
import com.bodaboda.bodaboda.activities.CustomerMainActivity;
import com.bodaboda.bodaboda.activities.MainActivity;
import com.bodaboda.bodaboda.activities.RegisterAccountActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.res.Resources;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(RegisterAccountActivity.class.getName(),null,false);


    @Before
    public void setUp() throws Exception {
        //resources = mActivityTestRule.getActivity().getResources();
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLogin() throws InterruptedException {

        //Find a view with id user_name and type "Si" and type "abcd" as password on that view and Close the keyboard.
        Espresso.onView(withId(R.id.main_enter_username_editText)).perform(typeText("Si"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.main_enter_password_editText)).perform(typeText("abcd"),closeSoftKeyboard());

        //Find the LOGIN button and perform click().
        Espresso.onView(withId(R.id.main_login_button)).perform(click());

        //Display the error message when Username is not matched with the minimum no.of characters
        Espresso.onView(allOf(withId(R.id.main_error_textView), withText("Username must be at least 3 characters!"))).check(matches(isDisplayed()));

        Thread.sleep(2000);
        //Clear the Text entered in username and Password fields
        Espresso.onView(withId(R.id.main_enter_username_editText)).perform(clearText());
        Espresso.onView(withId(R.id.main_enter_password_editText)).perform(clearText());

        //Find a view with id user_name and type "Sireesha" and type "abcd" as password on that view and Close the keyboard.
        Espresso.onView(withId(R.id.main_enter_username_editText)).perform(typeText("Sireesha"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.main_enter_password_editText)).perform(typeText("abcd"),closeSoftKeyboard());

        //Find the LOGIN button and perform click().
        Espresso.onView(withId(R.id.main_login_button)).perform(click());

        //Display the error message when password is not matched with the minimum no.of characters
        Espresso.onView(allOf(withId(R.id.main_error_textView),withText("Password must be at least 6 characters!"))).check(matches(isDisplayed()));

        Thread.sleep(2000);

        //Clear the Text entered in username and Password fields
        Espresso.onView(withId(R.id.main_enter_username_editText)).perform(clearText());
        Espresso.onView(withId(R.id.main_enter_password_editText)).perform(clearText());

        //Find a view with id user_name and type "Si" and type "abcd" as password on that view and Close the keyboard.
        Espresso.onView(withId(R.id.main_enter_username_editText)).perform(typeText("SireeshaK"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.main_enter_password_editText)).perform(typeText("software@"),closeSoftKeyboard());

        //Find the LOGIN button and perform click().
        Espresso.onView(withId(R.id.main_login_button)).perform(click());
        Thread.sleep(2000);

    }

    @Test
    public void testRegister() throws InterruptedException {

        assertNotNull(mActivity.findViewById(R.id.main_register_button));
        Espresso.onView(withId(R.id.main_register_button)).perform(click());
        Activity RegisterAccountActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,2000);
        assertNotNull(RegisterAccountActivity);

        Espresso.onView(withId(R.id.reg_username_editText)).perform(typeText("Sir"));
        Espresso.onView(withId(R.id.reg_firstname_editText)).perform(typeText("Sireesha"));
        Espresso.onView(withId(R.id.reg_lastname_editText)).perform(typeText("Kulari"));
        Espresso.onView(withId(R.id.reg_phoneNo_editText)).perform(scrollTo(), typeText("795158087"));
        Espresso.onView(withId(R.id.reg_email_editText)).perform(scrollTo(), typeText("abcd1234gmail.com"));
        Espresso.onView(withId(R.id.reg_password_editText)).perform(scrollTo(), typeText("abcd12"));
        Espresso.onView(withId(R.id.reg_confirm_password_editText)).perform(scrollTo(), typeText("abcd123"),closeSoftKeyboard());

     /*   Espresso.onView(withId(R.id.reg_driver_reg_checkBox)).perform(scrollTo()).perform(click());
        Espresso.onView(withId(R.id.reg_mileage_price_editText)).perform(scrollTo(),typeText("10"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.reg_trip_starting_fee_editText)).perform(scrollTo(),typeText("10"),closeSoftKeyboard());*/

        Espresso.onView(withId(R.id.reg_register_button)).perform(scrollTo()).perform(click());

        //Validating incorrect username error message
        Espresso.onView(allOf(withId(R.id.reg_error_textView),withText("Username must have atleast 3 characters!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

        //Validating incorrect password error message
        Espresso.onView(withId(R.id.reg_username_editText)).perform(clearText());
        Espresso.onView(withId(R.id.reg_username_editText)).perform(typeText("SireeshaK"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.reg_register_button)).perform(scrollTo()).perform(click());
        Espresso.onView(allOf(withId(R.id.reg_error_textView),withText("Password must have atleast 6 characters!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

        //Validating Incorrect email format error message
        Espresso.onView(withId(R.id.reg_password_editText)).perform(clearText());
        Espresso.onView(withId(R.id.reg_confirm_password_editText)).perform(clearText());
        Espresso.onView(withId(R.id.reg_password_editText)).perform(scrollTo(), typeText("abcd1234@"));
        Espresso.onView(withId(R.id.reg_confirm_password_editText)).perform(scrollTo(), typeText("abcd123"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.reg_register_button)).perform(scrollTo()).perform(click());
        Espresso.onView(allOf(withId(R.id.reg_error_textView),withText("Incorrect email format!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

        //Validating Password does not match error message
        Espresso.onView(withId(R.id.reg_email_editText)).perform(clearText());
        Espresso.onView(withId(R.id.reg_password_editText)).perform(clearText());
        Espresso.onView(withId(R.id.reg_confirm_password_editText)).perform(clearText());
        Espresso.onView(withId(R.id.reg_email_editText)).perform(scrollTo(), typeText("abcd1234@gmail.com"));
        Espresso.onView(withId(R.id.reg_password_editText)).perform(scrollTo(), typeText("abcd1234@"));
        Espresso.onView(withId(R.id.reg_confirm_password_editText)).perform(scrollTo(), typeText("abcd123"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.reg_register_button)).perform(scrollTo()).perform(click());
        Espresso.onView(allOf(withId(R.id.reg_error_textView),withText("Password does not match!"))).check(matches(isDisplayed()));
        Thread.sleep(2000);

        //Entering all valid data and tap Register button
        Espresso.onView(withId(R.id.reg_password_editText)).perform(clearText());
        Espresso.onView(withId(R.id.reg_confirm_password_editText)).perform(clearText());
        Espresso.onView(withId(R.id.reg_password_editText)).perform(scrollTo(), typeText("software"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.reg_confirm_password_editText)).perform(scrollTo(), typeText("software"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.reg_register_button)).perform(scrollTo()).perform(click());
        Thread.sleep(2000);

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}