package org.strassburger.subscriptionmanager.util;

import org.junit.jupiter.api.Test;
import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateCalculatorTest {

    @Test
    public void testConvertLongToDate() {
        String date = DateCalculator.convertLongToDate(1738345845928L);

        assertEquals(date, "31.1.2025");
    }

    @Test
    public void testNextBillingDate_Yearly() {
        long nextDateMillis = DateCalculator.getNextBillingDate(BillingPeriod.YEARLY, 1738345845928L);
        String nextDate = DateCalculator.convertLongToDate(nextDateMillis);

        assertEquals("1.1.2026", nextDate);
    }

    @Test
    public void testNextBillingDate_Quarterly() {
        long nextDateMillis = DateCalculator.getNextBillingDate(BillingPeriod.QUARTERLY, 1738345845928L);
        String nextDate = DateCalculator.convertLongToDate(nextDateMillis);

        assertEquals("1.4.2025", nextDate);
    }

    @Test
    public void testNextBillingDate_Monthly() {
        long nextDateMillis = DateCalculator.getNextBillingDate(BillingPeriod.MONTHLY, 1738345845928L);
        String nextDate = DateCalculator.convertLongToDate(nextDateMillis);

        assertEquals("1.2.2025", nextDate);
    }

    @Test
    public void testNextBillingDate_Weekly() {
        long nextDateMillis = DateCalculator.getNextBillingDate(BillingPeriod.MONTHLY, 1738345845928L);
        String nextDate = DateCalculator.convertLongToDate(nextDateMillis);

        assertEquals("1.2.2025", nextDate);
    }
}
