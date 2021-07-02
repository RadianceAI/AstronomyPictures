package by.radiance.space.picrures.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.radiance.space.picrures.R
import by.radiance.space.picrures.ui.list.recycler.AstronomyPictureAdapter
import by.radiance.space.picrures.ui.list.recycler.AstronomyPictureAdapter.Companion.PICTURE
import by.radiance.space.picrures.ui.list.recycler.AstronomyPictureAdapter.Companion.TODAY_PICTURE
import by.radiance.space.picrures.ui.list.viewModel.AstronomyPictureListViewModel
import by.radiance.space.picrures.ui.list.viewModel.RandomAstronomyPictureViewModel
import by.radiance.space.picrures.ui.list.viewModel.TodayAstronomyPictureViewModel
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.PictureId
import by.radiance.space.pictures.domain.presenter.PictureListViewModel
import by.radiance.space.pictures.domain.presenter.RandomPictureViewModel
import by.radiance.space.pictures.domain.presenter.TodayPictureViewModel
import kotlinx.android.synthetic.main.astronomy_picture_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class AstronomyPictureListFragment : Fragment() {

    private val listViewModel: PictureListViewModel by viewModel<AstronomyPictureListViewModel>()
    private val todayViewModel: TodayPictureViewModel by viewModel<TodayAstronomyPictureViewModel>()
    private val randomViewModel: RandomPictureViewModel by viewModel<RandomAstronomyPictureViewModel>()

    private var currentList: List<AstronomyPicture?> = emptyList()
    private var todayPicture: AstronomyPicture? = null
    private var randomPicture: AstronomyPicture? = null

    private val adapter: AstronomyPictureAdapter = AstronomyPictureAdapter(emptyList(), ::onClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.astronomy_picture_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModels()
        observeViewModels()

        rv_pictures.layoutManager = GridLayoutManager(requireContext(), 6).apply {
            spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
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
        listViewModel.init()
        todayViewModel.init()
        randomViewModel.init()
    }

    private fun observeViewModels() {
        listViewModel.astronomyPictureList.observe(viewLifecycleOwner) { pictures ->
            pictures?.let {
                currentList = pictures
                updateAdapterData()
            }
        }
        todayViewModel.todayPicture.observe(viewLifecycleOwner) { today ->
            todayPicture = today
            updateAdapterData()
        }
        randomViewModel.randomPicture.observe(viewLifecycleOwner) { random ->
            randomPicture = random
            updateAdapterData()
        }
    }

    private fun updateAdapterData() {
        adapter.data = currentList.toMutableList().apply {
            add(0, randomPicture)
            add(0, todayPicture)
        }
        adapter.notifyDataSetChanged()
    }

    private fun onClick(id: PictureId) {
        val destination = AstronomyPictureListFragmentDirections.actionAstronomyPictureListFragmentToAstronomyPictureFragment2(id)
        findNavController().navigate(destination)
    }
}