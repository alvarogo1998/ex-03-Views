package com.agalobr.ex03views.features.ex02movie.data.remote

import com.agalobr.ex03views.features.ex02movie.domain.Movie

class ApiMoviesRemoteSource {

    companion object{
        val moviesList: List<Movie> = listOf(
            Movie(
                1,
                "Jungle Cruise",
                "12",
                "1.03 GB",
                "2 h 9 min",
                "https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/F3A06E15EE141CF4BAC34B4FAB7FBE5F7E3757D0CE5EDF869800FA6A862D9368/scale?width=1200&aspectRatio=1.78&format=jpeg",
            ),
            Movie(
                2,
                "Black Widow",
                "12",
                "1.15 GB",
                "2 h 13 min",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2u95wOJokLo-ebA0DzV6JyWiuT5-h5I83TPanle9Cvb8OSeFzwhBXVxZ176YSpDkfcsQ&usqp=CAU"
            ),
            Movie(
                3,
                "Mohana: Un mar de Aventuras",
                "L",
                "803 MB",
                "1 h 47 min",
                "https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/EFCEE1C61D9AD1E82A6EA1FAD2E3B7DCF027A48C5D5FEE3D9E22871AF9FD21B2/scale?width=1200&aspectRatio=1.78&format=jpeg"
            )
        )
    }
}