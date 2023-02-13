package com.juanpoveda.cocktails.presentation.cocktaildetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.transition.addListener
import androidx.core.transition.doOnEnd
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.juanpoveda.cocktails.databinding.FragmentCocktailDetailsBinding
import com.juanpoveda.cocktails.presentation.base.BaseFragment

/**
 * This Fragment displays the details of a specific cocktail
 */
class CocktailDetailsFragment : BaseFragment<FragmentCocktailDetailsBinding>() {

    private val args: CocktailDetailsFragmentArgs by navArgs()

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCocktailDetailsBinding {
        return FragmentCocktailDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImageAndTransitions()
        showCocktailDetails()
    }

    private fun setImageAndTransitions() {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        binding.cocktailIv.apply {
            transitionName = args.uiCocktail.thumb
            Glide.with(binding.root)
                .load(args.uiCocktail.thumb)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .into(this)
        }
        postponeEnterTransition()
    }

    private fun showCocktailDetails() {
        binding.title.text = args.uiCocktail.name
    }

}
