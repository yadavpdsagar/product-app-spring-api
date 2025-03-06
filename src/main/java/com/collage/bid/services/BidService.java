package com.collage.bid.services;

import com.collage.bid.model.Bid;
import com.collage.bid.model.Item;
import com.collage.bid.repositories.BidRepository;
import com.collage.bid.repositories.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private   ItemRepository itemRepository;

    @Autowired
    public BidService(BidRepository bidRepository, ItemRepository itemRepository) {
        this.bidRepository = bidRepository;
        this.itemRepository = itemRepository;
    }


    @Transactional
    public Bid createBidWithItems(Bid bid, List<Item> items) {
        // First, save the bid
        bid.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Bid savedBid = bidRepository.save(bid);

        // Assign the saved bid to each item
        for (Item item : items) {
            item.setBid(savedBid);  // Set the bid for each item
            itemRepository.save(item);  // Save each item
        }

        // Return the saved bid (now with its associated items)
        return savedBid;
    }



    public List<Bid> getAllCategories(){
        return bidRepository.findAll();
    }

    public Bid getCategoryById(Long id) {
        return bidRepository.findById(id).orElse(null);
    }

    public List<Bid> getCategoryByUsername() {
      String   userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return bidRepository.findBidByUsername(userName);
    }

    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    public void deleteCategory(Long id){
        bidRepository.deleteById(id);
    }
}
