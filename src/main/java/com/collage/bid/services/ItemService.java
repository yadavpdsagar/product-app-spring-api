package com.collage.bid.services;

import com.collage.bid.model.Item;
import com.collage.bid.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllSubCategories(){
        return itemRepository.findAll();
    }

    public Item getSubCategoryById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void deleteSubCategory(Long id){
        itemRepository.deleteById(id);
    }
}
