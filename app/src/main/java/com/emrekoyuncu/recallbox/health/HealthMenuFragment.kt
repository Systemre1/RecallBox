package com.emrekoyuncu.recallbox.health

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.emrekoyuncu.recallbox.R

class HealthMenuFragment : Fragment() {

    lateinit var bmiButton: Button
    lateinit var waterButton: Button
    lateinit var calorieButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_health_menu, container, false)

        bmiButton = v.findViewById(R.id.bmi_button)
        waterButton = v.findViewById(R.id.water_button)
        calorieButton = v.findViewById(R.id.calorie_button)

        bmiButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_nav_health_to_bmiFragment)
        )

        waterButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_nav_health_to_waterFragment)
        )

        calorieButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_nav_health_to_calorieFragment)
        )

        setHasOptionsMenu(true)

        return v
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,requireView().findNavController()) ||
                super.onOptionsItemSelected(item)
    }
}