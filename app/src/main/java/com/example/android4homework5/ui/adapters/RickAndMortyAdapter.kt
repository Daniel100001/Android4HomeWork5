package com.example.android4homework5.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android4homework5.data.models.PairModel
import com.example.android4homework5.databinding.OneItemBinding

class RickAndMortyAdapter :
    ListAdapter<PairModel, RickAndMortyAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(private val binding: OneItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(pairModel: PairModel?) {
            binding.itemCharacterName.text = pairModel?.characterModel?.name
            binding.lastKnowLocation.text =
                pairModel?.locationModel?.name

            Glide.with(binding.itemCharacterImage).load(pairModel?.characterModel?.image)
                .into(binding.itemCharacterImage)
            binding.itemCharacterStatus.text = pairModel?.characterModel?.status
            binding.itemCharacterSpecies.text = pairModel?.characterModel?.species
            binding.gender.text = pairModel?.locationModel?.type

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OneItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<PairModel>() {

        override fun areItemsTheSame(
            oldItem: PairModel,
            newItem: PairModel
        ): Boolean {
            return oldItem.characterModel.id == newItem.characterModel.id
        }

        override fun areContentsTheSame(
            oldItem: PairModel,
            newItem: PairModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}