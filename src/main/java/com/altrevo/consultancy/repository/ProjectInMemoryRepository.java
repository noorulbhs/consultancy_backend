package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.Project;
import java.util.*;

public class ProjectInMemoryRepository {
    private static final Map<Long, Project> store = InMemoryProjectStore.projects;

    public List<Project> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Project> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Project save(Project project) {
        if (project.getId() == null) {
            project.setId(InMemoryProjectStore.projectIdSequence++);
        }
        store.put(project.getId(), project);
        return project;
    }

    public void deleteById(Long id) {
        store.remove(id);
    }

    public Optional<Project> findByProjectCode(String projectCode) {
        return store.values().stream().filter(p -> projectCode.equals(p.getProjectCode())).findFirst();
    }

    public List<Project> findByStatusAndPriorityAndCategoryIgnoreCaseAndClientNameIgnoreCaseOrderByCreatedAtDesc(
            com.altrevo.consultancy.enums.ProjectStatus status,
            com.altrevo.consultancy.enums.ProjectPriority priority,
            String category,
            String clientName,
            org.springframework.data.domain.Pageable pageable) {
        return findAll().stream()
                .filter(p -> status.equals(p.getStatus()) &&
                        priority.equals(p.getPriority()) &&
                        category.equalsIgnoreCase(p.getCategory()) &&
                        clientName.equalsIgnoreCase(p.getClientName()))
                .sorted(Comparator.comparing(Project::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public List<Project> findByStatusAndPriorityAndCategoryIgnoreCaseOrderByCreatedAtDesc(
            com.altrevo.consultancy.enums.ProjectStatus status,
            com.altrevo.consultancy.enums.ProjectPriority priority,
            String category,
            org.springframework.data.domain.Pageable pageable) {
        return findAll().stream()
                .filter(p -> status.equals(p.getStatus()) &&
                        priority.equals(p.getPriority()) &&
                        category.equalsIgnoreCase(p.getCategory()))
                .sorted(Comparator.comparing(Project::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public List<Project> findByStatusAndPriorityOrderByCreatedAtDesc(
            com.altrevo.consultancy.enums.ProjectStatus status,
            com.altrevo.consultancy.enums.ProjectPriority priority,
            org.springframework.data.domain.Pageable pageable) {
        return findAll().stream()
                .filter(p -> status.equals(p.getStatus()) && priority.equals(p.getPriority()))
                .sorted(Comparator.comparing(Project::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public List<Project> findByStatusAndCategoryIgnoreCaseOrderByCreatedAtDesc(
            com.altrevo.consultancy.enums.ProjectStatus status,
            String category,
            org.springframework.data.domain.Pageable pageable) {
        return findAll().stream()
                .filter(p -> status.equals(p.getStatus()) && category.equalsIgnoreCase(p.getCategory()))
                .sorted(Comparator.comparing(Project::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public List<Project> findByStatusAndClientNameIgnoreCaseOrderByCreatedAtDesc(
            com.altrevo.consultancy.enums.ProjectStatus status,
            String clientName,
            org.springframework.data.domain.Pageable pageable) {
        return findAll().stream()
                .filter(p -> status.equals(p.getStatus()) && clientName.equalsIgnoreCase(p.getClientName()))
                .sorted(Comparator.comparing(Project::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public List<Project> findByStatusOrderByCreatedAtDesc(
            com.altrevo.consultancy.enums.ProjectStatus status,
            org.springframework.data.domain.Pageable pageable) {
        return findAll().stream()
                .filter(p -> status.equals(p.getStatus()))
                .sorted(Comparator.comparing(Project::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public List<Project> findByPriorityOrderByCreatedAtDesc(
            com.altrevo.consultancy.enums.ProjectPriority priority,
            org.springframework.data.domain.Pageable pageable) {
        return findAll().stream()
                .filter(p -> priority.equals(p.getPriority()))
                .sorted(Comparator.comparing(Project::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public List<Project> findByCategoryIgnoreCaseOrderByCreatedAtDesc(
            String category,
            org.springframework.data.domain.Pageable pageable) {
        return findAll().stream()
                .filter(p -> category.equalsIgnoreCase(p.getCategory()))
                .sorted(Comparator.comparing(Project::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public List<Project> findByClientNameIgnoreCaseOrderByCreatedAtDesc(
            String clientName,
            org.springframework.data.domain.Pageable pageable) {
        return findAll().stream()
                .filter(p -> clientName.equalsIgnoreCase(p.getClientName()))
                .sorted(Comparator.comparing(Project::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public List<Project> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrClientNameContainingIgnoreCaseOrderByCreatedAtDesc(
            String name, String description, String clientName, org.springframework.data.domain.Pageable pageable) {
        return findAll().stream()
                .filter(p -> (p.getName() != null && p.getName().toLowerCase().contains(name.toLowerCase())) ||
                        (p.getDescription() != null && p.getDescription().toLowerCase().contains(description.toLowerCase())) ||
                        (p.getClientName() != null && p.getClientName().toLowerCase().contains(clientName.toLowerCase())))
                .sorted(Comparator.comparing(Project::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .toList();
    }

    public long count() {
        return store.size();
    }

    public long countByStatus(com.altrevo.consultancy.enums.ProjectStatus status) {
        return findAll().stream().filter(p -> status.equals(p.getStatus())).count();
    }

    public List<String> findDistinctCategories() {
        return findAll().stream().map(Project::getCategory).filter(Objects::nonNull).distinct().toList();
    }

    public List<String> findDistinctProjectManagers() {
        return findAll().stream().map(Project::getProjectManager).filter(Objects::nonNull).distinct().toList();
    }

    public List<String> findDistinctClients() {
        return findAll().stream().map(Project::getClientName).filter(Objects::nonNull).distinct().toList();
    }

    public double sumBudget() {
        return findAll().stream().mapToDouble(p -> p.getEstimatedBudget() != null ? p.getEstimatedBudget() : 0).sum();
    }

    public double sumActualCost() {
        return findAll().stream().mapToDouble(p -> p.getActualBudget() != null ? p.getActualBudget() : 0).sum();
    }

    public double averageProgress() {
        return findAll().stream().mapToDouble(p -> p.getProgress() != null ? p.getProgress() : 0).average().orElse(0);
    }
}
