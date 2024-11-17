package com.filmbot.constants;

public class Constants {
    //Base
    public static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3/";

    //Headers
    public static final String ACCEPT_H = "accept";
    public static final String APPLICATION_JSON = "application/json";
    public static final String AUTH = "Authorization";
    public static final String GET_METHOD = "GET";


    //Film details
    public static final String SEARCH_MOVIE_NAME_COMPONENT = "search/movie?query=";
    public static final String INCLUDE_ADULT_FILM_COMPONENT = "&include_adult=";
    public static final String LANGUAGE_COMPONENT = "&language=";
    public static final String ENGLISH_FILTER = "en-US";
    public static final String PAGINATION_COUNT_COMPONENT = "&page=";
    public static final String NO_filmS_FOUND = "No films found in TMDB under ";
    public static final String ESCAPE_PARAN = "\"";
}
