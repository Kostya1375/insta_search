package ua.com.dowell.instasearch.model.impl

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.model.rest.Api

/**
 * Created by kosty on 29.01.2018.
 */
@Module
class ApiImpl {

    @Provides
    fun providesApi(accHelper: AccountHelper): Api {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BASE_URL)
                .client(createOkHttpClient(accHelper))
                .build()
                .create(Api::class.java)
    }

    private fun createOkHttpClient(accountHelper: AccountHelper) = OkHttpClient
            .Builder()
            .addInterceptor(createHeaderInterceptor(accountHelper))
            .addInterceptor(createAuthorizedInterceptor())
            .addInterceptor(createLogInterceptor())
            .build()

    private fun createHeaderInterceptor(accHelper: AccountHelper) = Interceptor { chain ->
        val request: Request = chain.request()
        val builder: Request.Builder = request.newBuilder()
        builder.addHeader("Content-Type", "application/json")
        accHelper.getToken().let { builder.addHeader("Authorization", it) }
        chain.proceed(builder.build())
    }

    private fun createAuthorizedInterceptor() = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)
        Timber.d("Result code: ${response.code()}")
//        if (response.code() == 401) {
//            openLoginActivity(context)
//        }
        response
    }

    private fun createLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor({ message -> Timber.tag(OK_HTTP).d(message) })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    companion object {
        private const val BASE_URL = "https://scanerme.herokuapp.com/"
        private const val OK_HTTP = "okHttp"
    }
}