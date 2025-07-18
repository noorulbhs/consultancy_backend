package com.altrevo.consultancy.controller;

import com.altrevo.consultancy.entity.StaticPage;
import com.altrevo.consultancy.service.StaticPageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(name = "Static Pages", description = "Static pages management endpoints")
public class StaticPageController {

    @Autowired
    private StaticPageService staticPageService;

    @GetMapping({"/public/static-pages"})
    public ResponseEntity<?> getAllStaticPages() {
        return ResponseEntity.ok(staticPageService.getAllStaticPages());
    }

    @GetMapping({"/public/static-pages/{id}"})
    public ResponseEntity<?> getAllStaticPagesById(@PathVariable String id) {
        return staticPageService.getStaticPageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/admin/static-pages/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> getStaticPageById(@PathVariable String id) {
        return staticPageService.getStaticPageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/admin/static-pages")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<StaticPage> createStaticPage(@RequestBody StaticPage page) {
        StaticPage saved = staticPageService.createStaticPage(page);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/admin/static-pages/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<StaticPage> updateStaticPage(@PathVariable String id, @RequestBody StaticPage page) {
        Optional<StaticPage> updated = staticPageService.updateStaticPage(id, page);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/admin/static-pages/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> deleteStaticPage(@PathVariable String id) {
        boolean deleted = staticPageService.deleteStaticPage(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(java.util.Collections.singletonMap("message", "Static page deleted successfully"));
    }
}
