package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.InMemoryJobStore;
import com.altrevo.consultancy.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class JobInMemoryRepository {

    @Autowired
    private InMemoryJobStore inMemoryJobStore;

    private static final Map<Long, Job> store = InMemoryJobStore.JOBS;

    public JobInMemoryRepository() {
        // Initialize the repository with data from InMemoryJobStore
        inMemoryJobStore.JOBS.values().forEach(this::save);
    }

    public List<Job> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Job> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Job> findByDepartment(String department) {
        return store.values().stream()
                .filter(job -> department == null || department.equalsIgnoreCase(job.getDepartment()))
                .collect(Collectors.toList());
    }

    public Job save(Job job) {
        if (job.getId() == null) {
            job.setId(InMemoryJobStore.idCounter++);
        }
        store.put(job.getId(), job);
        return job;
    }

    public void deleteById(Long id) {
        store.remove(id);
    }

    public List<Job> findByIsPublicTrueAndIsOpenTrueOrderByCreatedAtDesc() {
        return store.values().stream()
                .filter(job -> Boolean.TRUE.equals(job.getIsPublic()) && Boolean.TRUE.equals(job.getIsOpen()))
                .sorted(Comparator.comparing(Job::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    public List<Job> findByIsPublicTrueAndIsOpenTrueAndFeaturedTrueOrderByCreatedAtDesc() {
        return store.values().stream()
                .filter(job -> Boolean.TRUE.equals(job.getIsPublic()) && Boolean.TRUE.equals(job.getIsOpen()) && Boolean.TRUE.equals(job.getFeatured()))
                .sorted(Comparator.comparing(Job::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    public List<Job> findByIsPublicTrueAndIsOpenTrueAndDepartmentIgnoreCaseOrderByCreatedAtDesc(String department) {
        return store.values().stream()
                .filter(job -> Boolean.TRUE.equals(job.getIsPublic()) && Boolean.TRUE.equals(job.getIsOpen()) && department.equalsIgnoreCase(job.getDepartment()))
                .sorted(Comparator.comparing(Job::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    public List<Job> findByIsPublicTrueAndIsOpenTrueAndTypeIgnoreCaseOrderByCreatedAtDesc(String type) {
        return store.values().stream()
                .filter(job -> Boolean.TRUE.equals(job.getIsPublic()) && Boolean.TRUE.equals(job.getIsOpen()) && type.equalsIgnoreCase(job.getType()))
                .sorted(Comparator.comparing(Job::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    public List<Job> findByIsPublicTrueAndIsOpenTrueAndLocationIgnoreCaseOrderByCreatedAtDesc(String location) {
        return store.values().stream()
                .filter(job -> Boolean.TRUE.equals(job.getIsPublic()) && Boolean.TRUE.equals(job.getIsOpen()) && location.equalsIgnoreCase(job.getLocation()))
                .sorted(Comparator.comparing(Job::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    public List<Job> findByIsPublicTrueAndIsOpenTrueAndLevelIgnoreCaseOrderByCreatedAtDesc(String level) {
        return store.values().stream()
                .filter(job -> Boolean.TRUE.equals(job.getIsPublic()) && Boolean.TRUE.equals(job.getIsOpen()) && level.equalsIgnoreCase(job.getLevel()))
                .sorted(Comparator.comparing(Job::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    public List<Job> findBySalaryRange(Double minSalary, Double maxSalary) {
        return store.values().stream()
                .filter(job -> job.getSalaryMin() >= minSalary && job.getSalaryMax() <= maxSalary)
                .sorted(Comparator.comparing(Job::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    public List<Job> findByMaxExperience(Integer experience) {
        return store.values().stream()
                .filter(job -> job.getExperience() <= experience)
                .sorted(Comparator.comparing(Job::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    public List<Job> findByKeyword(String keyword) {
        return store.values().stream()
                .filter(job -> job.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        job.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .sorted(Comparator.comparing(Job::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    public long count() {
        return store.size();
    }

    public long countByIsOpenTrue() {
        return store.values().stream().filter(Job::getIsOpen).count();
    }

    public List<String> findAllDepartments() {
        return store.values().stream()
                .map(Job::getDepartment)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> findAllLocations() {
        return store.values().stream()
                .map(Job::getLocation)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> findAllJobTypes() {
        return store.values().stream()
                .map(Job::getType)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> findAllJobLevels() {
        return store.values().stream()
                .map(Job::getLevel)
                .distinct()
                .collect(Collectors.toList());
    }
}
