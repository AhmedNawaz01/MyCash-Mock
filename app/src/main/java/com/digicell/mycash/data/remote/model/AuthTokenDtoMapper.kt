package com.digicell.mycash.data.remote.model

import com.digicell.mycash.domain.models.AuthToken
import com.digicell.mycash.domain.util.DomainMapper

class AuthTokenDtoMapper : DomainMapper<AuthTokenDto, AuthToken> {

    override fun mapToDomainModel(model: AuthTokenDto): AuthToken {
        return AuthToken(
            token = model.token,
            error = model.error
        )
    }

    override fun mapFromDomainModel(domainModel: AuthToken): AuthTokenDto {
        return AuthTokenDto(
            token = domainModel.token,
            error = domainModel.error
        )
    }

}