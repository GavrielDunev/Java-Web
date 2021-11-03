package bg.softuni.MobiLeLeLe.service;

import bg.softuni.MobiLeLeLe.model.view.StatsView;

public interface StatsService {

    void onRequest();

    StatsView getStats();
}
