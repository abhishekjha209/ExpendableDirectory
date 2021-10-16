package com.abhishekdev.expendabledirectory

interface Item {
    fun getItemType(): Int
}

const val PARENT = 0
const val CHILD = 1

data class Parent(val id: Long, val ageGroup:String="Not defined") : Item {
    val childItems = ArrayList<Child>()
    var isExpanded = false
//    var selectedChild: Child? = null

    override fun getItemType() = PARENT
}

data class Child(
    val parent: Parent,
    val id: Long,
    var imageResource: Int,
    var name: String,
    var designation: String,
    var company: String,
    var age: Int,
    var price: Int = 0) : Item {

    override fun getItemType() = CHILD

    fun statement() = "${designation} at ${company}"
}
