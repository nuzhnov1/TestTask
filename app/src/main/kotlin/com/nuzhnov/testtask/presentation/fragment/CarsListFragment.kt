package com.nuzhnov.testtask.presentation.fragment

import com.nuzhnov.testtask.R
import com.nuzhnov.testtask.databinding.CarsListFragmentBinding
import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.SortOrder
import com.nuzhnov.testtask.presentation.adapter.CarAdapter
import com.nuzhnov.testtask.presentation.model.CarUiModel
import com.nuzhnov.testtask.presentation.viewmodel.CarsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@[AndroidEntryPoint WithFragmentBindings]
class CarsListFragment : Fragment() {

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

        createObservers()
        createListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createObservers() {
        carsViewModel.carsListFlow
            .onEach(::onCarsListUpdated)
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            )
            .launchIn(scope = viewLifecycleOwner.lifecycleScope)

        carsViewModel.carSortTypeStateFlow
            .onEach(::onCarSortTypeUpdated)
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            )
            .launchIn(scope = viewLifecycleOwner.lifecycleScope)

        carsViewModel.carSortOrderStateFlow
            .onEach(::onCarSortOrderUpdated)
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            )
            .launchIn(scope = viewLifecycleOwner.lifecycleScope)
    }

    private fun createListeners() {
        binding.carNumber.doOnTextChanged(::onNumberTextChanged)
        binding.carSortTypeMenu.setOnItemClickListener(::onCarSortTypeTextViewClick)
        binding.carSortOrderButton.setOnClickListener(::onCarSortOrderButtonClick)
    }

    private fun onCarsListUpdated(carsList: List<CarUiModel>) {
        val adapter = binding.carsList.adapter as CarAdapter
        adapter.submitList(carsList)
    }

    private fun onCarSortTypeUpdated(sortType: CarSortType) {
        @StringRes val sortTypeStringResId = when (sortType) {
            CarSortType.NUMBER       -> R.string.sort_by_number
            CarSortType.MODEL        -> R.string.sort_by_model
            CarSortType.RELEASE_YEAR -> R.string.sort_by_release_year
            CarSortType.MILLAGE      -> R.string.sort_by_millage
        }

        binding.carSortTypeMenu.setText(
            /* text = */ getString(sortTypeStringResId),
            /* filter = */ false
        )
    }

    private fun onCarSortOrderUpdated(sortOrder: SortOrder) {
        @DrawableRes val sortOrderImageResId = when (sortOrder) {
            SortOrder.ASC  -> R.drawable.ic_asc_sort_type_24
            SortOrder.DESC -> R.drawable.ic_desc_sort_type_24
        }

        binding.carSortOrderButton.icon = ContextCompat.getDrawable(
            /* context = */ requireContext(),
            /* id = */ sortOrderImageResId
        )
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onCarSortTypeTextViewClick(
        parent: AdapterView<*>,
        view: View,
        position: Int,
        id: Long
    ) {
        val textView = view as? TextView ?: return

        val sortType = when (textView.text) {
            getString(R.string.sort_by_number)       -> CarSortType.NUMBER
            getString(R.string.sort_by_model)        -> CarSortType.MODEL
            getString(R.string.sort_by_release_year) -> CarSortType.RELEASE_YEAR
            getString(R.string.sort_by_millage)      -> CarSortType.MILLAGE
            else                                     -> CarSortType.NUMBER
        }

        carsViewModel.setCarSortType(sortType)
    }

    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    private fun onCarSortOrderButtonClick(view: View) {
        when (val currentOrder = carsViewModel.carSortOrderStateFlow.value) {
            SortOrder.ASC  -> carsViewModel.setCarSortOrder(SortOrder.DESC)
            SortOrder.DESC -> carsViewModel.setCarSortOrder(SortOrder.ASC)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onNumberTextChanged(
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        carsViewModel.setCarNumber(carNumber = text?.toString() ?: "")
    }
}
