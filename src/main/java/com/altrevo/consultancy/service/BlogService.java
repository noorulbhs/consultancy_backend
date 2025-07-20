package com.altrevo.consultancy.service;

import com.altrevo.consultancy.entity.BlogPost;
import com.altrevo.consultancy.enums.BlogStatus;
import com.altrevo.consultancy.repository.BlogInMemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BlogService {
    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);
    private final BlogInMemoryRepository blogPostRepository = new BlogInMemoryRepository();

    // Public methods (no authentication required)
    public List<BlogPost> getPublicBlogs(String category, Boolean featured, int page, int limit) {
        logger.info("Fetching public blogs - category: {}, featured: {}, page: {}, limit: {}", category, featured, page, limit);
        List<BlogPost> all = blogPostRepository.findAll().stream()
                .filter(blog -> blog.getStatus() == BlogStatus.PUBLISHED)
                .filter(blog -> category == null || (blog.getCategory() != null && blog.getCategory().equalsIgnoreCase(category)))
                .filter(blog -> featured == null || (featured && Boolean.TRUE.equals(blog.getFeatured())))
                .sorted(Comparator.comparing(BlogPost::getPublishedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
        return paginate(all, page, limit);
    }

    public List<BlogPost> getFeaturedBlogs(int page, int limit) {
        logger.info("Fetching featured blogs - page: {}, limit: {}", page, limit);
        List<BlogPost> all = blogPostRepository.findAll().stream()
                .filter(blog -> blog.getStatus() == BlogStatus.PUBLISHED)
                .filter(blog -> Boolean.TRUE.equals(blog.getFeatured()))
                .sorted(Comparator.comparing(BlogPost::getPublishedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
        return paginate(all, page, limit);
    }

    public List<BlogPost> searchBlogs(String keyword, int page, int limit) {
        logger.info("Searching blogs with keyword: {} - page: {}, limit: {}", keyword, page, limit);
        List<BlogPost> all = blogPostRepository.findAll().stream()
                .filter(blog -> blog.getStatus() == BlogStatus.PUBLISHED)
                .filter(blog -> (blog.getTitle() != null && blog.getTitle().toLowerCase().contains(keyword.toLowerCase())) ||
                        (blog.getContent() != null && blog.getContent().toLowerCase().contains(keyword.toLowerCase())))
                .sorted(Comparator.comparing(BlogPost::getPublishedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
        return paginate(all, page, limit);
    }

    public Optional<BlogPost> getPublicBlogById(Long id) {
        logger.info("Fetching public blog by ID: {}", id);
        return blogPostRepository.findById(id)
                .filter(blog -> blog.getStatus() == BlogStatus.PUBLISHED);
    }

    public Optional<BlogPost> getPublicBlogBySlug(String slug) {
        logger.info("Fetching public blog by slug: {}", slug);
        return blogPostRepository.findBySlug(slug)
                .filter(blog -> blog.getStatus() == BlogStatus.PUBLISHED);
    }

    public List<String> getPublicCategories() {
        logger.info("Fetching public blog categories");
        return blogPostRepository.findAll().stream()
                .filter(blog -> blog.getStatus() == BlogStatus.PUBLISHED)
                .map(BlogPost::getCategory)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getPublicTags() {
        logger.info("Fetching public blog tags");
        return blogPostRepository.findAll().stream()
                .filter(blog -> blog.getStatus() == BlogStatus.PUBLISHED)
                .flatMap(blog -> blog.getTags() != null ? blog.getTags().stream() : Stream.empty())
                .distinct()
                .collect(Collectors.toList());
    }

    // Admin methods (authentication required)
    public List<BlogPost> getAllBlogs(BlogStatus status, String category, Boolean featured, int page, int limit) {
        logger.info("Fetching all blogs for admin - status: {}, category: {}, featured: {}, page: {}, limit: {}", status, category, featured, page, limit);
        List<BlogPost> all = blogPostRepository.findAll().stream()
                .filter(blog -> status == null || blog.getStatus() == status)
                .filter(blog -> category == null || (blog.getCategory() != null && blog.getCategory().equalsIgnoreCase(category)))
                .filter(blog -> featured == null || (featured && Boolean.TRUE.equals(blog.getFeatured())))
                .sorted(Comparator.comparing(BlogPost::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
        return paginate(all, page, limit);
    }

    public Optional<BlogPost> getBlogById(Long id) {
        logger.info("Fetching blog by ID for admin: {}", id);
        return blogPostRepository.findById(id);
    }

    public BlogPost createBlog(BlogPost blog) {
        logger.info("Creating new blog: {}", blog.getTitle());
        return blogPostRepository.save(blog);
    }

    public BlogPost updateBlog(Long id, BlogPost updatedBlog) {
        logger.info("Updating blog with ID: {}", id);
        return blogPostRepository.findById(id)
                .map(blog -> {
                    blog.setTitle(updatedBlog.getTitle());
                    blog.setSlug(updatedBlog.getSlug());
                    blog.setSummary(updatedBlog.getSummary());
                    blog.setExcerpt(updatedBlog.getExcerpt());
                    blog.setContent(updatedBlog.getContent());
                    blog.setAuthorName(updatedBlog.getAuthorName());
                    blog.setAuthorTitle(updatedBlog.getAuthorTitle());
                    blog.setAuthorAvatar(updatedBlog.getAuthorAvatar());
                    blog.setAuthorBio(updatedBlog.getAuthorBio());
                    blog.setCategory(updatedBlog.getCategory());
                    blog.setTags(updatedBlog.getTags());
                    blog.setReadingTime(updatedBlog.getReadingTime());
                    blog.setViews(updatedBlog.getViews());
                    blog.setFeatured(updatedBlog.getFeatured());
                    blog.setFeaturedImage(updatedBlog.getFeaturedImage());
                    blog.setPublishedAt(updatedBlog.getPublishedAt());
                    blog.setStatus(updatedBlog.getStatus());
                    blog.setMetaTitle(updatedBlog.getMetaTitle());
                    blog.setMetaDescription(updatedBlog.getMetaDescription());
                    blog.setSortOrder(updatedBlog.getSortOrder());
                    blog.setUpdatedBy(updatedBlog.getUpdatedBy());
                    return blogPostRepository.save(blog);
                })
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + id));
    }

    public void deleteBlog(Long id) {
        logger.info("Deleting blog with ID: {}", id);
        blogPostRepository.deleteById(id);
    }

    // Statistics methods
    public BlogStats getBlogStats() {
        logger.info("Fetching blog statistics");
        List<BlogPost> all = blogPostRepository.findAll();
        long totalBlogs = all.size();
        long publishedBlogs = all.stream().filter(b -> b.getStatus() == BlogStatus.PUBLISHED).count();
        long draftBlogs = all.stream().filter(b -> b.getStatus() == BlogStatus.DRAFT).count();
        return new BlogStats(totalBlogs, publishedBlogs, draftBlogs);
    }

    // Helper for pagination
    private List<BlogPost> paginate(List<BlogPost> list, int page, int limit) {
        int fromIndex = Math.max(0, page * limit);
        int toIndex = Math.min(list.size(), fromIndex + limit);
        if (fromIndex > toIndex) return Collections.emptyList();
        return list.subList(fromIndex, toIndex);
    }

    // Inner class for response DTO
    public static class BlogStats {
        public final long totalBlogs;
        public final long publishedBlogs;
        public final long draftBlogs;
        public BlogStats(long totalBlogs, long publishedBlogs, long draftBlogs) {
            this.totalBlogs = totalBlogs;
            this.publishedBlogs = publishedBlogs;
            this.draftBlogs = draftBlogs;
        }
    }
}
