package ikirhmn.example.tugaslogin

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_regis,
            R.string.tab_login
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val tabs: TabLayout = findViewById(R.id.tabs)
        viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()

    }
}