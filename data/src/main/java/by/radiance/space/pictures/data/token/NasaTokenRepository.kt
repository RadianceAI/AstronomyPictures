package by.radiance.space.pictures.data.token

import by.radiance.space.pictures.domain.repository.token.TokenRepository

class NasaTokenRepository: TokenRepository {
    override suspend fun get(): String {
        return ""
    }
}