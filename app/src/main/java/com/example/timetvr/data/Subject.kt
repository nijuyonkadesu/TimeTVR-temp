package com.example.timetvr.data

data class Subject (
    val subjectCode: String,
    val title: String,
    val infoCode: Int
    )

class Semester() {
    fun loadSubjects(): List<Subject>{
        return listOf(
            Subject("", "Relax, You have no other classes Today!", 0),
            Subject("AD18501", "Deep Learning Algorithms and Architectures", 1),
            Subject("AD18502", "Digital Signal Processing for Data Science", 2),
            Subject("CS18502", "Data Mining and Data Warehousing", 3),
            Subject("AD18503", "Internet Of Things towards Data Science", 4),
            Subject("OH18007", "English for Technologists", 5),
            Subject("AD18511/12", "DL Laboratory/ IoT Laboratory",6),
            Subject("HS18561", "Interview and Career Skills Laboratory", 7),
            Subject("MC18001", "Indian Constitution and Society", 8),
            Subject("NoCode", "SEMINAR - I", 9),
            Subject("NoCode", "SEMINAR - II", 10),
        )
    }
}
