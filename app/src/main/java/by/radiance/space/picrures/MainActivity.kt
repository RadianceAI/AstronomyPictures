package by.radiance.space.picrures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.radiance.space.picrures.ui.AstronomyPictureFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_place_holder, AstronomyPictureFragment.newInstance(), null)
            .commit()
    }
}