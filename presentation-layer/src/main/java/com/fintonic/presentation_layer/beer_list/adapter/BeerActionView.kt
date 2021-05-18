package com.fintonic.presentation_layer.beer_list.adapter

import com.fintonic.presentation_layer.domain.BeerVo


/**
 * A sealed class which models all actions available for a beer view
 */
sealed class BeerActionView  {
    data class BeerItemClick(val item: BeerVo) : BeerActionView()
}
