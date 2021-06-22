package by.radiance.space.picrures.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.radiance.space.picrures.R
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.usecase.today.TodayAstronomyPictureUseCase
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.astronomy_picture_fragment.*
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AstronomyPictureFragment : Fragment(), KoinComponent {

    companion object {
        fun newInstance() = AstronomyPictureFragment()
    }

    private lateinit var viewModel: AstronomyPictureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.astronomy_picture_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todayAstronomyPictureUseCase: TodayAstronomyPictureUseCase = get()


        GlobalScope.launch(Dispatchers.IO) {
            val data = todayAstronomyPictureUseCase.get()

            launch(Dispatchers.Main) {
                tv_title.text = data.title
                tv_explanation.text = data.explanation
                tv_copyright.text = data.copyright

                Glide
                    .with(view)
                    .load((data.source as Image).light)
                    .into(iv_picture)
            }
        }
    }

}