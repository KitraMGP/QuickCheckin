package kitra.quickcheckin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrepareScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = { Text("请确认签到信息") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack(route = "home", false)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "菜单"
                    )
                }
            },
            modifier = Modifier.shadow(elevation = 4.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            ListItem(
                headlineContent = { Text("签到班级") },
                supportingContent = { Text("计科四班") }
            )
            ListItem(
                headlineContent = { Text("班级人数") },
                supportingContent = { Text("34人") }
            )
        }
        HorizontalDivider()
        Text(
            text = "签到学生列表",
            modifier = Modifier.padding(16.dp, 8.dp)
        )

        // TODO 学生列表

        Spacer(modifier = Modifier.height(100.dp))

    }

    Box(modifier = Modifier.fillMaxSize()) {
        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.Check, "确认") },
            text = { Text("开始签到") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 50.dp)
        )
    }

}

@Preview
@Composable
private fun PreviewPrepareScreen() {
    PrepareScreen(rememberNavController())
}
