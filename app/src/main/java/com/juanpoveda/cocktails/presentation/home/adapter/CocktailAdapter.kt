package com.juanpoveda.cocktails.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juanpoveda.cocktails.databinding.CocktailListItemBinding
import com.juanpoveda.cocktails.presentation.model.UiCocktail

typealias OnCocktailClickListener = (UiCocktail) -> Unit

class CocktailAdapter(
    private val onCocktailClickListener: OnCocktailClickListener
) : ListAdapter<UiCocktail, CocktailAdapter.CocktailViewHolder>(CocktailListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = CocktailListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailViewHolder(binding, onCocktailClickListener)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CocktailViewHolder(
        private val binding: CocktailListItemBinding,
        private val onCocktailClickListener: OnCocktailClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cocktail: UiCocktail) {
            binding.title.text = cocktail.name
            //Glide.with(binding.root).load(this.image).into(binding.ingredientImageView)
            binding.root.setOnClickListener { onCocktailClickListener(cocktail) }
        }
    }

}

class CocktailListDiffCallback : DiffUtil.ItemCallback<UiCocktail>() {

    override fun areItemsTheSame(oldItem: UiCocktail, newItem: UiCocktail): Boolean {
        return oldItem.id.equals(newItem.id, true)
    }

    override fun areContentsTheSame(oldItem: UiCocktail, newItem: UiCocktail): Boolean {
        return oldItem == newItem
    }

}
