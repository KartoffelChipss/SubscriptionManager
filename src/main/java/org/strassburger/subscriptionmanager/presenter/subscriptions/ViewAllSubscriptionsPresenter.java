package org.strassburger.subscriptionmanager.presenter.subscriptions;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.model.entity.Profile;
import org.strassburger.subscriptionmanager.model.entity.Subscription;
import org.strassburger.subscriptionmanager.model.entity.SubscriptionOrder;
import org.strassburger.subscriptionmanager.util.DateCalculator;
import org.strassburger.subscriptionmanager.view.subscriptions.ViewAllSubscriptionsView;

import java.util.Comparator;
import java.util.List;

public class ViewAllSubscriptionsPresenter {
    private final ViewAllSubscriptionsView view;
    private final DatabaseManager dbManager;
    private final Profile profile;

    public ViewAllSubscriptionsPresenter(ViewAllSubscriptionsView view, DatabaseManager dbManager, Profile profile) {
        this.view = view;
        this.dbManager = dbManager;
        this.profile = profile;
    }

    public void start() {
        view.sendStartMessage();

        SubscriptionOrder subscriptionOrder = view.getSubscriptionOrder();
        List<Subscription> subscriptionList = dbManager.getSubscriptionRepository().getAllSubscriptions(profile.getId());

        Comparator<Subscription> comp = switch (subscriptionOrder) {
            case NORMALIZED_PRICE -> Comparator.comparingDouble(sub -> (-1) * sub.getNormalizedPrice()); // negation for desc. order
            case NEXT_BILLING_DATE -> Comparator.comparingLong(sub -> DateCalculator.getNextBillingDate(sub.getBillingPeriod(), System.currentTimeMillis()));
            case NAME -> Comparator.comparing(Subscription::getName);
        };
        subscriptionList.sort(comp);

        if (subscriptionList.isEmpty()) {
            view.sendNoSubscriptionsAvailableMessage();
        } else {
            view.showAllSubscriptions(subscriptionList);
        }

        view.enterToContinue();
    }
}
