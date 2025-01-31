package org.strassburger.subscriptionmanager.util;

import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;

import java.util.Calendar;
import java.util.Date;

public class DateCalculator {

    /**
     * Calculate the next billing date for a subscription.
     * @param billingPeriod Billing period of Subscription to calculate next billing date for.
     * @param currentDateMillis Current Date in milliseconds.
     * @return Next billing date in milliseconds.
     */
    public static Long getNextBillingDate(BillingPeriod billingPeriod, Long currentDateMillis) {
        Date currentDate = new Date(currentDateMillis);

        Calendar nextBillingDateCalendar = Calendar.getInstance();
        nextBillingDateCalendar.setTime(currentDate);
        nextBillingDateCalendar.setLenient(true);

        switch (billingPeriod) {
            case BillingPeriod.WEEKLY:
                nextBillingDateCalendar.add(Calendar.WEEK_OF_YEAR, 1);
                nextBillingDateCalendar.set(Calendar.DAY_OF_WEEK, 0);
                break;
            case BillingPeriod.MONTHLY:
                nextBillingDateCalendar.add(Calendar.MONTH, 1);
                nextBillingDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
                break;
            case BillingPeriod.QUARTERLY:
                int month = nextBillingDateCalendar.get(Calendar.MONTH);
                if (month < 3) {
                    nextBillingDateCalendar.set(Calendar.MONTH, 3);
                } else if (month < 6) {
                    nextBillingDateCalendar.set(Calendar.MONTH, 6);
                } else if (month < 9) {
                    nextBillingDateCalendar.set(Calendar.MONTH, 9);
                } else {
                    nextBillingDateCalendar.set(Calendar.MONTH, 0);
                }

                nextBillingDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
                break;
            // BillingPeriod.YEARLY
            default:
                nextBillingDateCalendar.add(Calendar.YEAR, 1);
                nextBillingDateCalendar.set(Calendar.MONTH, 0);
                nextBillingDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
                break;
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
