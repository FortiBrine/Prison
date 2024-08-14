package me.fortibrine.prison.di.module

import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import dagger.Module
import dagger.Provides
import me.fortibrine.prison.api.data.Connection
import org.bson.UuidRepresentation
import org.bukkit.configuration.file.FileConfiguration
import javax.inject.Singleton

@Module
class MongoModule {

    @Provides
    @Singleton
    fun provideConnection(config: FileConfiguration): Connection {
        val section = config.getConfigurationSection("database")!!

        val host = section.getString("host")!!
        val database = section.getString("database")!!
        val username = section.getString("username")!!
        val password = section.getString("password")!!

        return Connection (
            host = host,
            database = database,
            username = username,
            password = password
        )
    }

    @Provides
    @Singleton
    fun provideMongoClient(connection: Connection): MongoClient {

        val settings = MongoClientSettings.builder()
            .applyToClusterSettings {
                it.hosts(listOf(ServerAddress(connection.host)))
            }
            .credential(MongoCredential.createCredential(connection.username, connection.database, connection.password.toCharArray()))
            .uuidRepresentation(UuidRepresentation.STANDARD)
            .build()

        return MongoClient.create(settings)
    }

    @Provides
    @Singleton
    fun provideMongoDatabase(connection: Connection, mongoClient: MongoClient): MongoDatabase {
        return mongoClient.getDatabase(connection.database)
    }

}