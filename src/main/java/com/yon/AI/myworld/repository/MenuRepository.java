package com.yon.AI.myworld.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MenuRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> getMenuList() {
        Query query = entityManager.createNamedQuery("Menu.getMenuList");
        List<Object[]> results = query.getResultList();

        List<Map<String, Object>> menuList = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> menuMap = new HashMap<>();
            menuMap.put("menuId", row[0]);
            menuMap.put("menuName", row[1]);
            menuMap.put("menuUrl", row[2]);
            menuList.add(menuMap);
        }
        return menuList;
    }

    public Map<String, Object> getMenuDesign(int id) {
        try {
            Query query = entityManager.createNamedQuery("Menu.getMenuDesign");
            query.setParameter("id", id);
            List<Object[]> results = query.getResultList();

            if (results.isEmpty()) {
                return null; // No result found
            }

            Object[] row = results.get(0);
            Map<String, Object> menuDesign = new HashMap<>();
            menuDesign.put("menuId", row[0]);
            menuDesign.put("style", row[1]);
            menuDesign.put("script", row[2]);
            menuDesign.put("body", row[3]);
            menuDesign.put("param", row[4]);

            return menuDesign;
        } catch (NoResultException e) {
            return null; // Handle case where no result is found
        }
    }
}
