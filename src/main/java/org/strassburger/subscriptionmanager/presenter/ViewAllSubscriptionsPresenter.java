package org.strassburger.subscriptionmanager.presenter;

import org.strassburger.subscriptionmanager.model.DatabaseManager;
import org.strassburger.subscriptionmanager.model.entity.Subscription;
import org.strassburger.subscriptionmanager.view.ViewAllSubscriptionsView;

import java.util.List;

public class ViewAllSubscriptionsPresenter {
    private final ViewAllSubscriptionsView view;
    private final DatabaseManager dbManager;

    public ViewAllSubscriptionsPresenter(ViewAllSubscriptionsView view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void start() {
        view.sendStartMessage();

        List<Subscription> subscriptionList = dbManager.getSubscriptionRepository().getAllSubscriptions();

        if (subscriptionList.isEmpty()) {
            view.sendNoSubscriptionsAvailableMessage();
        } else {
            view.showAllSubscriptions(subscriptionList);
        }

        view.enterToContinue();
    }
}
