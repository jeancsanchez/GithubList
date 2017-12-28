package com.github.jeancarlos.githublist.features.main.adapters

/**
 * This class represents an item clicked listener
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 28/12/17.
 * Jesus loves you.
 */
interface OnItemClickedListener<in T> {

    /**
     * Clicked item
     * @param any Any item
     */
    fun onClicked(any: T)
}