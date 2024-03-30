package com.example.sayed_selene_gr05_comp304_sec005_lab04_ex01


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.sayed_selene_gr05_comp304_sec005_lab04_ex01.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var selectedRadioButtonText:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.radioGroupLandMarkCategories.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton != null) {
                selectedRadioButtonText = selectedRadioButton.text.toString()
            }
        }

        binding.nextBtn.setOnClickListener(View.OnClickListener {

            if (!selectedRadioButtonText.isEmpty()) {
                val intent = Intent(this,LandmarksActivity::class.java)
                intent.putExtra("selectedCategory",selectedRadioButtonText)
                Log.d("INTENT_IN_MAIN",selectedRadioButtonText)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Please Select a category to visit!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


    }

}