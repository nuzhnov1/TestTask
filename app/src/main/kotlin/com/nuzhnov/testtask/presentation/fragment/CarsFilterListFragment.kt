package com.nuzhnov.testtask.presentation.fragment

import com.nuzhnov.testtask.R
import com.nuzhnov.testtask.databinding.CarsFilterListFragmentBinding
import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.SortOrder
import com.nuzhnov.testtask.presentation.viewmodel.CarsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@[AndroidEntryPoint WithFragmentBindings]
class CarsFilterListFragment : BottomSheetDialogFragment() {

    private var _binding: CarsFilterListFragmentBinding? = null
    private val binding get() = _binding!!

    private val carsViewModel: CarsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CarsFilterListFragmentBinding.inflate(
            /* inflater = */ inflater,
            /* parent = */ container,
            /* attachToParent = */ false
        )

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createListeners()
        createObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createListeners() {
        binding.carSortTypeMenu.setOnItemClickListener(::onCarSortTypeTextViewClick)
        binding.carSortOrderButton.setOnClickListener(::onCarSortOrderButtonClick)
    }

    private fun createObservers() {
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

    @Suppress("UNUSED_PARAMETER")
    private fun onCarSortTypeTextViewClick(
        parent: AdapterView<*>,
        view: View,
        position: Int,
        id: Long
    ) {
        val sortType = when (id.toInt()) {
            R.string.sort_by_number       -> CarSortType.NUMBER
            R.string.sort_by_model        -> CarSortType.MODEL
            R.string.sort_by_release_year -> CarSortType.RELEASE_YEAR
            R.string.sort_by_millage      -> CarSortType.MILLAGE
            else                          -> CarSortType.NUMBER
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


    internal companion object {
        const val TAG = "CarsFilterListFragment"
    }
}
