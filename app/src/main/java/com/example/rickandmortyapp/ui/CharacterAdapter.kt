package com.example.rickandmortyapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.databinding.ItemRvFragmentHomeAdapterBinding
import com.example.rickandmortyapp.repository.Character


class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: ItemRvFragmentHomeAdapterBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterViewHolder {
        return CharacterViewHolder(ItemRvFragmentHomeAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        val character = characters[position]

        holder.binding.apply {
            tvName.text = character.name
            tvGender.text = character.gender
            tvSpecie.text = character.species
            tvStatus.text = character.status
            Glide.with(holder.itemView).load(character.image).into(ivPersonaje)
        }
    }

    override fun getItemCount(): Int = characters.size


    private val diffCallBack = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var characters: List<Character>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

}