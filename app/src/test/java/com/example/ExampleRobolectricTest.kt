package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.calculator.DivineTimeCalculator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Dhawq", appName)
  }

  @Test
  fun `verify divine time scaling based on Surah Al-Hajj 47`() {
    // 1000 earth years = 24 divine hours = 86 400 divine seconds
    // => 1 earth year = 86.4 divine seconds.
    val secondsInOneEarthYear = DivineTimeCalculator.DIVINE_SECONDS_PER_EARTH_YEAR
    assertEquals(86.4, secondsInOneEarthYear, 0.001)

    // A five minute prayer = 5 * 0.0001643 = 0.00082 divine seconds.
    val fiveMinutePrayer = DivineTimeCalculator.DIVINE_SECONDS_PER_EARTH_MINUTE * 5.0
    assertEquals(0.000821, fiveMinutePrayer, 0.000001)
    assertTrue("5 minutes must be less than 0.001 divine seconds", fiveMinutePrayer < 0.001)

    // 80 years of 5 daily prayers is 12 174.75 earth hours, i.e. ≈ 2.0 divine
    // minutes — about 1.7 % of the 120 divine minutes of a life.
    val lifetimePrayerEarthHours =
      DivineTimeCalculator.REFERENCE_LIFESPAN_YEARS *
        DivineTimeCalculator.DAYS_PER_EARTH_YEAR *
        DivineTimeCalculator.DAILY_PRAYER_EARTH_MINUTES / 60.0
    assertEquals(12_174.75, lifetimePrayerEarthHours, 0.01)
    assertEquals(2.0, DivineTimeCalculator.LIFETIME_PRAYER_DIVINE_MINUTES, 0.01)
    assertTrue(DivineTimeCalculator.LIFETIME_PRAYER_PERCENT < 2.0)
  }

  @Test
  fun `verify two divine hours mapping for middle age`() {
    val mapping = DivineTimeCalculator.calculateTwoDivineHoursMapping(40.0)
    // 40 years = 40 / 1000 * 24 * 60 = 57.6 divine minutes (end of the 1st hour).
    assertEquals(1, mapping.currentHourNumber)
    assertEquals(57.6, mapping.elapsedMinutes, 0.5)
  }
}
