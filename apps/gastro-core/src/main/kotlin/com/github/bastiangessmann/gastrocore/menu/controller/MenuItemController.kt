package com.github.bastiangessmann.gastrocore.menu.controller

import com.github.bastiangessmann.gastrocore.menu.model.resource.MenuItemResource
import com.github.bastiangessmann.gastrocore.menu.service.MenuItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu-items")
class MenuItemController(
    val menuItemService: MenuItemService,
) {

    @GetMapping
    fun getMenuItems(): ResponseEntity<List<MenuItemResource>> =
        ResponseEntity.ok(menuItemService.getMenuItems())

}