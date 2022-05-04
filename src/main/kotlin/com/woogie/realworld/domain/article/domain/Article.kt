package com.woogie.realworld.domain.article.domain

import com.woogie.realworld.domain.tag.Tag
import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.support.BaseAggregateRoot
import javax.persistence.CascadeType
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class Article(
    /** 작성자 **/
    @OneToOne(cascade = [CascadeType.ALL])
    val author: User,

    /** 제목 **/
    var title: ArticleTitle,

    /** 슬로건 **/
    var slug: ArticleSlug,

    /** 설명 **/
    var description: ArticleDescription,

    /** 내용 **/
    var body: ArticleBody,

    /** 태그 **/
    @ElementCollection
    var tags: MutableList<Tag> = mutableListOf()
) : BaseAggregateRoot<Article>()
