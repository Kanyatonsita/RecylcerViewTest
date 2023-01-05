package com.example.attendencelist

// Singel tal var ett obejek som det bara kan finas en av och som det alltid fins en av.
object DataManager {
    val students = mutableListOf<Student>()

    init {
        createMockDate()
    }

    fun createMockDate(){
        students.add(Student("David", "APP22", true))
        students.add(Student("Sita", "APP22"))
        students.add(Student("Emil", "APP22"))
        students.add(Student("Sara", "APP22"))
        students.add(Student("Joel", "APP22",true))
        students.add(Student("Simon", "APP22"))
    }
}