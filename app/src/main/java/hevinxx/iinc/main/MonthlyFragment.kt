package hevinxx.iinc.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hevinxx.iinc.databinding.FragmentMonthlyBinding

class MonthlyFragment : Fragment() {

    private lateinit var binding: FragmentMonthlyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMonthlyBinding.inflate(inflater, container, false)
        return binding.root
    }
}