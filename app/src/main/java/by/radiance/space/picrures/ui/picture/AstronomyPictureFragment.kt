package by.radiance.space.picrures.ui.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.radiance.space.picrures.R
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.PictureId
import by.radiance.space.pictures.domain.usecase.today.TodayAstronomyPictureUseCase
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.astronomy_picture_fragment.*
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.android.viewmodel.ext.android.viewModel

class AstronomyPictureFragment : Fragment(), KoinComponent {

    private val pictureViewModel: AstronomyPictureViewModel by viewModel()
    val args: AstronomyPictureFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.astronomy_picture_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModels()
        observeViewModels()

        tv_collection.setOnClickListener {
            findNavController().navigate(AstronomyPictureFragmentDirections.actionAstronomyPictureFragmentToAstronomyPictureListFragment())
        }
        iv_is_saved.setOnClickListener {
            pictureViewModel.onSaveClicked()
        }
    }

    private fun initViewModels() {
        pictureViewModel.setPicture(args.id?: PictureId.today)
    }

    private fun observeViewModels() {
        pictureViewModel.picture.observe(viewLifecycleOwner) { picture ->
            view?.let { view ->
                Glide
                    .with(view)
                    .load((picture.source as Image).huge)
                    .into(iv_picture)
            }

            iv_is_saved.setImageResource(if (picture.isSaved) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_24 )
        }
    }
}