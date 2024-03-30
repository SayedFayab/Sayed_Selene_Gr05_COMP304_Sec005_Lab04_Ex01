package com.example.sayed_selene_gr05_comp304_sec005_lab04_ex01



import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.sayed_selene_gr05_comp304_sec005_lab04_ex01.databinding.ActivityLandmarksBinding


class LandmarksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandmarksBinding
    private var selectedLandmark: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandmarksBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val selectedOption = intent.getStringExtra("selectedCategory")

        val layoutContentContainer = binding.landmarksRadioBtnContainer

        val textView = TextView(this)
        textView.text = selectedOption
        textView.textSize = 28f
        textView.setTypeface(null, Typeface.BOLD)
        val textColor = ContextCompat.getColor(this, R.color.teal_200)
        textView.setTextColor(textColor)

        layoutContentContainer.addView(textView)

        val radioGroup = RadioGroup(this)
        radioGroup.layoutParams = RadioGroup.LayoutParams(
            RadioGroup.LayoutParams.MATCH_PARENT,
            RadioGroup.LayoutParams.WRAP_CONTENT,
        )
        (radioGroup.layoutParams as RadioGroup.LayoutParams).setMargins(0,80,0,0)


        if (selectedOption != null) {
            // Show the corresponding fragment based on the selectedOption
            when (selectedOption) {
                "Monuments and Architecture" -> createLayout(radioGroup,R.array.Monument_and_architectures)
                "Parks and Natural environment" -> createLayout(radioGroup,R.array.parks_Natural_environment)
                "Museums and Galleries" -> createLayout(radioGroup,R.array.museums_galleries)
                "Entertainment and Culture" -> createLayout(radioGroup,R.array.entertainment_culture)
                "Shopping Malls" -> createLayout(radioGroup,R.array.shopping_Malls)
            }
        }
        layoutContentContainer.addView(radioGroup)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton != null) {
                selectedLandmark = selectedRadioButton.text.toString()
            }
        }

        binding.btnShowMap.setOnClickListener{
            if(!selectedLandmark.isEmpty()){
                val intent = Intent(this,MapsActivity::class.java)
                intent.putExtra("selectedLandmark",selectedLandmark)
                startActivity(intent)
            }else{
                Toast.makeText(
                    this,
                    "Please select a landmark to visit!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun createLayout(radioGroup: RadioGroup, arrayId: Int) {
        val landmarkNames = binding.root.context.resources.getStringArray(arrayId)
        landmarkNames.forEach { name ->
            val radioButton = RadioButton(this)
            radioButton.text = name
            radioButton.textSize = 16f // Set your desired text size
            radioButton.setTextColor(ContextCompat.getColor(this, R.color.textColor))
            radioButton.setTypeface(null, Typeface.BOLD)
            radioButton.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.skyblue))

            radioGroup.addView(radioButton)

        }
    }

}