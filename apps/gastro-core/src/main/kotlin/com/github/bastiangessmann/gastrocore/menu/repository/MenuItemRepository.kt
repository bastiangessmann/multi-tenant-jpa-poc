package com.github.bastiangessmann.gastrocore.menu.repository

import com.github.bastiangessmann.gastrocore.menu.model.entity.MenuItem
import org.springframework.data.jpa.repository.JpaRepository

interface MenuItemRepository : JpaRepository<MenuItem, Long>