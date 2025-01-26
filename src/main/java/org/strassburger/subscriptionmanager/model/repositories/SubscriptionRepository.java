package org.strassburger.subscriptionmanager.model.repositories;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.QOM;
import org.strassburger.subscriptionmanager.jooq.tables.Subscriptions;
import org.strassburger.subscriptionmanager.model.entity.BillingPeriod;
import org.strassburger.subscriptionmanager.model.entity.Subscription;

import java.util.List;
import java.util.Optional;

import static org.strassburger.subscriptionmanager.util.PriceNormalizer.normalizePrice;

public class SubscriptionRepository {
    private final DSLContext dsl;

    public SubscriptionRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * Add a new subscription to the database.
     *
     * @param name The name of the subscription.
     * @param price The price of the subscription per billing period.
     * @param billingPeriod The billing period of the subscription.
     * @param startDate The start date of the subscription in milliseconds since epoch (nullable).
     * @return True if the subscription was added successfully, false otherwise.
     */
    public boolean addSubscription(String name, double price, BillingPeriod billingPeriod, Long startDate) {
        return dsl.insertInto(Subscriptions.SUBSCRIPTIONS)
                .set(Subscriptions.SUBSCRIPTIONS.NAME, name)
                .set(Subscriptions.SUBSCRIPTIONS.PRICE, (float) price)
                .set(Subscriptions.SUBSCRIPTIONS.NORMALIZED_PRICE, (float) normalizePrice(price, billingPeriod))
                .set(Subscriptions.SUBSCRIPTIONS.BILLING_PERIOD, billingPeriod.toString())
                .set(Subscriptions.SUBSCRIPTIONS.START_DATE, startDate)
                .execute() == 1;
    }

    /**
     * Get all subscriptions from the database.
     * @return A list of all subscriptions.
     */
    public List<Subscription> getAllSubscriptions() {
        return dsl.selectFrom(Subscriptions.SUBSCRIPTIONS)
                .orderBy(Subscriptions.SUBSCRIPTIONS.NORMALIZED_PRICE.desc())
                .fetch(this::mapRecordToSubscription);
    }

    /**
     * Get a subscription by its ID.
     * @param id The ID of the subscription.
     * @return An optional containing the subscription if it exists, empty otherwise.
     */
    public Optional<Subscription> getSubscriptionById(int id) {
        return dsl.selectFrom(Subscriptions.SUBSCRIPTIONS)
                .where(Subscriptions.SUBSCRIPTIONS.ID.eq(id))
                .fetchOptional(this::mapRecordToSubscription);
    }

    /**
     * Get a subscription by its name.
     * @param name The name of the subscription.
     * @return An optional containing the subscription if it exists, empty otherwise.
     */
    public Optional<Subscription> getSubscriptionByName(String name) {
        return dsl.selectFrom(Subscriptions.SUBSCRIPTIONS)
                .where(Subscriptions.SUBSCRIPTIONS.NAME.eq(name))
                .fetchOptional(this::mapRecordToSubscription);
    }

    /**
     * Update a subscription in the database.
     * @param subscription The subscription to update.
     * @return True if the subscription was updated successfully, false otherwise.
     */
    public boolean updateSubscription(Subscription subscription) {
        return dsl.update(Subscriptions.SUBSCRIPTIONS)
                .set(Subscriptions.SUBSCRIPTIONS.NAME, subscription.getName())
                .set(Subscriptions.SUBSCRIPTIONS.PRICE, (float) subscription.getPrice())
                .set(Subscriptions.SUBSCRIPTIONS.NORMALIZED_PRICE, (float) subscription.getNormalizedPrice())
                .set(Subscriptions.SUBSCRIPTIONS.BILLING_PERIOD, subscription.getBillingPeriod().toString())
                .set(Subscriptions.SUBSCRIPTIONS.START_DATE, subscription.getStartDate())
                .where(Subscriptions.SUBSCRIPTIONS.ID.eq(subscription.getId()))
                .execute() == 1;
    }

    /**
     * Delete a subscription from the database.
     * @param id The ID of the subscription to delete.
     * @return True if the subscription was deleted successfully, false otherwise.
     */
    public boolean deleteSubscription(int id) {
        return dsl.deleteFrom(Subscriptions.SUBSCRIPTIONS)
                .where(Subscriptions.SUBSCRIPTIONS.ID.eq(id))
                .execute() == 1;
    }

    /**
     * Map a database record to a Subscription object.
     * @param record The database record.
     * @return The Subscription object.
     */
    private Subscription mapRecordToSubscription(Record record) {
        return new Subscription(
                record.get(Subscriptions.SUBSCRIPTIONS.ID),
                record.get(Subscriptions.SUBSCRIPTIONS.NAME),
                record.get(Subscriptions.SUBSCRIPTIONS.PRICE),
                BillingPeriod.fromDisplayName(record.get(Subscriptions.SUBSCRIPTIONS.BILLING_PERIOD)),
                record.get(Subscriptions.SUBSCRIPTIONS.START_DATE)
        );
    }
}
