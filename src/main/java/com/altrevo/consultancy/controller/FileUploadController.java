package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@Tag(name = "File Upload", description = "File upload management")
public class FileUploadController {
    
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    
    @Value("${app.file.upload-dir:uploads}")
    private String uploadDir;
    
    @Value("${app.file.max-size:10485760}") // 10MB default
    private long maxFileSize;
    
    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('EDITOR')")
    @Operation(summary = "Upload file", description = "Upload files (images, documents, etc.)")
    public ResponseEntity<ApiResponse<FileUploadResponse>> uploadFile(
            @Parameter(description = "File to upload")
            @RequestParam("file") MultipartFile file,
            @Parameter(description = "File type category")
            @RequestParam(required = false, defaultValue = "general") String type,
            @Parameter(description = "Folder to organize files")
            @RequestParam(required = false, defaultValue = "general") String folder) {
        
        logger.info("Uploading file: {} to folder: {}", file.getOriginalFilename(), folder);
        
        try {
            // Validate file
            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.<FileUploadResponse>builder()
                                .success(false)
                                .message("File is empty")
                                .build());
            }
            
            if (file.getSize() > maxFileSize) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.<FileUploadResponse>builder()
                                .success(false)
                                .message("File size exceeds maximum limit of " + (maxFileSize / 1024 / 1024) + "MB")
                                .build());
            }
            
            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String uniqueId = UUID.randomUUID().toString().substring(0, 8);
            String filename = timestamp + "_" + uniqueId + fileExtension;
            
            // Create directory if it doesn't exist
            Path folderPath = Paths.get(uploadDir, folder);
            Files.createDirectories(folderPath);
            
            // Save file
            Path filePath = folderPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Create response
            FileUploadResponse response = FileUploadResponse.builder()
                    .filename(filename)
                    .originalName(originalFilename)
                    .url("/uploads/" + folder + "/" + filename)
                    .size(file.getSize())
                    .type(file.getContentType())
                    .uploadedAt(LocalDateTime.now())
                    .build();
            
            logger.info("File uploaded successfully: {}", filename);
            
            return ResponseEntity.ok(ApiResponse.<FileUploadResponse>builder()
                    .success(true)
                    .message("File uploaded successfully")
                    .data(response)
                    .build());
            
        } catch (IOException e) {
            logger.error("Error uploading file", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.<FileUploadResponse>builder()
                            .success(false)
                            .message("Error uploading file: " + e.getMessage())
                            .build());
        }
    }
    
    @DeleteMapping("/upload/{folder}/{filename}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Delete file", description = "Delete uploaded file")
    public ResponseEntity<ApiResponse<Void>> deleteFile(
            @Parameter(description = "Folder name")
            @PathVariable String folder,
            @Parameter(description = "Filename to delete")
            @PathVariable String filename) {
        
        logger.info("Deleting file: {} from folder: {}", filename, folder);
        
        try {
            Path filePath = Paths.get(uploadDir, folder, filename);
            
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                logger.info("File deleted successfully: {}", filename);
                
                return ResponseEntity.ok(ApiResponse.<Void>builder()
                        .success(true)
                        .message("File deleted successfully")
                        .build());
            } else {
                return ResponseEntity.notFound().build();
            }
            
        } catch (IOException e) {
            logger.error("Error deleting file", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.<Void>builder()
                            .success(false)
                            .message("Error deleting file: " + e.getMessage())
                            .build());
        }
    }
    
    // Inner class for file upload response
    public static class FileUploadResponse {
        public final String filename;
        public final String originalName;
        public final String url;
        public final long size;
        public final String type;
        public final LocalDateTime uploadedAt;
        
        private FileUploadResponse(String filename, String originalName, String url, 
                                  long size, String type, LocalDateTime uploadedAt) {
            this.filename = filename;
            this.originalName = originalName;
            this.url = url;
            this.size = size;
            this.type = type;
            this.uploadedAt = uploadedAt;
        }
        
        public static FileUploadResponseBuilder builder() {
            return new FileUploadResponseBuilder();
        }
        
        public static class FileUploadResponseBuilder {
            private String filename;
            private String originalName;
            private String url;
            private long size;
            private String type;
            private LocalDateTime uploadedAt;
            
            public FileUploadResponseBuilder filename(String filename) {
                this.filename = filename;
                return this;
            }
            
            public FileUploadResponseBuilder originalName(String originalName) {
                this.originalName = originalName;
                return this;
            }
            
            public FileUploadResponseBuilder url(String url) {
                this.url = url;
                return this;
            }
            
            public FileUploadResponseBuilder size(long size) {
                this.size = size;
                return this;
            }
            
            public FileUploadResponseBuilder type(String type) {
                this.type = type;
                return this;
            }
            
            public FileUploadResponseBuilder uploadedAt(LocalDateTime uploadedAt) {
                this.uploadedAt = uploadedAt;
                return this;
            }
            
            public FileUploadResponse build() {
                return new FileUploadResponse(filename, originalName, url, size, type, uploadedAt);
            }
        }
    }
}
