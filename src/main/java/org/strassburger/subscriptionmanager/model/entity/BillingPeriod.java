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

    public String getPriceString() {
        return switch (this) {
            case WEEKLY -> "/w";
            case MONTHLY -> "/m";
            case QUARTERLY -> "/q";
            case YEARLY -> "/y";
        };
    }

    public static BillingPeriod fromDisplayName(String displayName) {
        for (BillingPeriod billingPeriod : values()) {
            if (billingPeriod.toString().equals(displayName)) {
                return billingPeriod;
            }
        }
        throw new IllegalArgumentException("No billing period with display name " + displayName);
    }
}
