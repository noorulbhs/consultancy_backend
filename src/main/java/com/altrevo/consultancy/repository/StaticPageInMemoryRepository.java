package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.InMemoryStaticPageStore;
import com.altrevo.consultancy.entity.StaticPage;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StaticPageInMemoryRepository {
    public Optional<StaticPage> findById(String id) {
        return Optional.ofNullable(InMemoryStaticPageStore.STATIC_PAGES.get(id));
    }

    public List<StaticPage> findAll() {
        return new ArrayList<>(InMemoryStaticPageStore.STATIC_PAGES.values());
    }

    public StaticPage save(StaticPage page) {
        InMemoryStaticPageStore.STATIC_PAGES.put(page.getId(), page);
        return page;
    }

    public void deleteById(String id) {
        InMemoryStaticPageStore.STATIC_PAGES.remove(id);
    }
}

