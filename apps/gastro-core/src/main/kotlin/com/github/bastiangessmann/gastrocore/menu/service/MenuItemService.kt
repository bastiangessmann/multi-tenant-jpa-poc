package com.github.bastiangessmann.gastrocore.menu.service

import com.github.bastiangessmann.gastrocore.menu.model.resource.MenuItemResource
import com.github.bastiangessmann.gastrocore.menu.repository.MenuItemRepository
import org.springframework.stereotype.Service

@Service
class MenuItemService(
    private val menuItemRepository: MenuItemRepository,
) {

    fun getMenuItems(): List<MenuItemResource> {
        val menuItems = menuItemRepository.findAll()
        return menuItems.map { MenuItemResource.from(it) }
    }

}