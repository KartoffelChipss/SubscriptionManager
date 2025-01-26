package org.strassburger.subscriptionmanager.model.entity;

import static org.strassburger.subscriptionmanager.util.PriceNormalizer.normalizePrice;

public class Subscription {
    private final int id;
    private String name;
    private double price;
    private double normalizedPrice;
    private BillingPeriod billingPeriod;
    private Long startDate;

    public Subscription(int id, String name, double price, BillingPeriod billingPeriod, Long startDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.normalizedPrice = normalizePrice(price, billingPeriod);
        this.billingPeriod = billingPeriod;
        this.startDate = startDate;
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

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", billingPeriod=" + getBillingPeriod() +
                ", startDate=" + getStartDate() +
                '}';
    }
}
