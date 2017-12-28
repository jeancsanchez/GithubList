package com.github.jeancarlos.githublist.data.remote.Pojos

import com.github.jeancarlos.githublist.data.models.DUser
import com.google.gson.annotations.SerializedName

/**
 * This class represents a Search Pojo model.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
class SearchPojo(
        @SerializedName("items") val result: List<DUser> = emptyList(),
        @SerializedName("total_count") val count: Int = 0,
        @SerializedName("incomplete_results") val isIncomplete: Boolean = false
)