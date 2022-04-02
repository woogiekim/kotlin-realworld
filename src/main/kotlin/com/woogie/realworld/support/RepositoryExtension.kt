package com.woogie.realworld.support

import org.springframework.data.repository.CrudRepository

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T = findById(id).get()
