package com.collage.bid.services;

import com.collage.bid.model.Bid;
import com.collage.bid.model.Seller;
import com.collage.bid.model.User;
import com.collage.bid.repositories.BidRepository;
import com.collage.bid.repositories.SellerRepository;
import com.collage.bid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    @Autowired
    private BidRepository bidRepository;

    public SellerService(SellerRepository productRepository) {
        this.sellerRepository = productRepository;
    }

    public List<Seller> getSeller(){
        return sellerRepository.findAll();
    }

    public Seller getSeller(Long id){
        return sellerRepository.findById(id).orElse(null);
    }

    public Seller addSeller(Seller seller){
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public void deleteSeller(Long id){
        sellerRepository.deleteById(id);
    }


    private static final String UPLOAD_DIR = "D:\\FINAYEARPROJECT\\backup\\productfor";
//
//    public String storeImage(MultipartFile file, String username) throws IOException {
//        // Create the uploads directory if it doesn't exist
//        File uploadDir = new File(UPLOAD_DIR);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
//
//        // Create a unique file name using username and timestamp
//        String timestamp = String.valueOf(System.currentTimeMillis());
//        String fileName = username + "_" + timestamp + "_" + file.getOriginalFilename();
//
//        // Save the image in the file system
//        File destinationFile = new File(UPLOAD_DIR + fileName);
//        file.transferTo(destinationFile); // Store the file in the file system
//
//        // Convert the image to byte array for database storage
//        byte[] imageBytes = file.getBytes();
//
//        // Create Image entity and save to database
//        Seller seller = new Seller();
//        seller.setFilePath(destinationFile.getPath());  // Store the file path
//        seller.setData(imageBytes);  // Store the image as a BLOB
//
//        sellerRepository.save(seller);  // Save the image to the database
//
//        return "File uploaded and saved in both file system and database successfully!";
//    }
//
//    // Method to retrieve image from the file system
//    public Path getImagePath(String fileName) {
//        return Paths.get(UPLOAD_DIR + fileName);
//    }


     @Autowired
    private   UserRepository userRepository;




    public String applyForBid(String base64FileContent, String title, Long bidId, Long price) throws IOException {
        // Get the authenticated user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch the User entity based on the username
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return "User not found!";
        }

        // Fetch the Bid entity by bidId
        Bid bid = bidRepository.findById(bidId).orElse(null);

        if (bid == null) {
            return "Bid not found!";
        }

        // Decode the Base64 file content to get the raw bytes
        byte[] decodedBytes = Base64.getDecoder().decode(base64FileContent);

        // Generate a unique file name using UUID and the original file name (or just ".jpg")
        String fileName = UUID.randomUUID().toString() + ".jpg";  // Or based on the file type
        File destinationFile = new File(UPLOAD_DIR + fileName);

        // Write the decoded bytes to a file
        try (FileOutputStream fos = new FileOutputStream(destinationFile)) {
            fos.write(decodedBytes);
        }

        // Create a Seller entity
        Seller seller = new Seller();
        seller.setUsername(username);
        seller.setTitle(title);
        seller.setCreatedAt(LocalDate.now());
        seller.setCitizenship(user.getCitizenship());
        seller.setPanNo(user.getPanNo());
        seller.setVatNo(user.getVatNo());
        seller.setPrice(price);  // Set the price the seller is applying with
        seller.setFilePath(destinationFile.getPath());  // Store the file path
        seller.setData(decodedBytes);  // Store the document as a BLOB
        seller.setBid(bid);  // Associate the seller with the bid

        // Save the Seller entity to the database
        sellerRepository.save(seller);

        return "File uploaded and Seller applied to the bid successfully!";
    }

}



