package com.altrevo.consultancy.entity;

import com.altrevo.consultancy.enums.BlogStatus;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InMemoryBlogStore {
    public static final Map<Long, BlogPost> BLOG_POSTS = new HashMap<>();
    public static long idCounter = 1L;

    static {
        // Blog 1
        BlogPost blog1 = new BlogPost();
        blog1.setId(1L);
        blog1.setTitle("The Future of Cloud Computing in 2024");
        blog1.setSlug(null);
        blog1.setSummary("Cloud computing continues to evolve rapidly, bringing new opportunities and challenges for businesses worldwide...");
        blog1.setExcerpt(null);
        blog1.setContent("<h2>Introduction</h2><p>Cloud computing has revolutionized how businesses operate, and 2024 promises even more exciting developments...</p><h2>Key Trends</h2><ul><li>Multi-cloud strategies</li><li>Edge computing integration</li><li>Serverless architecture</li><li>AI-powered cloud services</li></ul>");
        blog1.setAuthorName("John Smith");
        blog1.setAuthorTitle("Senior Cloud Architect");
        blog1.setAuthorAvatar("");
        blog1.setAuthorBio("John is a seasoned cloud architect with 10+ years of experience.");
        blog1.setCategory("Cloud Computing");
        blog1.setTags(Arrays.asList("cloud", "technology", "trends", "2024", "cloud", "migration", "aws", "azure", "best-practices"));
        blog1.setReadingTime("8 min read");
        blog1.setViews(null);
        blog1.setFeatured(false);
        blog1.setFeaturedImage("https://images.unsplash.com/photo-1451187580459-43490279c0fa?w=800&h=400&fit=crop");
        blog1.setPublishedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 19));
        blog1.setStatus(BlogStatus.PUBLISHED);
        blog1.setMetaTitle("Exploring the latest trends and innovations in cloud technology for 2024");
        blog1.setMetaDescription("The Future of Cloud Computing in 2024");
        blog1.setSortOrder(0);
        blog1.setUpdatedBy("noorul@altrevo.com");
        BLOG_POSTS.put(blog1.getId(), blog1);

        // Blog 2
        BlogPost blog2 = new BlogPost();
        blog2.setId(2L);
        blog2.setTitle("DevOps Best Practices for 2024");
        blog2.setSlug("devops-best-practices-2024");
        blog2.setSummary("DevOps has become essential for modern software development, but implementing it correctly requires following proven best practices...");
        blog2.setExcerpt("Learn essential DevOps practices for 2024. Expert guide on CI/CD, infrastructure as code, and automation strategies.");
        blog2.setContent("<h2>Core DevOps Principles</h2><p>DevOps success depends on implementing core principles that foster collaboration and automation...</p><h2>Best Practices</h2><ul><li>Infrastructure as Code</li><li>Continuous Integration/Continuous Deployment</li><li>Automated Testing</li><li>Monitoring and Logging</li></ul>");
        blog2.setAuthorName("Sarah Johnson");
        blog2.setAuthorTitle("DevOps Lead");
        blog2.setAuthorAvatar("https://example.com/sarah-johnson.jpg");
        blog2.setAuthorBio("Sarah specializes in DevOps automation and CI/CD implementations.");
        blog2.setCategory("DevOps");
        blog2.setTags(Arrays.asList("devops", "automation", "best practices", "ci/cd", "ci-cd", "best-practices", "deployment"));
        blog2.setReadingTime("6 min read");
        blog2.setViews(980L);
        blog2.setFeatured(true);
        blog2.setFeaturedImage("https://example.com/devops-practices.jpg");
        blog2.setPublishedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 19));
        blog2.setStatus(BlogStatus.PUBLISHED);
        blog2.setMetaTitle("DevOps Best Practices for 2024 - Altrevo Tech Solutions");
        blog2.setMetaDescription("Essential DevOps practices every organization should implement");
        blog2.setSortOrder(2);
        blog2.setUpdatedBy(null);
        BLOG_POSTS.put(blog2.getId(), blog2);

        // Blog 3
        BlogPost blog3 = new BlogPost();
        blog3.setId(3L);
        blog3.setTitle("AI and Machine Learning in Business");
        blog3.setSlug("ai-machine-learning-business");
        blog3.setSummary("Artificial intelligence and machine learning are no longer future technologies—they are transforming businesses today...");
        blog3.setExcerpt("Discover how AI and machine learning are transforming modern business operations. Expert insights on implementation strategies and best practices.");
        blog3.setContent("<h2>AI in Business Today</h2><p>AI and machine learning technologies are being adopted across industries to drive efficiency and innovation...</p><h2>Implementation Strategies</h2><ul><li>Start with clear use cases</li><li>Invest in data quality</li><li>Choose the right tools</li><li>Train your team</li></ul>");
        blog3.setAuthorName("Michael Chen");
        blog3.setAuthorTitle("AI/ML Engineer");
        blog3.setAuthorAvatar("https://example.com/michael-chen.jpg");
        blog3.setAuthorBio("Michael is an expert in AI and ML with a focus on business applications.");
        blog3.setCategory("Artificial Intelligence");
        blog3.setTags(Arrays.asList("ai", "machine learning", "business", "automation", "machine-learning", "future-tech", "development"));
        blog3.setReadingTime("10 min read");
        blog3.setViews(1450L);
        blog3.setFeatured(false);
        blog3.setFeaturedImage("https://example.com/ai-business.jpg");
        blog3.setPublishedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 19));
        blog3.setStatus(BlogStatus.PUBLISHED);
        blog3.setMetaTitle("AI and Machine Learning in Business - Altrevo Tech Solutions");
        blog3.setMetaDescription("How AI and ML are transforming modern business operations");
        blog3.setSortOrder(3);
        blog3.setUpdatedBy(null);
        BLOG_POSTS.put(blog3.getId(), blog3);

        // Blog 4
        BlogPost blog4 = new BlogPost();
        blog4.setId(4L);
        blog4.setTitle("Getting Started with Cloud Migration");
        blog4.setSlug("getting-started-cloud-migration");
        blog4.setSummary("Learn the essential steps and best practices for successful cloud migration in this comprehensive guide.");
        blog4.setExcerpt("Complete guide to cloud migration with step-by-step instructions and best practices for businesses.");
        blog4.setContent("Cloud migration is a crucial step for modern businesses looking to scale and improve their infrastructure. In this comprehensive guide, we will walk you through the essential steps and best practices for successfully migrating your applications to the cloud.");
        blog4.setAuthorName(null);
        blog4.setAuthorTitle(null);
        blog4.setAuthorAvatar(null);
        blog4.setAuthorBio(null);
        blog4.setCategory("Cloud Computing");
        blog4.setTags(Arrays.asList("cloud", "migration"));
        blog4.setReadingTime(null);
        blog4.setViews(245L);
        blog4.setFeatured(false);
        blog4.setFeaturedImage("cloud-migration-guide.jpg");
        blog4.setPublishedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 29));
        blog4.setStatus(BlogStatus.PUBLISHED);
        blog4.setMetaTitle("Cloud Migration Guide - Best Practices and Steps");
        blog4.setMetaDescription("Getting Started with Cloud Migration");
        blog4.setSortOrder(4);
        blog4.setUpdatedBy("system");
        BLOG_POSTS.put(blog4.getId(), blog4);

        // Blog 5
        BlogPost blog5 = new BlogPost();
        blog5.setId(5L);
        blog5.setTitle("10 Best Practices for DevOps Implementation");
        blog5.setSlug("devops-best-practices");
        blog5.setSummary("Discover the top 10 DevOps best practices that every organization should implement for successful software delivery.");
        blog5.setExcerpt("Learn the most important DevOps practices for improving software delivery and team collaboration.");
        blog5.setContent("DevOps has revolutionized software development and deployment. Here are the top 10 best practices every organization should follow when implementing DevOps in their workflow.");
        blog5.setAuthorName(null);
        blog5.setAuthorTitle(null);
        blog5.setAuthorAvatar(null);
        blog5.setAuthorBio(null);
        blog5.setCategory("DevOps");
        blog5.setTags(Arrays.asList("devops"));
        blog5.setReadingTime(null);
        blog5.setViews(189L);
        blog5.setFeatured(false);
        blog5.setFeaturedImage("devops-practices.jpg");
        blog5.setPublishedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 29));
        blog5.setStatus(BlogStatus.PUBLISHED);
        blog5.setMetaTitle("10 Essential DevOps Best Practices for Success");
        blog5.setMetaDescription("10 Best Practices for DevOps Implementation");
        blog5.setSortOrder(5);
        blog5.setUpdatedBy("system");
        BLOG_POSTS.put(blog5.getId(), blog5);

        // Blog 6 (Draft)
        BlogPost blog6 = new BlogPost();
        blog6.setId(6L);
        blog6.setTitle("Introduction to Machine Learning for Developers");
        blog6.setSlug("machine-learning-for-developers");
        blog6.setSummary("Get started with machine learning as a developer with this comprehensive introduction to ML concepts and tools.");
        blog6.setExcerpt("Comprehensive introduction to machine learning concepts and tools for software developers.");
        blog6.setContent("Machine Learning is no longer just for data scientists. This article introduces ML concepts and tools that every developer should know about.");
        blog6.setAuthorName(null);
        blog6.setAuthorTitle(null);
        blog6.setAuthorAvatar(null);
        blog6.setAuthorBio(null);
        blog6.setCategory("Machine Learning");
        blog6.setTags(Arrays.asList("machine-learning"));
        blog6.setReadingTime(null);
        blog6.setViews(156L);
        blog6.setFeatured(false);
        blog6.setFeaturedImage("ml-developers.jpg");
        blog6.setPublishedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 29));
        blog6.setStatus(BlogStatus.DRAFT);
        blog6.setMetaTitle("Machine Learning for Developers - Getting Started Guide");
        blog6.setMetaDescription("Introduction to Machine Learning for Developers");
        blog6.setSortOrder(6);
        blog6.setUpdatedBy("system");
        BLOG_POSTS.put(blog6.getId(), blog6);
    }

    public static synchronized long getNextId() {
        return ++idCounter;
    }
}
