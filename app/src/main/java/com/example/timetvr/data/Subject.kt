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

class SubjectDetails() {
    val moreInfo = mapOf(
        1 to listOf(
            "The students are made to\n" +
                "\uF0B7 Understand the basis of linear and softmax regression\n" +
                "\uF0B7 Learn the notions of over fitting, under fitting and regularization techniques.\n" +
                "\uF0B7 Design convolutional neural network\n" +
                "\uF0B7 Handle sequential information\n" +
                "\uF0B7 Study the transformation of an idea to concrete math model and make it work",
            "3",
            "Upon completion of the course, students will be able to\n" +
                "\uF0B7 Train models for multi-category classification.\n" +
                "\uF0B7 Train deep learning models and ensure the gradients are well controlled\n" +
                "\uF0B7 Construct a complex CNN and tune various hyper parameters\n" +
                "\uF0B7 Construct a sequential model which can capture the dependencies for time series data\n" +
                "\uF0B7 Familiar with the encoder-decoder architecture\n"
        ),

        2 to listOf(
            "The students will be made to :\n" +
                "\uF06C Understand the basics of signals and systems\n" +
                "\uF06C Gain an understanding of discrete time signals and systems\n" +
                "\uF06C Understand the various probability distributions\n" +
                "\uF06C Study how to build machine learning models using audio signals\n" +
                "\uF06C Study deep learning model and architectures for computer vision",
            "4",
            "At the end of the course, learners will be able to:\n" +
                "\uF06C Use signals for modelling\n" +
                "\uF06C Know various signal transformations\n" +
                "\uF06C Appreciate necessity of various probability distributions\n" +
                "\uF06C Design models that can process audio signals\n" +
                "\uF06C Use existing architectures and create their own architectures for computer vision"
        ),

        3 to listOf(
            "To understand data warehouse concepts, architecture, business analysis and tools\n" +
                "\uF0B7 To understand data pre-processing and data visualization techniques\n" +
                "\uF0B7 To study algorithms for finding hidden and interesting patterns in data\n" +
                "\uF0B7 To understand and apply various classification and clustering techniques using tools.\n" +
                "\uF0B7 Master data mining techniques in various applications like social, scientific and environmental\n" +
                "context.",
            "4",
            "Students will be able to understand data warehouse concepts, architecture, business analysis\n" +
                "and tools.\n" +
                "\uF0B7 Students will be able to understand data pre- processing and data visualization techniques\n" +
                "\uF0B7 Students will be able to study algorithms for finding hidden and interesting patterns in data\n" +
                "using association algorithms\n" +
                "\uF0B7 Students will be able to apply various classification and clustering techniques using tools\n" +
                "\uF0B7 Students will be mastering the data mining techniques in various applications like social,\n" +
                "scientific and environmental context"
        ),

        4 to listOf(
            "To introduce the terminology, technology and its applications\n" +
                "\uF06C To introduce the concept of M2M (machine to machine) with necessary protocols\n" +
                "\uF06C To introduce the Python Scripting Language which is used in many IoT devices\n" +
                "\uF06C To introduce the Raspberry PI platform, that is widely used in IoT applications.\n" +
                "\uF06C To apply the concept of Internet of Things in the real world scenario\n",
            "3",
            "Analyze various protocols for IoT.\n" +
                "\uF0B7 Develop IoT application using scripting languages\n" +
                "\uF0B7 Design a portable IoT using Raspberry Pi\n" +
                "\uF0B7 Develop web services to access/control IoT devices.\n" +
                "\uF0B7 Analyze applications of IoT in real time scenario" +
                "scientific and environmental context"
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
            "This lab will let the students understand the importance of English towards Technology.",
            "2", "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."
        ),

        9 to listOf(
            "This lab will let the students understand the importance of English towards Technology.",
            "2", "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."
        ),

        10 to listOf(
            "This lab will let the students understand the importance of English towards Technology.",
            "2", "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."
        ),
    )
}
