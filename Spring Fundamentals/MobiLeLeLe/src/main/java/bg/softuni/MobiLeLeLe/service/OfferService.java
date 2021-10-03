package bg.softuni.MobiLeLeLe.service;

import bg.softuni.MobiLeLeLe.view.OfferSummaryView;

import java.util.List;

public interface OfferService {
    void initializeOffers();

    List<OfferSummaryView> getAllOffers();
}
