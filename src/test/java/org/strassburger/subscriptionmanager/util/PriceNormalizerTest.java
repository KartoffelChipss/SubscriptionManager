package org.strassburger.subscriptionmanager.util;

import org.junit.jupiter.api.Test;
import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceNormalizerTest {

    @Test
    public void testNormalizePriceYearly() {
        double price = 120.0;
        double expected = 10.0;
        double actual = PriceNormalizer.normalizePrice(price, BillingPeriod.YEARLY);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testNormalizePriceQuarterly() {
        double price = 30.0;
        double expected = 10.0;
        double actual = PriceNormalizer.normalizePrice(price, BillingPeriod.QUARTERLY);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testNormalizePriceWeekly() {
        double price = 10.0;
        double expected = 43.3;
        double actual = PriceNormalizer.normalizePrice(price, BillingPeriod.WEEKLY);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testNormalizePriceMonthly() {
        double price = 10.0;
        double expected = 10.0;
        double actual = PriceNormalizer.normalizePrice(price, BillingPeriod.MONTHLY);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testNormalizePriceString() {
        double price = 120.0;
        double expected = 10.0;
        double actual = PriceNormalizer.normalizePrice(price, "YEARLY");
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testConvertPriceYearlyToMonthly() {
        double price = 120.0;
        double expected = 10.0;
        double actual = PriceNormalizer.convertPrice(price, BillingPeriod.YEARLY, BillingPeriod.MONTHLY);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testConvertPriceMonthlyToYearly() {
        double price = 10.0;
        double expected = 120.0;
        double actual = PriceNormalizer.convertPrice(price, BillingPeriod.MONTHLY, BillingPeriod.YEARLY);
        assertEquals(expected, actual, 0.01);
    }
}