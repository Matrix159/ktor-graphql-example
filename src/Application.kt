package com.matrix159.graphqlexample

import com.apurebase.kgraphql.GraphQL
import io.ktor.application.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(GraphQL) {
        playground = true
        schema {
            schemaValue()
        }
    }
}

