package by.radiance.space.picrures.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import by.radiance.space.picrures.R
import by.radiance.space.picrures.ui.list.recycler.AstronomyPictureAdapter
import by.radiance.space.picrures.ui.list.recycler.AstronomyPictureAdapter.Companion.PICTURE
import by.radiance.space.picrures.ui.list.recycler.AstronomyPictureAdapter.Companion.TODAY_PICTURE
import by.radiance.space.pictures.domain.entity.PictureId
import kotlinx.android.synthetic.main.astronomy_picture_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class AstronomyPictureListFragment : Fragment() {

    private val listViewModel: AstronomyPictureListViewModel by viewModel()
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

        rv_pictures.layoutManager = GridLayoutManager(requireContext(), 3).apply {
            spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapter.getItemViewType(position)) {
                        TODAY_PICTURE -> 3
                        PICTURE -> 1
                        else -> 1
                    }
                }
            }
        }
        rv_pictures.adapter = adapter
    }

    private fun initViewModels() {
        listViewModel.init()
    }

    private fun observeViewModels() {
        listViewModel.pictures.observe(viewLifecycleOwner) { pictures ->
            if (pictures == null) return@observe

            adapter.data = pictures
            adapter.notifyDataSetChanged()
        }
    }

    private fun onClick(id: PictureId) {

    }
}