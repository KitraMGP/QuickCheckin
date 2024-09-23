package kitra.quickcheckin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kitra.quickcheckin.MainApplication
import kitra.quickcheckin.data.local.datamodel.TeachingClass
import kitra.quickcheckin.viewmodels.ClassManagementViewModel
import kitra.quickcheckin.viewmodels.factory.ClassManagementViewModelFactory

@Composable
fun ComposableClassManagementScreen(navController: NavController) {
    val owner = LocalViewModelStoreOwner.current
    val factory = ClassManagementViewModelFactory(MainApplication.getDatabase(LocalContext.current))
    owner?.let {
        val viewModel =
            viewModel<ClassManagementViewModel>(owner, "ClassManagementViewModel", factory)
        Show(viewModel, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Show(viewModel: ClassManagementViewModel, navController: NavController) {
    val allClasses by viewModel.allClasses.observeAsState(listOf())
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        TopAppBar(
            title = { Text("班级管理") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack("home", false) }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, "返回")
                }
            },
            modifier = Modifier.shadow(elevation = 4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(modifier = Modifier.wrapContentSize(), onClick = { viewModel.addTest("111") }) {
                Text("创建班级")
            }
        }
        HorizontalDivider()
        Text(
            text = "班级列表（点击班级进行操作）",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.95f)
                .align(Alignment.CenterHorizontally)
        ) {

            LazyColumn {
                items(allClasses) { teachingClass ->
                    TeachingClassItem(teachingClass)
                }
            }

        }

    }
}

@Composable
private fun TeachingClassItem(teachingClass: TeachingClass) {
    ListItem(
        headlineContent = { Text(teachingClass.name) },
        supportingContent = { Text("测试 id=" + teachingClass.uniqueId) }
    )
}

@Preview
@Composable
private fun Preview() {
    ComposableClassManagementScreen(rememberNavController())
}


