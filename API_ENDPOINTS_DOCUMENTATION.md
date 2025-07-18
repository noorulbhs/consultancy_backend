# API Endpoints Documentation
## Altrevo Tech Solutions - IT Consultancy Platform

This document provides a comprehensive specification of all API endpoints needed to replace mock data with real backend data, including authentication, request/response formats, and required user roles.

### 🔓 PUBLIC vs 🔐 ADMIN ENDPOINTS - SECURITY OVERVIEW

**RECOMMENDED ARCHITECTURE: ADMIN-MANAGED + PUBLIC API**
- Admin manages all content through authenticated admin panel
- Public website fetches latest content from unauthenticated API endpoints
- Best of both worlds: dynamic content management + public performance
- Content updates instantly without code deployments

**PUBLIC ENDPOINTS (🔓 No Authentication) - RECOMMENDED**
- Used by public website to fetch latest content
- Completely unauthenticated - no tokens, API keys, or headers required
- Serves admin-managed content to public visitors
- Fast, cached responses for optimal performance
- Base URL: `https://api.altrevo.com/v1/public`

**ADMIN ENDPOINTS (🔐 Authentication Required) - REQUIRED**
- Used by admin panel for content management
- Requires JWT token authentication via Bearer token
- Role-based access control (SUPER_ADMIN, ADMIN, EDITOR, VIEWER)
- Base URL: `https://api.altrevo.com/v1/admin`

**BENEFITS OF THIS APPROACH:**
- ✅ Dynamic content management without code changes
- ✅ Content updates reflect immediately on public site
- ✅ Admin can manage services, testimonials, team members, etc.
- ✅ Public API can be cached for optimal performance
- ✅ No authentication burden on public users
- ✅ Scalable and maintainable architecture

---

## AUTHENTICATION & AUTHORIZATION

### Base URLs
```
Public API (No Authentication): https://api.altrevo.com/v1/public
Admin API (Authentication Required): https://api.altrevo.com/v1/admin
```

### Authentication Strategy
- **Public Endpoints**: 
  - **NO AUTHENTICATION REQUIRED** - completely unauthenticated
  - Used by public website to fetch admin-managed content
  - High-performance, cached responses
  - No API keys, tokens, or headers needed
  - Open access for all public content
- **Admin Endpoints**: 
  - **AUTHENTICATION REQUIRED** - JWT token authentication
  - Used by admin panel for content management
  - All public content is created/managed here
  - Requires valid Bearer token in Authorization header

### Authentication Types (Admin Only)
- **Bearer Token**: JWT token for authenticated admin requests
- **Session**: For admin panel sessions

### User Roles (Admin Only)
- **SUPER_ADMIN**: Full system access
- **ADMIN**: Admin panel access, content management
- **EDITOR**: Content creation and editing
- **VIEWER**: Read-only access

### Important Security Notes
- Public endpoints serve admin-managed content without authentication
- Admin endpoints require valid JWT tokens for content management
- Public API responses should be cached for optimal performance
- Admin endpoints should implement rate limiting and security measures
- All content displayed publicly is controlled through admin panel

---

## 1. AUTHENTICATION ENDPOINTS (Admin Only)

### 1.1 Admin Login
**Endpoint**: `POST /admin/auth/login`  
**Access**: Public (Login endpoint)  
**Description**: Authenticate admin user and get JWT token

**Request Body**:
```json
{
  "email": "adminuser@altrevo.com", // password: adminpassword123
  "password": "adminpassword123"
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": "user123",
      "email": "adminuser@altrevo.com", // password: adminpassword123
      "name": "Admin User",
      "role": "ADMIN",
      "permissions": ["READ", "WRITE", "DELETE"]
    },
    "expiresIn": 3600
  }
}
```

### 1.1 Editor Login
**Endpoint**: `POST /admin/auth/login`  
**Access**: Public (Login endpoint)  
**Description**: Authenticate editor user and get JWT token

**Request Body**:
```json
{
  "email": "apitestuser3@altrevo.com", // password: apitestpassword3
  "password": "apitestpassword3"
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": "user456",
      "email": "apitestuser3@altrevo.com", // password: apitestpassword3
      "name": "API Test User 3",
      "role": "EDITOR",
      "permissions": ["READ", "WRITE"]
    },
    "expiresIn": 3600
  }
}
```

### 1.2 Refresh Token
**Endpoint**: `POST /admin/auth/refresh`  
**Access**: Authenticated  
**Description**: Refresh JWT token

**Request Body**:
```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Response Body**:
```json
{
  "success": true,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "expiresIn": 3600
  }
}
```

### 1.3 Logout
**Endpoint**: `POST /admin/auth/logout`  
**Access**: Authenticated  
**Description**: Logout user and invalidate tokens

**Request Body**:
```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Logout successful"
}
```

---

## 2. SITE SETTINGS ENDPOINTS

### 2.1 Get Site Settings (Public) 🔓
**Endpoint**: `GET /public/settings`  
**Access**: Public (No Authentication Required)  
**Required Role**: None  
**Description**: Get current site settings managed by admin - serves latest admin-configured content

**Response Body**:
```json
{
  "success": true,
  "data": {
    "id": "main-settings",
    "version": "2.0",
    "companyName": "Altrevo Tech Solutions",
    "tagline": "Innovative Technology Solutions for Modern Business",
    "description": "Leading technology consultancy...",
    "logoUrl": "altrevo-logo.png",
    "faviconUrl": "altrevo-favicon.png",
    "email": "contact@altrevo.com",
    "phone": "+1 (555) 123-4567",
    "address": "123 Innovation Drive, Suite 100",
    "city": "San Francisco",
    "state": "California",
    "country": "United States",
    "zipCode": "94107",
    "social": {
      "linkedin": "https://linkedin.com/company/altrevo-tech-solutions",
      "facebook": "https://facebook.com/altrevotechsolutions",
      "instagram": "https://instagram.com/altrevotechsolutions"
    },
    "seo": {
      "metaTitle": "Altrevo Tech Solutions - Leading Technology Consultancy",
      "metaDescription": "Transform your business with Altrevo's expert technology consulting...",
      "keywords": ["technology consulting", "digital transformation", "cloud solutions"]
    },
    "footer": {
      "copyrightText": "© 2024 Altrevo Tech Solutions. All rights reserved.",
      "quickLinks": [
        {"title": "Privacy Policy", "url": "/privacy-policy"},
        {"title": "Terms of Service", "url": "/terms-of-service"}
      ]
    },
    "contactForm": {
      "recipientEmail": "inquiries@altrevo.com",
      "subjectOptions": [
        {"value": "general", "label": "General Inquiry", "enabled": true},
        {"value": "consultation", "label": "Free Consultation", "enabled": true}
      ],
      "serviceOptions": [
        {"value": "Cloud Migration", "label": "Cloud Migration", "enabled": true},
        {"value": "DevOps Strategy", "label": "DevOps Strategy", "enabled": true}
      ]
    },
    "statistics": {
      "projectsCompleted": {
        "number": "150+",
        "label": "Projects Completed",
        "icon": "fas fa-project-diagram",
        "enabled": true
      }
    }
  }
}
```

### 2.2 Update Site Settings (Admin) 🔐
**Endpoint**: `PUT /admin/settings`  
**Access**: Authenticated (Admin Only - JWT Token Required)  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Update site settings - requires Bearer token authentication

**Request Body**:
```json
{
  "companyName": "Altrevo Tech Solutions",
  "tagline": "Updated tagline",
  "email": "contact@altrevo.com",
  "phone": "+1 (555) 123-4567",
  "social": {
    "linkedin": "https://linkedin.com/company/altrevo-tech-solutions"
  },
  "contactForm": {
    "subjectOptions": [
      {"value": "general", "label": "General Inquiry", "enabled": true}
    ]
  }
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Settings updated successfully",
  "data": {
    "id": "main-settings",
    "lastUpdated": "2025-01-15T10:30:00Z",
    "updatedBy": "admin@altrevo.com"
  }
}
```

---

## 3. SERVICES ENDPOINTS

### 3.1 Get All Services (Public) 🔓
**Endpoint**: `GET /public/services`  
**Access**: Public (No Authentication Required)  
**Required Role**: None  
**Description**: Get all published services managed by admin - serves latest admin-configured services

**Query Parameters**:
- `category` (optional): Filter by service category
- `featured` (optional): Filter featured services only
- `limit` (optional): Number of services to return
- `offset` (optional): Pagination offset

**Response Body**:
```json
{
  "success": true,
  "data": {
    "services": [
      {
        "id": 1,
        "title": "Cloud Migration",
        "description": "Seamless cloud migration services",
        "detailedDescription": "Complete cloud migration with zero downtime...",
        "category": "Cloud Solutions",
        "icon": "fas fa-cloud",
        "features": [
          "Zero downtime migration",
          "Cost optimization",
          "Security assessment"
        ],
        "technologies": ["AWS", "Azure", "Google Cloud"],
        "duration": "4-8 weeks",
        "deliverables": [
          "Migration strategy document",
          "Implemented cloud infrastructure"
        ],
        "caseStudy": {
          "client": "TechCorp Inc.",
          "challenge": "Legacy system migration",
          "solution": "Phased cloud migration approach",
          "results": "50% cost reduction, 99.9% uptime"
        },
        "featured": true,
        "status": "active"
      }
    ],
    "pagination": {
      "total": 8,
      "page": 1,
      "limit": 10,
      "totalPages": 1
    }
  }
}
```

### 3.2 Get Service by ID (Public) 🔓
**Endpoint**: `GET /public/services/{id}`  
**Access**: Public (No Authentication Required)  
**Required Role**: None  
**Description**: Get detailed service information managed by admin - serves latest admin-configured service details

**Response Body**:
```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "Cloud Migration",
    "description": "Seamless cloud migration services",
    "detailedDescription": "Complete cloud migration...",
    "category": "Cloud Solutions",
    "icon": "fas fa-cloud",
    "features": ["Zero downtime migration"],
    "technologies": ["AWS", "Azure"],
    "duration": "4-8 weeks",
    "deliverables": ["Migration strategy document"],
    "caseStudy": {
      "client": "TechCorp Inc.",
      "challenge": "Legacy system migration",
      "solution": "Phased cloud migration approach",
      "results": "50% cost reduction"
    },
    "featured": true,
    "status": "active"
  }
}
```

### 3.3 Get All Services (Admin) 🔐
**Endpoint**: `GET /admin/services`  
**Access**: Authenticated (Admin Only - JWT Token Required)  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Get all services including drafts for admin management - requires Bearer token authentication

**Query Parameters**:
- `category` (optional): Filter by service category
- `status` (optional): Filter by status (active/inactive/draft)
- `featured` (optional): Filter featured services only
- `limit` (optional): Number of services to return
- `offset` (optional): Pagination offset

### 3.4 Create Service (Admin)
**Endpoint**: `POST /admin/services`  
**Access**: Authenticated (Admin Only)  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Create new service

**Request Body**:
```json
{
  "title": "AI Implementation",
  "description": "AI and machine learning solutions",
  "detailedDescription": "Custom AI solutions for business automation...",
  "category": "Artificial Intelligence",
  "icon": "fas fa-robot",
  "features": [
    "Custom AI models",
    "Machine learning algorithms"
  ],
  "technologies": ["Python", "TensorFlow", "AWS SageMaker"],
  "duration": "6-12 weeks",
  "deliverables": [
    "AI model implementation",
    "Training documentation"
  ],
  "featured": false,
  "status": "active"
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Service created successfully",
  "data": {
    "id": 9,
    "title": "AI Implementation",
    "createdAt": "2025-01-15T10:30:00Z"
  }
}
```

### 3.5 Update Service (Admin)
**Endpoint**: `PUT /admin/services/{id}`  
**Access**: Authenticated (Admin Only)  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Update existing service

**Request Body**:
```json
{
  "title": "Updated Cloud Migration",
  "description": "Updated description",
  "featured": true,
  "status": "active"
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Service updated successfully",
  "data": {
    "id": 1,
    "updatedAt": "2025-01-15T10:30:00Z"
  }
}
```

### 3.6 Delete Service (Admin)
**Endpoint**: `DELETE /admin/services/{id}`  
**Access**: Authenticated (Admin Only)  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Delete service

**Response Body**:
```json
{
  "success": true,
  "message": "Service deleted successfully"
}
```

---

## 4. BLOG ENDPOINTS

### 4.1 Get All Blog Posts
**Endpoint**: `GET /blogs`  
**Access**: Public  
**Required Role**: None (Public)  
**Description**: Get all published blog posts

**Query Parameters**:
- `category` (optional): Filter by category
- `featured` (optional): Filter featured posts only
- `limit` (optional): Number of posts to return
- `offset` (optional): Pagination offset
- `status` (optional): Filter by status (published/draft) - Admin only

**Response Body**:
```json
{
  "success": true,
  "data": {
    "posts": [
      {
        "id": 1,
        "title": "The Future of Cloud Computing",
        "slug": "future-of-cloud-computing",
        "summary": "Exploring emerging trends in cloud technology",
        "excerpt": "Cloud computing continues to evolve...",
        "content": "<p>Full HTML content here...</p>",
        "author": {
          "name": "John Smith",
          "title": "Cloud Architect",
          "avatar": "https://example.com/avatar.jpg",
          "bio": "Expert in cloud technologies"
        },
        "category": "Cloud Computing",
        "tags": ["cloud", "technology", "future"],
        "readingTime": "5 min read",
        "views": 1250,
        "featured": true,
        "featuredImage": "https://example.com/featured.jpg",
        "publishedAt": "2024-01-15T10:30:00Z",
        "status": "published"
      }
    ],
    "pagination": {
      "total": 15,
      "page": 1,
      "limit": 10,
      "totalPages": 2
    }
  }
}
```

### 4.2 Get Blog Post by ID/Slug
**Endpoint**: `GET /blogs/{id}` or `GET /blogs/slug/{slug}`  
**Access**: Public  
**Required Role**: None (Public)  
**Description**: Get detailed blog post

**Response Body**:
```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "The Future of Cloud Computing",
    "slug": "future-of-cloud-computing",
    "content": "<p>Full HTML content...</p>",
    "author": {
      "name": "John Smith",
      "title": "Cloud Architect",
      "avatar": "https://example.com/avatar.jpg"
    },
    "category": "Cloud Computing",
    "tags": ["cloud", "technology"],
    "readingTime": "5 min read",
    "views": 1250,
    "publishedAt": "2024-01-15T10:30:00Z",
    "relatedPosts": [
      {
        "id": 2,
        "title": "Cloud Security Best Practices",
        "slug": "cloud-security-best-practices"
      }
    ]
  }
}
```

### 4.3 Create Blog Post
**Endpoint**: `POST /blogs`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Create new blog post

**Request Body**:
```json
{
  "title": "New Blog Post",
  "slug": "new-blog-post",
  "summary": "Brief summary",
  "excerpt": "Post excerpt",
  "content": "<p>Full HTML content</p>",
  "author": {
    "name": "Jane Doe",
    "title": "Technical Writer",
    "avatar": "https://example.com/avatar.jpg",
    "bio": "Expert technical writer"
  },
  "category": "Technology",
  "tags": ["tech", "innovation"],
  "featuredImage": "https://example.com/featured.jpg",
  "featured": false,
  "status": "draft"
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Blog post created successfully",
  "data": {
    "id": 16,
    "title": "New Blog Post",
    "slug": "new-blog-post",
    "createdAt": "2025-01-15T10:30:00Z"
  }
}
```

### 4.4 Update Blog Post
**Endpoint**: `PUT /blogs/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Update existing blog post

### 4.5 Delete Blog Post
**Endpoint**: `DELETE /blogs/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Delete blog post

---

## 5. TEAM ENDPOINTS

### 5.1 Get All Team Members
**Endpoint**: `GET /team`  
**Access**: Public  
**Required Role**: None (Public)  
**Description**: Get all public team members

**Query Parameters**:
- `featured` (optional): Filter featured team members only
- `department` (optional): Filter by department
- `isPublic` (optional): Filter public visibility (Admin only)

**Response Body**:
```json
{
  "success": true,
  "data": {
    "members": [
      {
        "id": 1,
        "name": "John Smith",
        "role": "Senior Cloud Architect",
        "department": "Engineering",
        "email": "john.smith@altrevo.com",
        "phone": "+1 (555) 123-4567",
        "linkedin": "https://linkedin.com/in/johnsmith",
        "twitter": "https://twitter.com/johnsmith",
        "github": "https://github.com/johnsmith",
        "photoUrl": "https://example.com/john-smith.jpg",
        "bio": "John is a seasoned cloud architect with 10+ years of experience...",
        "skills": ["AWS", "Azure", "Kubernetes", "Docker"],
        "experience": "10+ years",
        "education": "MS Computer Science, Stanford University",
        "location": "San Francisco, CA",
        "joinDate": "2020-01-15",
        "isPublic": true,
        "featured": true,
        "achievements": [
          "AWS Solutions Architect Professional",
          "Led 50+ cloud migrations"
        ],
        "languages": ["English", "Spanish"],
        "specializations": ["Cloud Architecture", "DevOps", "Microservices"],
        "certifications": [
          "AWS Solutions Architect Professional",
          "Certified Kubernetes Administrator"
        ],
        "interests": ["Technology trends", "Open source"],
        "workStyle": "Collaborative and detail-oriented",
        "motto": "Innovation through collaboration"
      }
    ],
    "pagination": {
      "total": 12,
      "page": 1,
      "limit": 10,
      "totalPages": 2
    }
  }
}
```

### 5.2 Get Team Member by ID
**Endpoint**: `GET /team/{id}`  
**Access**: Public  
**Required Role**: None (Public)  
**Description**: Get detailed team member information

### 5.3 Create Team Member
**Endpoint**: `POST /team`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Add new team member

**Request Body**:
```json
{
  "name": "Jane Doe",
  "role": "Frontend Developer",
  "department": "Engineering",
  "email": "jane.doe@altrevo.com",
  "linkedin": "https://linkedin.com/in/janedoe",
  "photoUrl": "https://example.com/jane-doe.jpg",
  "bio": "Jane is a skilled frontend developer...",
  "skills": ["React", "Angular", "Vue.js"],
  "experience": "5+ years",
  "isPublic": true,
  "featured": false
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Team member created successfully",
  "data": {
    "id": 13,
    "name": "Jane Doe",
    "createdAt": "2025-01-15T10:30:00Z"
  }
}
```

### 5.4 Update Team Member
**Endpoint**: `PUT /team/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Update team member information

### 5.5 Delete Team Member
**Endpoint**: `DELETE /team/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Remove team member

---

## 6. TESTIMONIALS ENDPOINTS

### 6.1 Get All Testimonials (Public) 🔓
**Endpoint**: `GET /public/testimonials`  
**Access**: Public (No Authentication Required)  
**Required Role**: None  
**Description**: Get all published testimonials managed by admin - serves latest admin-configured testimonials

**Query Parameters**:
- `featured` (optional): Filter featured testimonials only
- `rating` (optional): Filter by minimum rating
- `limit` (optional): Number of testimonials to return

**Response Body**:
```json
{
  "success": true,
  "data": {
    "testimonials": [
      {
        "id": 1,
        "name": "Sarah Johnson",
        "designation": "CTO",
        "company": "TechCorp Solutions",
        "companyLogo": "https://example.com/techcorp-logo.png",
        "message": "Altrevo transformed our entire infrastructure. Their expertise in cloud migration was exceptional...",
        "rating": 5,
        "photoUrl": "https://example.com/sarah-johnson.jpg",
        "published": true,
        "featured": true,
        "date": "2024-01-15",
        "projectType": "Cloud Migration",
        "location": "San Francisco, CA",
        "tags": ["cloud", "migration", "infrastructure"]
      }
    ],
    "pagination": {
      "total": 25,
      "page": 1,
      "limit": 10,
      "totalPages": 3
    }
  }
}
```

### 6.2 Create Testimonial
**Endpoint**: `POST /testimonials`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Add new testimonial

**Request Body**:
```json
{
  "name": "Michael Chen",
  "designation": "CEO",
  "company": "StartupXYZ",
  "companyLogo": "https://example.com/startupxyz-logo.png",
  "message": "Outstanding service and support...",
  "rating": 5,
  "photoUrl": "https://example.com/michael-chen.jpg",
  "published": true,
  "featured": false,
  "projectType": "Digital Transformation",
  "location": "New York, NY",
  "tags": ["digital", "transformation"]
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Testimonial created successfully",
  "data": {
    "id": 26,
    "name": "Michael Chen",
    "createdAt": "2025-01-15T10:30:00Z"
  }
}
```

### 6.3 Update Testimonial
**Endpoint**: `PUT /testimonials/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Update existing testimonial

### 6.4 Delete Testimonial
**Endpoint**: `DELETE /testimonials/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Delete testimonial

---

## 7. CAREERS/JOBS ENDPOINTS

### 7.1 Get All Job Openings
**Endpoint**: `GET /careers`  
**Access**: Public  
**Required Role**: None (Public)  
**Description**: Get all open job positions

**Query Parameters**:
- `department` (optional): Filter by department
- `location` (optional): Filter by location
- `type` (optional): Filter by employment type
- `isOpen` (optional): Filter open positions only

**Response Body**:
```json
{
  "success": true,
  "data": {
    "jobs": [
      {
        "id": 1,
        "title": "Senior Cloud Engineer",
        "department": "Engineering",
        "location": "San Francisco, CA",
        "type": "Full-time",
        "experience": "5+ years",
        "salary": "$120,000 - $160,000",
        "remoteWork": "Hybrid",
        "openings": 2,
        "description": "We are looking for a Senior Cloud Engineer to join our growing team...",
        "requirements": [
          "5+ years of cloud experience",
          "AWS/Azure certifications",
          "Kubernetes experience"
        ],
        "responsibilities": [
          "Design and implement cloud solutions",
          "Mentor junior engineers",
          "Optimize cloud costs"
        ],
        "skills": ["AWS", "Azure", "Kubernetes", "Docker", "Terraform"],
        "benefits": [
          "Health insurance",
          "401(k) matching",
          "Flexible work hours",
          "Professional development budget"
        ],
        "isOpen": true,
        "postedAt": "2024-01-15T10:30:00Z",
        "applicationDeadline": "2024-03-15T23:59:59Z"
      }
    ],
    "pagination": {
      "total": 8,
      "page": 1,
      "limit": 10,
      "totalPages": 1
    }
  }
}
```

### 7.2 Get Job by ID
**Endpoint**: `GET /careers/{id}`  
**Access**: Public  
**Required Role**: None (Public)  
**Description**: Get detailed job information

### 7.3 Create Job Opening
**Endpoint**: `POST /careers`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Create new job opening

**Request Body**:
```json
{
  "title": "Frontend Developer",
  "department": "Engineering",
  "location": "Remote",
  "type": "Full-time",
  "experience": "3+ years",
  "salary": "$90,000 - $120,000",
  "remoteWork": "Fully Remote",
  "openings": 1,
  "description": "Join our frontend team to build amazing user experiences...",
  "requirements": [
    "3+ years React experience",
    "TypeScript proficiency",
    "CSS/SCSS expertise"
  ],
  "skills": ["React", "TypeScript", "CSS", "HTML"],
  "benefits": [
    "Health insurance",
    "Remote work",
    "Learning budget"
  ],
  "isOpen": true,
  "applicationDeadline": "2024-04-15T23:59:59Z"
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Job opening created successfully",
  "data": {
    "id": 9,
    "title": "Frontend Developer",
    "createdAt": "2025-01-15T10:30:00Z"
  }
}
```

### 7.4 Update Job Opening
**Endpoint**: `PUT /careers/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Update job opening

### 7.5 Delete Job Opening
**Endpoint**: `DELETE /careers/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Delete job opening

---

## 8. PROJECT ENDPOINTS

### 8.1 Get All Projects
**Endpoint**: `GET /projects`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Get all projects (Admin only)

**Query Parameters**:
- `status` (optional): Filter by project status
- `priority` (optional): Filter by priority
- `category` (optional): Filter by category
- `clientName` (optional): Filter by client name
- `limit` (optional): Number of projects to return
- `offset` (optional): Pagination offset

**Response Body**:
```json
{
  "success": true,
  "data": {
    "projects": [
      {
        "id": "proj-001",
        "name": "E-commerce Platform Development",
        "description": "Full-scale e-commerce platform with payment integration...",
        "clientName": "TechCorp Solutions",
        "clientEmail": "contact@techcorp.com",
        "clientPhone": "+1-555-0123",
        "clientCompany": "TechCorp Solutions Inc.",
        "startDate": "2024-01-15T00:00:00Z",
        "endDate": "2024-06-30T00:00:00Z",
        "estimatedBudget": 85000,
        "actualBudget": 78000,
        "status": "ACTIVE",
        "priority": "HIGH",
        "progress": 75,
        "category": "Web Development",
        "technologies": ["Angular", "Node.js", "MongoDB", "Stripe API"],
        "projectManager": "John Smith",
        "teamMembers": [
          {
            "id": "tm-001",
            "name": "John Smith",
            "role": "Project Manager",
            "allocatedHours": 40,
            "hourlyRate": 75
          }
        ],
        "milestones": [
          {
            "id": "ms-001",
            "title": "Requirements Analysis",
            "description": "Complete requirements gathering and analysis",
            "dueDate": "2024-02-15T00:00:00Z",
            "status": "COMPLETED",
            "completedDate": "2024-02-10T00:00:00Z"
          }
        ],
        "documents": [
          {
            "id": "doc-001",
            "title": "Project Proposal",
            "type": "PDF",
            "url": "https://example.com/project-proposal.pdf",
            "uploadedAt": "2024-01-15T10:30:00Z"
          }
        ],
        "createdAt": "2024-01-15T10:30:00Z",
        "updatedAt": "2024-01-20T10:30:00Z"
      }
    ],
    "pagination": {
      "total": 45,
      "page": 1,
      "limit": 10,
      "totalPages": 5
    }
  }
}
```

### 8.2 Get Project by ID
**Endpoint**: `GET /projects/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Get detailed project information

### 8.3 Create Project
**Endpoint**: `POST /projects`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Create new project

**Request Body**:
```json
{
  "name": "Mobile App Development",
  "description": "Native mobile app for iOS and Android",
  "clientName": "StartupXYZ",
  "clientEmail": "ceo@startupxyz.com",
  "clientPhone": "+1-555-0456",
  "clientCompany": "StartupXYZ Inc.",
  "startDate": "2024-03-01T00:00:00Z",
  "endDate": "2024-08-31T00:00:00Z",
  "estimatedBudget": 120000,
  "status": "PLANNING",
  "priority": "MEDIUM",
  "category": "Mobile Development",
  "technologies": ["React Native", "Firebase", "Node.js"],
  "projectManager": "Jane Doe"
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Project created successfully",
  "data": {
    "id": "proj-046",
    "name": "Mobile App Development",
    "createdAt": "2025-01-15T10:30:00Z"
  }
}
```

### 8.4 Update Project
**Endpoint**: `PUT /projects/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Update project information

### 8.5 Delete Project
**Endpoint**: `DELETE /projects/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Delete project

---

## 9. ENQUIRY ENDPOINTS

### 9.1 Submit Enquiry (Public)
**Endpoint**: `POST /public/enquiries`  
**Access**: Public (No Authentication)  
**Required Role**: None  
**Description**: Submit contact form enquiry from public website

**Request Body**:
```json
{
  "name": "Bob Wilson",
  "email": "bob@example.com",
  "phone": "+1-555-0321",
  "company": "Wilson Industries",
  "subject": "general",
  "service": "DevOps Strategy",
  "message": "We need help with our DevOps implementation..."
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Thank you for your enquiry! We will get back to you within 24 hours.",
  "data": {
    "id": 128,
    "referenceNumber": "ALT-2025-0128",
    "createdAt": "2025-01-15T10:30:00Z"
  }
}
```

### 9.2 Get All Enquiries (Admin)
**Endpoint**: `GET /admin/enquiries`  
**Access**: Authenticated (Admin Only)  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Get all contact form enquiries for admin management

**Query Parameters**:
- `isRead` (optional): Filter by read status
- `subject` (optional): Filter by subject
- `service` (optional): Filter by service of interest
- `dateFrom` (optional): Filter from date
- `dateTo` (optional): Filter to date
- `limit` (optional): Number of enquiries to return
- `offset` (optional): Pagination offset

**Response Body**:
```json
{
  "success": true,
  "data": {
    "enquiries": [
      {
        "id": 1,
        "name": "Alice Johnson",
        "email": "alice@example.com",
        "phone": "+1-555-0789",
        "company": "Example Corp",
        "subject": "consultation",
        "service": "Cloud Migration",
        "message": "We are interested in migrating our legacy systems to the cloud...",
        "date": "2024-01-15",
        "isRead": false,
        "createdAt": "2024-01-15T14:30:00Z",
        "updatedAt": "2024-01-15T14:30:00Z"
      }
    ],
    "pagination": {
      "total": 127,
      "page": 1,
      "limit": 10,
      "totalPages": 13
    },
    "stats": {
      "totalEnquiries": 127,
      "unreadEnquiries": 15,
      "thisMonth": 23,
      "lastMonth": 18
    }
  }
}
```

### 9.3 Get Enquiry by ID (Admin)
**Endpoint**: `GET /admin/enquiries/{id}`  
**Access**: Authenticated (Admin Only)  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Get detailed enquiry information

### 9.4 Update Enquiry Status (Admin)
**Endpoint**: `PUT /admin/enquiries/{id}`  
**Access**: Authenticated (Admin Only)  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Update enquiry read status

**Request Body**:
```json
{
  "isRead": true
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Enquiry status updated successfully",
  "data": {
    "id": 1,
    "isRead": true,
    "updatedAt": "2025-01-15T10:30:00Z"
  }
}
```

### 9.5 Delete Enquiry (Admin)
**Endpoint**: `DELETE /admin/enquiries/{id}`  
**Access**: Authenticated (Admin Only)  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Delete enquiry

---

## 10. STATIC PAGES ENDPOINTS

### 10.1 Get All Static Pages
**Endpoint**: `GET /pages`  
**Access**: Public  
**Required Role**: None (Public)  
**Description**: Get all published static pages

**Response Body**:
```json
{
  "success": true,
  "data": {
    "pages": [
      {
        "id": "privacy-policy",
        "title": "Privacy Policy",
        "metaDescription": "Altrevo's privacy policy and data protection practices",
        "content": "<h1>Privacy Policy</h1><p>This privacy policy describes...</p>",
        "slug": "privacy-policy",
        "status": "published",
        "lastUpdated": "2024-01-15T10:30:00Z",
        "createdAt": "2024-01-01T00:00:00Z"
      },
      {
        "id": "terms-of-service",
        "title": "Terms of Service",
        "metaDescription": "Terms and conditions for using Altrevo services",
        "content": "<h1>Terms of Service</h1><p>By using our services...</p>",
        "slug": "terms-of-service",
        "status": "published",
        "lastUpdated": "2024-01-15T10:30:00Z"
      }
    ]
  }
}
```

### 10.2 Get Static Page by ID/Slug
**Endpoint**: `GET /pages/{id}` or `GET /pages/slug/{slug}`  
**Access**: Public  
**Required Role**: None (Public)  
**Description**: Get specific static page

### 10.3 Create Static Page
**Endpoint**: `POST /pages`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Create new static page

**Request Body**:
```json
{
  "id": "cookie-policy",
  "title": "Cookie Policy",
  "metaDescription": "Information about cookies used on our website",
  "content": "<h1>Cookie Policy</h1><p>This website uses cookies...</p>",
  "slug": "cookie-policy",
  "status": "published"
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Static page created successfully",
  "data": {
    "id": "cookie-policy",
    "title": "Cookie Policy",
    "createdAt": "2025-01-15T10:30:00Z"
  }
}
```

### 10.4 Update Static Page
**Endpoint**: `PUT /pages/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Update static page

### 10.5 Delete Static Page
**Endpoint**: `DELETE /pages/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Delete static page

---

## 11. DASHBOARD & ANALYTICS ENDPOINTS

### 11.1 Get Dashboard Metrics
**Endpoint**: `GET /dashboard/metrics`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Get dashboard analytics and metrics

**Response Body**:
```json
{
  "success": true,
  "data": {
    "overview": {
      "totalProjects": 45,
      "activeProjects": 12,
      "completedProjects": 30,
      "onHoldProjects": 3,
      "totalClients": 35,
      "teamMembers": 25,
      "monthlyRevenue": 450000,
      "clientSatisfaction": 4.8
    },
    "recentActivities": [
      {
        "id": 1,
        "type": "project",
        "action": "created",
        "description": "New project 'Mobile App Development' created",
        "user": "admin@altrevo.com",
        "timestamp": "2025-01-15T10:30:00Z"
      },
      {
        "id": 2,
        "type": "enquiry",
        "action": "received",
        "description": "New enquiry from Alice Johnson",
        "timestamp": "2025-01-15T09:15:00Z"
      }
    ],
    "monthlyStats": {
      "enquiries": [
        {"month": "Jan", "count": 23},
        {"month": "Dec", "count": 18},
        {"month": "Nov", "count": 25}
      ],
      "projects": [
        {"month": "Jan", "count": 3},
        {"month": "Dec", "count": 2},
        {"month": "Nov", "count": 4}
      ],
      "revenue": [
        {"month": "Jan", "amount": 450000},
        {"month": "Dec", "amount": 420000},
        {"month": "Nov", "amount": 380000}
      ]
    },
    "topServices": [
      {"name": "Cloud Migration", "requests": 45},
      {"name": "DevOps Strategy", "requests": 32},
      {"name": "Digital Transformation", "requests": 28}
    ]
  }
}
```

### 11.2 Get Activity Log
**Endpoint**: `GET /dashboard/activities`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Get detailed activity log

**Query Parameters**:
- `type` (optional): Filter by activity type
- `user` (optional): Filter by user
- `dateFrom` (optional): Filter from date
- `dateTo` (optional): Filter to date
- `limit` (optional): Number of activities to return

**Response Body**:
```json
{
  "success": true,
  "data": {
    "activities": [
      {
        "id": 1,
        "type": "project",
        "action": "created",
        "description": "New project 'Mobile App Development' created",
        "details": {
          "projectId": "proj-046",
          "projectName": "Mobile App Development",
          "clientName": "StartupXYZ"
        },
        "user": "admin@altrevo.com",
        "userRole": "ADMIN",
        "ipAddress": "192.168.1.100",
        "timestamp": "2025-01-15T10:30:00Z"
      }
    ],
    "pagination": {
      "total": 1250,
      "page": 1,
      "limit": 50,
      "totalPages": 25
    }
  }
}
```

---

## 12. FEATURE TOGGLE ENDPOINTS

### 12.1 Get Feature Toggles
**Endpoint**: `GET /features`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Get all feature toggle settings

**Response Body**:
```json
{
  "success": true,
  "data": {
    "features": [
      {
        "id": "stats-section",
        "name": "Our Track Record",
        "description": "Display statistics and metrics on the home page",
        "enabled": true,
        "section": "home",
        "updatedAt": "2024-01-15T10:30:00Z",
        "updatedBy": "admin@altrevo.com"
      },
      {
        "id": "featured-team-section",
        "name": "Meet Our Expert Team",
        "description": "Show featured team members on the home page",
        "enabled": true,
        "section": "home",
        "updatedAt": "2024-01-15T10:30:00Z",
        "updatedBy": "admin@altrevo.com"
      }
    ]
  }
}
```

### 12.2 Update Feature Toggle
**Endpoint**: `PUT /features/{id}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Update feature toggle status

**Request Body**:
```json
{
  "enabled": false
}
```

**Response Body**:
```json
{
  "success": true,
  "message": "Feature toggle updated successfully",
  "data": {
    "id": "stats-section",
    "enabled": false,
    "updatedAt": "2025-01-15T10:30:00Z",
    "updatedBy": "admin@altrevo.com"
  }
}
```

---

## 13. FILE UPLOAD ENDPOINTS

### 13.1 Upload File
**Endpoint**: `POST /upload`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN, EDITOR  
**Description**: Upload files (images, documents, etc.)

**Request Body** (multipart/form-data):
```
file: [binary file data]
type: "image" | "document" | "logo" | "avatar"
folder: "services" | "team" | "blog" | "testimonials"
```

**Response Body**:
```json
{
  "success": true,
  "message": "File uploaded successfully",
  "data": {
    "filename": "altrevo-logo-2025.png",
    "originalName": "logo.png",
    "url": "https://cdn.altrevo.com/uploads/logos/altrevo-logo-2025.png",
    "size": 45678,
    "type": "image/png",
    "uploadedAt": "2025-01-15T10:30:00Z"
  }
}
```

### 13.2 Delete File
**Endpoint**: `DELETE /upload/{filename}`  
**Access**: Authenticated  
**Required Role**: ADMIN, SUPER_ADMIN  
**Description**: Delete uploaded file

**Response Body**:
```json
{
  "success": true,
  "message": "File deleted successfully"
}
```

---

## ERROR RESPONSES

### Standard Error Format
All endpoints return errors in the following format:

```json
{
  "success": false,
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Validation failed",
    "details": [
      {
        "field": "email",
        "message": "Email is required"
      },
      {
        "field": "name",
        "message": "Name must be at least 2 characters"
      }
    ]
  },
  "timestamp": "2025-01-15T10:30:00Z",
  "path": "/api/v1/services"
}
```

### Common Error Codes
- `VALIDATION_ERROR` (400): Request validation failed
- `UNAUTHORIZED` (401): Authentication required
- `FORBIDDEN` (403): Insufficient permissions
- `NOT_FOUND` (404): Resource not found
- `DUPLICATE_ENTRY` (409): Resource already exists
- `RATE_LIMIT_EXCEEDED` (429): Too many requests
- `INTERNAL_SERVER_ERROR` (500): Server error

---

## AUTHENTICATION HEADERS

### Required Headers
```
Authorization: Bearer {jwt_token}
Content-Type: application/json
Accept: application/json
```

### Optional Headers
```
X-API-Key: {api_key}
X-Request-ID: {unique_request_id}
```

---

## RATE LIMITING

### Rate Limits by Role
- **Public**: 100 requests per hour
- **EDITOR**: 1000 requests per hour
- **ADMIN**: 5000 requests per hour
- **SUPER_ADMIN**: 10000 requests per hour

### Rate Limit Headers
```
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 95
X-RateLimit-Reset: 1642248000
```

---

## PAGINATION

### Standard Pagination Format
```json
{
  "pagination": {
    "total": 150,
    "page": 1,
    "limit": 10,
    "totalPages": 15,
    "hasNext": true,
    "hasPrevious": false
  }
}
```

### Pagination Parameters
- `page`: Current page number (default: 1)
- `limit`: Number of items per page (default: 10, max: 100)
- `offset`: Alternative to page (skip N items)

---

## WEBHOOK ENDPOINTS

### 13.3 Webhook for Form Submissions
**Endpoint**: `POST /webhooks/form-submission`  
**Access**: Internal  
**Description**: Webhook triggered on form submission

**Payload**:
```json
{
  "event": "form.submitted",
  "data": {
    "enquiryId": 128,
    "name": "John Doe",
    "email": "john@example.com",
    "service": "Cloud Migration",
    "timestamp": "2025-01-15T10:30:00Z"
  }
}
```

---

## 🔓 PUBLIC vs 🔐 ADMIN ENDPOINTS - FINAL SUMMARY

### RECOMMENDED ARCHITECTURE: ADMIN-MANAGED CONTENT + PUBLIC API

**PUBLIC ENDPOINTS (No Authentication Required)**
All endpoints with `🔓` symbol and `/public/` path are **completely unauthenticated**:
- No Bearer tokens required
- No API keys required  
- No authentication headers needed
- Serve admin-managed content to public website visitors
- Fast, cached responses for optimal performance
- Content updates instantly when admin makes changes

**ADMIN ENDPOINTS (Authentication Required)**
All endpoints with `🔐` symbol and `/admin/` path require **JWT authentication**:
- Bearer token in Authorization header required
- Role-based access control enforced
- Session management for admin panel
- Content creation, editing, and management operations
- All public content is controlled from here

### CONTENT FLOW
```
Admin Panel → Admin API → Database → Public API → Public Website
     🔐           🔐         💾         🔓         🌐
```

### QUICK REFERENCE
```
🔓 Public:  GET /public/services     (Serves admin-managed services)
🔐 Admin:   GET /admin/services      (Manage services)
🔓 Public:  GET /public/testimonials (Serves admin-managed testimonials)
🔐 Admin:   POST /admin/testimonials (Create testimonials)
```

### BENEFITS OF THIS APPROACH
- ✅ **Dynamic Content**: Admin updates reflect immediately on public site
- ✅ **No Code Deployments**: Content changes don't require app rebuilds
- ✅ **Performance**: Public API can be cached and optimized
- ✅ **Security**: Admin operations are protected, public access is open
- ✅ **Scalability**: Can handle high public traffic with proper caching
- ✅ **User Experience**: Non-technical users can manage content easily

---

*This API documentation provides complete specifications for replacing mock data with real backend integration. All public endpoints are unauthenticated, while admin endpoints require proper authentication and role-based access control.*

**Last Updated**: January 2025  
**Version**: 1.1  
**Base URL**: https://api.altrevo.com/v1  
**Platform**: Altrevo Tech Solutions IT Consultancy
