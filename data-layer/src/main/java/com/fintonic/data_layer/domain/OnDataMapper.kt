package com.fintonic.data_layer.domain

import com.fintonic.domain_layer.domain.*

private const val DEFAULT_INTEGER_VALUE = 0
private const val DEFAULT_STRING_VALUE = ""


fun BeersDtoWrapper.dtoToBo(): List<Beer> {
    val mutableList = mutableListOf<Beer>()
    this.value.mapNotNull {
        mutableList.add(it.dtoToBo())
    }
    return mutableList.toList()
}

fun BeerDto.dtoToBo() = Beer(
    abv = this.abv,
    attenuationLevel = this.attenuationLevel,
    brewersTips = this.brewersTips,
    name = this.name,
    boilVolume = this.boilVolume.dtoToBo(),
    contributedBy = this.contributedBy,
    description = this.description,
    ebc = this.ebc,
    firstBrewed = this.firstBrewed,
    foodPairing = this.foodPairing,
    ibu = this.ibu,
    id = this.id,
    imageUrl = this.imageUrl,
    ingredients = this.ingredients.dtoToBo(),
    method = this.method.dtoToBo(),
    ph = this.ph,
    srm = this.srm,
    tagLine = this.tagLine,
    targetFg = this.targetFg,
    targetOg = this.targetOg,
    volume = this.volumeDto.dtoToBo()
)


fun UnitValueDto.dtoToBo() = "${this.value}${this.unit}"

fun IngredientsDto.dtoToBo() = Ingredients(
    hops = this.hops.map { it.dtoToBo() },
    malt = this.malt.map { it.dtoToBo() },
    yeast = this.yeast
)

fun HopDto.dtoToBo() = Hop(
    add = this.add,
    name = this.name,
    amount = this.amount.dtoToBo(),
    attribute = this.attribute
)

fun MaltDto.dtoToBo() = Malt(
    amount = this.amount.dtoToBo(),
    name = this.name
)

fun MethodDto.dtoToBo() = Method(
    fermentation = this.fermentation.dtoToBo(),
    mashTemp = this.mashTemp.map { it.dtoToBo() },
    twist = this.twist
)


fun FermentationDto.dtoToBo() = Fermentation(
    temp = this.temp.dtoToBo()
)

fun MashTempDto.dtoToBo() = MashTemp(
    duration = this.duration,
    temp = this.temp.dtoToBo()
)

fun FoodPairingWrapper.dtoToBo() = this.list.map { it }

/**
 * Maps a [FailureDto] into a [FailureBo]
 */
fun FailureDto.dtoToBoFailure(): FailureBo = when (this) {
    FailureDto.NoConnection -> FailureBo.NoConnection
    is FailureDto.RequestError -> FailureBo.RequestError(msg = msg ?: DEFAULT_STRING_VALUE)
    is FailureDto.Error -> FailureBo.ServerError(msg = msg ?: DEFAULT_STRING_VALUE)
    FailureDto.NoData -> FailureBo.NoData
    FailureDto.Unknown -> FailureBo.Unknown
}

