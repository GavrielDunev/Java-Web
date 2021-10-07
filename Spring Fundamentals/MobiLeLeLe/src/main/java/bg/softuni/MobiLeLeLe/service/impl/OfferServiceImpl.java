package bg.softuni.MobiLeLeLe.service.impl;

import bg.softuni.MobiLeLeLe.model.entity.OfferEntity;
import bg.softuni.MobiLeLeLe.model.entity.enums.EngineEnum;
import bg.softuni.MobiLeLeLe.model.entity.enums.TransmissionEnum;
import bg.softuni.MobiLeLeLe.repository.ModelRepository;
import bg.softuni.MobiLeLeLe.repository.OfferRepository;
import bg.softuni.MobiLeLeLe.repository.UserRepository;
import bg.softuni.MobiLeLeLe.service.OfferService;
import bg.softuni.MobiLeLeLe.view.OfferSummaryView;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeOffers() {
        if (this.offerRepository.count() == 0) {
            OfferEntity firstOffer = new OfferEntity()
                    .setDescription("New")
                    .setEngine(EngineEnum.GASOLINE)
                    .setImageUrl("https://dizzyriders.bg/uploads/thumbs/gallery/2021-04/408987c4b325668c030d415b85c1a319-620x427.jpg")
                    .setMileage(20)
                    .setModel(this.modelRepository.findById(1L).orElse(null))
                    .setPrice(BigDecimal.valueOf(200000))
                    .setSeller(this.userRepository.findByUsername("gosho").orElse(null))
                    .setTransmission(TransmissionEnum.AUTOMATIC)
                    .setYear(2021);

            OfferEntity secondOffer = new OfferEntity()
                    .setDescription("Very well serviced and in good condition.")
                    .setEngine(EngineEnum.DIESEL)
                    .setImageUrl("https://i.ytimg.com/vi/GlKYm70zyu4/maxresdefault.jpg")
                    .setMileage(100000)
                    .setModel(this.modelRepository.findById(2L).orElse(null))
                    .setPrice(BigDecimal.valueOf(40000))
                    .setSeller(this.userRepository.findByUsername("admin").orElse(null))
                    .setTransmission(TransmissionEnum.MANUAL)
                    .setYear(2015);

            this.offerRepository.saveAll(List.of(firstOffer, secondOffer));
        }
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
        return this.offerRepository.findAll().stream()
                .map(this::map).collect(Collectors.toList());
    }

    private OfferSummaryView map(OfferEntity offerEntity) {
        OfferSummaryView offerSummaryView = modelMapper.map(offerEntity, OfferSummaryView.class);

        offerSummaryView.setModel(offerEntity.getModel().getName());

        return offerSummaryView;
    }
}
