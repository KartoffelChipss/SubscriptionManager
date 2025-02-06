package org.strassburger.subscriptionmanager.model.entity;

import static org.strassburger.subscriptionmanager.util.PriceNormalizer.normalizePrice;

public class Subscription {
    private final int id;
    private final int profileId;
    private String name;
    private double price;
    private double normalizedPrice;
    private BillingPeriod billingPeriod;
    private Long startDate;
    private String category;

    public Subscription(int id, int profileId, String name, double price, BillingPeriod billingPeriod, Long startDate, String category) {
        this.id = id;
        this.profileId = profileId;
        this.name = name;
        this.price = price;
        this.normalizedPrice = normalizePrice(price, billingPeriod);
        this.billingPeriod = billingPeriod;
        this.startDate = startDate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setPrice(double price) {
        this.price = price;
        this.normalizedPrice = normalizePrice(price, billingPeriod);
    }

    public BillingPeriod getBillingPeriod() {
        return billingPeriod;
    }

    public void setBillingPeriod(BillingPeriod billingPeriod) {
        this.billingPeriod = billingPeriod;
        this.normalizedPrice = normalizePrice(price, billingPeriod);
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public double getNormalizedPrice() {
        return normalizedPrice;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", billingPeriod=" + getBillingPeriod() +
                ", startDate=" + getStartDate() +
                ", category=" + getCategory() +
                '}';
    }
}
