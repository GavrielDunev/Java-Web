package bg.softuni.MobiLeLeLe.service.impl;

import bg.softuni.MobiLeLeLe.model.entity.OfferEntity;
import bg.softuni.MobiLeLeLe.repository.OfferRepository;
import bg.softuni.MobiLeLeLe.service.OfferService;
import bg.softuni.MobiLeLeLe.view.OfferSummaryView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void initializeOffers() {
        //TODO:
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
        return this.offerRepository.findAll().stream()
                .map(this::map).collect(Collectors.toList());
    }

    private OfferSummaryView map(OfferEntity offerEntity) {
        //TODO:
        return new OfferSummaryView();
    }
}
