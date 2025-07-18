# -- -- Insert users with correct BCrypt hashes
# -- -- This will be executed by Spring Boot on startup after tables are created
# --
# -- -- Insert admin user (password: hello)
# -- INSERT INTO users (email, name, password, role, is_active, is_email_verified)
# -- VALUES ('admin@altrevo.com', 'Admin User', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'SUPER_ADMIN', true, true);
# --
# -- -- Insert editor user (password: hello)
# -- INSERT INTO users (email, name, password, role, is_active, is_email_verified)
# -- VALUES ('editor@altrevo.com', 'Editor User', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'EDITOR', true, true);
#
# -- Sample Data for Altrevo Tech Solutions Backend
# -- Run this script after the application starts to populate with sample data
#
# -- Create admin user (password: password123)
# # INSERT INTO users (email, name, password, role, is_active, is_email_verified, created_at, updated_at)
# # VALUES ('admin@altrevo.com', 'Admin User', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'SUPER_ADMIN', true, true, NOW(), NOW());
#
# -- Create editor user (password: password123)
# # INSERT INTO users (email, name, password, role, is_active, is_email_verified, created_at, updated_at)
# # VALUES ('editor@altrevo.com', 'Editor User', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'EDITOR', true, true, NOW(), NOW());
#
# -- Insert sample services (max 8)
# INSERT INTO services (title, description, detailed_description, category, icon, duration, featured, status, sort_order, created_at, updated_at) VALUES
# ('Cloud Migration', 'Seamless cloud migration services for your business transformation', 'Complete cloud migration services with zero downtime, cost optimization, and comprehensive security assessment. Our expert team ensures a smooth transition to cloud infrastructure.', 'Cloud Solutions', 'fas fa-cloud', '4-8 weeks', true, 'ACTIVE', 1, NOW(), NOW()),
# ('DevOps Strategy', 'Comprehensive DevOps consulting and implementation', 'Strategic DevOps consulting to streamline your development and operations processes. We implement CI/CD pipelines, automation, and monitoring solutions.', 'DevOps', 'fas fa-cogs', '6-10 weeks', true, 'ACTIVE', 2, NOW(), NOW()),
# ('Digital Transformation', 'End-to-end digital transformation solutions', 'Complete digital transformation services including process automation, digital strategy, and technology modernization to drive business growth.', 'Digital Strategy', 'fas fa-rocket', '8-12 weeks', true, 'ACTIVE', 3, NOW(), NOW()),
# ('AI Implementation', 'Custom AI and machine learning solutions', 'Implement cutting-edge AI and machine learning solutions to automate processes, gain insights, and drive innovation in your business.', 'Artificial Intelligence', 'fas fa-robot', '6-12 weeks', false, 'ACTIVE', 4, NOW(), NOW()),
# ('Cybersecurity Consulting', 'Comprehensive cybersecurity assessment and solutions', 'Protect your business with our comprehensive cybersecurity services including risk assessment, security implementation, and compliance consulting.', 'Security', 'fas fa-shield-alt', '4-6 weeks', false, 'ACTIVE', 5, NOW(), NOW()),
# ('Data Analytics', 'Advanced data analytics and business intelligence', 'Transform your data into actionable insights with our advanced analytics solutions, including data visualization, predictive analytics, and business intelligence.', 'Data Science', 'fas fa-chart-line', '6-8 weeks', false, 'ACTIVE', 6, NOW(), NOW());
#
# -- Insert service features
# INSERT INTO service_features (service_id, feature) VALUES
#                                                        (1, 'Zero downtime migration'),
#                                                        (1, 'Cost optimization'),
#                                                        (1, 'Security assessment'),
#                                                        (1, '24/7 monitoring'),
#                                                        (2, 'CI/CD pipeline setup'),
#                                                        (2, 'Infrastructure automation'),
#                                                        (2, 'Monitoring and alerting'),
#                                                        (2, 'Team training'),
#                                                        (3, 'Process automation'),
#                                                        (3, 'Digital strategy'),
#                                                        (3, 'Technology modernization'),
#                                                        (3, 'Change management'),
#                                                        (4, 'Custom AI models'),
#                                                        (4, 'Machine learning algorithms'),
#                                                        (4, 'Natural language processing'),
#                                                        (4, 'Computer vision'),
#                                                        (5, 'Security assessment'),
#                                                        (5, 'Compliance consulting'),
#                                                        (5, 'Incident response'),
#                                                        (5, 'Security training'),
#                                                        (6, 'Data visualization'),
#                                                        (6, 'Predictive analytics'),
#                                                        (6, 'Business intelligence'),
#                                                        (6, 'Real-time reporting');
#
# -- Insert service technologies
# INSERT INTO service_technologies (service_id, technology) VALUES
#                                                               (1, 'AWS'), (1, 'Azure'), (1, 'Google Cloud'), (1, 'Kubernetes'),
#                                                               (2, 'Docker'), (2, 'Jenkins'), (2, 'GitLab CI'), (2, 'Ansible'),
#                                                               (3, 'React'), (3, 'Node.js'), (3, 'MongoDB'), (3, 'PostgreSQL'),
#                                                               (4, 'Python'), (4, 'TensorFlow'), (4, 'PyTorch'), (4, 'AWS SageMaker'),
#                                                               (5, 'OWASP'), (5, 'Splunk'), (5, 'Nessus'), (5, 'Metasploit'),
#                                                               (6, 'Tableau'), (6, 'Power BI'), (6, 'Apache Spark'), (6, 'Elasticsearch');
#
# -- Insert service deliverables
# INSERT INTO service_deliverables (service_id, deliverable) VALUES
#                                                                (1, 'Migration strategy document'),
#                                                                (1, 'Implemented cloud infrastructure'),
#                                                                (1, 'Performance optimization report'),
#                                                                (1, 'Security compliance documentation'),
#                                                                (2, 'CI/CD pipeline implementation'),
#                                                                (2, 'Infrastructure as Code templates'),
#                                                                (2, 'Monitoring dashboard'),
#                                                                (2, 'Team training materials'),
#                                                                (3, 'Digital transformation roadmap'),
#                                                                (3, 'Process automation workflows'),
#                                                                (3, 'Modernized applications'),
#                                                                (3, 'Change management plan'),
#                                                                (4, 'AI model implementation'),
#                                                                (4, 'Training documentation'),
#                                                                (4, 'API integration guides'),
#                                                                (4, 'Performance metrics dashboard'),
#                                                                (5, 'Security assessment report'),
#                                                                (5, 'Compliance framework'),
#                                                                (5, 'Incident response plan'),
#                                                                (5, 'Security policy documentation'),
#                                                                (6, 'Analytics dashboard'),
#                                                                (6, 'Data pipeline implementation'),
#                                                                (6, 'Reporting templates'),
#                                                                (6, 'User training materials');
#
# -- Insert sample team members (max 8)
# INSERT INTO team_members (name, role, department, email, phone, linkedin_url, photo_url, bio, experience, education, location, join_date, is_public, featured, sort_order, created_at, updated_at) VALUES
# ('John Smith', 'Senior Cloud Architect', 'Engineering', 'john.smith@altrevo.com', '+1-555-0101', 'https://linkedin.com/in/johnsmith', 'https://example.com/john-smith.jpg', 'John is a seasoned cloud architect with 10+ years of experience in designing and implementing scalable cloud solutions.', '10+ years', 'MS Computer Science, Stanford University', 'San Francisco, CA', '2020-01-15', true, true, 1, NOW(), NOW()),
# ('Sarah Johnson', 'DevOps Lead', 'Engineering', 'sarah.johnson@altrevo.com', '+1-555-0102', 'https://linkedin.com/in/sarahjohnson', 'https://example.com/sarah-johnson.jpg', 'Sarah specializes in DevOps automation and has led numerous successful CI/CD implementations.', '8+ years', 'BS Software Engineering, MIT', 'Boston, MA', '2021-03-20', true, true, 2, NOW(), NOW()),
# ('Michael Chen', 'AI/ML Engineer', 'Engineering', 'michael.chen@altrevo.com', '+1-555-0103', 'https://linkedin.com/in/michaelchen', 'https://example.com/michael-chen.jpg', 'Michael is an expert in artificial intelligence and machine learning with a focus on practical business applications.', '7+ years', 'PhD Machine Learning, Carnegie Mellon', 'Pittsburgh, PA', '2019-08-10', true, true, 3, NOW(), NOW()),
# ('Emily Rodriguez', 'Cybersecurity Consultant', 'Security', 'emily.rodriguez@altrevo.com', '+1-555-0104', 'https://linkedin.com/in/emilyrodriguez', 'https://example.com/emily-rodriguez.jpg', 'Emily is a cybersecurity expert specializing in enterprise security solutions and compliance.', '9+ years', 'MS Cybersecurity, George Washington University', 'Washington, DC', '2020-11-05', true, false, 4, NOW(), NOW());
#
# -- Insert team member skills
# INSERT INTO team_member_skills (team_member_id, skill) VALUES
#                                                            (1, 'AWS'), (1, 'Azure'), (1, 'Kubernetes'), (1, 'Docker'), (1, 'Terraform'),
#                                                            (2, 'Jenkins'), (2, 'GitLab CI'), (2, 'Ansible'), (2, 'Docker'), (2, 'Kubernetes'),
#                                                            (3, 'Python'), (3, 'TensorFlow'), (3, 'PyTorch'), (3, 'AWS SageMaker'), (3, 'Scikit-learn'),
#                                                            (4, 'CISSP'), (4, 'CISM'), (4, 'OWASP'), (4, 'Penetration Testing'), (4, 'Security Auditing');
#
# -- Insert team member achievements
# INSERT INTO team_member_achievements (team_member_id, achievement) VALUES
#                                                                        (1, 'AWS Solutions Architect Professional'),
#                                                                        (1, 'Led 50+ cloud migrations'),
#                                                                        (1, 'Reduced infrastructure costs by 40%'),
#                                                                        (2, 'Certified Kubernetes Administrator'),
#                                                                        (2, 'Implemented CI/CD for 100+ projects'),
#                                                                        (2, 'Reduced deployment time by 80%'),
#                                                                        (3, 'Published 15+ research papers'),
#                                                                        (3, 'Deployed ML models for Fortune 500 companies'),
#                                                                        (3, 'TensorFlow Developer Certificate'),
#                                                                        (4, 'CISSP Certified'),
#                                                                        (4, 'Led security audits for 50+ companies'),
#                                                                        (4, 'Zero security incidents in managed projects');
#
# -- Insert sample testimonials (max 8)
# INSERT INTO testimonials (name, designation, company, company_logo, message, rating, photo_url, published, featured, testimonial_date, project_type, location, sort_order, created_at, updated_at) VALUES
# ('David Wilson', 'CTO', 'TechCorp Solutions', 'https://example.com/techcorp-logo.png', 'Altrevo transformed our entire infrastructure with their cloud migration expertise. The project was completed on time and under budget with zero downtime.', 5, 'https://example.com/david-wilson.jpg', true, true, '2024-01-15', 'Cloud Migration', 'San Francisco, CA', 1, NOW(), NOW()),
# ('Lisa Chen', 'CEO', 'InnovateTech', 'https://example.com/innovatetech-logo.png', 'Outstanding DevOps consulting that streamlined our development process. Our deployment frequency increased by 300% after working with Altrevo.', 5, 'https://example.com/lisa-chen.jpg', true, true, '2024-02-20', 'DevOps Strategy', 'Seattle, WA', 2, NOW(), NOW()),
# ('Robert Martinez', 'VP Technology', 'GlobalCorp', 'https://example.com/globalcorp-logo.png', 'The AI implementation project exceeded our expectations. The custom machine learning models have improved our operational efficiency by 45%.', 5, 'https://example.com/robert-martinez.jpg', true, true, '2024-03-10', 'AI Implementation', 'Austin, TX', 3, NOW(), NOW()),
# ('Amanda Taylor', 'CISO', 'SecureFinance', 'https://example.com/securefinance-logo.png', 'Altrevo\'s cybersecurity assessment was comprehensive and actionable. They helped us achieve compliance and significantly improved our security posture.', 5, 'https://example.com/amanda-taylor.jpg', true, false, '2024-04-05', 'Cybersecurity Consulting', 'New York, NY', 4, NOW(), NOW());
#
# -- Insert testimonial tags
# INSERT INTO testimonial_tags (testimonial_id, tag) VALUES
# (1, 'cloud'), (1, 'migration'), (1, 'infrastructure'),
# (2, 'devops'), (2, 'automation'), (2, 'deployment'),
# (3, 'ai'), (3, 'machine learning'), (3, 'efficiency'),
# (4, 'security'), (4, 'compliance'), (4, 'assessment');
#
# -- Insert sample blog posts (max 8)
# INSERT INTO blog_posts (title, slug, summary, excerpt, content, author_name, author_title, author_avatar, author_bio, category, reading_time, views, featured, featured_image, published_at, status, meta_title, meta_description, sort_order, created_at, updated_at) VALUES
# ('The Future of Cloud Computing in 2024', 'future-of-cloud-computing-2024', 'Exploring the latest trends and innovations in cloud technology for 2024', 'Cloud computing continues to evolve rapidly, bringing new opportunities and challenges for businesses worldwide...', '<h2>Introduction</h2><p>Cloud computing has revolutionized how businesses operate, and 2024 promises even more exciting developments...</p><h2>Key Trends</h2><ul><li>Multi-cloud strategies</li><li>Edge computing integration</li><li>Serverless architecture</li><li>AI-powered cloud services</li></ul>', 'John Smith', 'Senior Cloud Architect', 'https://example.com/john-smith.jpg', 'John is a seasoned cloud architect with 10+ years of experience.', 'Cloud Computing', '8 min read', 1250, true, 'https://example.com/cloud-future.jpg', NOW(), 'PUBLISHED', 'The Future of Cloud Computing in 2024 - Altrevo Tech Solutions', 'Discover the latest trends and innovations in cloud technology for 2024. Expert insights on multi-cloud strategies, edge computing, and AI-powered services.', 1, NOW(), NOW()),
# ('DevOps Best Practices for 2024', 'devops-best-practices-2024', 'Essential DevOps practices every organization should implement', 'DevOps has become essential for modern software development, but implementing it correctly requires following proven best practices...', '<h2>Core DevOps Principles</h2><p>DevOps success depends on implementing core principles that foster collaboration and automation...</p><h2>Best Practices</h2><ul><li>Infrastructure as Code</li><li>Continuous Integration/Continuous Deployment</li><li>Automated Testing</li><li>Monitoring and Logging</li></ul>', 'Sarah Johnson', 'DevOps Lead', 'https://example.com/sarah-johnson.jpg', 'Sarah specializes in DevOps automation and CI/CD implementations.', 'DevOps', '6 min read', 980, true, 'https://example.com/devops-practices.jpg', NOW(), 'PUBLISHED', 'DevOps Best Practices for 2024 - Altrevo Tech Solutions', 'Learn essential DevOps practices for 2024. Expert guide on CI/CD, infrastructure as code, and automation strategies.', 2, NOW(), NOW()),
# ('AI and Machine Learning in Business', 'ai-machine-learning-business', 'How AI and ML are transforming modern business operations', 'Artificial intelligence and machine learning are no longer future technologies—they are transforming businesses today...', '<h2>AI in Business Today</h2><p>AI and machine learning technologies are being adopted across industries to drive efficiency and innovation...</p><h2>Implementation Strategies</h2><ul><li>Start with clear use cases</li><li>Invest in data quality</li><li>Choose the right tools</li><li>Train your team</li></ul>', 'Michael Chen', 'AI/ML Engineer', 'https://example.com/michael-chen.jpg', 'Michael is an expert in AI and ML with a focus on business applications.', 'Artificial Intelligence', '10 min read', 1450, false, 'https://example.com/ai-business.jpg', NOW(), 'PUBLISHED', 'AI and Machine Learning in Business - Altrevo Tech Solutions', 'Discover how AI and machine learning are transforming modern business operations. Expert insights on implementation strategies and best practices.', 3, NOW(), NOW());
#
# -- Insert blog post tags
# INSERT INTO blog_post_tags (blog_post_id, tag) VALUES
# (1, 'cloud'), (1, 'technology'), (1, 'trends'), (1, '2024'),
# (2, 'devops'), (2, 'automation'), (2, 'best practices'), (2, 'ci/cd'),
# (3, 'ai'), (3, 'machine learning'), (3, 'business'), (3, 'automation');
#
# -- Clear enquiries table before inserting to avoid duplicate reference_number errors
# DELETE FROM enquiries;
# -- Insert sample enquiries (max 3, unique reference_number)
# INSERT INTO enquiries (reference_number, name, email, phone, company, subject, service, message, enquiry_date, is_read, created_at, updated_at) VALUES
# ('ALT-2024-001', 'John Doe', 'john.doe@example.com', '+1-555-1001', 'Example Corp', 'consultation', 'Cloud Migration', 'We are interested in migrating our legacy systems to the cloud. Can you provide a consultation?', CURDATE(), false, NOW(), NOW()),
# ('ALT-2024-002', 'Jane Smith', 'jane.smith@techstart.com', '+1-555-1002', 'TechStart Inc', 'general', 'DevOps Strategy', 'We need help implementing DevOps practices in our organization. What does your consulting process look like?', CURDATE(), false, NOW(), NOW()),
# ('ALT-2024-003', 'Mike Johnson', 'mike.johnson@innovate.co', '+1-555-1003', 'Innovate Co', 'consultation', 'AI Implementation', 'Looking to implement AI solutions for our customer service. Would like to discuss options.', CURDATE(), true, NOW(), NOW());
#
#
# -- Sample job data
# INSERT INTO jobs (title, department, location, type, level, salary_min, salary_max, salary_currency, experience, openings, description, is_open, is_public, featured, created_at, updated_at, created_by, updated_by) VALUES
# ('Frontend Developer', 'Engineering', 'Remote', 'Full-time', 'Mid', 70000, 90000, 'USD', 3, 2, 'Join our frontend team to build amazing user experiences with React and TypeScript.', true, true, true, NOW(), NOW(), 'system', 'system'),
# ('Backend Developer', 'Engineering', 'San Francisco', 'Full-time', 'Senior', 100000, 130000, 'USD', 5, 1, 'Senior backend developer role working with Java Spring Boot and microservices.', true, true, false, NOW(), NOW(), 'system', 'system'),
# ('DevOps Engineer', 'Engineering', 'New York', 'Full-time', 'Mid', 90000, 110000, 'USD', 4, 1, 'DevOps engineer to help scale our infrastructure and improve deployment processes.', true, true, false, NOW(), NOW(), 'system', 'system'),
# ('UI/UX Designer', 'Design', 'Remote', 'Full-time', 'Mid', 75000, 95000, 'USD', 3, 1, 'Create beautiful and intuitive user interfaces for our web applications.', true, true, true, NOW(), NOW(), 'system', 'system'),
# ('Data Scientist', 'Data', 'Boston', 'Full-time', 'Senior', 120000, 150000, 'USD', 6, 1, 'Work with large datasets to extract insights and build predictive models.', true, true, false, NOW(), NOW(), 'system', 'system');
#
# -- Sample job requirements
# INSERT INTO job_requirements (job_id, requirement) VALUES
# (1, '3+ years React experience'),
# (1, 'TypeScript proficiency'),
# (1, 'CSS/SCSS expertise'),
# (1, 'Experience with modern build tools'),
# (2, '5+ years Java/Spring Boot experience'),
# (2, 'Microservices architecture knowledge'),
# (2, 'Database design and optimization'),
# (2, 'REST API development'),
# (3, 'AWS/Azure cloud experience'),
# (3, 'Docker and Kubernetes'),
# (3, 'CI/CD pipeline setup'),
# (3, 'Infrastructure as Code'),
# (4, 'Figma/Sketch proficiency'),
# (4, 'User research experience'),
# (4, 'Prototyping skills'),
# (4, 'Design systems knowledge'),
# (5, 'Python/R programming'),
# (5, 'Machine learning frameworks'),
# (5, 'Statistical analysis'),
# (5, 'Data visualization tools');
#
# -- Sample job skills
# INSERT INTO job_skills (job_id, skill) VALUES
# (1, 'React'),
# (1, 'TypeScript'),
# (1, 'CSS'),
# (1, 'HTML'),
# (1, 'JavaScript'),
# (2, 'Java'),
# (2, 'Spring Boot'),
# (2, 'PostgreSQL'),
# (2, 'Redis'),
# (2, 'Docker'),
# (3, 'AWS'),
# (3, 'Kubernetes'),
# (3, 'Docker'),
# (3, 'Terraform'),
# (3, 'Jenkins'),
# (4, 'Figma'),
# (4, 'Sketch'),
# (4, 'Adobe Creative Suite'),
# (4, 'Prototyping'),
# (5, 'Python'),
# (5, 'R'),
# (5, 'TensorFlow'),
# (5, 'Pandas'),
# (5, 'Jupyter');
#
# -- Sample job benefits
# INSERT INTO job_benefits (job_id, benefit) VALUES
# (1, 'Health insurance'),
# (1, 'Remote work'),
# (1, 'Learning budget'),
# (1, 'Flexible hours'),
# (2, 'Health insurance'),
# (2, 'Stock options'),
# (2, 'Learning budget'),
# (2, 'Gym membership'),
# (3, 'Health insurance'),
# (3, 'Remote work'),
# (3, 'Conference budget'),
# (3, 'Equipment allowance'),
# (4, 'Health insurance'),
# (4, 'Remote work'),
# (4, 'Design conference budget'),
# (4, 'Creative software licenses'),
# (5, 'Health insurance'),
# (5, 'Research budget'),
# (5, 'Conference attendance'),
# (5, 'Publication bonuses');
#
# -- Sample project data
# INSERT INTO projects (id, project_code, name, description, client_name, client_email, client_phone, client_company, start_date, end_date, estimated_budget, actual_budget, status, priority, progress, category, project_manager, created_at, updated_at, created_by, updated_by) VALUES
# (1, 'PROJ-001', 'E-commerce Platform Development', 'Full-scale e-commerce platform with payment integration and admin dashboard', 'John Smith', 'john@techcorp.com', '+1-555-0123', 'TechCorp Solutions', '2024-01-15', '2024-06-30', 85000, 78000, 'ACTIVE', 'HIGH', 75, 'Web Development', 'Jane Doe', NOW(), NOW(), 'system', 'system'),
# (2, 'PROJ-002', 'Mobile App Development', 'Native mobile app for iOS and Android with real-time features', 'Sarah Johnson', 'sarah@startupxyz.com', '+1-555-0456', 'StartupXYZ', '2024-03-01', '2024-08-31', 120000, 95000, 'ACTIVE', 'MEDIUM', 60, 'Mobile Development', 'Mike Wilson', NOW(), NOW(), 'system', 'system'),
# (3, 'PROJ-003', 'Data Analytics Dashboard', 'Business intelligence dashboard with real-time analytics and reporting', 'David Brown', 'david@analytics.com', '+1-555-0789', 'Analytics Corp', '2024-02-01', '2024-05-31', 65000, 65000, 'COMPLETED', 'MEDIUM', 100, 'Data Analytics', 'Lisa Chen', NOW(), NOW(), 'system', 'system'),
# (4, 'PROJ-004', 'Cloud Migration Project', 'Migrate legacy systems to AWS cloud infrastructure', 'Emily Davis', 'emily@legacy.com', '+1-555-0321', 'Legacy Systems Inc', '2024-04-01', '2024-07-31', 95000, 0, 'PLANNING', 'HIGH', 15, 'Cloud Migration', 'John Smith', NOW(), NOW(), 'system', 'system'),
# (5, 'PROJ-005', 'DevOps Automation', 'Implement CI/CD pipelines and infrastructure automation', 'Robert Wilson', 'robert@devops.com', '+1-555-0654', 'DevOps Solutions', '2024-01-01', '2024-03-31', 45000, 45000, 'COMPLETED', 'LOW', 100, 'DevOps', 'Mike Wilson', NOW(), NOW(), 'system', 'system');
#
# -- Clear dependent table before parent to avoid FK constraint error
# DELETE FROM project_technologies;
# -- Clear projects table before inserting to avoid duplicate key errors
# DELETE FROM projects;
# -- Sample project technologies
# INSERT INTO project_technologies (project_id, technology) VALUES
# (1, 'React'),
# (1, 'Node.js'),
# (1, 'MongoDB'),
# (1, 'Stripe API'),
# (1, 'AWS'),
# (2, 'React Native'),
# (2, 'Firebase'),
# (2, 'Node.js'),
# (2, 'Socket.io'),
# (3, 'Angular'),
# (3, 'Python'),
# (3, 'PostgreSQL'),
# (3, 'D3.js'),
# (3, 'Docker'),
# (4, 'AWS'),
# (4, 'Terraform'),
# (4, 'Docker'),
# (4, 'Kubernetes'),
# (5, 'Jenkins'),
# (5, 'Docker'),
# (5, 'Kubernetes'),
# (5, 'Terraform'),
# (5, 'AWS');
#
# -- Sample blog posts
# INSERT INTO blog_posts (title, slug, content, excerpt, category, featured_image, status, featured, views, meta_title, meta_description, published_at, sort_order, created_at, updated_at, created_by, updated_by) VALUES
# ('Getting Started with Cloud Migration', 'getting-started-cloud-migration', 'Cloud migration is a crucial step for modern businesses looking to scale and improve their infrastructure. In this comprehensive guide, we will walk you through the essential steps and best practices for successfully migrating your applications to the cloud.', 'Learn the essential steps and best practices for successful cloud migration in this comprehensive guide.', 'Cloud Computing', 'cloud-migration-guide.jpg', 'PUBLISHED', true, 245, 'Cloud Migration Guide - Best Practices and Steps', 'Complete guide to cloud migration with step-by-step instructions and best practices for businesses.', NOW(), 1, NOW(), NOW(), 'system', 'system'),
# ('10 Best Practices for DevOps Implementation', 'devops-best-practices', 'DevOps has revolutionized software development and deployment. Here are the top 10 best practices every organization should follow when implementing DevOps in their workflow.', 'Discover the top 10 DevOps best practices that every organization should implement for successful software delivery.', 'DevOps', 'devops-practices.jpg', 'PUBLISHED', true, 189, '10 Essential DevOps Best Practices for Success', 'Learn the most important DevOps practices for improving software delivery and team collaboration.', NOW(), 2, NOW(), NOW(), 'system', 'system'),
# ('The Future of AI in Software Development', 'ai-future-software-development', 'Artificial Intelligence is transforming the software development landscape. From automated testing to intelligent code completion, AI is making developers more productive than ever before.', 'Explore how AI is revolutionizing software development and what the future holds for developers.', 'Artificial Intelligence', 'ai-development.jpg', 'PUBLISHED', false, 156, 'AI in Software Development - Future Trends and Impact', 'Comprehensive look at how AI is changing software development and future predictions.', NOW(), 3, NOW(), NOW(), 'system', 'system'),
# ('Building Scalable Microservices Architecture', 'building-scalable-microservices', 'Microservices architecture has become the go-to solution for building scalable and maintainable applications. Learn how to design and implement microservices effectively.', 'Complete guide to building scalable microservices architecture with practical examples and best practices.', 'Architecture', 'microservices-arch.jpg', 'PUBLISHED', true, 312, 'Scalable Microservices Architecture Guide', 'Learn how to design and implement scalable microservices architecture for modern applications.', NOW(), 4, NOW(), NOW(), 'system', 'system'),
# ('Introduction to Machine Learning for Developers', 'machine-learning-for-developers', 'Machine Learning is no longer just for data scientists. This article introduces ML concepts and tools that every developer should know about.', 'Get started with machine learning as a developer with this comprehensive introduction to ML concepts and tools.', 'Machine Learning', 'ml-developers.jpg', 'DRAFT', false, 0, 'Machine Learning for Developers - Getting Started Guide', 'Comprehensive introduction to machine learning concepts and tools for software developers.', NULL, 5, NOW(), NOW(), 'system', 'system');
#
# -- Sample blog post tags
# INSERT INTO blog_post_tags (blog_post_id, tag) VALUES
# (1, 'cloud'),
# (1, 'migration'),
# (1, 'aws'),
# (1, 'azure'),
# (1, 'best-practices'),
# (2, 'devops'),
# (2, 'automation'),
# (2, 'ci-cd'),
# (2, 'best-practices'),
# (2, 'deployment'),
# (3, 'ai'),
# (3, 'machine-learning'),
# (3, 'automation'),
# (3, 'future-tech'),
# (3, 'development'),
# (4, 'microservices'),
# (4, 'architecture'),
# (4, 'scalability'),
# (4, 'docker'),
# (4, 'kubernetes'),
# (5, 'machine-learning'),
# (5, 'ai'),
# (5, 'python'),
# (5, 'data-science'),
# (5, 'developers');
#
# -- Clear feature_toggles table before inserting to avoid duplicate key errors
# DELETE FROM feature_toggles;
# -- Insert sample feature toggles (max 8, no duplicates)
# INSERT INTO feature_toggles (id, name, description, enabled, section, updated_by, updated_at, created_at) VALUES
# ('stats-section', 'Our Track Record', 'Display statistics and metrics on the home page', true, 'home', 'admin@altrevo.com', NOW(), NOW()),
# ('featured-team-section', 'Meet Our Expert Team', 'Show featured team members on the home page', true, 'home', 'admin@altrevo.com', NOW(), NOW()),
# ('testimonials-section', 'Client Testimonials', 'Display client testimonials on the home page', true, 'home', 'admin@altrevo.com', NOW(), NOW()),
# ('services-section', 'Our Services', 'Show services section on the home page', true, 'home', 'admin@altrevo.com', NOW(), NOW()),
# ('blog-section', 'Latest Insights', 'Display latest blog posts on the home page', true, 'home', 'admin@altrevo.com', NOW(), NOW()),
# ('careers-section', 'Career Opportunities', 'Show career opportunities section', true, 'careers', 'admin@altrevo.com', NOW(), NOW()),
# ('projects-showcase', 'Project Portfolio', 'Display project showcase on the portfolio page', true, 'portfolio', 'admin@altrevo.com', NOW(), NOW()),
# ('contact-form', 'Contact Form', 'Enable contact form on the contact page', true, 'contact', 'admin@altrevo.com', NOW(), NOW());
# # -- COMMIT;
#
#
# INSERT INTO feature_toggles (id, name, description, enabled, section, created_at, created_by)
# VALUES
#     ('stats-section', 'Our Track Record', 'Display statistics and metrics on the home page', TRUE, 'home', NOW(), 'admin'),
#     ('featured-team-section', 'Meet Our Expert Team', 'Show featured team members on the home page', FALSE, 'home', NOW(), 'admin'),
#     ('testimonials-section', 'What Our Clients Say', 'Display client testimonials carousel on the home page', TRUE, 'home', NOW(), 'admin'),
#     ('why-choose-us-section', 'Why Choose Us', 'Show company advantages and benefits', TRUE, 'home', NOW(), 'admin'),
#     ('cta-section', 'Call to Action', 'Display call-to-action section on the home page', TRUE, 'home', NOW(), 'admin'),
#     ('navbar-blog', 'Blog Navigation', 'Show/hide Blog link in the main navigation menu', TRUE, 'navigation', NOW(), 'admin'),
#     ('navbar-careers', 'Careers Navigation', 'Show/hide Careers link in the main navigation menu', FALSE, 'navigation', NOW(), 'admin');
#
# INSERT INTO site_settings (
#     id, version, company_name, tagline, hero_subtext, description, logo_url, favicon_url,
#     email, phone, alternate_phone, address, city, state, country, zip_code,
#     business_hours, social, seo, footer, contact_form,
#     maintenance_mode, maintenance_message, theme, primary_color, secondary_color,
#     statistics, last_updated, updated_by, created_at, updated_at
# ) VALUES (
#              'main-settings',
#              '2.0',
#              'Altrevo Tech Solutions',
#              'Innovative Technology Solutions for Modern Business',
#              'We help startups and enterprises build scalable, secure, and cloud-native systems.',
#              'Leading technology consultancy providing cutting-edge solutions, digital transformation, and innovation services to businesses worldwide.',
#              'altrevo-logo.png',
#              'altrevo-favicon.png',
#              'contact@altrevo.com',
#              '+1 (555) 123-4567',
#              '+1 (555) 123-4568',
#              '123 Innovation Drive, Suite 100',
#              'San Francisco',
#              'California',
#              'United States',
#              '94107',
#              -- business_hours as JSON
#              '{
#                "monday": "9:00 AM - 6:00 PM",
#                "tuesday": "9:00 AM - 6:00 PM",
#                "wednesday": "9:00 AM - 6:00 PM",
#                "thursday": "9:00 AM - 6:00 PM",
#                "friday": "9:00 AM - 6:00 PM",
#                "saturday": "Closed",
#                "sunday": "Closed"
#              }',
#              -- social as JSON
#              '{
#                "linkedin": "https://linkedin.com/company/altrevo",
#                "twitter": "https://twitter.com/altrevo",
#                "facebook": "https://facebook.com/altrevo",
#                "instagram": "https://instagram.com/altrevo",
#                "youtube": "https://youtube.com/altrevo",
#                "github": "https://github.com/altrevo"
#              }',
#              -- seo as JSON
#              '{
#                "metaTitle": "Altrevo Tech Solutions",
#                "metaDescription": "Leading technology consultancy providing cutting-edge solutions.",
#                "keywords": ["technology", "consultancy", "cloud", "digital transformation"],
#                "googleAnalyticsId": "UA-XXXXX-Y",
#                "facebookPixelId": "1234567890"
#              }',
#              -- footer as JSON
#              '{
#                "copyrightText": "© 2025 Altrevo Tech Solutions. All rights reserved.",
#                "quickLinks": [{"title": "Home", "url": "/"}, {"title": "About", "url": "/about"}],
#                "services": [{"title": "Cloud", "url": "/services/cloud"}],
#                "aboutLinks": [{"title": "Team", "url": "/about/team"}]
#              }',
#              -- contact_form as JSON
#              '{
#                "recipientEmail": "contact@altrevo.com",
#                "autoReplyEnabled": true,
#                "autoReplySubject": "Thank you for contacting us!",
#                "autoReplyMessage": "We have received your enquiry.",
#                "subjectOptions": [{"value": "general", "label": "General", "enabled": true}],
#                "serviceOptions": [{"value": "cloud", "label": "Cloud", "enabled": true}]
#              }',
#              false,
#              '',
#              'light',
#              '#1976d2',
#              '#424242',
#              -- statistics as JSON
#              '{
#                "projectsCompleted": {"number": "100+", "label": "Projects", "icon": "project", "enabled": true},
#                "happyClients": {"number": "50+", "label": "Clients", "icon": "client", "enabled": true},
#                "yearsExperience": {"number": "10", "label": "Years", "icon": "experience", "enabled": true},
#                "support": {"number": "24/7", "label": "Support", "icon": "support", "enabled": true},
#                "clientSatisfaction": {"number": "99%", "label": "Satisfaction", "icon": "satisfaction", "enabled": true},
#                "averageRating": {"number": "4.9", "label": "Rating", "icon": "rating", "enabled": true},
#                "teamMembers": {"number": "25", "label": "Team", "icon": "team", "enabled": true},
#                "successRate": {"number": "98%", "label": "Success", "icon": "success", "enabled": true}
#              }',
#              '2025-07-16T00:00:00Z',
#              'admin',
#              NOW(),
#              NOW()
#          );
#
# INSERT INTO enquiries (
#     id,
#     reference_number,
#     name,
#     email,
#     phone,
#     company,
#     subject,
#     service,
#     message,
# #     date,
#     is_read,
#     response_sent,
#     priority,
#     source,
#     notes,
#     assigned_to,
#     created_at,
#     updated_at,
#     created_by,
#     updated_by
# ) VALUES (
#              2,
#              'As1356',
#              'Noorul',
#              'a@gmail.com',
#              NULL,
#              'Altrevo',
#              'Enquiry',
#              'Cloud',
#              'Hi',
# #              '2025-07-15',
#              FALSE,
#              FALSE,
#              'Low',
#              'Cloud',
#              NULL,
#              'Cloud',
#              '2025-07-17 00:59:16',
#              '2025-07-17 00:59:31',
#              NULL,
#              'Noorul'
#          );
-- Insert static pages
# INSERT INTO static_page (id, title, content, last_updated, status, category, meta_description) VALUES
# ('about-story', 'Our Story', '<p>At Altrevo, we empower businesses with cutting-edge technology solutions. Our journey began with a vision to solve complex technical challenges for startups and enterprises alike.</p><p>With a dedicated team of developers, architects, and designers, we provide scalable, secure, and modern tech solutions.</p>', '2024-01-15 00:00:00', 'published', 'About Us', 'Learn about Altrevo''s journey and commitment to delivering exceptional technology solutions.'),
# ('about-mission', 'Our Mission', '<p>To deliver reliable and scalable digital solutions tailored to every business need.</p><p>We believe in empowering organizations through innovative technology that drives growth, efficiency, and competitive advantage in the digital age.</p>', '2024-01-15 00:00:00', 'published', 'About Us', 'Our mission is to deliver reliable and scalable digital solutions for every business.'),
# ('about-vision', 'Our Vision', '<p>To become a global leader in technology consultancy by continuously innovating and delivering value.</p><p>We envision a future where every business, regardless of size, has access to world-class technology solutions that propel them towards success.</p>', '2024-01-15 00:00:00', 'published', 'About Us', 'Our vision is to become a global leader in technology consultancy through innovation.'),
# ('home-hero', 'Home Hero Section', '<h1>Transform Your Business with Expert IT Solutions</h1><p>We deliver cutting-edge technology solutions that drive growth and innovation for businesses of all sizes.</p>', '2024-01-15 00:00:00', 'published', 'Home Page', 'Expert IT solutions to transform your business and drive growth.'),
# ('home-features', 'Home Features Section', '<div class="features"><div class="feature"><h3>Expert Team</h3><p>Our certified professionals bring years of experience in latest technologies.</p></div><div class="feature"><h3>24/7 Support</h3><p>Round-the-clock technical support to ensure your systems run smoothly.</p></div><div class="feature"><h3>Scalable Solutions</h3><p>Solutions that grow with your business needs and adapt to market changes.</p></div></div>', '2024-01-15 00:00:00', 'published', 'Home Page', 'Key features and benefits of our IT consultancy services.'),
# ('contact-info', 'Contact Information', '<div class="contact-info"><h3>Get in Touch</h3><p>Ready to transform your business? Contact our team of experts today.</p><ul><li><strong>Phone:</strong> +1 (555) 123-4567</li><li><strong>Email:</strong> info@itconsult.com</li><li><strong>Address:</strong> 123 Tech Street, Innovation City, IC 12345</li></ul></div>', '2024-01-15 00:00:00', 'published', 'Contact', 'Contact information for ITConsult - get in touch with our experts.'),
# ('privacy-policy', 'Privacy Policy', '<h2>Privacy Policy</h2><p>Last updated: January 15, 2024</p><h3>Information We Collect</h3><p>We collect information you provide directly to us, such as when you create an account, use our services, or contact us for support.</p><h3>How We Use Your Information</h3><p>We use the information we collect to provide, maintain, and improve our services, process transactions, and communicate with you.</p><h3>Information Sharing</h3><p>We do not sell, trade, or otherwise transfer your personal information to third parties without your consent, except as described in this policy.</p>', '2024-01-15 00:00:00', 'published', 'Legal', 'Privacy policy for ITConsult - how we collect and use your information.'),
# ('terms-of-service', 'Terms of Service', '<h2>Terms of Service</h2><p>Last updated: January 15, 2024</p><h3>Acceptance of Terms</h3><p>By accessing and using our services, you accept and agree to be bound by the terms and provision of this agreement.</p><h3>Service Description</h3><p>ITConsult provides technology consulting and software development services to businesses and organizations.</p><h3>User Responsibilities</h3><p>You are responsible for maintaining the confidentiality of your account and password and for restricting access to your computer.</p>', '2024-01-15 00:00:00', 'published', 'Legal', 'Terms of service for ITConsult - rules and guidelines for using our services.');
#
# -- Insert keywords for each static page
# INSERT INTO static_page_keywords (static_page_id, keywords) VALUES
#                                                                 ('about-story', 'technology consultancy'),
#                                                                 ('about-story', 'technology solutions'),
#                                                                 ('about-story', 'software development'),
#                                                                 ('about-story', 'digital transformation'),
#                                                                 ('about-mission', 'mission'),
#                                                                 ('about-mission', 'digital solutions'),
#                                                                 ('about-mission', 'business technology'),
#                                                                 ('about-mission', 'scalable solutions'),
#                                                                 ('about-vision', 'vision'),
#                                                                 ('about-vision', 'global leadership'),
#                                                                 ('about-vision', 'innovation'),
#                                                                 ('about-vision', 'technology consultancy'),
#                                                                 ('home-hero', 'IT solutions'),
#                                                                 ('home-hero', 'business transformation'),
#                                                                 ('home-hero', 'technology consulting'),
#                                                                 ('home-features', 'expert team'),
#                                                                 ('home-features', '24/7 support'),
#                                                                 ('home-features', 'scalable solutions'),
#                                                                 ('home-features', 'IT services'),
#                                                                 ('contact-info', 'contact'),
#                                                                 ('contact-info', 'phone'),
#                                                                 ('contact-info', 'email'),
#                                                                 ('contact-info', 'address'),
#                                                                 ('contact-info', 'support'),
#                                                                 ('privacy-policy', 'privacy'),
#                                                                 ('privacy-policy', 'policy'),
#                                                                 ('privacy-policy', 'data protection'),
#                                                                 ('privacy-policy', 'information security'),
#                                                                 ('terms-of-service', 'terms'),
#                                                                 ('terms-of-service', 'service'),
#                                                                 ('terms-of-service', 'agreement'),
#                                                                 ('terms-of-service', 'legal'),
#                                                                 ('terms-of-service', 'conditions');