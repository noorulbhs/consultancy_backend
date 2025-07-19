package com.altrevo.consultancy.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.time.LocalDateTime;
import com.altrevo.consultancy.enums.ServiceStatus;

public class InMemoryServiceStore {
    public static final Map<Long, Service> SERVICES = new HashMap<>();
    public static long idCounter = 1L;

    static {
        // Service 1
        Service s1 = new Service();
        s1.setId(1L);
        s1.setTitle("Cloud Solutions");
        s1.setDescription("Seamless cloud migration services for your business transformation");
        s1.setDetailedDescription("Complete cloud migration services with zero downtime, cost optimization, and comprehensive security assessment. Our expert team ensures a smooth transition to cloud infrastructure.");
        s1.setCategory("Cloud Migration");
        s1.setIcon("fas fa-cloud");
        s1.setFeatures(Arrays.asList("Zero downtime migration", "Cost optimization", "Security assessment", "24/7 monitoring"));
        s1.setTechnologies(Arrays.asList("AWS", "Azure", "Google Cloud", "Kubernetes"));
        s1.setDuration("4-8 weeks");
        s1.setDeliverables(Arrays.asList("Migration strategy document", "Implemented cloud infrastructure", "Performance optimization report", "Security compliance documentation"));
        s1.setCaseStudyClient(null);
        s1.setCaseStudyChallenge(null);
        s1.setCaseStudySolution(null);
        s1.setCaseStudyResults(null);
        s1.setFeatured(true);
        s1.setStatus(ServiceStatus.ACTIVE);
        s1.setImageUrl(null);
        s1.setSortOrder(1);
        s1.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        s1.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        InMemoryServiceStore.SERVICES.put(s1.getId(), s1);

        // Service 2
        Service s2 = new Service();
        s2.setId(2L);
        s2.setTitle("DevOps");
        s2.setDescription("Comprehensive DevOps consulting and implementation");
        s2.setDetailedDescription("Strategic DevOps consulting to streamline your development and operations processes. We implement CI/CD pipelines, automation, and monitoring solutions.");
        s2.setCategory("DevOps Strategy");
        s2.setIcon("fas fa-cogs");
        s2.setFeatures(Arrays.asList("CI/CD pipeline setup", "Infrastructure automation", "Monitoring and alerting", "Team training"));
        s2.setTechnologies(Arrays.asList("Docker", "Jenkins", "GitLab CI", "Ansible"));
        s2.setDuration("6-10 weeks");
        s2.setDeliverables(Arrays.asList("CI/CD pipeline implementation", "Infrastructure as Code templates", "Monitoring dashboard", "Team training materials"));
        s2.setCaseStudyClient(null);
        s2.setCaseStudyChallenge(null);
        s2.setCaseStudySolution(null);
        s2.setCaseStudyResults(null);
        s2.setFeatured(true);
        s2.setStatus(ServiceStatus.ACTIVE);
        s2.setImageUrl(null);
        s2.setSortOrder(2);
        s2.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        s2.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        InMemoryServiceStore.SERVICES.put(s2.getId(), s2);

        // Service 3
        Service s3 = new Service();
        s3.setId(3L);
        s3.setTitle("Digital Strategy");
        s3.setDescription("End-to-end digital transformation solutions");
        s3.setDetailedDescription("Complete digital transformation services including process automation, digital strategy, and technology modernization to drive business growth.");
        s3.setCategory("Digital Transformation");
        s3.setIcon("fas fa-rocket");
        s3.setFeatures(Arrays.asList("Process automation", "Digital strategy", "Technology modernization", "Change management"));
        s3.setTechnologies(Arrays.asList("React", "Node.js", "MongoDB", "PostgreSQL"));
        s3.setDuration("8-12 weeks");
        s3.setDeliverables(Arrays.asList("Digital transformation roadmap", "Process automation workflows", "Modernized applications", "Change management plan"));
        s3.setCaseStudyClient(null);
        s3.setCaseStudyChallenge(null);
        s3.setCaseStudySolution(null);
        s3.setCaseStudyResults(null);
        s3.setFeatured(true);
        s3.setStatus(ServiceStatus.ACTIVE);
        s3.setImageUrl(null);
        s3.setSortOrder(3);
        s3.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        s3.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        InMemoryServiceStore.SERVICES.put(s3.getId(), s3);

        // Service 4
        Service s4 = new Service();
        s4.setId(4L);
        s4.setTitle("Artificial Intelligence");
        s4.setDescription("Custom AI and machine learning solutions");
        s4.setDetailedDescription("Implement cutting-edge AI and machine learning solutions to automate processes, gain insights, and drive innovation in your business.");
        s4.setCategory("AI Implementation");
        s4.setIcon("fas fa-robot");
        s4.setFeatures(Arrays.asList("Custom AI models", "Machine learning algorithms", "Natural language processing", "Computer vision"));
        s4.setTechnologies(Arrays.asList("Python", "TensorFlow", "PyTorch", "AWS SageMaker"));
        s4.setDuration("6-12 weeks");
        s4.setDeliverables(Arrays.asList("AI model implementation", "Training documentation", "API integration guides", "Performance metrics dashboard"));
        s4.setCaseStudyClient(null);
        s4.setCaseStudyChallenge(null);
        s4.setCaseStudySolution(null);
        s4.setCaseStudyResults(null);
        s4.setFeatured(false);
        s4.setStatus(ServiceStatus.ACTIVE);
        s4.setImageUrl(null);
        s4.setSortOrder(4);
        s4.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        s4.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        InMemoryServiceStore.SERVICES.put(s4.getId(), s4);

        // Service 5
        Service s5 = new Service();
        s5.setId(5L);
        s5.setTitle("Security");
        s5.setDescription("Comprehensive cybersecurity assessment and solutions");
        s5.setDetailedDescription("Protect your business with our comprehensive cybersecurity services including risk assessment, security implementation, and compliance consulting.");
        s5.setCategory("Cybersecurity Consulting");
        s5.setIcon("fas fa-shield-alt");
        s5.setFeatures(Arrays.asList("Security assessment", "Compliance consulting", "Incident response", "Security training"));
        s5.setTechnologies(Arrays.asList("OWASP", "Splunk", "Nessus", "Metasploit"));
        s5.setDuration("4-6 weeks");
        s5.setDeliverables(Arrays.asList("Security assessment report", "Compliance framework", "Incident response plan", "Security policy documentation"));
        s5.setCaseStudyClient(null);
        s5.setCaseStudyChallenge(null);
        s5.setCaseStudySolution(null);
        s5.setCaseStudyResults(null);
        s5.setFeatured(false);
        s5.setStatus(ServiceStatus.ACTIVE);
        s5.setImageUrl(null);
        s5.setSortOrder(5);
        s5.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        s5.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        InMemoryServiceStore.SERVICES.put(s5.getId(), s5);

        // Service 6
        Service s6 = new Service();
        s6.setId(6L);
        s6.setTitle("Data Science");
        s6.setDescription("Advanced data analytics and business intelligence");
        s6.setDetailedDescription("Transform your data into actionable insights with our advanced analytics solutions, including data visualization, predictive analytics, and business intelligence.");
        s6.setCategory("Data Analytics");
        s6.setIcon("fas fa-chart-line");
        s6.setFeatures(Arrays.asList("Data visualization", "Predictive analytics", "Business intelligence", "Real-time reporting"));
        s6.setTechnologies(Arrays.asList("Tableau", "Power BI", "Apache Spark", "Elasticsearch"));
        s6.setDuration("6-8 weeks");
        s6.setDeliverables(Arrays.asList("Analytics dashboard", "Data pipeline implementation", "Reporting templates", "User training materials"));
        s6.setCaseStudyClient(null);
        s6.setCaseStudyChallenge(null);
        s6.setCaseStudySolution(null);
        s6.setCaseStudyResults(null);
        s6.setFeatured(false);
        s6.setStatus(ServiceStatus.ACTIVE);
        s6.setImageUrl(null);
        s6.setSortOrder(6);
        s6.setCreatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        s6.setUpdatedAt(LocalDateTime.of(2025, 7, 16, 21, 2, 18));
        InMemoryServiceStore.SERVICES.put(s6.getId(), s6);

        InMemoryServiceStore.idCounter = 7L;
    }
}
