package com.example.timetvr.data

import com.example.timetvr.R

data class Subject (
    val subjectCode: String,
    val title: String,
    val infoCode: Int,
    val imgId: Int
    )

class Semester() {
    fun loadSubjects(): List<Subject>{
        return listOf(
            Subject("", "Relax, You have no other classes Today!", 0,0),
            Subject("IT18501", "Data Communications and Networks", 1, R.drawable.networks),
            Subject("CS18501", "User Interface Technologies", 2, R.drawable.uit_therory),
            Subject("IT18502", "Mobile Computing", 3, R.drawable.mobile),
            Subject("IT18503", "Internet Of Things", 4, R.drawable.iot_theory),
            Subject("IT18005/21", "FOSS/Machine Learning", 5, R.drawable.foss_ml),
            Subject("MC18001", "Indian Constitution and Society",6, R.drawable.ind),
            Subject("CS18511/IT18512", "UIT/IOT Laboratory", 7, R.drawable.uit_lab),
            Subject("IT18512/IT18511", "IOT/Data Communications and Networks Laboratory", 8, R.drawable.networks_lab),
            Subject("MISC", "Placement Training", 9, R.drawable.placement),
        )
    }
}

class SubjectDetails() {
    val moreInfo = mapOf(
        1 to listOf(
            "Data Communications and Networks course enables one to truly understand routing & address schemes techniques, functions of various network layers enabling us to unite huge amount of devices under a stable network",
            "4",
            "The students are now capable of tracing packets & debugging errors in network layers, find optimal path using routing scheme and analise network using virtualization."
        ),

        2 to listOf(
            "User Interface is the medium through with computers communicate with us, here we build static to dynamic web pages, incorporating advanced features of JavaScript, NODE.js, and peak interactive pages using a database such as MongoDB all hosted remotely.",
            "4",
            "The students are now capable of crafting their own website with functionalities using client & server side scripting. Knowing the importance of CRUD and able to connect with database technology."
        ),

        3 to listOf(
            "Mobile Computing course describes the underlying process required to establish a connection with our smartphones, all while explaining the evolution of various generation from wired communication technologies to modern day multimedia streaming.",
            "4",
            "The students can now explain the theories of mobile computing technologies, describing infrastructure, and make mobile focused applications to offer public & business services."
        ),

        4 to listOf(
            "Internet of Things help students understand state of the art cloud integration and apply concepts of IOT in real world scenarios.",
            "4",
            "Students, by the end of this course will be able to bring out the vision of IOT from a global context, manage data, network gateways and build architectures for IOT."
        ),
        5 to listOf(
            "These courses help understand visions of FOSS and promote sustainable software development. On the other hand learning ML algorithms & trends to craft and bring imagination into reality to solve the world problems.",
            "3",
            "Helps students to develop scripting for automation. Analyse and suggest appropriate machine learning approaches for various cases of problem using decision trees and overcome overfitting issues.",
        ),

        6 to listOf(
            "This course will help understand the cause of the Indian Society and what happens everywhere",
            "0",
            "Students, by the end of this course will be understanding the legislature, and have certain knowledge to be a model Indian."
        ),

        7 to listOf(
            "These labs will let the students understand the importance of User Interface, fetching data from various sources. Implement python programs on Raspberry pie to integrate servers with targeted functionality.",
            "2",
            "Students, by the end of this laboratory will be ABLE to use their technical knowledge, and to build simple IoT applications to perform the predictive analysis on gathered data."
        ),

        8 to listOf(
            "These labs will let the students learn socket programming and hands-on experience on network protocols. Implement python programs on Raspberry pie to integrate servers with targeted functionality.",
            "2",
            "Students can implement various network protocols, analyse performance of various routing algorithm. To build simple IoT applications to perform the predictive analysis on gathered data"
        ),

        9 to listOf(
            "To make students aware of the necessities and the actions needed to find a job before graduation",
            "999",
            "Students are expected to improve ability to provide reasoning and leverage problem solving skills"
        ),
    )
}
