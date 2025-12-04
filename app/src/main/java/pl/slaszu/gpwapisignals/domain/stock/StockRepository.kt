package pl.slaszu.gpwapisignals.domain.stock

interface StockRepository {
    suspend fun getAll(): List<Stock>
}