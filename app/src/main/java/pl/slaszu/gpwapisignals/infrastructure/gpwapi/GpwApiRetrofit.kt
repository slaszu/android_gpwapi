package pl.slaszu.gpwapisignals.infrastructure.gpwapi

import pl.slaszu.gpwapisignals.domain.stock.Stock
import retrofit2.http.GET

interface GpwApiRetrofit {
    @GET("stocks")
    suspend fun getStocks(): List<Stock>
}