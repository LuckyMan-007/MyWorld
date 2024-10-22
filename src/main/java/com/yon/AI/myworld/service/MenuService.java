package com.yon.AI.myworld.service;

import com.yon.AI.myworld.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Map<String, Object>> getMenuList() {
        return menuRepository.getMenuList();
    }

    public Map<String, Object> getMenuDesign(int id) {
        return menuRepository.getMenuDesign(id);
    }
}
