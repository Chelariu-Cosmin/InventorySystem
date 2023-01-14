package ro.beenear.onlineshop.inventoryservice.mappers;

import org.mapstruct.Mapper;
import ro.beenear.onlineshop.inventoryservice.beans.LotDto;
import ro.beenear.onlineshop.inventoryservice.entities.Lot;

@Mapper
public interface LotMapper {

    LotDto toDto(Lot lot);

    Lot toLot(LotDto lotDto);
}
