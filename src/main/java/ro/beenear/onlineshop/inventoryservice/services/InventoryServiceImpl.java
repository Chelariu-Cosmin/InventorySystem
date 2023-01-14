package ro.beenear.onlineshop.inventoryservice.services;

import org.springframework.stereotype.Service;
import ro.beenear.onlineshop.inventoryservice.beans.LotDto;
import ro.beenear.onlineshop.inventoryservice.entities.Lot;
import ro.beenear.onlineshop.inventoryservice.mappers.LotMapper;
import ro.beenear.onlineshop.inventoryservice.repositories.InventoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final LotMapper lotMapper;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, LotMapper lotMapper) {
        this.inventoryRepository = inventoryRepository;
        this.lotMapper = lotMapper;
    }

    @Override
    public Long save(LotDto lotDto) {

        Lot lot = lotMapper.toLot(lotDto);
        inventoryRepository.save(lot);

        return lot.getId() ;
    }

    @Override
    public LotDto updateById(Long id, LotDto lotDto) {

        Lot lot = lotMapper.toLot(lotDto);
        inventoryRepository.save(lot);

        return lotMapper.toDto(lot);
    }

    @Override
    public List<LotDto> findByArticleId(Long articleId) {

        return inventoryRepository.findAllLotsByArticleId(articleId)
                .stream()
                .map(lotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LotDto> findAll() {

        return inventoryRepository.findAll()
                .stream()
                .map(lotMapper::toDto)
                .collect(Collectors.toList());
    }
}
