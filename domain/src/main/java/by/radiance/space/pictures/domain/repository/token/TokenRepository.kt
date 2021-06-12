package by.radiance.space.pictures.domain.repository.token

interface TokenRepository {
    suspend fun get(): String
}