package com.practice.initialarchitecture.data.remote.model

import com.practice.initialarchitecture.domain.models.AuthToken
import com.practice.initialarchitecture.domain.util.DomainMapper

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