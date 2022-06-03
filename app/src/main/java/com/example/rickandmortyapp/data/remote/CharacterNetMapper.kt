package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.data.Mapper
import com.example.rickandmortyapp.data.remote.parsedata.RemoteCharacter
import com.example.rickandmortyapp.repository.Character
import javax.inject.Inject


class CharacterNetMapper @Inject constructor(): Mapper<RemoteCharacter, Character> {

    override fun mapFromEntity(entity: RemoteCharacter): Character {
        return Character(
            id = entity.id,
            name = entity.name,
            status = entity.status,
            species = entity.species,
            gender = entity.gender,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Character): RemoteCharacter {
        return RemoteCharacter(
            id = domainModel.id,
            name = domainModel.name,
            status = domainModel.status,
            species = domainModel.species,
            gender = domainModel.gender,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<RemoteCharacter>): List<Character> {
        return entities.map { mapFromEntity(it) }
    }
}