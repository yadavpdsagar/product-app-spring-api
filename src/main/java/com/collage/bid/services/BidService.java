package com.collage.bid.services;

import com.collage.bid.dto.BidDTO;
import com.collage.bid.dto.ItemDTO;
import com.collage.bid.model.Bid;
import com.collage.bid.model.Item;
import com.collage.bid.repositories.BidRepository;
import com.collage.bid.repositories.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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


    @Transactional
    public BidDTO getBidWithItems(Long bidId) {
        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(() -> new NoSuchElementException("Bid not found with id " + bidId));

        List<ItemDTO> itemDTOs = bid.getSubCategories().stream()
                .map(this::convertToItemDTO)
                .collect(Collectors.toList());

        return convertToBidDTO(bid, itemDTOs);
    }

    private ItemDTO convertToItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(item.getName());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setSpecification(item.getSpecification());
        itemDTO.setDeliveryTime(item.getDeliveryTime());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    private BidDTO convertToBidDTO(Bid bid, List<ItemDTO> itemDTOs) {
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(bid.getId());
        bidDTO.setUsername(bid.getUsername());
        bidDTO.setTitle(bid.getTitle());
        bidDTO.setBankAccNo(bid.getBankAccNo());
        bidDTO.setValidDate(bid.getValidDate());
        bidDTO.setCloseDate(bid.getCloseDate());
        bidDTO.setBankName(bid.getBankName());
        bidDTO.setItems(itemDTOs);
        return bidDTO;
    }

    @Transactional
    public List<BidDTO> getAllBids() {
        List<Bid> bids = bidRepository.findAll();  // Get all bids
        return bids.stream()
                .map(bid -> convertToBidDTO(bid, bid.getSubCategories().stream()
                        .map(this::convertToItemDTO)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
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
