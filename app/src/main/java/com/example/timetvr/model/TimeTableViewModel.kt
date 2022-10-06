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
        "Monday" to listOf(5,2,1,4,7),
        "Tuesday" to listOf(5,6,1,3,8),
        "Wednesday" to listOf(5,3,2,4,6,9),
        "Thursday" to listOf(2,1,3,4,2,1,3),
        "Friday" to listOf(5,8,3,4,2),
    )
}

class TimeTableViewModel: ViewModel() {
    // list of all subjects for IT - < Semester 5 >
    val subjects = Semester().loadSubjects()
    lateinit var subject: Subject
    private val timeTable = TimeTableMaker().subjectsInThisDay

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow() // Exposing data to UI, & read-only

    private fun valDate(d1: String, d2: String, cd: String): Boolean{
        val sdf = SimpleDateFormat("HH:mm", Locale.US)

        val firstDate = sdf.parse(d1) as Date
        val currentDate = sdf.parse(cd) as Date
        val secondDate = sdf.parse(d2) as Date

        if(currentDate in firstDate..secondDate){
            return true
        }
        return false
    }
    fun logic() {
        val hours = SimpleDateFormat("HH", Locale.US)
        val hour: Int = hours.format(Date()).toInt()

        val hoursMinutes = SimpleDateFormat("HH:mm", Locale.US)
        val hourMinute: String = hoursMinutes.format(Date()).toString()

        val convertDateTo = SimpleDateFormat("EEEE", Locale.US)
        val theDay: String = convertDateTo.format(Date())


        if (theDay == "Sunday" || theDay == "Saturday") {
            subject = subjects[0]
        }
        else if (hour >= 14 || hour < 7){
            subject = subjects[0]
        }
        else {
            subject = when {
                hour == 7 -> {
                    subjects[timeTable[theDay]!![0]]
                }
                valDate("08:30", "09:20", hourMinute) -> {
                    subjects[timeTable[theDay]!![1]]
                }
                valDate("09:21", "10:25", hourMinute) -> {
                    subjects[timeTable[theDay]!![2]]
                }
                valDate("10:26", "11:15", hourMinute) -> {
                    subjects[timeTable[theDay]!![3]]
                }
                valDate("11:16", "12:45", hourMinute) -> {
                    subjects[timeTable[theDay]!![4]]
                }
                valDate("12:46", "13:35", hourMinute) -> {
                    subjects[timeTable[theDay]!![5]]
                }
                valDate("13:36", "14:25", hourMinute) -> {
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