package nl.mythicproductions.socialdealdemo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DealsClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideJSONConfig(): Json {
        return Json {
            prettyPrint = true
            isLenient = true
            namingStrategy = JsonNamingStrategy.SnakeCase
            ignoreUnknownKeys = true
        }
    }

    @Singleton
    @Provides
    fun provideBaseHttpClient(jsonConfig: Json): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(jsonConfig)
            }
            if (BuildConfig.DEBUG) {
                install(Logging) {
                    logger = Logger.ANDROID
                }
            }
        }
    }

    @Singleton
    @DealsClient
    @Provides
    fun provideDealsService(@Named("deals_service_url") url: String, baseHttpClient: HttpClient): HttpClient {
        return baseHttpClient.config {
            defaultRequest {
                contentType(ContentType.Application.Json)
                url(url)
            }
        }
    }

    @Provides
    @Named("deals_service_url")
    fun provideDealsServiceUrl(): String {
        return "https://media.socialdeal.nl/"
    }
}