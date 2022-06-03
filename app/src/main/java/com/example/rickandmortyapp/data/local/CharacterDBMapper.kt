package com.example.rickandmortyapp.data.local

import com.example.rickandmortyapp.data.Mapper
import com.example.rickandmortyapp.data.local.entities.CharacterEntity
import com.example.rickandmortyapp.repository.Character
import javax.inject.Inject


class CharacterDBMapper @Inject constructor(): Mapper<CharacterEntity, Character> {

    override fun mapFromEntity(entity: CharacterEntity): Character {
        return Character(
            id = entity.id,
            name = entity.name,
            status = entity.status,
            species = entity.species,
            gender = entity.gender,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Character): CharacterEntity {
        return CharacterEntity(
            id = domainModel.id,
            name = domainModel.name,
            status = domainModel.status,
            species = domainModel.species,
            gender = domainModel.gender,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<CharacterEntity>): List<Character> {
        return entities.map { mapFromEntity(it) }
    }

}