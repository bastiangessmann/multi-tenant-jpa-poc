package com.github.bastiangessmann.gastrocore.menu.model.resource

import com.github.bastiangessmann.gastrocore.menu.model.entity.MenuItem

data class MenuItemResource(
     val name: String,
) {

    companion object {
        fun from(entity: MenuItem) = MenuItemResource(name = entity.name)
    }
}