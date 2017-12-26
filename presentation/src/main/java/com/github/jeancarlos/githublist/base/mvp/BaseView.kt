package com.github.jeancarlos.githublist.base.mvp

/**
 * This class represents a base view.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */

interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun showError(exception: Throwable)
}
