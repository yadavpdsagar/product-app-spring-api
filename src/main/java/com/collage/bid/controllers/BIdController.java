package com.collage.bid.controllers;

import com.collage.bid.dto.BidDTO;
import com.collage.bid.dto.CreateBidRequest;
import com.collage.bid.model.Item;
import com.collage.bid.services.BidService;
import com.collage.bid.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BIdController {

    private BidService bidService;

    @Autowired
    public BIdController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/create")
    public ResponseEntity<Bid> createBid(@RequestBody CreateBidRequest createBidRequest) {

        Bid bid = createBidRequest.getBid();
        List<Item> items = createBidRequest.getItems();


        Bid savedBid = bidService.createBidWithItems(bid, items);


        return new ResponseEntity<>(savedBid, HttpStatus.CREATED);
    }

    // Get All Bids
    @GetMapping("/categories")
    public ResponseEntity<List<BidDTO>> getAllBids() {
        List<BidDTO> bidDTOs = bidService.getAllBids();  // Assuming the service method fetches all bids and converts them to DTOs
        return new ResponseEntity<>(bidDTOs, HttpStatus.OK);
    }

    // Get Bid by ID
    @GetMapping("/category/{id}")
    public ResponseEntity<BidDTO> getBidById(@PathVariable Long id) {
        BidDTO bidDTO = bidService.getBidWithItems(id);  // Fetches the bid by id and converts it to DTO
        return new ResponseEntity<>(bidDTO, HttpStatus.OK);
    }

//    @GetMapping("/categories")
//    public List<Bid> getCategories(){
//        return bidService.getAllCategories();
//    }

//    @GetMapping("/category/{id}")
//    public Bid getCategory(@PathVariable("id") Long id){
//        return bidService.getCategoryById(id);
//    }

    @GetMapping("/category/ByUsername")
    public List<Bid> getCategoryByUsername(){
        return bidService.getCategoryByUsername();
    }

//    @PutMapping("/category/{id}")
//    public Bid updateCategory(@RequestBody() Bid bid, @PathVariable("id") Long id){
//        return bidService.save(bid);
//    }

//    @PostMapping("/categories")
//    public Bid addNew(@RequestBody() Bid bid){
//        return bidService.save(bid);
//    }

//    @DeleteMapping("/category/{id}")
//    public void deleteCategory(@PathVariable("id") Long id){
//        bidService.deleteCategory(id);
//    }

}
