package bg.softuni.MobiLeLeLe.service;

import bg.softuni.MobiLeLeLe.model.view.OfferDetailsView;
import bg.softuni.MobiLeLeLe.model.view.OfferSummaryView;

import java.util.List;

public interface OfferService {
    void initializeOffers();

    List<OfferSummaryView> getAllOffers();

    OfferDetailsView getById(Long id);

    void deleteOffer(Long id);
}
