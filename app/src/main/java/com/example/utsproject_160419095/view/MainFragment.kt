package com.example.utsproject_160419095.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.utsproject_160419095.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCreate.setOnClickListener {
            Navigation.findNavController(it).navigate(MainFragmentDirections.actionToRegisterFragment())
        }

        btnHelp.setOnClickListener {
            Navigation.findNavController(it).navigate(MainFragmentDirections.actionToHelpFragment())
        }
    }
}