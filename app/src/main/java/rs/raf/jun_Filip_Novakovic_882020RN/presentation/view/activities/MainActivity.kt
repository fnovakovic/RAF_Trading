package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import rs.raf.jun_Filip_Novakovic_882020RN.R
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.ActivityMainBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.adapters.MainPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initViewPager()
        initNavigation()
    }


    private fun initViewPager() {

        binding.viewPager.adapter = MainPagerAdapter(supportFragmentManager,this)
    }

    private fun initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_1 -> binding.viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT_1, false)
                R.id.nav_2 -> binding.viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT_2, false)
                else -> binding.viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT_3, false)

            }
            true
        }
    }
}
