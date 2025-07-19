package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.Service;
import com.altrevo.consultancy.entity.InMemoryServiceStore;
import com.altrevo.consultancy.enums.ServiceStatus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ServiceInMemoryRepository {
    public Service save(Service service) {
        if (service.getId() == null) {
            service.setId(InMemoryServiceStore.idCounter++);
        }
        InMemoryServiceStore.SERVICES.put(service.getId(), service);
        return service;
    }

    public Optional<Service> findById(Long id) {
        return Optional.ofNullable(InMemoryServiceStore.SERVICES.get(id));
    }

    public List<Service> findAll() {
        return new ArrayList<>(InMemoryServiceStore.SERVICES.values());
    }

    public void deleteById(Long id) {
        InMemoryServiceStore.SERVICES.remove(id);
    }

    public List<Service> findByStatusOrderBySortOrder(ServiceStatus status) {
        List<Service> result = new ArrayList<>();
        for (Service s : InMemoryServiceStore.SERVICES.values()) {
            if (status.equals(s.getStatus())) {
                result.add(s);
            }
        }
        result.sort(Comparator.comparingInt(s -> s.getSortOrder() != null ? s.getSortOrder() : 0));
        return result;
    }

    public List<Service> findFeaturedByStatus(ServiceStatus status) {
        List<Service> result = new ArrayList<>();
        for (Service s : InMemoryServiceStore.SERVICES.values()) {
            if (status.equals(s.getStatus()) && Boolean.TRUE.equals(s.getFeatured())) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Service> findByStatusAndCategory(ServiceStatus status, String category) {
        List<Service> result = new ArrayList<>();
        for (Service s : InMemoryServiceStore.SERVICES.values()) {
            if (status.equals(s.getStatus()) && category.equals(s.getCategory())) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Service> findByStatus(ServiceStatus status) {
        List<Service> result = new ArrayList<>();
        for (Service s : InMemoryServiceStore.SERVICES.values()) {
            if (status.equals(s.getStatus())) {
                result.add(s);
            }
        }
        return result;
    }

    public long count() {
        return InMemoryServiceStore.SERVICES.size();
    }

    public long countByStatus(ServiceStatus status) {
        return InMemoryServiceStore.SERVICES.values().stream()
                .filter(s -> status.equals(s.getStatus()))
                .count();
    }
}
