package com.woogie.realworld.domain.tag.service

import com.woogie.realworld.domain.tag.domain.Tag
import com.woogie.realworld.domain.tag.domain.TagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TagRevisionUseCase {
    /**
     * 태그 어멘드
     *
     * 같은 이름의 태그가 있으면, 첮은 태그로 리턴
     * 같은 이름의 태그가 없으면, 태그 저장후 리턴
     */
    fun amend(tag: Tag): Tag
}

@Service
@Transactional
class TagRevisionCommand(
    private val tagRepository: TagRepository
) : TagRevisionUseCase {
    override fun amend(tag: Tag): Tag {
        return tagRepository.findByName(tag.name) ?: tagRepository.save(tag)
    }
}
