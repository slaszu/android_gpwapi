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

@Composable
fun StockListScreen(
    stocks: List<Stock>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            state = rememberTextFieldState(),
            label = { Text("Label") }
        )

        LazyColumn {
            items(stocks) { stock ->
                Text(stock.name)
            }
        }
    }
}
