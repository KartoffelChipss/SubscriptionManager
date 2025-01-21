package org.strassburger.subscriptionmanager.model;

import org.jooq.DSLContext;
import org.strassburger.subscriptionmanager.model.repositories.ProfileRepository;
import org.strassburger.subscriptionmanager.model.repositories.SubscriptionRepository;

public abstract class DatabaseManager {

    public abstract DSLContext getDslContext();

    public ProfileRepository getProfileRepository() {
        return new ProfileRepository(getDslContext());
    }

    public SubscriptionRepository getSubscriptionRepository() {
        return new SubscriptionRepository(getDslContext());
    }
}
