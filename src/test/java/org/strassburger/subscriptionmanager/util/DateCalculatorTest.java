package org.strassburger.subscriptionmanager.util;

import org.junit.jupiter.api.Test;
import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateCalculatorTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d.M.yyyy", Locale.GERMANY);

    @Test
    public void testConvertLongToDate() {
        String date = DateCalculator.convertLongToDate(1738345845928L);
        assertEquals("31.1.2025", date);
    }

    @Test
    public void testNextBillingDate_Yearly() {
        assertNextBillingDate(BillingPeriod.YEARLY, 1738345845928L, Calendar.YEAR, 1);
    }

    @Test
    public void testNextBillingDate_Quarterly() {
        assertNextBillingDate(BillingPeriod.QUARTERLY, 1738345845928L, Calendar.MONTH, 3);
    }

    @Test
    public void testNextBillingDate_Monthly() {
        assertNextBillingDate(BillingPeriod.MONTHLY, 1738345845928L, Calendar.MONTH, 1);
    }

    @Test
    public void testNextBillingDate_Weekly() {
        assertNextBillingDate(BillingPeriod.WEEKLY, 1738345845928L, Calendar.WEEK_OF_YEAR, 1);
    }

    private void assertNextBillingDate(BillingPeriod billingPeriod, long startMillis, int calendarField, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startMillis);
        calendar.add(calendarField, amount);

        long expectedMillis = calendar.getTimeInMillis();
        long actualMillis = DateCalculator.getNextBillingDate(billingPeriod, startMillis);

        assertEquals(DATE_FORMAT.format(expectedMillis), DateCalculator.convertLongToDate(actualMillis));
    }
}