package com.nuzhnov.testtask.presentation.fragment

import com.nuzhnov.testtask.R
import com.nuzhnov.testtask.databinding.CarsListFragmentBinding
import com.nuzhnov.testtask.presentation.adapter.CarAdapter
import com.nuzhnov.testtask.presentation.model.CarUiModel
import com.nuzhnov.testtask.presentation.viewmodel.CarsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.search.SearchView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@[AndroidEntryPoint WithFragmentBindings]
class CarsListFragment : Fragment(), MenuProvider {

    private var _binding: CarsListFragmentBinding? = null
    private val binding get() = _binding!!

    private val carsViewModel: CarsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CarsListFragmentBinding.inflate(
            /* inflater = */ inflater,
            /* parent = */ container,
            /* attachToParent = */ false
        )

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = requireActivity()

        binding.carsList.adapter = CarAdapter(context = activity)
        activity.addMenuProvider(
            /* provider = */ this,
            /* owner = */ viewLifecycleOwner,
            /* state = */ Lifecycle.State.STARTED
        )

        createObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.card_list_fragment_menu, menu)
        createMenuItemsListeners(menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem) = true

    private fun createMenuItemsListeners(menu: Menu) {
        val searchView = menu.findItem(R.id.search_button) as SearchView
        val filterButton = menu.findItem(R.id.filter_button)

        searchView.addTransitionListener(::onSearchViewTransition)
        searchView.editText.doOnTextChanged(::onSearchViewTextChanged)
        filterButton.setOnMenuItemClickListener(::onFilterMenuItemClick)
    }

    private fun createObservers() {
        carsViewModel.carsListFlow
            .onEach(::onCarsListUpdated)
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            )
            .launchIn(scope = viewLifecycleOwner.lifecycleScope)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onSearchViewTextChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) = carsViewModel.setCarNumber(carNumber = text?.toString() ?: "")

    @Suppress("UNUSED_PARAMETER")
    private fun onSearchViewTransition(
        searchView: SearchView,
        oldState: SearchView.TransitionState,
        newState: SearchView.TransitionState
    ) {
        if (newState == SearchView.TransitionState.SHOWN) {
            searchView.editText.setText(carsViewModel.carNumberStateFlow.value)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onFilterMenuItemClick(menuItem: MenuItem): Boolean {
        val activity = requireActivity()
        val modalBottomSheet = CarsFilterListFragment()

        modalBottomSheet.show(activity.supportFragmentManager, CarsFilterListFragment.TAG)

        return true
    }

    private fun onCarsListUpdated(carsList: List<CarUiModel>) {
        val adapter = binding.carsList.adapter as CarAdapter
        adapter.submitList(carsList)
    }
}
