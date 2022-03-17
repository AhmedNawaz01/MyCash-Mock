package com.digicell.mycash.data.local.model

import com.digicell.mycash.domain.models.AuthToken
import com.digicell.mycash.domain.util.DomainMapper

class AuthTokenEntityMapper : DomainMapper<AuthTokenEntity, AuthToken> {

    override fun mapToDomainModel(model: AuthTokenEntity): AuthToken {
        return AuthToken(
            pk = model.account_pk,
            token = model.token
        )
    }

    override fun mapFromDomainModel(domainModel: AuthToken): AuthTokenEntity {
        return AuthTokenEntity(
            account_pk = domainModel.pk,
            token = domainModel.token
        )
    }
}