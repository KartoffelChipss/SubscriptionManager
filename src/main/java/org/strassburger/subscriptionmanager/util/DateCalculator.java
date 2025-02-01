package org.strassburger.subscriptionmanager.util;

import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;

import java.util.Calendar;
import java.util.Date;

public class DateCalculator {

    /**
     * Calculate the next billing date for a subscription.
     * @param billingPeriod Billing period of Subscription to calculate next billing date for.
     * @param startDateMillis Start date of Subscription to calculate next billing date for in milliseconds.
     * @return Next billing date in milliseconds.
     */
    public static Long getNextBillingDate(BillingPeriod billingPeriod, Long startDateMillis) {
        Calendar nextBillingDateCalendar = Calendar.getInstance();
        nextBillingDateCalendar.setTimeInMillis(startDateMillis);
        nextBillingDateCalendar.setLenient(true);

        switch (billingPeriod) {
            case WEEKLY:
                nextBillingDateCalendar.add(Calendar.WEEK_OF_YEAR, 1);
                break;
            case MONTHLY:
                nextBillingDateCalendar.add(Calendar.MONTH, 1);
                break;
            case QUARTERLY:
                nextBillingDateCalendar.add(Calendar.MONTH, 3);
                break;
            case YEARLY:
                nextBillingDateCalendar.add(Calendar.YEAR, 1);
                break;
            default:
                throw new IllegalArgumentException("Unsupported billing period: " + billingPeriod);
        }

        return nextBillingDateCalendar.getTimeInMillis();
    }

    /**
     * Convert time in milliseconds to String representation of date.
     * @param dateLong Time in milliseconds.
     * @return Date as String.
     */
    public static String convertLongToDate(Long dateLong) {
        if (dateLong == null) return "/";

        Date date = new Date(dateLong);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR);
    }
}
