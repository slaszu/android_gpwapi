package pl.slaszu.gpwapisignals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pl.slaszu.gpwapisignals.domain.stock.Stock
import pl.slaszu.gpwapisignals.domain.stock.StockRepository
import pl.slaszu.gpwapisignals.ui.screen.StockListScreen
import pl.slaszu.gpwapisignals.ui.theme.GpwApiSignalsTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var stockRepository: StockRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var stocks by remember { mutableStateOf(emptyList<Stock>()) }

            LaunchedEffect(true) {
                lifecycleScope.launch {
                    stocks = stockRepository.getAll()
                }
            }

            GpwApiSignalsTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    StockListScreen(
                        stocks = stocks,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}