package com.example.timetvr.model

import androidx.lifecycle.ViewModel
import com.example.timetvr.data.Semester
import com.example.timetvr.data.Subject
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
} // TODO: Have to think how to use this to generate timetable automatically, 7 a day..

class TimeTableViewModel: ViewModel() {
    // list of all subjects for AI & DS - < Semester 5 >
    private val subjects = Semester().loadSubjects()
    private val timeTable = TimeTableMaker().subjectsInThisDay

    fun logic(): Subject {
        val hours = SimpleDateFormat("HH", Locale.US)
        val hour: Int = hours.format(Date()).toInt()

        val minutes = SimpleDateFormat("mm", Locale.US)
        val minute: Int = minutes.format(Date()).toInt()

        val hourMinute: String = hours.format(Date())+":"+minutes.format(Date())

        val convertDateTo = SimpleDateFormat("EEEE", Locale.US)
        val theDay: String = convertDateTo.format(Date())

        var hasClass = true

        if (theDay == "Sunday" || theDay == "Saturday") {
            hasClass = false
            return subjects[0]
        }
        else if (hour >= 14 || hour < 7){
            hasClass = false
            return subjects[0]
        }
        else{
            when (theDay) {
                "Monday" -> { // 5 6 6 6 9 4 2
                    when (hour) {
                        7 -> {
                            return subjects[timeTable["Monday"]!![0]]
                        }
                        8 -> {
                            return subjects[timeTable["Monday"]!![1]]
                        }
                        9 -> {
                            return subjects[timeTable["Monday"]!![2]]
                        }
                        10 -> {
                            return subjects[timeTable["Monday"]!![3]]
                        }
                        11 -> {
                            return subjects[timeTable["Monday"]!![4]]
                        }
                        12 -> {
                            return subjects[timeTable["Monday"]!![5]]
                        }
                        13 -> {
                            return subjects[timeTable["Monday"]!![6]]
                        }
                    }
                }
                "Tuesday" -> { // 5 2 3 1 4 3 8
                    if (hour == 7) {
                        return subjects[5]
                    }
                    else if (hour == 8) {
                        return subjects[2]
                        //return listOf("AD18511/12", "DL Laboratory/ IoT Laboratory", 1)
                    }
                    else if (hour == 9) {
                        return subjects[3]
                    }
                    else if (hour == 10 ) {
                        //return listOf("AD18511/12", "DL Laboratory/ IoT Laboratory", 1)
                        return subjects[1]
                    }
                    else if (hour == 11) {
                        //return listOf("", "SEMINAR - I", 1)
                        return subjects[4]
                    }
                    else if (hour == 12) {
                        //return listOf("AD18503", "Internet Of Things towards Data Science", 1)
                        return subjects[3]
                    }
                    else if (hour == 13) {
                        return subjects[8]
                    }
                }
                "Wednesday" -> { // 5 7 7 7 2 8 3
                    if (hour == 7) {
                        return subjects[5]
                    }
                    else if (hour == 8) {
                        return subjects[7]
                    }
                    else if (hour == 9) {
                        return subjects[7]
                    }
                    else if (hour == 10 ) {
                        return subjects[7]
                    }
                    else if (hour == 11) {
                        return subjects[2]
                    }
                    else if (hour == 12) {
                        return subjects[8]
                    }
                    else if (hour == 13) {
                        return subjects[3]
                    }
                }
                "Thursday" -> { // 2 6 6 6 8 3 1
                    if (hour == 7) {
                        return subjects[2]
                    }
                    else if (hour == 8) {
                        return subjects[6]
                    }
                    else if (hour == 9) {
                        return subjects[6]
                    }
                    else if (hour == 10 ) {
                        return subjects[6]
                    }
                    else if (hour == 11) {
                        return subjects[8]
                    }
                    else if (hour == 12) {
                        return subjects[3]
                    }
                    else if (hour == 13) {
                        return subjects[1]
                    }
                }
                "Friday" -> { // 1 4 3 4 4 2 1
                    if (hour == 7) {
                        return subjects[1]
                    }
                    else if (hour == 8) {
                        return subjects[4]
                    }
                    else if (hour == 9) {
                        return subjects[3]
                    }
                    else if (hour == 10 ) {
                        return subjects[4]
                    }
                    else if (hour == 11) {
                        return subjects[2]
                    }
                    else if (hour == 12) {
                        return subjects[1]
                    }
                    else if (hour == 13) {
                        return subjects[10]
                    }
                }
            }
        }
        return Subject("", "", 1) // TODO: make meaningful
    }

}