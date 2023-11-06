package com.finale.neulhaerang.ui.app.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.finale.neulhaerang.data.CheckList
import com.finale.neulhaerang.domain.MainScreenViewModel
import com.finale.neulhaerang.ui.theme.Typography


@Composable
fun CheckList() {
    val viewModel = viewModel<MainScreenViewModel>()

    val selectedDate = viewModel.selectedDate
    val routineList = viewModel.routineList
    val todoList = viewModel.todoList

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState(0))
            .padding(16.dp),
    ) {
        Text(text = selectedDate.toString())
        Routine(routineList)
        Spacer(modifier = Modifier.height(16.dp))
        TodoList(todoList)
    }
}


@Composable
fun Routine(routines: List<CheckList>) {
    Text(text = "Routine", style = Typography.bodyLarge)
    Column(
//        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        routines.forEach { item ->
            CheckListItem(item)
        }
    }
}


@Composable
fun TodoList(todolist: List<CheckList>) {
    Text(text = "To do", style = Typography.bodyLarge)
    Column(
//        modifier = Modifier.fillMaxSize()
//        contentPadding = PaddingValues(start = 16.dp, top = 72.dp, end = 16.dp, bottom = 16.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        todolist.forEach { item ->
            CheckListItem(item)
        }
    }
}

@Composable
fun CheckListItem(item: CheckList) {
    var isCompleted by remember { mutableStateOf(item.isCompleted) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isCompleted,
            onCheckedChange = { (!isCompleted).let { isCompleted = it;item.isCompleted = it } },
        )
        Text(
            text = item.content, style = Typography.bodyLarge.merge(
                TextStyle(
                    lineHeight = 30.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.FirstLineTop
                    )
                )
            )
        )
    }

}