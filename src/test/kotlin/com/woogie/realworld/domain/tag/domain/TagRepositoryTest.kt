package com.woogie.realworld.domain.tag.domain

import com.woogie.realworld.support.BaseRepositoryTest
import com.woogie.realworld.support.findByIdOrThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class TagRepositoryTest @Autowired constructor(
    private val tagRepository: TagRepository
) : BaseRepositoryTest() {

    private lateinit var tag: Tag

    @BeforeEach
    fun setUp() {
        tag = tagRepository.save(Tag("kotlin"))
    }

    @Test
    fun `Tag 생성 성공`() {
        var tag = Tag("tag")

        assertThat(tag.id).isNull()

        tag = tagRepository.save(tag)

        val foundTag = tagRepository.findByIdOrThrow(tag.id)

        assertThat(foundTag.id).isNotNull
    }

    @Test
    fun `이름으로 Tag 조회`() {
        val foundTag = tagRepository.findByName(tag.name)!!

        assertThat(foundTag.name).isEqualTo(tag.name)
    }
}
