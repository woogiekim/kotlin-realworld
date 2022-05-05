package com.woogie.realworld.domain.article.domain

import com.woogie.realworld.domain.tag.domain.Tag
import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.support.BaseAggregateRoot
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToMany
import javax.persistence.OneToOne

@Entity
class Article(
    /** 작성자 **/
    @OneToOne(fetch = FetchType.LAZY)
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
    @ManyToMany
    var tags: MutableList<Tag> = mutableListOf()
) : BaseAggregateRoot<Article>() {

    fun withTag(tag: Tag) {
        this.tags.add(tag)
    }

    companion object {
        fun create(author: User, title: ArticleTitle, description: ArticleDescription, body: ArticleBody): Article {
            return Article(author, title, ArticleSlug.create(title), description, body)
        }
    }
}
