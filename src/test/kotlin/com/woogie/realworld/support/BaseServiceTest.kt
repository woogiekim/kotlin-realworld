package com.woogie.realworld.support

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest(webEnvironment = NONE)
abstract class BaseServiceTest
