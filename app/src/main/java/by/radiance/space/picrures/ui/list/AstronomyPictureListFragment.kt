package by.radiance.space.picrures.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.radiance.space.picrures.R
import org.koin.android.architecture.ext.viewModel

class AstronomyPictureListFragment : Fragment() {

    private val listViewModel: AstronomyPictureListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.astronomy_picture_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModels()
        observeViewModels()
    }

    private fun initViewModels() {
        listViewModel.init()
    }

    private fun observeViewModels() {
        listViewModel.pictures.observe(viewLifecycleOwner) { pictures ->
            //todo user recycler
        }
    }
}