package com.github.jeancarlos.githublist.utils;

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */

import android.content.SearchRecentSuggestionsProvider;

/**
 * This class create a simple search suggestions provider.
 * It creates suggestions (as the user types) based on recent queries and/or recent views.
 *
 * @author Jean Carlos
 * @date 26/12/17
 */

public class SuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.github.jeancarlos.githublist.utils.SuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
