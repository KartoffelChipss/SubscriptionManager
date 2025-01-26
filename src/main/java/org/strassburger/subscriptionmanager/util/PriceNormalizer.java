package org.strassburger.subscriptionmanager.util;

import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;

public class PriceNormalizer {
    /**
     * Normalize the price of a subscription to a monthly price.
     * @param price the price of the subscription
     * @param billingPeriod the billing period of the subscription
     * @return the normalized price
     */
    public static double normalizePrice(double price, BillingPeriod billingPeriod) {
        return switch (billingPeriod) {
            case YEARLY -> price / 12;
            case QUARTERLY -> price / 3;
            case WEEKLY -> price * 4.33;
            default -> price;
        };
    }

    /**
     * Normalize the price of a subscription to a monthly price.
     * @param price the price of the subscription
     * @param billingPeriod the billing period of the subscription
     * @return the normalized price
     */
    public static double normalizePrice(double price, String billingPeriod) {
        return normalizePrice(price, BillingPeriod.valueOf(billingPeriod));
    }

    /**
     * Convert a price from one billing period to another.
     * @param price the price to convert
     * @param fromPeriod the billing period of the price
     * @param toPeriod the billing period to convert to
     * @return the converted price
     */
    public static double convertPrice(double price, BillingPeriod fromPeriod, BillingPeriod toPeriod) {
        return normalizePrice(price, fromPeriod) / normalizePrice(1, toPeriod);
    }
}
