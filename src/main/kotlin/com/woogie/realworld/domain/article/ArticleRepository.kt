package com.woogie.realworld.domain.article

import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Long>
