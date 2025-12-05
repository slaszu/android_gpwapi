package pl.slaszu.gpwapisignals.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.slaszu.gpwapisignals.domain.stock.Stock
import pl.slaszu.gpwapisignals.ui.viewmodel.StockListResult

@Composable
fun StockListScreen(
    stocks: StockListResult,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            state = rememberTextFieldState(),
            label = { Text("Label") }
        )

        when(stocks) {
            is StockListResult.StockListError -> Text(stocks.message)
            is StockListResult.StockListLoading -> Text("Loading")
            is StockListResult.StockListSuccess -> StockList(stocks.stocks)
        }

    }
}

@Composable
private fun StockList(
    stocks: List<Stock>
) {
    LazyColumn {
        items(stocks) { stock ->
            Text(stock.name)
        }
    }
}
