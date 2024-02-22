package com.ecomerce.controller;

import com.ecomerce.model.User;
import com.ecomerce.services.PdfGenerator;
import com.ecomerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    PdfGenerator pdfGenerator;
    @Autowired
    UserService userService;

    @Value("${pdf.generator.generated.path}")
    String filePath;

    @GetMapping("/invoice/download/{order_id}")
    public ResponseEntity<Resource> download(@PathVariable UUID order_id) throws IOException {
        pdfGenerator.generatePDF();
        File file = new File(filePath);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }


    @PostMapping("/create")
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public User get(@PathVariable UUID uuid) {
        //logged in user 
        return userService.get(uuid);
    }
}
