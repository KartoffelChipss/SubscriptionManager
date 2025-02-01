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

}
