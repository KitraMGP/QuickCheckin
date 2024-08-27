package kitra.quickcheckin.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kitra.quickcheckin.themes.MyApplicationTheme

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                ShowHomeScreen()
            }
        }
    }
}

@Composable
fun ShowHomeScreen() {
    ComposableHomeScreen(rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableHomeScreen(navController: NavController) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        TopAppBar(
            title = { Text("请选择签到班级") },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "菜单"
                    )
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "更多选项"
                    )
                }
            },
            modifier = Modifier.shadow(elevation = 4.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Card(
                onClick = {
                    navController.navigate("prepare")
                },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            {
                Text(
                    text = "数据结构-计科[1-4]班",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    ),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "34人",
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 0.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                )
            }
        }


    }
}

@Composable
fun LargeMenuButton(text: String) {
    /*Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .padding(all = 10.dp)
            .size(180.dp, 150.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(all = 10.dp),
            textAlign = TextAlign.Center
        )
    }*/
}

@Composable
fun MenuButton(text: String) {
    /*Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .padding(all = 10.dp)
            .size(180.dp, 52.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(all = 10.dp),
            textAlign = TextAlign.Center
        )
    }*/
    Button(onClick = { /*TODO*/ }) {
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyApplicationTheme {
        ShowHomeScreen()
    }
}