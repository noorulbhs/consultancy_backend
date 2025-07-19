(get)http://localhost:8080/api/v1/admin/projects/summary

response
{
"success": true,
"message": null,
"data": {
"totalProjects": 1,
"activeProjects": 0,
"completedProjects": 0,
"totalBudget": 100,
"totalActualCost": 0,
"averageProgress": 0,
"overdueMilestones": 0
},
"error": null,
"timestamp": "2025-07-19T13:59:35Z",
"path": "/api/v1/admin/projects/summary"
}

(get)http://localhost:8080/api/v1/admin/projects
response
{
"success": true,
"message": null,
"data": [
{
"createdAt": "2025-07-17T13:24:37Z",
"updatedAt": "2025-07-17T13:24:37Z",
"createdBy": "noorul@altrevo.com",
"updatedBy": "noorul@altrevo.com",
"id": 6,
"projectCode": "PROJ-877520",
"name": "Banking",
"description": "I need to have banking project for my client.",
"clientName": "Atif",
"clientEmail": "atif@gmail.com",
"clientPhone": "9876543210",
"clientCompany": "ABC",
"startDate": "2025-07-17T00:00:00",
"endDate": "2025-08-06T00:00:00",
"estimatedBudget": 100,
"actualBudget": 0,
"status": "PLANNING",
"priority": "MEDIUM",
"progress": 0,
"category": "Web Development",
"technologies": [
"Java"
],
"projectManager": "Atif",
"milestones": [],
"documents": [],
"teamMembers": [],
"externalTeamMembers": []
}
],
"error": null,
"timestamp": null,
"path": null
}


(put)http://localhost:8080/api/v1/admin/projects/6
or
(post)http://localhost:8080/api/v1/admin/projects
resquestBody:
{
"name": "Banking",
"description": "I need to have banking project for my client.",
"clientName": "Atif",
"clientEmail": "atif@gmail.com",
"clientPhone": "9876543210",
"clientCompany": "ABC",
"startDate": "2025-07-16T00:00:00.000Z",
"endDate": "2025-08-05T00:00:00.000Z",
"estimatedBudget": 100,
"actualBudget": 0,
"status": "planning",
"priority": "medium",
"progress": 0,
"category": "Web Development",
"technologies": [
"Java"
],
"projectManager": "Atif",
"teamMembers": [
{
"createdAt": "2025-07-17T02:32:18Z",
"updatedAt": "2025-07-17T02:32:18Z",
"createdBy": null,
"updatedBy": null,
"id": 1,
"name": "John Smith",
"role": "Senior Cloud Architect",
"department": "Engineering",
"email": "john.smith@altrevo.com",
"phone": "+1-555-0101",
"linkedinUrl": "https://linkedin.com/in/johnsmith",
"twitterUrl": null,
"githubUrl": null,
"photoUrl": "https://example.com/john-smith.jpg",
"bio": "John is a seasoned cloud architect with 10+ years of experience in designing and implementing scalable cloud solutions.",
"skills": [
"AWS",
"Azure",
"Kubernetes",
"Docker",
"Terraform"
],
"experience": "10+ years",
"education": "MS Computer Science, Stanford University",
"location": "San Francisco, CA",
"joinDate": "2020-01-15",
"isPublic": true,
"featured": true,
"achievements": [
"AWS Solutions Architect Professional",
"Led 50+ cloud migrations",
"Reduced infrastructure costs by 40%"
],
"languages": [],
"specializations": [],
"certifications": [],
"interests": [],
"workStyle": null,
"motto": null,
"sortOrder": 1,
"allocatedHours": 40,
"isProjectLead": true,
"joinedDate": "2025-07-19T14:01:56.343Z"
},
{
"createdAt": "2025-07-17T02:32:18Z",
"updatedAt": "2025-07-17T02:32:18Z",
"createdBy": null,
"updatedBy": null,
"id": 3,
"name": "Michael Chen",
"role": "AI/ML Engineer",
"department": "Engineering",
"email": "michael.chen@altrevo.com",
"phone": "+1-555-0103",
"linkedinUrl": "https://linkedin.com/in/michaelchen",
"twitterUrl": null,
"githubUrl": null,
"photoUrl": "https://example.com/michael-chen.jpg",
"bio": "Michael is an expert in artificial intelligence and machine learning with a focus on practical business applications.",
"skills": [
"Python",
"TensorFlow",
"PyTorch",
"AWS SageMaker",
"Scikit-learn"
],
"experience": "7+ years",
"education": "PhD Machine Learning, Carnegie Mellon",
"location": "Pittsburgh, PA",
"joinDate": "2019-08-10",
"isPublic": true,
"featured": true,
"achievements": [
"Published 15+ research papers",
"Deployed ML models for Fortune 500 companies",
"TensorFlow Developer Certificate"
],
"languages": [],
"specializations": [],
"certifications": [],
"interests": [],
"workStyle": null,
"motto": null,
"sortOrder": 3,
"allocatedHours": 10,
"isProjectLead": null,
"joinedDate": "2025-07-19T14:02:06.845Z"
}
],
"externalTeamMembers": [
{
"name": "Noorul",
"email": "noorul@abc.com",
"phone": "9876543210",
"company": "ABC",
"role": "Developer",
"skills": [
"Java",
"Skills"
],
"hourlyRate": 0,
"contractType": "freelancer",
"contractStartDate": "2025-07-19T00:00:00.000Z",
"contractEndDate": "2025-07-30T00:00:00.000Z",
"allocatedHours": 20,
"paymentTerms": "Cash",
"notes": "Good Developer",
"isActive": true
}
],
"milestones": [
{
"title": "Designing",
"description": "Designing Completed",
"dueDate": "2025-07-20T00:00:00.000Z",
"status": "completed",
"progress": 10,
"assignedTo": [],
"deliverables": [
"Completed"
],
"id": "1752933796362"
}
],
"documents": [],
"notes": []
}


response:

{
"success": true,
"message": "Project updated successfully",
"data": {
"createdAt": "2025-07-17T13:24:37Z",
"updatedAt": "2025-07-19T19:34:05Z",
"createdBy": "noorul@altrevo.com",
"updatedBy": "noorul@altrevo.com",
"id": 6,
"projectCode": "PROJ-877520",
"name": "Banking",
"description": "I need to have banking project for my client.",
"clientName": "Atif",
"clientEmail": "atif@gmail.com",
"clientPhone": "9876543210",
"clientCompany": "ABC",
"startDate": "2025-07-16T00:00:00",
"endDate": "2025-08-05T00:00:00",
"estimatedBudget": 100,
"actualBudget": 0,
"status": "PLANNING",
"priority": "MEDIUM",
"progress": 0,
"category": "Web Development",
"technologies": [
"Java"
],
"projectManager": "Atif",
"milestones": [
{
"createdAt": "2025-07-19T19:34:05Z",
"updatedAt": "2025-07-19T19:34:05Z",
"createdBy": null,
"updatedBy": null,
"id": 1,
"milestoneCode": null,
"title": "Designing",
"description": "Designing Completed",
"dueDate": "2025-07-20T00:00:00",
"status": "COMPLETED",
"completedDate": null,
"progress": 10,
"sortOrder": 0,
"completed": false
}
],
"documents": [],
"teamMembers": [
{
"createdAt": "2025-07-19T19:34:05Z",
"updatedAt": "2025-07-19T19:34:05Z",
"createdBy": null,
"updatedBy": null,
"id": 1,
"memberId": null,
"name": "John Smith",
"role": "Senior Cloud Architect",
"allocatedHours": 40,
"hourlyRate": null,
"isActive": true
},
{
"createdAt": "2025-07-19T19:34:05Z",
"updatedAt": "2025-07-19T19:34:05Z",
"createdBy": null,
"updatedBy": null,
"id": 2,
"memberId": null,
"name": "Michael Chen",
"role": "AI/ML Engineer",
"allocatedHours": 10,
"hourlyRate": null,
"isActive": true
}
],
"externalTeamMembers": [
{
"id": 1,
"name": "Noorul",
"email": "noorul@abc.com",
"phone": "9876543210",
"company": "ABC",
"role": "Developer",
"contractType": "freelancer",
"contractStartDate": "2025-07-19T00:00:00.000Z",
"allocatedHours": 20,
"hourlyRate": 0,
"paymentTerms": "Cash",
"notes": "Good Developer",
"isActive": true
}
]
},
"error": null,
"timestamp": null,
"path": null
}