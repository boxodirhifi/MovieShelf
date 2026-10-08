package uz.ttpu.movieshelf.data

interface MovieLocalDataSource {
    suspend fun getCachedMovies(): List<MovieDto>?   // null = nothing cached yet
    suspend fun saveMovies(movies: List<MovieDto>)
    suspend fun getFavoriteIds(): Set<Int>
    suspend fun setFavorite(id: Int, favorite: Boolean)
}