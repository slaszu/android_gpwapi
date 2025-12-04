package pl.slaszu.gpwapisignals.infrastructure.gpwapi

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.slaszu.gpwapisignals.domain.stock.StockRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GpwApiModule {

    @Provides
    @Singleton
    fun provideGpwAp(): GpwApiRetrofit {
        return Retrofit.Builder()
            .baseUrl("https://gpw.g-sitemap-generator.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GpwApiRetrofit::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsModule {

    @Binds
    abstract fun bindStockRepository(
        stockRepositoryImpl: GpwApiStockRepository
    ): StockRepository
}