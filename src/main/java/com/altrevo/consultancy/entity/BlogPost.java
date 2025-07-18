package com.altrevo.consultancy.entity;

import com.altrevo.consultancy.enums.BlogStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "blog_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BlogPost extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
//    @Column(unique = true, nullable = false)
    private String slug;
    
    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;
    
    @Column(name = "excerpt", columnDefinition = "TEXT")
    private String excerpt;
    
    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;
    
    @Column(name = "author_name")
    private String authorName;
    
    @Column(name = "author_title")
    private String authorTitle;
    
    @Column(name = "author_avatar")
    private String authorAvatar;
    
    @Column(name = "author_bio", columnDefinition = "TEXT")
    private String authorBio;
    
    @Column(name = "category")
    private String category;
    
    @ElementCollection
    @CollectionTable(name = "blog_post_tags", joinColumns = @JoinColumn(name = "blog_post_id"))
    @Column(name = "tag")
    private List<String> tags;
    
    @Column(name = "reading_time")
    private String readingTime;
    
    @Column(name = "views")
    private Long views = 0L;
    
    @Column(name = "featured")
    private Boolean featured = false;
    
    @Column(name = "featured_image")
    private String featuredImage;
    
    @Column(name = "published_at")
    private LocalDateTime publishedAt;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BlogStatus status = BlogStatus.DRAFT;
    
    @Column(name = "meta_title")
    private String metaTitle;
    
    @Column(name = "meta_description")
    private String metaDescription;
    
    @Column(name = "sort_order")
    private Integer sortOrder = 0;
}
