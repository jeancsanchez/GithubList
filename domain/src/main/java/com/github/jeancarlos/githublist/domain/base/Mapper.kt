package com.github.jeancarlos.githublist.domain.base

/**
 * This class is a base mapper for mapping the models between the layers.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
abstract class Mapper<FROM, TO> {

    abstract fun transform(value: FROM): TO

    fun transform(value: List<FROM>): List<TO> {
        val result = arrayListOf<TO>()
        value.forEach { result.add(transform(it)) }
        return result
    }
}