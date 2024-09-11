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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kitra.quickcheckin.MainApplication
import kitra.quickcheckin.themes.MyApplicationTheme
import kitra.quickcheckin.viewmodels.HomeScreenViewModel
import kitra.quickcheckin.viewmodels.factory.HomeScreenViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableHomeScreen(navController: NavController) {

    var dropDownMenuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val factory = HomeScreenViewModelFactory(MainApplication.getDatabase(context))
    val viewModel = viewModel(
        modelClass = HomeScreenViewModel::class,
        key = HomeScreenViewModel::class.simpleName,
        factory = factory
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 标题栏
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
                IconButton(onClick = { dropDownMenuExpanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "更多选项"
                    )
                }
                DropdownMenu(
                    expanded = dropDownMenuExpanded,
                    onDismissRequest = { dropDownMenuExpanded = false },
                    modifier = Modifier.width(IntrinsicSize.Min)
                    ) {
                    DropdownMenuItem(
                        text = { Text("班级管理") },
                        onClick = { navController.navigate("class_management") })
                    DropdownMenuItem(
                        text = { Text("学生管理") },
                        onClick = { navController.navigate("student_management") }
                    )
                }
            },
            modifier = Modifier.shadow(elevation = 4.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ClassListArea(viewModel, navController)
    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        ComposableHomeScreen(rememberNavController())
    }
}

@Composable
private fun ClassListArea(viewModel: HomeScreenViewModel, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        ClassListItem(viewModel, navController)
    }
}

@Composable
private fun ClassListItem(viewModel: HomeScreenViewModel, navController: NavController) {
    val studentCount = viewModel.studentCount.observeAsState()

    Card(
        modifier = Modifier.fillMaxWidth(0.85f), colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        onClick = { navController.navigate("prepare") },
        shape = RoundedCornerShape(20)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "默认班级",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${studentCount.value}人",
                fontSize = 16.sp
            )
        }
    }
}