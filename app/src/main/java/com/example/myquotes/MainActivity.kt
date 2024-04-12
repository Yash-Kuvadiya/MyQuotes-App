package com.example.myquotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Applier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myquotes.screens.QuoteDetail
import com.example.myquotes.screens.QuoteListScreen
import com.example.myquotes.ui.theme.MyQuotesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            DataMAnager.loadAssetsFromFile(applicationContext)
        }

        setContent {
                App()
        }
    }
}

@Composable
fun App() {
        if(DataMAnager.isDataLoaded.value){

            if (DataMAnager.currentPage.value == Pages.LISTING){
                QuoteListScreen(data = DataMAnager.data) {
                            DataMAnager.switchPages(it)
                }
            }
            else{
                DataMAnager.currentQuote?.let { QuoteDetail(quote = it) }
            }


        }
}

enum class Pages{
    LISTING,
    DETAIL
}
