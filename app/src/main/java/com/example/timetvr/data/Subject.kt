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
            Subject("AD18511/12", "DL/IoT Laboratory",6),
            Subject("HS18561", "Interview and Career Skills Laboratory", 7),
            Subject("MC18001", "Indian Constitution and Society", 8),
            Subject("NoCode", "SEMINAR - I", 9),
            Subject("NoCode", "SEMINAR - II", 10),
        )
    }
}

class SubjectDetails() {
    val moreInfo = mapOf(
        1 to listOf(
            "Deep learning uses artificial neural networks to perform sophisticated computations on large amounts of data. It is a type of machine learning that works based on the structure and function of the human brain.",
            "3",
            "The students are expected to understand all concepts of Deep Learning and it's algorithms in detail."
        ),

        2 to listOf(
            "Digital signal processing is the use of digital processing, such as by computers or more specialized digital signal processors, to perform a wide variety of signal processing operations.",
            "4",
            "The students are expected to understand all the concepts of Systems and Signals, and the transformation and processing of the same."
        ),

        3 to listOf(
            "Data mining is the process of extracting and discovering patterns in large data sets involving methods at the intersection of machine learning, statistics, and database systems.",
            "4",
            "The students are expected to understand all forms of editing and performing analysis on data. As a specific part of ML, the concepts go deep into the analysis."
        ),

        4 to listOf(
            "The Internet of things describes physical objects with sensors, processing ability, software, and other technologies that connect and exchange data with other devices and systems over the Internet or other communications networks. ",
            "3",
            "The students are expected to understand the workings of whatever they've learnt, actually implemented in projects as such. There's also a laboratory for the same."
        ),
        5 to listOf(
            "This course will let the students understand the importance of English towards Technology.",
            "3",
            "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."
        ),

        6 to listOf(
            "These Labs will help the students get ready with learning AI&DS and the processing of Data, and management of the same. The students are requested to follow the lab procedures and protocol.",
            "2",
            "The labs will train the average student into corporate-AI ready fellows."
        ),

        7 to listOf(
            "This lab will let the students understand the importance of English towards Technology.",
            "2",
            "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."
        ),

        8 to listOf(
            "This course will help understand the cause of the Indian Society and what happens everywhere.",
            "2", "Students, by the end of this course will be understanding the legislature, and have certain knowledge to be a model Indian."
        ),

        9 to listOf(
            "Grow your communication and speaking skills, by giving a Seminar! Try your best to represent yourself to the people!",
            "-", "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."
        ),

        10 to listOf(
            "Grow your communication and speaking skills, by giving a Seminar! Try your best to represent yourself to the people!",
            "-", "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."
        ),
    )
}
