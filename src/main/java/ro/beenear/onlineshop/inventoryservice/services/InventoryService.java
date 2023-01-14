package ro.beenear.onlineshop.inventoryservice.services;

import ro.beenear.onlineshop.inventoryservice.beans.LotDto;

import java.util.List;


public interface InventoryService {

    Long save(LotDto lotDto);

    LotDto updateById(Long id, LotDto lotDto);

    List<LotDto> findByArticleId(Long articleId);

    List<LotDto> findAll();
}
