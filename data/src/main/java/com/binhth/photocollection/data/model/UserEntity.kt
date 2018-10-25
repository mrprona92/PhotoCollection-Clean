package com.binhth.photocollection.data.model

import com.binhth.photocollection.data.base.BaseEntity
import com.binhth.photocollection.data.base.EntityMapper
import com.binhth.photocollection.domain.model.User

data class UserEntity(
    val id: String? = null,
    val username: String? = null
) : BaseEntity()


class UserEntityMapper : EntityMapper<User, UserEntity> {
    override fun mapToDomain(entity: UserEntity) = User(
        id = entity.id,
        username = entity.username
    )

    override fun mapToEntity(model: User) = UserEntity(
        id = model.id,
        username = model.username
    )
}