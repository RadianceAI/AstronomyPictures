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

            val title = view.findViewById<TextView>(R.id.tv_title)
            val explanation = view.findViewById<TextView>(R.id.tv_explanation)
            val copyright = view.findViewById<TextView>(R.id.tv_copyright)
            val picture = view.findViewById<ImageView>(R.id.iv_picture)

            launch(Dispatchers.Main) {
                title.text = data.title
                explanation.text = data.explanation
                copyright.text = data.copyright

                Glide
                    .with(view)
                    .load((data.source as Image).light)
                    .into(picture)
            }
        }
    }

}