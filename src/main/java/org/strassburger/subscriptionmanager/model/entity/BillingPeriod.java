package org.strassburger.subscriptionmanager.model.entity;

public enum BillingPeriod {
    WEEKLY("Weekly"),
    MONTHLY("Monthly"),
    QUARTERLY("Quarterly"),
    YEARLY("Yearly");

    private final String displayName;

    BillingPeriod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static BillingPeriod fromDisplayName(String displayName) {
        for (BillingPeriod billingPeriod : values()) {
            if (billingPeriod.getDisplayName().equals(displayName)) {
                return billingPeriod;
            }
        }
        throw new IllegalArgumentException("No billing period with display name " + displayName);
    }
}
