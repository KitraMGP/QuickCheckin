package kitra.quickcheckin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kitra.quickcheckin.themes.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableHomeScreen(navController: NavController) {

    var menuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
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
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "更多选项"
                    )
                }
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                    modifier = Modifier.width(IntrinsicSize.Min),

                    ) {
                    DropdownMenuItem(
                        text = { Text("班级管理") },
                        onClick = { navController.navigate("class_management") })
                    DropdownMenuItem(text = { Text("学生管理") }, onClick = { /*TODO*/ })
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

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MyApplicationTheme {
        ComposableHomeScreen(rememberNavController())
    }
}