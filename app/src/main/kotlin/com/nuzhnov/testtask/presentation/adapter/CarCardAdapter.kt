package com.nuzhnov.testtask.presentation.adapter

import com.nuzhnov.testtask.databinding.CarCardBinding
import com.nuzhnov.testtask.presentation.adapter.CarCardAdapter.CarViewHolder
import com.nuzhnov.testtask.presentation.model.CarUiModel
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

internal class CarCardAdapter(private val context: Context) :
    ListAdapter<CarUiModel, CarViewHolder>(CarDiffUtil.asyncDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            binding = CarCardBinding.inflate(
                /* inflater = */ LayoutInflater.from(context),
                /* parent = */ parent,
                /* attachToParent = */ false
            )
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(uiModel = getItem(position))
    }


    internal class CarViewHolder(private val binding: CarCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uiModel: CarUiModel) = binding.run {
            model.text = uiModel.model
            number.numberAndSeries.text = uiModel.registrationNumber
            number.countryShort.text = uiModel.countryShort
            number.regionCode.text = uiModel.regionCode.toString()
            releaseYear.text = uiModel.releaseYear.toString()
            millage.text = uiModel.millage.toString()
        }
    }

    internal object CarDiffUtil : DiffUtil.ItemCallback<CarUiModel>() {
        val asyncDiffer = AsyncDifferConfig.Builder(/* diffCallback = */ this)
            .setBackgroundThreadExecutor { diffTask -> diffTask.run() }
            .build()


        override fun areItemsTheSame(oldItem: CarUiModel, newItem: CarUiModel): Boolean {
            return oldItem.registrationNumber == newItem.registrationNumber &&
                    oldItem.regionCode == newItem.regionCode &&
                    oldItem.countryShort == newItem.countryShort
        }

        override fun areContentsTheSame(oldItem: CarUiModel, newItem: CarUiModel): Boolean {
            return oldItem == newItem
        }
    }
}
