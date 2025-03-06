package com.collage.bid.controllers;

import com.collage.bid.model.Seller;
import com.collage.bid.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

import java.util.List;

@RestController
public class SellerController {

    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping("/products")
    public List<Seller> getProducts(){
        return sellerService.getSeller();
    }

    @GetMapping("/product/{id}")
    public Seller getProduct(@PathVariable("id") Long id){
        return sellerService.getSeller(id);
    }

    @PutMapping("/product/{id}")
    public Seller updateProduct(@RequestBody() Seller seller, @PathVariable("id") Long id){
        return sellerService.updateSeller(seller);
    }

    @PostMapping("/products")
    public ResponseEntity<Seller> addNew(@RequestBody() Seller seller){
        Seller newSeller = sellerService.addSeller(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSeller);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        sellerService.deleteSeller(id);
    }



//    // Upload image to both file system and database
//    @PostMapping("/upload")
//    public String uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {
//        try {
//            return sellerService.storeImage(file, username);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "File upload failed: " + e.getMessage();
//        }
//    }

//    // Retrieve image from the file system
//    @GetMapping("/view/file/{filename}")
//    public FileSystemResource viewImageFromFileSystem(@PathVariable String filename) {
//        Path imagePath = sellerService.getImagePath(filename);
//        return new FileSystemResource(imagePath.toFile());
//    }

    // Retrieve image from the database (BLOB)
//    @GetMapping("/view/db/{id}")
//    public ByteArrayResource viewImageFromDatabase(@PathVariable Long id) {
//        Optional<Product> image = productService.findById(id);
//        if (image.isPresent()) {
//            return new ByteArrayResource(image.get().getData());
//        }
//        return null; // or throw a 404 error
//    }




    @PostMapping("/apply-for-bid")
    public ResponseEntity<String> applyForBid(
            @RequestParam("file") String base64FileContent,  // Accept Base64 string
            @RequestParam("title") String title,
            @RequestParam("bidId") Long bidId,
            @RequestParam("price") Long price) {
        try {
            String message = sellerService.applyForBid(base64FileContent, title, bidId, price);
            return ResponseEntity.ok(message);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    // Retrieve image from the file system
//    @GetMapping("/view/{filename}")
//    public FileSystemResource viewImage(@PathVariable String filename) {
//        Path imagePath = sellerService.getImagePath(filename);
//        return new FileSystemResource(imagePath.toFile());
//    }

}
