-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: altrevo_services
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `blog_post_tags`
--

LOCK TABLES `blog_post_tags` WRITE;
/*!40000 ALTER TABLE `blog_post_tags` DISABLE KEYS */;
INSERT INTO `blog_post_tags` VALUES (2,'devops'),(2,'automation'),(2,'best practices'),(2,'ci/cd'),(3,'ai'),(3,'machine learning'),(3,'business'),(3,'automation'),(2,'devops'),(2,'automation'),(2,'ci-cd'),(2,'best-practices'),(2,'deployment'),(3,'ai'),(3,'machine-learning'),(3,'automation'),(3,'future-tech'),(3,'development'),(4,'microservices'),(4,'architecture'),(4,'scalability'),(4,'docker'),(4,'kubernetes'),(5,'machine-learning'),(5,'ai'),(5,'python'),(5,'data-science'),(5,'developers'),(1,'cloud'),(1,'technology'),(1,'trends'),(1,'2024'),(1,'cloud'),(1,'migration'),(1,'aws'),(1,'azure'),(1,'best-practices');
/*!40000 ALTER TABLE `blog_post_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `blog_posts`
--

LOCK TABLES `blog_posts` WRITE;
/*!40000 ALTER TABLE `blog_posts` DISABLE KEYS */;
INSERT INTO `blog_posts` VALUES (_binary '',0,'2025-07-16 21:02:19.000000',1,NULL,'2025-07-17 18:55:31.641943',0,'','John is a seasoned cloud architect with 10+ years of experience.','John Smith','Senior Cloud Architect','Cloud Computing','<h2>Introduction</h2><p>Cloud computing has revolutionized how businesses operate, and 2024 promises even more exciting developments...</p><h2>Key Trends</h2><ul><li>Multi-cloud strategies</li><li>Edge computing integration</li><li>Serverless architecture</li><li>AI-powered cloud services</li></ul>',NULL,'Cloud computing continues to evolve rapidly, bringing new opportunities and challenges for businesses worldwide...','https://images.unsplash.com/photo-1451187580459-43490279c0fa?w=800&h=400&fit=crop','','','8 min read',NULL,'Exploring the latest trends and innovations in cloud technology for 2024','The Future of Cloud Computing in 2024','noorul@altrevo.com','PUBLISHED'),(_binary '',2,'2025-07-16 21:02:19.000000',2,'2025-07-16 21:02:19.000000','2025-07-16 21:02:19.000000',980,'https://example.com/sarah-johnson.jpg','Sarah specializes in DevOps automation and CI/CD implementations.','Sarah Johnson','DevOps Lead','DevOps','<h2>Core DevOps Principles</h2><p>DevOps success depends on implementing core principles that foster collaboration and automation...</p><h2>Best Practices</h2><ul><li>Infrastructure as Code</li><li>Continuous Integration/Continuous Deployment</li><li>Automated Testing</li><li>Monitoring and Logging</li></ul>',NULL,'DevOps has become essential for modern software development, but implementing it correctly requires following proven best practices...','https://example.com/devops-practices.jpg','Learn essential DevOps practices for 2024. Expert guide on CI/CD, infrastructure as code, and automation strategies.','DevOps Best Practices for 2024 - Altrevo Tech Solutions','6 min read','devops-best-practices-2024','Essential DevOps practices every organization should implement','DevOps Best Practices for 2024',NULL,'PUBLISHED'),(_binary '\0',3,'2025-07-16 21:02:19.000000',3,'2025-07-16 21:02:19.000000','2025-07-16 21:02:19.000000',1450,'https://example.com/michael-chen.jpg','Michael is an expert in AI and ML with a focus on business applications.','Michael Chen','AI/ML Engineer','Artificial Intelligence','<h2>AI in Business Today</h2><p>AI and machine learning technologies are being adopted across industries to drive efficiency and innovation...</p><h2>Implementation Strategies</h2><ul><li>Start with clear use cases</li><li>Invest in data quality</li><li>Choose the right tools</li><li>Train your team</li></ul>',NULL,'Artificial intelligence and machine learning are no longer future technologies—they are transforming businesses today...','https://example.com/ai-business.jpg','Discover how AI and machine learning are transforming modern business operations. Expert insights on implementation strategies and best practices.','AI and Machine Learning in Business - Altrevo Tech Solutions','10 min read','ai-machine-learning-business','How AI and ML are transforming modern business operations','AI and Machine Learning in Business',NULL,'PUBLISHED'),(_binary '',1,'2025-07-16 21:02:29.000000',4,'2025-07-16 21:02:29.000000','2025-07-16 21:02:29.000000',245,NULL,NULL,NULL,NULL,'Cloud Computing','Cloud migration is a crucial step for modern businesses looking to scale and improve their infrastructure. In this comprehensive guide, we will walk you through the essential steps and best practices for successfully migrating your applications to the cloud.','system','Learn the essential steps and best practices for successful cloud migration in this comprehensive guide.','cloud-migration-guide.jpg','Complete guide to cloud migration with step-by-step instructions and best practices for businesses.','Cloud Migration Guide - Best Practices and Steps',NULL,'getting-started-cloud-migration',NULL,'Getting Started with Cloud Migration','system','PUBLISHED'),(_binary '',2,'2025-07-16 21:02:29.000000',5,'2025-07-16 21:02:29.000000','2025-07-16 21:02:29.000000',189,NULL,NULL,NULL,NULL,'DevOps','DevOps has revolutionized software development and deployment. Here are the top 10 best practices every organization should follow when implementing DevOps in their workflow.','system','Discover the top 10 DevOps best practices that every organization should implement for successful software delivery.','devops-practices.jpg','Learn the most important DevOps practices for improving software delivery and team collaboration.','10 Essential DevOps Best Practices for Success',NULL,'devops-best-practices',NULL,'10 Best Practices for DevOps Implementation','system','PUBLISHED'),(_binary '\0',3,'2025-07-16 21:02:29.000000',6,'2025-07-16 21:02:29.000000','2025-07-16 21:02:29.000000',156,NULL,NULL,NULL,NULL,'Artificial Intelligence','Artificial Intelligence is transforming the software development landscape. From automated testing to intelligent code completion, AI is making developers more productive than ever before.','system','Explore how AI is revolutionizing software development and what the future holds for developers.','ai-development.jpg','Comprehensive look at how AI is changing software development and future predictions.','AI in Software Development - Future Trends and Impact',NULL,'ai-future-software-development',NULL,'The Future of AI in Software Development','system','PUBLISHED'),(_binary '',4,'2025-07-16 21:02:29.000000',7,'2025-07-16 21:02:29.000000','2025-07-16 21:02:29.000000',312,NULL,NULL,NULL,NULL,'Architecture','Microservices architecture has become the go-to solution for building scalable and maintainable applications. Learn how to design and implement microservices effectively.','system','Complete guide to building scalable microservices architecture with practical examples and best practices.','microservices-arch.jpg','Learn how to design and implement scalable microservices architecture for modern applications.','Scalable Microservices Architecture Guide',NULL,'building-scalable-microservices',NULL,'Building Scalable Microservices Architecture','system','PUBLISHED'),(_binary '\0',5,'2025-07-16 21:02:29.000000',8,NULL,'2025-07-16 21:02:29.000000',0,NULL,NULL,NULL,NULL,'Machine Learning','Machine Learning is no longer just for data scientists. This article introduces ML concepts and tools that every developer should know about.','system','Get started with machine learning as a developer with this comprehensive introduction to ML concepts and tools.','ml-developers.jpg','Comprehensive introduction to machine learning concepts and tools for software developers.','Machine Learning for Developers - Getting Started Guide',NULL,'machine-learning-for-developers',NULL,'Introduction to Machine Learning for Developers','system','DRAFT');
/*!40000 ALTER TABLE `blog_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `enquiries`
--

LOCK TABLES `enquiries` WRITE;
/*!40000 ALTER TABLE `enquiries` DISABLE KEYS */;
INSERT INTO `enquiries` VALUES ('2025-07-16',_binary '\0',NULL,'2025-07-16 21:02:26.000000',1,'2025-07-16 21:02:26.000000',NULL,'Example Corp',NULL,'john.doe@example.com','We are interested in migrating our legacy systems to the cloud. Can you provide a consultation?','John Doe',NULL,'+1-555-1001',NULL,'ALT-2024-001','Cloud Migration',NULL,'consultation',NULL),('2025-07-16',_binary '\0',NULL,'2025-07-16 21:02:26.000000',2,'2025-07-16 21:02:26.000000',NULL,'TechStart Inc',NULL,'jane.smith@techstart.com','We need help implementing DevOps practices in our organization. What does your consulting process look like?','Jane Smith',NULL,'+1-555-1002',NULL,'ALT-2024-002','DevOps Strategy',NULL,'general',NULL),('2025-07-16',_binary '',NULL,'2025-07-16 21:02:26.000000',3,'2025-07-16 21:02:26.000000',NULL,'Innovate Co',NULL,'mike.johnson@innovate.co','Looking to implement AI solutions for our customer service. Would like to discuss options.','Mike Johnson',NULL,'+1-555-1003',NULL,'ALT-2024-003','AI Implementation',NULL,'consultation',NULL);
/*!40000 ALTER TABLE `enquiries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `feature_toggles`
--

LOCK TABLES `feature_toggles` WRITE;
/*!40000 ALTER TABLE `feature_toggles` DISABLE KEYS */;
INSERT INTO `feature_toggles` VALUES (_binary '','2025-07-16 21:02:29.000000','2025-07-16 21:02:29.000000',NULL,'Enable contact form on the contact page','contact-form','Contact Form','contact','admin@altrevo.com'),(_binary '\0','2025-07-16 21:02:29.000000','2025-07-18 08:34:36.470403',NULL,'Display all call to action on the home page.','cta-section','Call to Action','home','noorul@altrevo.com'),(_binary '\0','2025-07-16 21:02:29.000000','2025-07-17 19:51:20.527913',NULL,'Show featured team members on the home page','featured-team-section','Meet Our Expert Team','home','noorul@altrevo.com'),(_binary '','2025-07-16 21:02:29.000000','2025-07-17 15:19:38.674646',NULL,'Display latest blog posts on the home page','navbar-blog','Blog Naviagtion','navigation','noorul@altrevo.com'),(_binary '\0','2025-07-16 21:02:29.000000','2025-07-17 19:51:17.272308',NULL,'Show career opportunities section','navbar-career','Career Navigation','navigation','noorul@altrevo.com'),(_binary '','2025-07-16 21:02:29.000000','2025-07-17 15:27:53.459095',NULL,'Display statistics and metrics on the home page','stats-section','Our Track Record','home','noorul@altrevo.com'),(_binary '','2025-07-16 21:02:29.000000','2025-07-17 15:27:49.399038',NULL,'Display client testimonials on the home page','testimonials-section','What Our Clients Say','home','noorul@altrevo.com'),(_binary '','2025-07-16 21:02:29.000000','2025-07-17 15:27:51.578295',NULL,'Show Company advantage and benefits','why-choose-us-section','Why Choose Us','home','noorul@altrevo.com');
/*!40000 ALTER TABLE `feature_toggles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `job_benefits`
--

LOCK TABLES `job_benefits` WRITE;
/*!40000 ALTER TABLE `job_benefits` DISABLE KEYS */;
INSERT INTO `job_benefits` VALUES (1,'Health insurance'),(1,'Remote work'),(1,'Learning budget'),(1,'Flexible hours'),(2,'Health insurance'),(2,'Stock options'),(2,'Learning budget'),(2,'Gym membership'),(3,'Health insurance'),(3,'Remote work'),(3,'Conference budget'),(3,'Equipment allowance'),(4,'Health insurance'),(4,'Remote work'),(4,'Design conference budget'),(4,'Creative software licenses'),(5,'Health insurance'),(5,'Research budget'),(5,'Conference attendance'),(5,'Publication bonuses');
/*!40000 ALTER TABLE `job_benefits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `job_requirements`
--

LOCK TABLES `job_requirements` WRITE;
/*!40000 ALTER TABLE `job_requirements` DISABLE KEYS */;
INSERT INTO `job_requirements` VALUES (1,'3+ years React experience'),(1,'TypeScript proficiency'),(1,'CSS/SCSS expertise'),(1,'Experience with modern build tools'),(2,'5+ years Java/Spring Boot experience'),(2,'Microservices architecture knowledge'),(2,'Database design and optimization'),(2,'REST API development'),(3,'AWS/Azure cloud experience'),(3,'Docker and Kubernetes'),(3,'CI/CD pipeline setup'),(3,'Infrastructure as Code'),(4,'Figma/Sketch proficiency'),(4,'User research experience'),(4,'Prototyping skills'),(4,'Design systems knowledge'),(5,'Python/R programming'),(5,'Machine learning frameworks'),(5,'Statistical analysis'),(5,'Data visualization tools');
/*!40000 ALTER TABLE `job_requirements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `job_skills`
--

LOCK TABLES `job_skills` WRITE;
/*!40000 ALTER TABLE `job_skills` DISABLE KEYS */;
INSERT INTO `job_skills` VALUES (1,'React'),(1,'TypeScript'),(1,'CSS'),(1,'HTML'),(1,'JavaScript'),(2,'Java'),(2,'Spring Boot'),(2,'PostgreSQL'),(2,'Redis'),(2,'Docker'),(3,'AWS'),(3,'Kubernetes'),(3,'Docker'),(3,'Terraform'),(3,'Jenkins'),(4,'Figma'),(4,'Sketch'),(4,'Adobe Creative Suite'),(4,'Prototyping'),(5,'Python'),(5,'R'),(5,'TensorFlow'),(5,'Pandas'),(5,'Jupyter');
/*!40000 ALTER TABLE `job_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (3,_binary '',_binary '',_binary '',2,90000,70000,NULL,'2025-07-16 21:02:27.000000',1,'2025-07-16 21:02:27.000000','system','Engineering','Join our frontend team to build amazing user experiences with React and TypeScript.','Mid','Remote','USD','Frontend Developer','Full-time','system'),(5,_binary '\0',_binary '',_binary '',1,130000,100000,NULL,'2025-07-16 21:02:27.000000',2,'2025-07-16 21:02:27.000000','system','Engineering','Senior backend developer role working with Java Spring Boot and microservices.','Senior','San Francisco','USD','Backend Developer','Full-time','system'),(4,_binary '\0',_binary '',_binary '',1,110000,90000,NULL,'2025-07-16 21:02:27.000000',3,'2025-07-16 21:02:27.000000','system','Engineering','DevOps engineer to help scale our infrastructure and improve deployment processes.','Mid','New York','USD','DevOps Engineer','Full-time','system'),(3,_binary '',_binary '',_binary '',1,95000,75000,NULL,'2025-07-16 21:02:27.000000',4,'2025-07-16 21:02:27.000000','system','Design','Create beautiful and intuitive user interfaces for our web applications.','Mid','Remote','USD','UI/UX Designer','Full-time','system'),(6,_binary '\0',_binary '',_binary '',1,150000,120000,NULL,'2025-07-16 21:02:27.000000',5,'2025-07-16 21:02:27.000000','system','Data','Work with large datasets to extract insights and build predictive models.','Senior','Boston','USD','Data Scientist','Full-time','system');
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project_documents`
--

LOCK TABLES `project_documents` WRITE;
/*!40000 ALTER TABLE `project_documents` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project_external_team_member`
--

LOCK TABLES `project_external_team_member` WRITE;
/*!40000 ALTER TABLE `project_external_team_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_external_team_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project_milestones`
--

LOCK TABLES `project_milestones` WRITE;
/*!40000 ALTER TABLE `project_milestones` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_milestones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project_team_members`
--

LOCK TABLES `project_team_members` WRITE;
/*!40000 ALTER TABLE `project_team_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_team_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project_technologies`
--

LOCK TABLES `project_technologies` WRITE;
/*!40000 ALTER TABLE `project_technologies` DISABLE KEYS */;
INSERT INTO `project_technologies` VALUES (6,'Java');
/*!40000 ALTER TABLE `project_technologies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (0.00,100.00,0,'2025-07-17 07:54:37.565344','2025-08-05 18:30:00.000000',6,'2025-07-16 18:30:00.000000','2025-07-17 07:54:37.565344','Web Development','ABC','atif@gmail.com','Atif','9876543210','noorul@altrevo.com','I need to have banking project for my client.','Banking','PROJ-877520','Atif','noorul@altrevo.com','MEDIUM','PLANNING');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `service_deliverables`
--

LOCK TABLES `service_deliverables` WRITE;
/*!40000 ALTER TABLE `service_deliverables` DISABLE KEYS */;
INSERT INTO `service_deliverables` VALUES (1,'Migration strategy document'),(1,'Implemented cloud infrastructure'),(1,'Performance optimization report'),(1,'Security compliance documentation'),(2,'CI/CD pipeline implementation'),(2,'Infrastructure as Code templates'),(2,'Monitoring dashboard'),(2,'Team training materials'),(3,'Digital transformation roadmap'),(3,'Process automation workflows'),(3,'Modernized applications'),(3,'Change management plan'),(4,'AI model implementation'),(4,'Training documentation'),(4,'API integration guides'),(4,'Performance metrics dashboard'),(5,'Security assessment report'),(5,'Compliance framework'),(5,'Incident response plan'),(5,'Security policy documentation'),(6,'Analytics dashboard'),(6,'Data pipeline implementation'),(6,'Reporting templates'),(6,'User training materials');
/*!40000 ALTER TABLE `service_deliverables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `service_features`
--

LOCK TABLES `service_features` WRITE;
/*!40000 ALTER TABLE `service_features` DISABLE KEYS */;
INSERT INTO `service_features` VALUES (1,'Zero downtime migration'),(1,'Cost optimization'),(1,'Security assessment'),(1,'24/7 monitoring'),(2,'CI/CD pipeline setup'),(2,'Infrastructure automation'),(2,'Monitoring and alerting'),(2,'Team training'),(3,'Process automation'),(3,'Digital strategy'),(3,'Technology modernization'),(3,'Change management'),(4,'Custom AI models'),(4,'Machine learning algorithms'),(4,'Natural language processing'),(4,'Computer vision'),(5,'Security assessment'),(5,'Compliance consulting'),(5,'Incident response'),(5,'Security training'),(6,'Data visualization'),(6,'Predictive analytics'),(6,'Business intelligence'),(6,'Real-time reporting');
/*!40000 ALTER TABLE `service_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `service_technologies`
--

LOCK TABLES `service_technologies` WRITE;
/*!40000 ALTER TABLE `service_technologies` DISABLE KEYS */;
INSERT INTO `service_technologies` VALUES (1,'AWS'),(1,'Azure'),(1,'Google Cloud'),(1,'Kubernetes'),(2,'Docker'),(2,'Jenkins'),(2,'GitLab CI'),(2,'Ansible'),(3,'React'),(3,'Node.js'),(3,'MongoDB'),(3,'PostgreSQL'),(4,'Python'),(4,'TensorFlow'),(4,'PyTorch'),(4,'AWS SageMaker'),(5,'OWASP'),(5,'Splunk'),(5,'Nessus'),(5,'Metasploit'),(6,'Tableau'),(6,'Power BI'),(6,'Apache Spark'),(6,'Elasticsearch');
/*!40000 ALTER TABLE `service_technologies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (_binary '',1,'2025-07-16 21:02:18.000000',1,'2025-07-16 21:02:18.000000',NULL,NULL,NULL,NULL,'Cloud Solutions',NULL,'Seamless cloud migration services for your business transformation','Complete cloud migration services with zero downtime, cost optimization, and comprehensive security assessment. Our expert team ensures a smooth transition to cloud infrastructure.','4-8 weeks','fas fa-cloud',NULL,'Cloud Migration',NULL,'ACTIVE'),(_binary '',2,'2025-07-16 21:02:18.000000',2,'2025-07-16 21:02:18.000000',NULL,NULL,NULL,NULL,'DevOps',NULL,'Comprehensive DevOps consulting and implementation','Strategic DevOps consulting to streamline your development and operations processes. We implement CI/CD pipelines, automation, and monitoring solutions.','6-10 weeks','fas fa-cogs',NULL,'DevOps Strategy',NULL,'ACTIVE'),(_binary '',3,'2025-07-16 21:02:18.000000',3,'2025-07-16 21:02:18.000000',NULL,NULL,NULL,NULL,'Digital Strategy',NULL,'End-to-end digital transformation solutions','Complete digital transformation services including process automation, digital strategy, and technology modernization to drive business growth.','8-12 weeks','fas fa-rocket',NULL,'Digital Transformation',NULL,'ACTIVE'),(_binary '\0',4,'2025-07-16 21:02:18.000000',4,'2025-07-16 21:02:18.000000',NULL,NULL,NULL,NULL,'Artificial Intelligence',NULL,'Custom AI and machine learning solutions','Implement cutting-edge AI and machine learning solutions to automate processes, gain insights, and drive innovation in your business.','6-12 weeks','fas fa-robot',NULL,'AI Implementation',NULL,'ACTIVE'),(_binary '\0',5,'2025-07-16 21:02:18.000000',5,'2025-07-16 21:02:18.000000',NULL,NULL,NULL,NULL,'Security',NULL,'Comprehensive cybersecurity assessment and solutions','Protect your business with our comprehensive cybersecurity services including risk assessment, security implementation, and compliance consulting.','4-6 weeks','fas fa-shield-alt',NULL,'Cybersecurity Consulting',NULL,'ACTIVE'),(_binary '\0',6,'2025-07-16 21:02:18.000000',6,'2025-07-16 21:02:18.000000',NULL,NULL,NULL,NULL,'Data Science',NULL,'Advanced data analytics and business intelligence','Transform your data into actionable insights with our advanced analytics solutions, including data visualization, predictive analytics, and business intelligence.','6-8 weeks','fas fa-chart-line',NULL,'Data Analytics',NULL,'ACTIVE');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `site_settings`
--

LOCK TABLES `site_settings` WRITE;
/*!40000 ALTER TABLE `site_settings` DISABLE KEYS */;
INSERT INTO `site_settings` VALUES (_binary '\0','2025-07-16 21:20:13.000000','2025-07-17 20:08:42.700857','Bank Road','','{\"monday\":\"9:00 AM - 6:00 PM\",\"tuesday\":\"9:00 AM - 6:00 PM\",\"wednesday\":\"9:00 AM - 6:00 PM\",\"thursday\":\"9:00 AM - 6:00 PM\",\"friday\":\"9:00 AM - 6:00 PM\",\"saturday\":\"Closed\",\"sunday\":\"Closed\"}','City','Altrevo Tech Solutions','{\"recipientEmail\":\"altrvo@altrevo.com\",\"autoReplyEnabled\":true,\"autoReplySubject\":\"\",\"autoReplyMessage\":\"\",\"subjectOptions\":[],\"serviceOptions\":[]}','Karnatka',NULL,'Leading technology consultancy providing cutting-edge solutions, digital transformation, and innovation services to businesses worldwide.','altrevo@altrevo.com','altrevo-favicon.png','{\"copyrightText\":\"© 2025 Altrevo Tech Solutions. All rights reserved.\",\"quickLinks\":[],\"services\":[],\"aboutLinks\":[]}','We help startups and enterprises build scalable, secure, and cloud-native systems.','main-settings',NULL,'altrevo-logo.png','','9876543210','#1976d2','#424242','{\"metaTitle\":\"Altrevo Tech Solutions\",\"metaDescription\":\"Leading technology consultancy providing cutting-edge solutions.\",\"googleAnalyticsId\":\"UA-XXXXX-Y\",\"facebookPixelId\":\"1234567890\",\"keywords\":[\"technology\",\"consultancy\",\"cloud\",\"digital transformation\"]}','{\"linkedin\":\"https://www.linkedin.com/in/noorul-islam658\",\"twitter\":\"https://www.linkedin.com/in/noorul-islam658\",\"facebook\":\"https://www.linkedin.com/in/noorul-islam658\",\"instagram\":\"https://www.linkedin.com/in/noorul-islam658\",\"youtube\":\"https://www.linkedin.com/in/noorul-islam658\",\"github\":\"https://www.linkedin.com/in/noorul-islam658\"}','banglore','{\"projectsCompleted\":{\"number\":\"101+\",\"label\":\"Projects\",\"icon\":\"project\",\"enabled\":true},\"happyClients\":{\"number\":\"50+\",\"label\":\"Clients\",\"icon\":\"client\",\"enabled\":true},\"yearsExperience\":{\"number\":\"10\",\"label\":\"Years\",\"icon\":\"experience\",\"enabled\":true},\"support\":{\"number\":\"24/7\",\"label\":\"Support\",\"icon\":\"support\",\"enabled\":true},\"clientSatisfaction\":{\"number\":\"99%\",\"label\":\"Satisfaction\",\"icon\":\"satisfaction\",\"enabled\":false},\"averageRating\":{\"number\":\"4.9\",\"label\":\"Rating\",\"icon\":\"rating\",\"enabled\":false},\"teamMembers\":{\"number\":\"25\",\"label\":\"Team\",\"icon\":\"team\",\"enabled\":false},\"successRate\":{\"number\":\"98%\",\"label\":\"Success\",\"icon\":\"success\",\"enabled\":false}}','Innovative Technology Solutions and for Modern Business','light',NULL,'2.0','211002');
/*!40000 ALTER TABLE `site_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `static_page`
--

LOCK TABLES `static_page` WRITE;
/*!40000 ALTER TABLE `static_page` DISABLE KEYS */;
INSERT INTO `static_page` VALUES ('about-mission','About Us','<p>To deliver reliable and scalable digital solutions tailored to every business need.</p><p>We believe in empowering organizations through innovative technology that drives growth, efficiency, and competitive advantage in the digital age.</p>','2025-07-17 18:18:54.565893','Our mission is to deliver reliable and scalable digital solutions for every business.','published','Our Mission'),('about-story','About Us','<p>At Altrevo Tech Solutions, we empower product companies, startups, and fast-growing businesses to scale quickly — without the overhead of expanding internal teams. Our expertise spans cloud deployment, infrastructure optimization, data migration, AI and data analytics, cybersecurity, and end-to-end website development — from design to deployment, with ongoing support and enhancements. Driven by a team that understands both code and commerce, Altrevo bridges the gap between technical excellence and business outcomes — bringing deep insight, speed, and strategic clarity to every engagement. Whether you&#39;re building a new platform or modernizing legacy systems, we deliver cost-effective, AI-enabled, and time-sensitive solutions that help you move fast and scale smart. Built on the pillars of innovation, trust, and affordability, we partner with clients to solve real problems — not just write code.</p>','2025-07-17 18:18:32.109569','Learn about Altrevo\'s journey and commitment to delivering exceptional technology solutions.','published','Our Story'),('about-vision','About Us','<p>To evolve into a cutting-edge product company, delivering intelligent, AI-powered solutions that help businesses adapt, scale, and lead in a fast-changing digital world.</p>','2025-07-17 18:19:02.740720','Our vision is to become a global leader in technology consultancy through innovation.','published','Our Vision'),('privacy-policy','Legal','<p>Welcome to WhatBytes (&quot;Company&quot;, &quot;we&quot;, &quot;our&quot;, or &quot;us&quot;). We are committed to protecting your personal information and your right to privacy. If you have any questions about this Privacy Policy or our practices with regard to your personal information, please contact us at info@whatbytes.com.</p><p></p><p></p><p></p><p>This Privacy Policy describes how we collect, use, disclose, and safeguard your information when you visit our website https://whatbytes.com, engage with our services, or interact with us in any way.</p><p></p><p>1. Information We Collect</p><p></p><p>We collect personal information that you voluntarily provide to us when you:</p><p></p><p>Contact us via our website, email, or social media</p><p></p><p>Sign up for newsletters or updates</p><p></p><p>Request a proposal or consultation</p><p></p><p>Engage in a business relationship with us</p><p></p><p>The personal information we collect may include:</p><p></p><p>Name</p><p></p><p>Email address</p><p></p><p>Company name</p><p></p><p>Phone number</p><p></p><p>Job title</p><p></p><p>Project-related information</p><p></p><p>We may also automatically collect certain information when you visit our website, such as:</p><p></p><p>IP address</p><p></p><p>Browser type and version</p><p></p><p>Pages visited</p><p></p><p>Time and date of visit</p><p></p><p>Referring website addresses</p><p></p><p>This information is used for analytics and to improve the user experience.</p><p></p><p>2. How We Use Your Information</p><p></p><p>We use the collected information for various purposes, including:</p><p></p><p>To respond to your inquiries and provide our services</p><p></p><p>To send administrative information (such as updates, confirmations, or security alerts)</p><p></p><p>To manage our business relationships</p><p></p><p>To improve our website, services, and marketing efforts</p><p></p><p>To comply with legal obligations</p><p></p><p>3. Sharing of Your Information</p><p></p><p>We do not sell, rent, or trade your personal information. However, we may share your information with:</p><p></p><p>Service providers who assist us in operating our business (e.g., hosting, CRM, email services)</p><p></p><p>Legal authorities if required by law, or to protect our rights</p><p></p><p>Business partners only with your consent</p><p></p><p>4. Data Security</p><p></p><p>We implement appropriate technical and organizational measures to protect your personal information. However, please note that no method of transmission over the Internet is 100% secure.</p><p></p><p>5. Your Privacy Rights</p><p></p><p>Depending on your location, you may have rights regarding your personal information, such as:</p><p></p><p>Accessing the data we hold about you</p><p></p><p>Requesting correction of inaccurate data</p><p></p><p>Requesting deletion of your data</p><p></p><p>Objecting to processing of your data</p><p></p><p>Withdrawing consent at any time</p><p></p><p>To exercise any of these rights, please contact us at info@whatbytes.com.</p><p></p><p>6. Retention of Data</p><p></p><p>We will retain your personal information only for as long as necessary to fulfill the purposes outlined in this Privacy Policy unless a longer retention period is required or permitted by law</p><p></p><p>7. Third-Party Links</p><p></p><p>Our website may contain links to third-party websites. We are not responsible for the privacy practices or content of such external websites.</p><p></p><p>8. Changes to This Privacy Policy</p><p></p><p>We may update this Privacy Policy from time to time. We encourage you to review this page periodically for any changes. Changes are effective when they are posted on this page.</p><p></p><p>9. Contact Us</p><p></p><p>If you have any questions about this Privacy Policy, you can contact us at:</p><p></p><p>WhatBytes Technologies (OPC) Private Limited</p><p></p><p>235 Binnamangala 2nd floor, 13th Cross Road 2nd Stage,</p><p></p><p>Indiranagar, Bangalore, Karnataka, India, 560038</p><p>Email: info@whatbytes.com</p>','2025-07-18 10:17:30.926556','Privacy policy for ITConsult - how we collect and use your information.','published','Privacy Policy'),('terms-of-service','Legal','<h2>Terms of Service</h2><p>Last updated: January 15, 2024</p><h3>Acceptance of Terms</h3><p>By accessing and using our services, you accept and agree to be bound by the terms and provision of this agreement.</p><h3>Service Description</h3><p>ITConsult provides technology consulting and software development services to businesses and organizations.</p><h3>User Responsibilities</h3><p>You are responsible for maintaining the confidentiality of your account and password and for restricting access to your computer.</p>','2024-01-15 00:00:00.000000','Terms of service for ITConsult - rules and guidelines for using our services.','published','Terms of Service');
/*!40000 ALTER TABLE `static_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `static_page_keywords`
--

LOCK TABLES `static_page_keywords` WRITE;
/*!40000 ALTER TABLE `static_page_keywords` DISABLE KEYS */;
INSERT INTO `static_page_keywords` VALUES ('terms-of-service','terms'),('terms-of-service','service'),('terms-of-service','agreement'),('terms-of-service','legal'),('terms-of-service','conditions'),('about-story','technology consultancy'),('about-story','technology solutions'),('about-story','software development'),('about-story','digital transformation'),('about-mission','mission'),('about-mission','digital solutions'),('about-mission','business technology'),('about-mission','scalable solutions'),('about-vision','vision'),('about-vision','global leadership'),('about-vision','innovation'),('about-vision','technology consultancy'),('privacy-policy','privacy'),('privacy-policy','policy'),('privacy-policy','data protection'),('privacy-policy','information security');
/*!40000 ALTER TABLE `static_page_keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `team_member_achievements`
--

LOCK TABLES `team_member_achievements` WRITE;
/*!40000 ALTER TABLE `team_member_achievements` DISABLE KEYS */;
INSERT INTO `team_member_achievements` VALUES (1,'AWS Solutions Architect Professional'),(1,'Led 50+ cloud migrations'),(1,'Reduced infrastructure costs by 40%'),(2,'Certified Kubernetes Administrator'),(2,'Implemented CI/CD for 100+ projects'),(2,'Reduced deployment time by 80%'),(3,'Published 15+ research papers'),(3,'Deployed ML models for Fortune 500 companies'),(3,'TensorFlow Developer Certificate'),(4,'CISSP Certified'),(4,'Led security audits for 50+ companies'),(4,'Zero security incidents in managed projects');
/*!40000 ALTER TABLE `team_member_achievements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `team_member_certifications`
--

LOCK TABLES `team_member_certifications` WRITE;
/*!40000 ALTER TABLE `team_member_certifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_member_certifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `team_member_interests`
--

LOCK TABLES `team_member_interests` WRITE;
/*!40000 ALTER TABLE `team_member_interests` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_member_interests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `team_member_languages`
--

LOCK TABLES `team_member_languages` WRITE;
/*!40000 ALTER TABLE `team_member_languages` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_member_languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `team_member_skills`
--

LOCK TABLES `team_member_skills` WRITE;
/*!40000 ALTER TABLE `team_member_skills` DISABLE KEYS */;
INSERT INTO `team_member_skills` VALUES (1,'AWS'),(1,'Azure'),(1,'Kubernetes'),(1,'Docker'),(1,'Terraform'),(2,'Jenkins'),(2,'GitLab CI'),(2,'Ansible'),(2,'Docker'),(2,'Kubernetes'),(3,'Python'),(3,'TensorFlow'),(3,'PyTorch'),(3,'AWS SageMaker'),(3,'Scikit-learn'),(4,'CISSP'),(4,'CISM'),(4,'OWASP'),(4,'Penetration Testing'),(4,'Security Auditing');
/*!40000 ALTER TABLE `team_member_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `team_member_specializations`
--

LOCK TABLES `team_member_specializations` WRITE;
/*!40000 ALTER TABLE `team_member_specializations` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_member_specializations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `team_members`
--

LOCK TABLES `team_members` WRITE;
/*!40000 ALTER TABLE `team_members` DISABLE KEYS */;
INSERT INTO `team_members` VALUES (_binary '',_binary '','2020-01-15',1,'2025-07-16 21:02:18.000000',1,'2025-07-16 21:02:18.000000','John is a seasoned cloud architect with 10+ years of experience in designing and implementing scalable cloud solutions.',NULL,'Engineering','MS Computer Science, Stanford University','john.smith@altrevo.com','10+ years',NULL,'https://linkedin.com/in/johnsmith','San Francisco, CA',NULL,'John Smith','+1-555-0101','https://example.com/john-smith.jpg','Senior Cloud Architect',NULL,NULL,NULL),(_binary '',_binary '','2021-03-20',2,'2025-07-16 21:02:18.000000',2,'2025-07-16 21:02:18.000000','Sarah specializes in DevOps automation and has led numerous successful CI/CD implementations.',NULL,'Engineering','BS Software Engineering, MIT','sarah.johnson@altrevo.com','8+ years',NULL,'https://linkedin.com/in/sarahjohnson','Boston, MA',NULL,'Sarah Johnson','+1-555-0102','https://example.com/sarah-johnson.jpg','DevOps Lead',NULL,NULL,NULL),(_binary '',_binary '','2019-08-10',3,'2025-07-16 21:02:18.000000',3,'2025-07-16 21:02:18.000000','Michael is an expert in artificial intelligence and machine learning with a focus on practical business applications.',NULL,'Engineering','PhD Machine Learning, Carnegie Mellon','michael.chen@altrevo.com','7+ years',NULL,'https://linkedin.com/in/michaelchen','Pittsburgh, PA',NULL,'Michael Chen','+1-555-0103','https://example.com/michael-chen.jpg','AI/ML Engineer',NULL,NULL,NULL),(_binary '\0',_binary '','2020-11-05',4,'2025-07-16 21:02:18.000000',4,'2025-07-16 21:02:18.000000','Emily is a cybersecurity expert specializing in enterprise security solutions and compliance.',NULL,'Security','MS Cybersecurity, George Washington University','emily.rodriguez@altrevo.com','9+ years',NULL,'https://linkedin.com/in/emilyrodriguez','Washington, DC',NULL,'Emily Rodriguez','+1-555-0104','https://example.com/emily-rodriguez.jpg','Cybersecurity Consultant',NULL,NULL,NULL);
/*!40000 ALTER TABLE `team_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `testimonial_tags`
--

LOCK TABLES `testimonial_tags` WRITE;
/*!40000 ALTER TABLE `testimonial_tags` DISABLE KEYS */;
INSERT INTO `testimonial_tags` VALUES (4,'security'),(4,'compliance'),(4,'assessment'),(3,'ai'),(3,'machine learning'),(3,'efficiency'),(2,'devops'),(2,'automation'),(2,'deployment'),(1,'cloud'),(1,'migration'),(1,'infrastructure');
/*!40000 ALTER TABLE `testimonial_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `testimonials`
--

LOCK TABLES `testimonials` WRITE;
/*!40000 ALTER TABLE `testimonials` DISABLE KEYS */;
INSERT INTO `testimonials` VALUES (_binary '',_binary '',5,0,'2024-01-15','2025-07-16 21:02:19.000000',1,'2025-07-18 10:29:07.820172','TechCorp Solutions','https://example.com/techcorp-logo.png',NULL,'CTO','San Francisco, CA','Altrevo transformed our entire infrastructure with their cloud migration expertise. The project was completed on time and under budget with zero downtime.','David Wilson','https://images.unsplash.com/photo-1580489944761-15a19d654956?w=150&h=150&fit=crop&crop=face&auto=format','Cloud Migration',NULL),(_binary '',_binary '',5,0,'2024-02-20','2025-07-16 21:02:19.000000',2,'2025-07-18 10:27:49.921001','InnovateTech','https://example.com/innovatetech-logo.png',NULL,'CEO','Seattle, WA','Outstanding DevOps consulting that streamlined our development process. Our deployment frequency increased by 300% after working with Altrevo.','Lisa Chen','','DevOps Strategy',NULL),(_binary '',_binary '',5,0,'2024-03-10','2025-07-16 21:02:19.000000',3,'2025-07-18 10:27:44.356136','GlobalCorp','https://example.com/globalcorp-logo.png',NULL,'VP Technology','Austin, TX','The AI implementation project exceeded our expectations. The custom machine learning models have improved our operational efficiency by 45%.','Robert Martinez','','AI Implementation',NULL),(_binary '\0',_binary '',5,4,'2024-04-05','2025-07-16 21:02:19.000000',4,'2025-07-16 21:02:19.000000','SecureFinance','https://example.com/securefinance-logo.png',NULL,'CISO','New York, NY','Altrevo\'s cybersecurity assessment was comprehensive and actionable. They helped us achieve compliance and significantly improved our security posture.','Amanda Taylor','https://example.com/amanda-taylor.jpg','Cybersecurity Consulting',NULL);
/*!40000 ALTER TABLE `testimonials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_permissions`
--

LOCK TABLES `user_permissions` WRITE;
/*!40000 ALTER TABLE `user_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary '',_binary '','2025-07-16 15:33:01.906718',1,'2025-07-16 15:33:01.906718','https://example.com/avatar-admin.jpg',NULL,'noorul@altrevo.com','Noorul','$2a$10$H5zxK8RSr.uizhs/n1dRN.XFgZ5dShp9KKoQmLHHyu9a.1Rr/F62C','+1-555-0001',NULL,'ADMIN');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `webhook_events`
--

LOCK TABLES `webhook_events` WRITE;
/*!40000 ALTER TABLE `webhook_events` DISABLE KEYS */;
INSERT INTO `webhook_events` VALUES (3,_binary '\0',0,'2025-07-17 19:48:07.314956',1,NULL,NULL,'2025-07-17 19:48:07.314956',NULL,NULL,'enquiry.created','{\"referenceNumber\":\"ALT-2025-0004\",\"service\":\"Other\",\"subject\":\"General\",\"name\":\"Noorul Islam\",\"enquiryId\":4,\"email\":\"altrvotech@gmail.com\",\"timestamp\":\"2025-07-18T01:18:07.0787318\"}',NULL,NULL,NULL),(3,_binary '\0',0,'2025-07-17 19:50:50.546341',2,NULL,NULL,'2025-07-17 19:50:50.546341',NULL,NULL,'enquiry.created','{\"referenceNumber\":\"ALT-2025-0004\",\"service\":\"Other\",\"subject\":\"General\",\"name\":\"Noorul Islam\",\"enquiryId\":5,\"email\":\"altrvotech@gmail.com\",\"timestamp\":\"2025-07-18T01:20:50.5403365\"}',NULL,NULL,NULL);
/*!40000 ALTER TABLE `webhook_events` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-18 16:22:16
