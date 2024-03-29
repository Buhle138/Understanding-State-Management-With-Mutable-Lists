package com.example.dynamiccontentexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dynamiccontentexample.ui.theme.DynamicContentExampleTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicContentExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = MainViewModel()){
    //Converting live data into a state.
val newNameStateContent = viewModel.textFieldState.observeAsState("")


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(newNameStateContent.value){
                newName -> viewModel.onTextChanged(newName)
        }
    }

}


@Composable
fun GreetingList(
                 textFieldValue: String,
                 textFieldUpdate: (newName: String) -> Unit
                 ) {


    TextField(value = textFieldValue, onValueChange = textFieldUpdate)
    
    Button( onClick = {} ) {
        Text("Add new name")
    }
}

@Composable
fun Greeting(name: String) {

    Text(
        text = "Hello $name!"
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DynamicContentExampleTheme {
       MainScreen()
    }
}