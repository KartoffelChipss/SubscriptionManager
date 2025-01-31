package org.strassburger.subscriptionmanager.model.entity;

public enum SubscriptionOrder {
    NORMALIZED_PRICE("Normalized Price"),
    NEXT_BILLING_DATE("Next billing date"),
    NAME("Name");

    private final String displayName;

    SubscriptionOrder(String displayName) {
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
            case NORMALIZED_PRICE -> "/w";
            case NEXT_BILLING_DATE -> "/m";
            case NAME -> "/q";
        };
    }

}
