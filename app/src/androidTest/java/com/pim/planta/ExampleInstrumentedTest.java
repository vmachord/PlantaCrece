package com.pim.planta;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.pim.planta", appContext.getPackageName());
    }

    @Test
    public void loginTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.pim.planta", appContext.getPackageName());

        try (ActivityScenario<LoginActivity> adminActivityScenario = ActivityScenario.launch(LoginActivity.class)) {

            onView(withId(R.id.editTextEmail))
                    .perform(ViewActions.typeText("admin@gmail.com"));
            onView(withId(R.id.editTextPassword))
                    .perform(ViewActions.typeText("1234"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.buttonLogin))
                    .perform(ViewActions.click());

            // Verificar que el elemento en la pantalla principal esté visible
            onView(withId(R.id.textViewPlantoo))
                    .check(matches(isDisplayed()));
        }
    }

    @Test
    public void perfilTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.pim.planta", appContext.getPackageName());
        try (ActivityScenario<LoginActivity> adminActivityScenario = ActivityScenario.launch(LoginActivity.class)) {
            onView(withId(R.id.editTextEmail))
                    .perform(ViewActions.typeText("admin@gmail.com"));
            onView(withId(R.id.editTextPassword))
                    .perform(ViewActions.typeText("1234"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.buttonLogin))
                    .perform(ViewActions.click());
        }
        try (ActivityScenario<PerfilActivity> adminActivityScenario = ActivityScenario.launch(PerfilActivity.class)) {
            onView(withId(R.id.imageButtonUsuario))
                    .perform(ViewActions.click());
            onView(withId(R.id.user_name))
                    .check(matches(isDisplayed()));
        }
    }

    @Test
    public void plantListTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.pim.planta", appContext.getPackageName());
        try (ActivityScenario<LoginActivity> adminActivityScenario = ActivityScenario.launch(LoginActivity.class)) {
            onView(withId(R.id.editTextEmail))
                    .perform(ViewActions.typeText("admin@gmail.com"));
            onView(withId(R.id.editTextPassword))
                    .perform(ViewActions.typeText("1234"), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.buttonLogin))
                    .perform(ViewActions.click());
        }
        try (ActivityScenario<PlantListActivity> adminActivityScenario = ActivityScenario.launch(PlantListActivity.class)) {
            onView(withId(R.id.imageButtonPlantadex))
                    .perform(ViewActions.click());
            onView(withId(R.id.plant_list_recyclerview))
                    .check(matches(isDisplayed()));
        }
    }
}