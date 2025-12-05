package pl.slaszu.gpwapisignals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import pl.slaszu.gpwapisignals.domain.stock.StockRepository
import pl.slaszu.gpwapisignals.ui.screen.StockListScreen
import pl.slaszu.gpwapisignals.ui.theme.GpwApiSignalsTheme
import pl.slaszu.gpwapisignals.ui.viewmodel.StockListViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var stockRepository: StockRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val stockListViewModel: StockListViewModel by viewModels()
            val stocks by stockListViewModel.stocks.collectAsStateWithLifecycle()

            GpwApiSignalsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StockListScreen(
                        stocks = stocks,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}