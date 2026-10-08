package uz.ttpu.movieshelf.data

import kotlinx.coroutines.delay
import java.io.IOException

data class MovieDto(
    val id: Int,
    val title: String,
    val releaseYear: Int,
    val score: Double,
)

//suspend is used because I/O must not block the main thread.
interface MovieRemoteDataSource {
    suspend fun fetchMovies(): List<MovieDto>
}

class InMemoryMovieLocalDataSource : MovieLocalDataSource {

    private var cached: List<MovieDto>? = null
    private val favorites = mutableSetOf<Int>()

    override suspend fun getCachedMovies(): List<MovieDto>? = cached

    override suspend fun saveMovies(movies: List<MovieDto>) {
        cached = movies
    }

    override suspend fun getFavoriteIds(): Set<Int> = favorites.toSet()

    override suspend fun setFavorite(id: Int, favorite: Boolean) {
        if (favorite) favorites.add(id) else favorites.remove(id)
    }
}