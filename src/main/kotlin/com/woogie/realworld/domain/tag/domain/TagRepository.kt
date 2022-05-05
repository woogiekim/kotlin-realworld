package com.woogie.realworld.domain.tag.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long> {
    fun findByName(name: String): Tag?
}
