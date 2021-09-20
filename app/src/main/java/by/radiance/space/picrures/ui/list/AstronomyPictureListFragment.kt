package by.radiance.space.picrures.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.radiance.space.picrures.R
import by.radiance.space.picrures.ui.list.recycler.AstronomyPictureAdapter
import by.radiance.space.picrures.ui.list.recycler.AstronomyPictureAdapter.Companion.PICTURE
import by.radiance.space.picrures.ui.list.recycler.AstronomyPictureAdapter.Companion.TODAY_PICTURE
import by.radiance.space.picrures.ui.list.viewModel.PicturesListViewModel
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.presenter.PictureListViewModel
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.presenter.state.PicturesListUiState
import kotlinx.android.synthetic.main.astronomy_picture_list_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.scope.scope
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class AstronomyPictureListFragment : Fragment() {

    private val viewModel: PictureListViewModel by viewModel<PicturesListViewModel>()

    private var currentList: List<Picture?> = emptyList()
    private var todayPicture: Picture? = null
    private var randomPicture: Picture? = null

    private val adapter: AstronomyPictureAdapter = AstronomyPictureAdapter(emptyList(), ::onClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.astronomy_picture_list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModels()
        observeViewModels()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_pictures.layoutManager = GridLayoutManager(requireContext(), 6).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapter.getItemViewType(position)) {
                        TODAY_PICTURE -> 3
                        PICTURE -> 2
                        else -> 2
                    }
                }
            }
        }
        rv_pictures.adapter = adapter
    }

    private fun initViewModels() {
        viewModel.init()
    }

    private fun observeViewModels() {
        lifecycleScope.launch {
            viewModel.today.collect { pictureState ->
                when (pictureState) {
                    is PictureUiState.Success -> applyTodayPicture(pictureState.picture)
                    is PictureUiState.Error -> applyTodayPictureError(pictureState.reason)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.random.collect { pictureState ->
                when (pictureState) {
                    is PictureUiState.Success -> applyRandomPicture(pictureState.picture)
                    is PictureUiState.Error -> applyRandomPictureError(pictureState.reason)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.list.collect { list ->
                when (list) {
                    is PicturesListUiState.Success -> applyList(list.pictures)
                    is PicturesListUiState.Error -> applyListError(list.reason)
                }
            }
        }
    }

    private fun applyTodayPicture(picture: Picture?) {
        todayPicture = picture
        updateAdapterData()
    }

    private fun applyRandomPicture(picture: Picture?) {
        randomPicture = picture
        updateAdapterData()
    }

    private fun applyList(pictures: List<Picture>) {
        currentList = pictures
        updateAdapterData()
    }

    private fun applyTodayPictureError(reason: Throwable?) {
        TODO()
    }

    private fun applyRandomPictureError(reason: Throwable?) {
        TODO()
    }

    private fun applyListError(reason: Throwable?) {
        TODO()
    }

    private fun updateAdapterData() {
        adapter.data = currentList.toMutableList().apply {
            add(0, randomPicture)
            add(0, todayPicture)
        }
        adapter.notifyDataSetChanged()
    }

    private fun onClick(id: Id) {
        val destination =
            AstronomyPictureListFragmentDirections.actionAstronomyPictureListFragmentToAstronomyPictureFragment2(
                id
            )
        findNavController().navigate(destination)
    }
}