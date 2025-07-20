//package com.altrevo.consultancy.entity;
//
//import lombok.Data;
//
//import java.util.List;
//
//@Data
//public class AllDocument {
//    private User user;
//    private BlogPost blog;
//    private Enquiry enquiry;
//    private FeatureToggle featureToggle;
//    private Job job;
//    private Project project;
//    private Service service;
//    private StaticPage staticPage;
//    private SiteSettings siteSettings;
//    private TeamMember teamMember;
//    private Testimonial testimonial;
//}

package com.altrevo.consultancy.entity;

import lombok.Data;
import java.util.List;

@Data
public class AllDocument {
    private List<User> users;
    private List<BlogPost> blogs;
    private List<Enquiry> enquiries;
    private List<FeatureToggle> featureToggles;
    private List<Job> jobs;
    private List<Project> projects;
    private List<Service> services;
    private List<StaticPage> staticPages;
    private SiteSettings siteSettings;
    private List<TeamMember> teamMembers;
    private List<Testimonial> testimonials;
}
