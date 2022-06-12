package com.matrix159.graphqlexample

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.matrix159.graphqlexample.models.Player
import com.matrix159.graphqlexample.models.PlayerInput
import com.matrix159.graphqlexample.models.Position
import com.matrix159.graphqlexample.repositories.IPlayerRepository
import com.matrix159.graphqlexample.repositories.PlayerRepository

fun SchemaBuilder.schemaValue() {
    val repository: IPlayerRepository = PlayerRepository()

    // Data setup
    inputType<PlayerInput>{
        description = "The input of the player without the identifier"
    }
    type<Player>{
        description = "Player object with the attributes name, team, position and identifier"
    }
    enum<Position>()

    // Queries
    query("playersByPosition") {
        description = "Retrieve all the players by his position"
        resolver { position: Position ->
            try {
                // 4
                repository.filterPlayersByPosition(position)
            } catch (e: Exception) {
                emptyList<Player>()
            }
        }
    }

    query("players") {
        description = "Retrieve all players"
        resolver { ->
            try {
                repository.listPlayers()
            } catch (e: Exception) {
                emptyList<Player>()
            }
        }
    }

    query("playersByTeam") {
        description = "Retrieve all the players by his team"
        resolver { team: String ->
            try {
                repository.filterPlayersByTeam(team)
            } catch (e: Exception) {
                emptyList<Player>()
            }
        }
    }

    // Mutations
    mutation("createPlayer") {
        // 2
        description = "Create a new player"
        // 3
        resolver { playerInput: PlayerInput ->
            try {
                // 4
                val uid = java.util.UUID.randomUUID().toString()
                val player = Player(uid, playerInput.name, playerInput.team, playerInput.position)
                repository.createPlayer(player)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}
