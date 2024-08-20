package kitra.quickcheckin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kitra.quickcheckin.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainMenu()
            }
        }
    }
}

@Composable
fun MainMenu() {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Hello!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp, bottom = 30.dp)
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            MenuButton(text = "Button1")
            MenuButton(text = "Button2")
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            MenuButton(text = "Button3")
            MenuButton(text = "Button4")
        }
    }
}

@Composable
fun MenuButton(text: String) {
    Surface(color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(all = 10.dp),
        shape = MaterialTheme.shapes.small) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(all = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    MyApplicationTheme {
        MainMenu()
    }
}