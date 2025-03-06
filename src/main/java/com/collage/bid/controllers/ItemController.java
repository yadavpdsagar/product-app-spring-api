package com.collage.bid.controllers;

import com.collage.bid.model.Item;
import com.collage.bid.services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/subCategories")
    public List<Item> getSubCategories(){
        return itemService.getAllSubCategories();
    }

    @GetMapping("/subCategory/{id}")
    public Item getSubCategory(@PathVariable("id") Long id){
        return itemService.getSubCategoryById(id);
    }

    @PutMapping("/subCategory/{id}")
    public Item updateSubCategory(@RequestBody() Item item, @PathVariable("id") Long id){
        return itemService.save(item);
    }

    @PostMapping("/subCategories")
    public Item addNew(@RequestBody() Item item){
        return itemService.save(item);
    }

    @DeleteMapping("/subCategory/{id}")
    public void deleteSubCategory(@PathVariable("id") Long id){
        itemService.deleteSubCategory(id);
    }
}
