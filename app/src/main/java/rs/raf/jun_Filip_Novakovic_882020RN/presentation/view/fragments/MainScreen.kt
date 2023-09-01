package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rs.raf.jun_Filip_Novakovic_882020RN.R
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.MainMainScreenBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.adapters.PagerAdapterr
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.adapter.StockAdapter

class MainScreen : Fragment(R.layout.main_main_screen) {

    private var _binding: MainMainScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()

    }

    private fun initUi() {
        initRecycler()

    }

    private fun initRecycler() {

        binding.viewPager.adapter =
            context?.let {
                PagerAdapterr(
                    childFragmentManager,
                    it
                )
            }
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}