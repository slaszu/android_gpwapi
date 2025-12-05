package pl.slaszu.gpwapisignals.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.slaszu.gpwapisignals.domain.stock.Stock
import pl.slaszu.gpwapisignals.domain.stock.StockRepository

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {
    private val _stocks = MutableStateFlow<StockListResult>(StockListResult.StockListLoading())
    val stocks: StateFlow<StockListResult> = _stocks.asStateFlow()

    init {
        this.viewModelScope.launch {
            try {
                _stocks.value = StockListResult.StockListSuccess(stockRepository.getAll())
            } catch (e: Exception) {
                _stocks.value = StockListResult.StockListError("Error: {$e.message}")
            }
        }
    }
}

sealed interface StockListResult {
    class StockListSuccess(val stocks: List<Stock>) : StockListResult
    class StockListError(val message: String) : StockListResult
    class StockListLoading : StockListResult
}
