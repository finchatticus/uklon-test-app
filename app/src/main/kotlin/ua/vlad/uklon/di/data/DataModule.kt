package ua.vlad.uklon.di.data

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import ua.vlad.uklon.BuildConfig
import ua.vlad.uklon.data.cache.MemoryCache
import ua.vlad.uklon.data.local.LocalCommentsDataSource
import ua.vlad.uklon.data.local.LocalPostDataSource
import ua.vlad.uklon.data.net.api.ApiService
import ua.vlad.uklon.data.net.source.RemoteCommentDataSource
import ua.vlad.uklon.data.net.source.RemotePostDataSource
import ua.vlad.uklon.data.repository.CommentRepositoryImpl
import ua.vlad.uklon.data.repository.PostRepositoryImpl
import ua.vlad.uklon.domain.model.Comment
import ua.vlad.uklon.domain.model.Post
import ua.vlad.uklon.domain.repository.CommentRepository
import ua.vlad.uklon.domain.repository.PostRepository

enum class CacheType {
    POST,
    COMMENTS
}

val netModule = module {

    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }.build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper().apply {
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            }))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

}

val cacheModule = module {

    single(named(CacheType.POST)) {
        MemoryCache<List<Post>>()
    }

    single(named(CacheType.COMMENTS)) {
        MemoryCache<List<Comment>>()
    }

}

val dataSourceModule = module {

    single {
        RemotePostDataSource(get())
    }

    single {
        LocalPostDataSource(get(named(CacheType.POST)))
    }

    single {
        RemoteCommentDataSource(get())
    }

    single {
        LocalCommentsDataSource(get(named(CacheType.COMMENTS)))
    }

}

val repositoryModule = module {

    single<PostRepository> {
        PostRepositoryImpl(get<RemotePostDataSource>(), get(named(CacheType.POST)), get<LocalPostDataSource>())
    }

    single<CommentRepository> {
        CommentRepositoryImpl(get<RemoteCommentDataSource>(), get(named(CacheType.COMMENTS)), get<LocalCommentsDataSource>())
    }

}