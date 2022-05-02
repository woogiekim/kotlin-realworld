package com.woogie.realworld.fixture

import com.woogie.realworld.domain.article.*
import com.woogie.realworld.domain.user.domain.User

fun createArticle(
    author: User = createUser(),
    title: ArticleTitle = createArticleTitle(),
    slug: ArticleSlug = createArticleSlug(),
    description: ArticleDescription = createArticleDescription(),
    body: ArticleBody = createArticleBody()
): Article {
    return Article(author, title, slug, description, body)
}

fun createArticleTitle(title: String = "title"): ArticleTitle {
    return ArticleTitle(title)
}

fun createArticleSlug(title: ArticleTitle = createArticleTitle()): ArticleSlug {
    return ArticleSlug.create(title)
}

fun createArticleDescription(description: String = "description"): ArticleDescription {
    return ArticleDescription(description)
}

fun createArticleBody(body: String = "body"): ArticleBody {
    return ArticleBody(body)
}
