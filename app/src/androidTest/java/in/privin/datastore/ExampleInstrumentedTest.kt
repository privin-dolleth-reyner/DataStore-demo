package `in`.privin.datastore

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    companion object{
        const val PACKAGE_NAME = "in.privin.datastore"
        const val PREF_ACTIVITY = ".pref.PrefActivity"
        const val PROTO_ACTIVITY = ".proto.ProtoActivity"
    }
    @get:Rule()
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(PACKAGE_NAME, appContext.packageName)
    }

    @Test
    fun navigateToPrefActivity(){
        onView(withId(R.id.button_1))
            .perform(click())
        intended(
            allOf(
                hasComponent(hasShortClassName(PREF_ACTIVITY)),
                toPackage(PACKAGE_NAME)
            )
        )
    }

    @Test
    fun navigateToProtoActivity(){
        onView(withId(R.id.button_2))
            .perform(click())
        intended(
            allOf(
                hasComponent(hasShortClassName(PROTO_ACTIVITY)),
                toPackage(PACKAGE_NAME)
            )
        )
    }
}