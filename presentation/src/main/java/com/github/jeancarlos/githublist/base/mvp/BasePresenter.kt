package com.github.jeancarlos.githublist.base.mvp

/**
 * This class represents a base presenter.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */

interface BasePresenter<VIEW : BaseView> {

    var view: VIEW?
}
