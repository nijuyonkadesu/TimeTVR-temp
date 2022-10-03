package com.example.timetvr.model

import androidx.lifecycle.ViewModel
import com.example.timetvr.data.Semester
import com.example.timetvr.data.Subject
import com.example.timetvr.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.*

class TimeTableMaker(){
    val subjectsInThisDay = mapOf(
        "Monday" to listOf(5,6,6,6,9,4,2),
        "Tuesday" to listOf(5,2,3,1,4,3,8),
        "Wednesday" to listOf(5,7,7,7,2,8,3),
        "Thursday" to listOf(2,6,6,6,8,3,1),
        "Friday" to listOf(1,4,3,4,4,2,1),
    )
} // TODO: Make time checking logic better

class TimeTableViewModel: ViewModel() {
    // list of all subjects for AI & DS - < Semester 5 >
    val subjects = Semester().loadSubjects()
    val cardSubjects = Semester().loadCardSubjects()
    lateinit var subject: Subject
    private val timeTable = TimeTableMaker().subjectsInThisDay

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow() // Exposing data to UI, & read-only

    fun logic() {
        val hours = SimpleDateFormat("HH", Locale.US)
        val hour: Int = hours.format(Date()).toInt()

        val convertDateTo = SimpleDateFormat("EEEE", Locale.US)
        val theDay: String = convertDateTo.format(Date())


        if (theDay == "Sunday" || theDay == "Saturday") {
            subject = subjects[0]
        }
        else if (hour >= 14 || hour < 7){
            subject = subjects[0]
        }
        else {
            subject = when (hour) {
                7 -> {
                    subjects[timeTable[theDay]!![0]]
                }
                8 -> {
                    subjects[timeTable[theDay]!![1]]
                }
                9 -> {
                    subjects[timeTable[theDay]!![2]]
                }
                10 -> {
                    subjects[timeTable[theDay]!![3]]
                }
                11 -> {
                    subjects[timeTable[theDay]!![4]]
                }
                12 -> {
                    subjects[timeTable[theDay]!![5]]
                }
                13 -> {
                    subjects[timeTable[theDay]!![6]]
                }
                else -> Subject("", "", 1, 0)
            }
        }
        _uiState.value = UiState(
            nextClassCode = subject.subjectCode,
            nextClassTitle = subject.title,
            nextClassInfoCode = subject.infoCode,
            nextImgId = subject.imgId
        )
    }
    init { // runs whenever viewModel is made for the first time, initialization
        logic()
    }
}