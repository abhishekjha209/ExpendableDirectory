package com.abhishekdev.expendabledirectory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishekdev.expendabledirectory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivSlider.setOnClickListener{
            Toast.makeText(this, "This feature isn't implemented yet :(", Toast.LENGTH_SHORT).show()
        }


        //some predefined values:
        val parent1 = Parent(0, "Age Group: 18-28")
        val childItems1 = ArrayList<Child>()
        childItems1.add(
            Child(
                parent1, 0, R.drawable.ic_fou, "Nancy Malkova",
                "Manager", "McKinsey & Rice", 26
            )
        )
        childItems1.add(
            Child(
                parent1, 1, R.drawable.ic_two, "Adam Levine",
                "Product Manager", "HashedIn Ltd.", 28
            )
        )
        parent1.childItems.clear()
        parent1.childItems.addAll(childItems1)

        val parent2 = Parent(1, "Age Group: 29-42")
        val childItems2 = ArrayList<Child>()
        childItems2.add(
            Child(
                parent2, 2, R.drawable.ic_one, "Dwight Scrute",
                "Lead", "Facebook Inc.", 35
            )
        )
        childItems2.add(
            Child(
                parent2, 3, R.drawable.ic_six, "Steve Bale", "R&D Head",
                "Ernst and young", 31
            )
        )
        childItems2.add(
            Child(
                parent2, 4, R.drawable.ic_eight, "Becka Cohen", "UI Engineer",
                "Target Ltd.", 23
            )
        )
        childItems2.add(
            Child(
                parent2, 5, R.drawable.ic_nine, "Russel Crowe", "Marketing Lead",
                "Walmart Services", 41
            )
        )
        parent2.childItems.clear()
        parent2.childItems.addAll(childItems2)

        val parent3 = Parent(2, "Age Group: 43-60")
        val childItems3 = ArrayList<Child>()
        childItems3.add(
            Child(
                parent3, 6, R.drawable.ic_nine, "Russel Crowe", "Marketing Lead",
                "Walmart Services", 44
            )
        )
        childItems3.add(
            Child(
                parent3, 7, R.drawable.ic_fiv, "Christina Applegate",
                "Senior Developer", "Adobe Systems", 48
            )
        )
        childItems3.add(
            Child(
                parent3, 8, R.drawable.ic_elev, "Martin Hozier", "DevOps L1",
                "Marsh McMcmelon", 52
            )
        )
        childItems3.add(
            Child(
                parent3, 9, R.drawable.ic_thr, "Hasan Minaj", "Software Engineer",
                "Trivago", 54
            )
        )
        parent3.childItems.clear()
        parent3.childItems.addAll(childItems3)



        val itemList = ArrayList<Item>()
        itemList.add(parent1)
        itemList.add(parent2)
        itemList.add(parent3)

        Log.d("new", "one ${parent1.childItems},")
        Log.d("new", "two ${parent2.childItems},")
        Log.d("new", "three ${parent3.childItems},")


        binding.list.adapter = OnlyOneOpenedAdapter(this, itemList)
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.setHasFixedSize(true)


    }


}