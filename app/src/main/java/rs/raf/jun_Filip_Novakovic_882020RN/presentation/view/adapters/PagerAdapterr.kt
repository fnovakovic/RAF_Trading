package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.jun_Filip_Novakovic_882020RN.R
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.fragments.*

class PagerAdapterr(
    fragmentManager: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 2
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_1 -> NewsFragment()
            else -> StocksFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> context.getString(R.string.news)
            else -> context.getString(R.string.stocks)
        }
    }

}