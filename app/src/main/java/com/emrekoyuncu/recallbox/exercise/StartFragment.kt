package com.emrekoyuncu.recallbox.exercise


import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.emrekoyuncu.recallbox.R


class StartFragment : Fragment() {

    lateinit var goButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_start, container, false)

        goButton = v.findViewById(R.id.go_button)

        goButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_exerciseFragment)
        )

        setHasOptionsMenu(true)

        return v
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,requireView().findNavController()) ||
        super.onOptionsItemSelected(item)
    }
}
