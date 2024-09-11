package kitra.quickcheckin.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kitra.quickcheckin.MainApplication
import kitra.quickcheckin.data.local.datamodel.Student
import kitra.quickcheckin.viewmodels.StudentManagementViewModel
import kitra.quickcheckin.viewmodels.factory.StudentManagementViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableStudentManagementScreen(navController: NavController) {
    var showAddStudentDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val viewModel = viewModel(
        modelClass = StudentManagementViewModel::class,
        factory = StudentManagementViewModelFactory(MainApplication.getDatabase(context)),
        key = StudentManagementViewModel::class.simpleName
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = { Text("学生管理") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
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
            Button(onClick = { showAddStudentDialog = true }) {
                Text("创建学生")
            }
        }
        HorizontalDivider()
        Text(
            text = "学生列表（点击可操作）",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        StudentListArea(context, viewModel)
    }

    if (showAddStudentDialog) AddStudentDialog(
        context = context,
        onDismiss = { showAddStudentDialog = false },
        viewModel = viewModel
    )
}

@Preview
@Composable
private fun Preview() {
    ComposableStudentManagementScreen(rememberNavController())
}

/**
 * 整个学生列表区域
 */
@Composable
private fun StudentListArea(context: Context, viewModel: StudentManagementViewModel) {
    val studentList by viewModel.allStudents.observeAsState(listOf())
    LazyColumn {
        items(studentList) { student ->
            StudentListItem(context, viewModel, student)
        }
    }
}

/**
 * 学生列表显示的每一行学生条目
 */
@Composable
private fun StudentListItem(
    context: Context,
    viewModel: StudentManagementViewModel,
    student: Student
) {
    var showDropdownMenu by remember { mutableStateOf(false) }
    ListItem(
        headlineContent = { Text(student.name) },
        supportingContent = { Text(student.number) },
        modifier = Modifier.clickable { showDropdownMenu = true }
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        StudentDropdownMenu(
            context = context,
            viewModel = viewModel,
            expanded = showDropdownMenu,
            onDismiss = { showDropdownMenu = false },
            student = student
        )
    }
}

/**
 * 创建学生的对话框
 */
@Composable
private fun AddStudentDialog(
    context: Context,
    viewModel: StudentManagementViewModel,
    onDismiss: () -> Unit
) {
    var inputStudentName by remember { mutableStateOf("") }
    var inputStudentNumber by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                if (inputStudentName.isEmpty() || inputStudentNumber.isEmpty()) {
                    Toast.makeText(context, "请输入姓名和学号", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.addStudent(
                        { Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show() },
                        { err ->
                            Toast.makeText(context, err.localizedMessage, Toast.LENGTH_SHORT).show()
                        },
                        Student(0, inputStudentNumber, inputStudentName)
                    )
                    onDismiss.invoke()
                }
            }) {
                Text("确定")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("取消")
            }
        },
        title = { Text("请输入学生信息") },
        text = {
            Column {
                TextField(
                    value = inputStudentName,
                    onValueChange = { inputStudentName = it },
                    singleLine = true,
                    placeholder = { Text("姓名") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = inputStudentNumber,
                    onValueChange = { inputStudentNumber = it.replace(Regex("[^0-9]+"), "") },
                    singleLine = true,
                    placeholder = { Text("学号") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            }
        }
    )
}

/**
 * 点击一个学生条目后显示的下拉菜单
 */
@Composable
private fun StudentDropdownMenu(
    context: Context,
    viewModel: StudentManagementViewModel,
    expanded: Boolean,
    onDismiss: () -> Unit,
    student: Student
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .wrapContentSize()
    ) {
        DropdownMenuItem(text = { Text("删除学生") }, onClick = {
            viewModel.deleteStudent(
                { Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show() },
                { err -> Toast.makeText(context, err.localizedMessage, Toast.LENGTH_SHORT).show() },
                student
            )
            onDismiss.invoke()
        })
    }
}