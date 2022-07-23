package com.example.contextualcards.model

data class CardGroup(
    val card_type: Int,
    val cards: List<Card>,
    val design_type: String,
    val height: Int,
    val id: Int,
    val is_scrollable: Boolean,
    val level: Int,
    val name: String
)

data class Card(
    val bg_color: String,
    val bg_image: BgImage,
    val cta: List<Cta>,
    val description: String,
    val formatted_description: FormattedDescription,
    val formatted_title: FormattedTitle,
    val icon: Icon,
    val is_disabled: Boolean,
    val name: String,
    val title: String,
    val url: String
)

data class BgImage(
    val aspect_ratio: Double,
    val image_type: String,
    val image_url: String
)

data class Cta(
    val bg_color: String,
    val text: String,
    val text_color: String,
    val url: String,
    val url_choice: String
)

data class FormattedDescription(
    val align: String,
    val entities: List<Any>,
    val text: String
)

data class FormattedTitle(
    val align: String,
    val entities: List<Any>,
    val text: String
)

data class Icon(
    val aspect_ratio: Int,
    val image_type: String,
    val image_url: String
)