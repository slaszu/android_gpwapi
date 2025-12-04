package pl.slaszu.gpwapisignals.infrastructure.gpwapi

import pl.slaszu.gpwapisignals.domain.stock.Stock
import pl.slaszu.gpwapisignals.domain.stock.StockRepository
import javax.inject.Inject

class GpwApiStockRepository @Inject constructor(
    private val gpwApiRetrofit: GpwApiRetrofit
) : StockRepository {
    override suspend fun getAll(): List<Stock> {
        return gpwApiRetrofit.getStocks()
    }
}