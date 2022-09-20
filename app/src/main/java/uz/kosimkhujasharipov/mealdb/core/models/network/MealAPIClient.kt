package uz.kosimkhujasharipov.mealdb.core.models.network

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.kosimkhujasharipov.mealdb.BuildConfig

class MealAPIClient {
    companion object {
        fun mealAPI(context: Context): MealAPI {
            return retrofit(context).create(MealAPI::class.java)
        }

        private fun retrofit(context: Context): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
            if (BuildConfig.DEBUG) {
                retrofit.client(getOkhttp(context))
            } else {
                retrofit.client(getOkhttpRelease())
            }
            return retrofit.build()
        }

        private fun getOkhttpRelease(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor(headerInterceptor())
                .addInterceptor(loggingInterceptor())
                .build()
        }

        private fun getOkhttp(context: Context): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor(headerInterceptor())
                .addInterceptor(loggingInterceptor())
                .addInterceptor(chuckInterceptor(context))
                .build()
        }

        private fun headerInterceptor(): Interceptor {
            val interceptor = Interceptor { chain ->
                val request = chain.request()
                val requestBuilder = request.newBuilder()
                requestBuilder.addHeader("content-type", "application/json")
                return@Interceptor chain.proceed(requestBuilder.build())
            }
            return interceptor
        }

        private fun loggingInterceptor(): Interceptor {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor { message ->
                    Log.d("TAG", message)
                }
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return httpLoggingInterceptor
        }

        private fun chuckInterceptor(context: Context): Interceptor {
            return ChuckerInterceptor.Builder(context)
                .maxContentLength(500000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(true)
                .build()
        }
    }
}