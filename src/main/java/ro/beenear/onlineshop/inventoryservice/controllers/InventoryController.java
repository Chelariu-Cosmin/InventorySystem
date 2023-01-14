package ro.beenear.onlineshop.inventoryservice.controllers;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.beenear.onlineshop.inventoryservice.beans.LotDto;
import ro.beenear.onlineshop.inventoryservice.services.InventoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Search for a specific Lot of articles by the id of the articles
     *
     * @param articleId cannot be null
     * @return lotDto and response status 200 OK if the id from an entity exists,
     * otherwise if you are in looking for a LOT,
     * will return one empty list with the same status
     * 500 Status response, that indicate the server has encountered
     * an internal error and was unable to complete the request.
     */
    @GetMapping("/{articleId}")
    public ResponseEntity<List<LotDto>> findLotByArticleId (@PathVariable("articleId") Long articleId) {

        try {
            return new ResponseEntity<>(inventoryService.findByArticleId(articleId), HttpStatus.OK);
        } catch (Throwable throwable) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
        }
    }

    /**
     * Save a lot of articles
     *
     * @param lotDto shouldn't be null
     * @return the saved entity and 201 Created response status
     * when the request succeeded,
     * BAD_REQUEST (400) and INTERNAL_SERVER_ERROR (500) otherwise.
     * response status 500 when the server has encountered
     * an internal error and was unable to complete the request.
     * 400 response status if the server will
     * not process with an invalid object
     */
    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody LotDto lotDto) {
        try {
            return new ResponseEntity<>(inventoryService.save(lotDto), HttpStatus.CREATED);
        } catch (InvalidDataAccessApiUsageException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Throwable throwable) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
        }
    }

    /**
     * Update entity with the given ID
     * update lot after order has been sent to the server
     * @param id cannot be null
     * @param lotDto provided data
     */
    @PutMapping("/id")
    public ResponseEntity <LotDto> lotChangeAfterOrder(@PathVariable("id") Long id, @RequestBody LotDto lotDto){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @return a list of LotDto, entities with response 200 OK
     * If the list is empty but everything works fine, will return also 200 OK
     * 500 Internal Server Error if the server has encountered
     * an internal error and was unable to complete the request.
     */
    @GetMapping("/")
    public ResponseEntity<List<LotDto>> findAll() {

        try {
            return new ResponseEntity<>(inventoryService.findAll(), HttpStatus.OK);
        } catch (Throwable throwable) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
        }
    }
}