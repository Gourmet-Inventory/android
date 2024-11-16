package com.example.gourmet_inventory_mobile.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
// [START android_compose_components_datepicker_modal]
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(
            modifier = Modifier.background(White).border(1.dp, GI_AzulMarinho, RoundedCornerShape(4.dp)),
            state = datePickerState,
            dateFormatter = DatePickerDefaults.dateFormatter("dd/MM/yyyy"),
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = GI_AzulMarinho,

            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DatePickerModalPreview(modifier: Modifier = Modifier) {
    DatePickerModal(
        onDateSelected = { },
        onDismiss = { }
    )
}
// [END android_compose_components_datepicker_modal]

@OptIn(ExperimentalMaterial3Api::class)
// [START android_compose_components_datepicker_inputmodal]
@Composable
fun DatePickerModalInput(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Preview(showSystemUi = true)
@Composable
fun DatePickerModalInputPreview(modifier: Modifier = Modifier) {
    DatePickerModalInput(
        onDateSelected = { },
        onDismiss = { }
    )
}
// [END android_compose_components_datepicker_inputmodal]

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var displayedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = displayedDate,
            onValueChange = { },
            label = { Text("Data Cadastro") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
//                        .background(MaterialTheme.colorScheme.surface)
//                        .padding(16.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.height(900.dp),
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = {
                                    showDatePicker = false
                                    displayedDate = ""
                                },
                                modifier = Modifier
                            ) {
                                Text("Close")
                            }
                            TextButton(
                                onClick = {
                                    showDatePicker = false
                                    datePickerState.selectedDateMillis?.let {
                                        displayedDate = convertMillisToDate(it)
                                    }
                                },
                                modifier = Modifier
                            ) {
                                Text("OK")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DatePickerDockedPreview(modifier: Modifier = Modifier) {
    DatePickerDocked()
}

@Composable
fun DatePickerFieldToModal(modifier: Modifier = Modifier) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showModal by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedDate?.let { convertMillisToDate(it) } ?: "",
        onValueChange = { },
//        label = { Text("DOB") },
        placeholder = { Text("DD/MM/YYYY") },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { showModal = true }) {
                Icon(Icons.Default.DateRange, contentDescription = "Select date")
            }
        },
        modifier = modifier
            .fillMaxWidth(),
        )

    if (showModal) {
        DatePickerModal(
            onDateSelected = { selectedDate = it },
            onDismiss = { showModal = false }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DatePickerFieldToModalPreview(modifier: Modifier = Modifier) {
    DatePickerFieldToModal()
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}