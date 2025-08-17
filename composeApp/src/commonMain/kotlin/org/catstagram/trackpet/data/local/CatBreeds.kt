package org.catstagram.trackpet.data.local

import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.*
import org.catstagram.trackpet.domain.handbook.CatBreed

object CatBreeds {
    val breeds = listOf(
        CatBreed(
            id = 1,
            name = Res.string.abyssinian_name,
            shortDescription = Res.string.abyssinian_short_description,
            origin = Res.string.origin_ethiopia,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_active,
                Res.string.personality_intelligent,
                Res.string.personality_curious,
                Res.string.personality_social,
                Res.string.personality_playful
            ),
            careTips = Res.string.abyssinian_care_tips,
            funFact = Res.string.abyssinian_fun_fact,
            image = Res.drawable.abyssinian_cat
        ),
        CatBreed(
            id = 2,
            name = Res.string.american_shorthair_name,
            shortDescription = Res.string.american_shorthair_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_easygoing,
                Res.string.personality_adaptable,
                Res.string.personality_intelligent,
                Res.string.personality_affectionate,
                Res.string.personality_independent
            ),
            careTips = Res.string.american_shorthair_care_tips,
            funFact = Res.string.american_shorthair_fun_fact,
            image = Res.drawable.american_shorthair_cat
        ),
        CatBreed(
            id = 3,
            name = Res.string.american_wirehair_name,
            shortDescription = Res.string.american_wirehair_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_wire_hair,
            personality = listOf(
                Res.string.personality_easygoing,
                Res.string.personality_affectionate,
                Res.string.personality_tolerant,
                Res.string.personality_patient,
                Res.string.personality_playful
            ),
            careTips = Res.string.american_wirehair_care_tips,
            funFact = Res.string.american_wirehair_fun_fact,
            image = Res.drawable.american_wirehair_cat
        ),
        CatBreed(
            id = 4,
            name = Res.string.american_bobtail_name,
            shortDescription = Res.string.american_bobtail_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_large,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_intelligent,
                Res.string.personality_friendly,
                Res.string.personality_adaptable,
                Res.string.personality_confident,
                Res.string.personality_athletic
            ),
            careTips = Res.string.american_bobtail_care_tips,
            funFact = Res.string.american_bobtail_fun_fact,
            image = Res.drawable.american_bobtail_cat
        ),
        CatBreed(
            id = 5,
            name = Res.string.american_curl_name,
            shortDescription = Res.string.american_curl_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_playful,
                Res.string.personality_curious,
                Res.string.personality_adaptable,
                Res.string.personality_gentle
            ),
            careTips = Res.string.american_curl_care_tips,
            funFact = Res.string.american_curl_fun_fact,
            image = Res.drawable.american_curl_cat
        ),
        CatBreed(
            id = 6,
            name = Res.string.arabian_mau_name,
            shortDescription = Res.string.arabian_mau_short_description,
            origin = Res.string.origin_arabian_peninsula,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_energetic,
                Res.string.personality_athletic,
                Res.string.personality_intelligent,
                Res.string.personality_vocal,
                Res.string.personality_independent
            ),
            careTips = Res.string.arabian_mau_care_tips,
            funFact = Res.string.arabian_mau_fun_fact,
            image = Res.drawable.arabian_mau_cat
        ),
        CatBreed(
            id = 7,
            name = Res.string.asian_name,
            shortDescription = Res.string.asian_short_description,
            origin = Res.string.origin_united_kingdom,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_outgoing,
                Res.string.personality_friendly,
                Res.string.personality_lively,
                Res.string.personality_affectionate,
                Res.string.personality_intelligent
            ),
            careTips = Res.string.asian_care_tips,
            funFact = Res.string.asian_fun_fact,
            image = Res.drawable.asian_cat
        ),
        CatBreed(
            id = 8,
            name = Res.string.australian_mist_name,
            shortDescription = Res.string.australian_mist_short_description,
            origin = Res.string.origin_australia,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_tolerant,
                Res.string.personality_affectionate,
                Res.string.personality_calm,
                Res.string.personality_adaptable
            ),
            careTips = Res.string.australian_mist_care_tips,
            funFact = Res.string.australian_mist_fun_fact,
            image = Res.drawable.australian_mist_cat
        ),
        CatBreed(
            id = 9,
            name = Res.string.balinese_name,
            shortDescription = Res.string.balinese_short_description,
            origin = Res.string.origin_thailand,
            size = Res.string.size_medium,
            coat = Res.string.coat_semi_long_hair,
            personality = listOf(
                Res.string.personality_vocal,
                Res.string.personality_intelligent,
                Res.string.personality_playful,
                Res.string.personality_affectionate,
                Res.string.personality_social
            ),
            careTips = Res.string.balinese_care_tips,
            funFact = Res.string.balinese_fun_fact,
            image = Res.drawable.balinese_cat
        ),
        CatBreed(
            id = 10,
            name = Res.string.bambino_name,
            shortDescription = Res.string.bambino_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_small,
            coat = Res.string.coat_hairless,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_playful,
                Res.string.personality_energetic,
                Res.string.personality_social,
                Res.string.personality_outgoing
            ),
            careTips = Res.string.bambino_care_tips,
            funFact = Res.string.bambino_fun_fact,
            image = Res.drawable.bambino_cat
        ),
        CatBreed(
            id = 11,
            name = Res.string.bengal_name,
            shortDescription = Res.string.bengal_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_active,
                Res.string.personality_intelligent,
                Res.string.personality_curious,
                Res.string.personality_athletic,
                Res.string.personality_mischievous
            ),
            careTips = Res.string.bengal_care_tips,
            funFact = Res.string.bengal_fun_fact,
            image = Res.drawable.bengal_cat
        ),
        CatBreed(
            id = 12,
            name = Res.string.birman_name,
            shortDescription = Res.string.birman_short_description,
            origin = Res.string.origin_myanmar,
            size = Res.string.size_medium,
            coat = Res.string.coat_semi_long_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_affectionate,
                Res.string.personality_laid_back,
                Res.string.personality_social,
                Res.string.personality_loyal
            ),
            careTips = Res.string.birman_care_tips,
            funFact = Res.string.birman_fun_fact,
            image = Res.drawable.birman_cat
        ),
        CatBreed(
            id = 13,
            name = Res.string.bombay_name,
            shortDescription = Res.string.bombay_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_social,
                Res.string.personality_affectionate,
                Res.string.personality_vocal,
                Res.string.personality_playful,
                Res.string.personality_outgoing
            ),
            careTips = Res.string.bombay_care_tips,
            funFact = Res.string.bombay_fun_fact,
            image = Res.drawable.bombay_cat
        ),
        CatBreed(
            id = 14,
            name = Res.string.brazilian_shorthair_name,
            shortDescription = Res.string.brazilian_shorthair_short_description,
            origin = Res.string.origin_brazil,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_adaptable,
                Res.string.personality_intelligent,
                Res.string.personality_social,
                Res.string.personality_friendly,
                Res.string.personality_easygoing
            ),
            careTips = Res.string.brazilian_shorthair_care_tips,
            funFact = Res.string.brazilian_shorthair_fun_fact,
            image = Res.drawable.brazilian_shorthair_cat
        ),
        CatBreed(
            id = 15,
            name = Res.string.british_shorthair_name,
            shortDescription = Res.string.british_shorthair_short_description,
            origin = Res.string.origin_united_kingdom,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_calm,
                Res.string.personality_independent,
                Res.string.personality_gentle,
                Res.string.personality_easygoing,
                Res.string.personality_patient
            ),
            careTips = Res.string.british_shorthair_care_tips,
            funFact = Res.string.british_shorthair_fun_fact,
            image = Res.drawable.british_shorthair_cat
        ),
        CatBreed(
            id = 16,
            name = Res.string.british_longhair_name,
            shortDescription = Res.string.british_longhair_short_description,
            origin = Res.string.origin_united_kingdom,
            size = Res.string.size_medium,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_calm,
                Res.string.personality_independent,
                Res.string.personality_affectionate,
                Res.string.personality_gentle,
                Res.string.personality_patient
            ),
            careTips = Res.string.british_longhair_care_tips,
            funFact = Res.string.british_longhair_fun_fact,
            image = Res.drawable.british_longhair_cat
        ),
        CatBreed(
            id = 17,
            name = Res.string.burmese_name,
            shortDescription = Res.string.burmese_short_description,
            origin = Res.string.origin_myanmar,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_social,
                Res.string.personality_affectionate,
                Res.string.personality_playful,
                Res.string.personality_intelligent,
                Res.string.personality_outgoing
            ),
            careTips = Res.string.burmese_care_tips,
            funFact = Res.string.burmese_fun_fact,
            image = Res.drawable.burmese_cat
        ),
        CatBreed(
            id = 18,
            name = Res.string.burmilla_name,
            shortDescription = Res.string.burmilla_short_description,
            origin = Res.string.origin_united_kingdom,
            size = Res.string.size_medium,
            coat = Res.string.coat_semi_long_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_playful,
                Res.string.personality_independent,
                Res.string.personality_affectionate,
                Res.string.personality_friendly
            ),
            careTips = Res.string.burmilla_care_tips,
            funFact = Res.string.burmilla_fun_fact,
            image = Res.drawable.burmilla_cat
        ),
        CatBreed(
            id = 19,
            name = Res.string.havana_brown_name,
            shortDescription = Res.string.havana_brown_short_description,
            origin = Res.string.origin_united_kingdom,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_social,
                Res.string.personality_intelligent,
                Res.string.personality_playful,
                Res.string.personality_affectionate,
                Res.string.personality_mischievous
            ),
            careTips = Res.string.havana_brown_care_tips,
            funFact = Res.string.havana_brown_fun_fact,
            image = Res.drawable.havana_brown_cat
        ),
        CatBreed(
            id = 20,
            name = Res.string.devon_rex_name,
            shortDescription = Res.string.devon_rex_short_description,
            origin = Res.string.origin_united_kingdom,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_active,
                Res.string.personality_social,
                Res.string.personality_mischievous,
                Res.string.personality_outgoing,
                Res.string.personality_playful
            ),
            careTips = Res.string.devon_rex_care_tips,
            funFact = Res.string.devon_rex_fun_fact,
            image = Res.drawable.devon_rex_cat
        ),
        CatBreed(
            id = 21,
            name = Res.string.donskoy_name,
            shortDescription = Res.string.donskoy_short_description,
            origin = Res.string.origin_russia,
            size = Res.string.size_medium,
            coat = Res.string.coat_hairless,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_social,
                Res.string.personality_playful,
                Res.string.personality_mischievous,
                Res.string.personality_intelligent
            ),
            careTips = Res.string.donskoy_care_tips,
            funFact = Res.string.donskoy_fun_fact,
            image = Res.drawable.donskoy_cat
        ),
        CatBreed(
            id = 22,
            name = Res.string.egyptian_mau_name,
            shortDescription = Res.string.egyptian_mau_short_description,
            origin = Res.string.origin_egypt,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_athletic,
                Res.string.personality_loyal,
                Res.string.personality_intelligent,
                Res.string.personality_active,
                Res.string.personality_independent
            ),
            careTips = Res.string.egyptian_mau_care_tips,
            funFact = Res.string.egyptian_mau_fun_fact,
            image = Res.drawable.egyptian_mau_cat
        ),
        CatBreed(
            id = 23,
            name = Res.string.canaan_name,
            shortDescription = Res.string.canaan_short_description,
            origin = Res.string.origin_israel,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_intelligent,
                Res.string.personality_independent,
                Res.string.personality_adaptable,
                Res.string.personality_loyal,
                Res.string.personality_friendly
            ),
            careTips = Res.string.canaan_care_tips,
            funFact = Res.string.canaan_fun_fact,
            image = Res.drawable.canaan_cat
        ),
        CatBreed(
            id = 24,
            name = Res.string.khao_manee_name,
            shortDescription = Res.string.khao_manee_short_description,
            origin = Res.string.origin_thailand,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_social,
                Res.string.personality_vocal,
                Res.string.personality_playful,
                Res.string.personality_affectionate,
                Res.string.personality_intelligent
            ),
            careTips = Res.string.khao_manee_care_tips,
            funFact = Res.string.khao_manee_fun_fact,
            image = Res.drawable.khao_manee_cat
        ),
        CatBreed(
            id = 25,
            name = Res.string.cymric_name,
            shortDescription = Res.string.cymric_short_description,
            origin = Res.string.origin_united_kingdom,
            size = Res.string.size_medium,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_intelligent,
                Res.string.personality_playful,
                Res.string.personality_gentle,
                Res.string.personality_loyal,
                Res.string.personality_adaptable
            ),
            careTips = Res.string.cymric_care_tips,
            funFact = Res.string.cymric_fun_fact,
            image = Res.drawable.cymric_cat
        ),
        CatBreed(
            id = 26,
            name = Res.string.cornish_rex_name,
            shortDescription = Res.string.cornish_rex_short_description,
            origin = Res.string.origin_united_kingdom,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_energetic,
                Res.string.personality_social,
                Res.string.personality_intelligent,
                Res.string.personality_playful,
                Res.string.personality_outgoing
            ),
            careTips = Res.string.cornish_rex_care_tips,
            funFact = Res.string.cornish_rex_fun_fact,
            image = Res.drawable.cornish_rex_cat
        ),
        CatBreed(
            id = 27,
            name = Res.string.korat_name,
            shortDescription = Res.string.korat_short_description,
            origin = Res.string.origin_thailand,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_intelligent,
                Res.string.personality_loyal,
                Res.string.personality_sweet,
                Res.string.personality_calm
            ),
            careTips = Res.string.korat_care_tips,
            funFact = Res.string.korat_fun_fact,
            image = Res.drawable.korat_cat
        ),
        CatBreed(
            id = 28,
            name = Res.string.kurilian_bobtail_name,
            shortDescription = Res.string.kurilian_bobtail_short_description,
            origin = Res.string.origin_russia,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_athletic,
                Res.string.personality_intelligent,
                Res.string.personality_gentle,
                Res.string.personality_active,
                Res.string.personality_independent
            ),
            careTips = Res.string.kurilian_bobtail_care_tips,
            funFact = Res.string.kurilian_bobtail_fun_fact,
            image = Res.drawable.kurilian_bobtail_cat
        ),
        CatBreed(
            id = 29,
            name = Res.string.laperm_name,
            shortDescription = Res.string.laperm_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_gentle,
                Res.string.personality_social,
                Res.string.personality_intelligent,
                Res.string.personality_adaptable
            ),
            careTips = Res.string.laperm_care_tips,
            funFact = Res.string.laperm_fun_fact,
            image = Res.drawable.laperm_cat
        ),
        CatBreed(
            id = 30,
            name = Res.string.lykoi_name,
            shortDescription = Res.string.lykoi_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_energetic,
                Res.string.personality_playful,
                Res.string.personality_intelligent,
                Res.string.personality_outgoing,
                Res.string.personality_loyal
            ),
            careTips = Res.string.lykoi_care_tips,
            funFact = Res.string.lykoi_fun_fact,
            image = Res.drawable.lykoi_cat
        ),
        CatBreed(
            id = 31,
            name = Res.string.maine_coon_name,
            shortDescription = Res.string.maine_coon_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_large,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_friendly,
                Res.string.personality_intelligent,
                Res.string.personality_social,
                Res.string.personality_laid_back
            ),
            careTips = Res.string.maine_coon_care_tips,
            funFact = Res.string.maine_coon_fun_fact,
            image = Res.drawable.maine_coon_cat
        ),
        CatBreed(
            id = 32,
            name = Res.string.munchkin_name,
            shortDescription = Res.string.munchkin_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_small,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_playful,
                Res.string.personality_outgoing,
                Res.string.personality_confident,
                Res.string.personality_sweet,
                Res.string.personality_energetic
            ),
            careTips = Res.string.munchkin_care_tips,
            funFact = Res.string.munchkin_fun_fact,
            image = Res.drawable.munchkin_cat
        ),
        CatBreed(
            id = 33,
            name = Res.string.manx_name,
            shortDescription = Res.string.manx_short_description,
            origin = Res.string.origin_united_kingdom,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_loyal,
                Res.string.personality_intelligent,
                Res.string.personality_playful,
                Res.string.personality_gentle,
                Res.string.personality_adaptable
            ),
            careTips = Res.string.manx_care_tips,
            funFact = Res.string.manx_fun_fact,
            image = Res.drawable.manx_cat
        ),
        CatBreed(
            id = 34,
            name = Res.string.nebelung_name,
            shortDescription = Res.string.nebelung_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_semi_long_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_intelligent,
                Res.string.personality_loyal,
                Res.string.personality_calm,
                Res.string.personality_affectionate
            ),
            careTips = Res.string.nebelung_care_tips,
            funFact = Res.string.nebelung_fun_fact,
            image = Res.drawable.nebelung_cat
        ),
        CatBreed(
            id = 35,
            name = Res.string.norwegian_forest_name,
            shortDescription = Res.string.norwegian_forest_short_description,
            origin = Res.string.origin_norway,
            size = Res.string.size_large,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_independent,
                Res.string.personality_intelligent,
                Res.string.personality_gentle,
                Res.string.personality_athletic,
                Res.string.personality_patient
            ),
            careTips = Res.string.norwegian_forest_care_tips,
            funFact = Res.string.norwegian_forest_fun_fact,
            image = Res.drawable.norwegian_forest_cat
        ),
        CatBreed(
            id = 36,
            name = Res.string.oriental_shorthair_name,
            shortDescription = Res.string.oriental_shorthair_short_description,
            origin = Res.string.origin_united_kingdom,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_vocal,
                Res.string.personality_intelligent,
                Res.string.personality_active,
                Res.string.personality_social,
                Res.string.personality_curious
            ),
            careTips = Res.string.oriental_shorthair_care_tips,
            funFact = Res.string.oriental_shorthair_fun_fact,
            image = Res.drawable.oriental_shorthair_cat
        ),
        CatBreed(
            id = 37,
            name = Res.string.ocicat_name,
            shortDescription = Res.string.ocicat_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_large,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_athletic,
                Res.string.personality_social,
                Res.string.personality_intelligent,
                Res.string.personality_outgoing,
                Res.string.personality_vocal
            ),
            careTips = Res.string.ocicat_care_tips,
            funFact = Res.string.ocicat_fun_fact,
            image = Res.drawable.ocicat_cat
        ),
        CatBreed(
            id = 38,
            name = Res.string.persian_name,
            shortDescription = Res.string.persian_short_description,
            origin = Res.string.origin_iran,
            size = Res.string.size_medium,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_calm,
                Res.string.personality_gentle,
                Res.string.personality_affectionate,
                Res.string.personality_quiet,
                Res.string.personality_laid_back
            ),
            careTips = Res.string.persian_care_tips,
            funFact = Res.string.persian_fun_fact,
            image = Res.drawable.persian_cat
        ),
        CatBreed(
            id = 39,
            name = Res.string.peterbald_name,
            shortDescription = Res.string.peterbald_short_description,
            origin = Res.string.origin_russia,
            size = Res.string.size_medium,
            coat = Res.string.coat_hairless,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_energetic,
                Res.string.personality_social,
                Res.string.personality_intelligent,
                Res.string.personality_loyal
            ),
            careTips = Res.string.peterbald_care_tips,
            funFact = Res.string.peterbald_fun_fact,
            image = Res.drawable.peterbald_cat
        ),
        CatBreed(
            id = 40,
            name = Res.string.ragamuffin_name,
            shortDescription = Res.string.ragamuffin_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_large,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_docile,
                Res.string.personality_affectionate,
                Res.string.personality_laid_back,
                Res.string.personality_sweet,
                Res.string.personality_patient
            ),
            careTips = Res.string.ragamuffin_care_tips,
            funFact = Res.string.ragamuffin_fun_fact,
            image = Res.drawable.ragamuffin_cat
        ),
        CatBreed(
            id = 41,
            name = Res.string.ragdoll_name,
            shortDescription = Res.string.ragdoll_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_large,
            coat = Res.string.coat_semi_long_hair,
            personality = listOf(
                Res.string.personality_docile,
                Res.string.personality_affectionate,
                Res.string.personality_calm,
                Res.string.personality_gentle,
                Res.string.personality_loyal
            ),
            careTips = Res.string.ragdoll_care_tips,
            funFact = Res.string.ragdoll_fun_fact,
            image = Res.drawable.ragdoll_cat
        ),
        CatBreed(
            id = 42,
            name = Res.string.russian_blue_name,
            shortDescription = Res.string.russian_blue_short_description,
            origin = Res.string.origin_russia,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_reserved,
                Res.string.personality_intelligent,
                Res.string.personality_loyal,
                Res.string.personality_gentle,
                Res.string.personality_independent
            ),
            careTips = Res.string.russian_blue_care_tips,
            funFact = Res.string.russian_blue_fun_fact,
            image = Res.drawable.russian_blue_cat
        ),
        CatBreed(
            id = 43,
            name = Res.string.savannah_name,
            shortDescription = Res.string.savannah_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_large,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_energetic,
                Res.string.personality_athletic,
                Res.string.personality_intelligent,
                Res.string.personality_curious,
                Res.string.personality_adventurous
            ),
            careTips = Res.string.savannah_care_tips,
            funFact = Res.string.savannah_fun_fact,
            image = Res.drawable.savannah_cat
        ),
        CatBreed(
            id = 44,
            name = Res.string.selkirk_rex_name,
            shortDescription = Res.string.selkirk_rex_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_large,
            coat = Res.string.coat_curly,
            personality = listOf(
                Res.string.personality_mellow,
                Res.string.personality_affectionate,
                Res.string.personality_playful,
                Res.string.personality_patient,
                Res.string.personality_easygoing
            ),
            careTips = Res.string.selkirk_rex_care_tips,
            funFact = Res.string.selkirk_rex_fun_fact,
            image = Res.drawable.selkirk_rex_cat
        ),
        CatBreed(
            id = 45,
            name = Res.string.siamese_name,
            shortDescription = Res.string.siamese_short_description,
            origin = Res.string.origin_thailand,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_vocal,
                Res.string.personality_intelligent,
                Res.string.personality_social,
                Res.string.personality_affectionate,
                Res.string.personality_extroverted
            ),
            careTips = Res.string.siamese_care_tips,
            funFact = Res.string.siamese_fun_fact,
            image = Res.drawable.siamese_cat
        ),
        CatBreed(
            id = 46,
            name = Res.string.siberian_name,
            shortDescription = Res.string.siberian_short_description,
            origin = Res.string.origin_russia,
            size = Res.string.size_large,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_adventurous,
                Res.string.personality_loyal,
                Res.string.personality_affectionate,
                Res.string.personality_playful,
                Res.string.personality_hypoallergenic
            ),
            careTips = Res.string.siberian_care_tips,
            funFact = Res.string.siberian_fun_fact,
            image = Res.drawable.siberian_cat
        ),
        CatBreed(
            id = 47,
            name = Res.string.singapura_name,
            shortDescription = Res.string.singapura_short_description,
            origin = Res.string.origin_singapore,
            size = Res.string.size_small,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_energetic,
                Res.string.personality_curious,
                Res.string.personality_affectionate,
                Res.string.personality_extroverted,
                Res.string.personality_mischievous
            ),
            careTips = Res.string.singapura_care_tips,
            funFact = Res.string.singapura_fun_fact,
            image = Res.drawable.singapura_cat
        ),
        CatBreed(
            id = 48,
            name = Res.string.snowshoe_name,
            shortDescription = Res.string.snowshoe_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_intelligent,
                Res.string.personality_social,
                Res.string.personality_adaptable,
                Res.string.personality_vocal
            ),
            careTips = Res.string.snowshoe_care_tips,
            funFact = Res.string.snowshoe_fun_fact,
            image = Res.drawable.snowshoe_cat
        ),
        CatBreed(
            id = 49,
            name = Res.string.somali_name,
            shortDescription = Res.string.somali_short_description,
            origin = Res.string.origin_somalia,
            size = Res.string.size_medium,
            coat = Res.string.coat_semi_long_hair,
            personality = listOf(
                Res.string.personality_energetic,
                Res.string.personality_intelligent,
                Res.string.personality_curious,
                Res.string.personality_athletic,
                Res.string.personality_mischievous
            ),
            careTips = Res.string.somali_care_tips,
            funFact = Res.string.somali_fun_fact,
            image = Res.drawable.somali_cat
        ),
        CatBreed(
            id = 50,
            name = Res.string.sphynx_name,
            shortDescription = Res.string.sphynx_short_description,
            origin = Res.string.origin_canada,
            size = Res.string.size_medium,
            coat = Res.string.coat_hairless,
            personality = listOf(
                Res.string.personality_extroverted,
                Res.string.personality_energetic,
                Res.string.personality_affectionate,
                Res.string.personality_social,
                Res.string.personality_demanding
            ),
            careTips = Res.string.sphynx_care_tips,
            funFact = Res.string.sphynx_fun_fact,
            image = Res.drawable.sphynx_cat
        ),
        CatBreed(
            id = 51,
            name = Res.string.thai_name,
            shortDescription = Res.string.thai_short_description,
            origin = Res.string.origin_thailand,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_social,
                Res.string.personality_intelligent,
                Res.string.personality_vocal,
                Res.string.personality_playful
            ),
            careTips = Res.string.thai_care_tips,
            funFact = Res.string.thai_fun_fact,
            image = Res.drawable.thai_cat
        ),
        CatBreed(
            id = 52,
            name = Res.string.toyger_name,
            shortDescription = Res.string.toyger_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_playful,
                Res.string.personality_intelligent,
                Res.string.personality_social,
                Res.string.personality_calm
            ),
            careTips = Res.string.toyger_care_tips,
            funFact = Res.string.toyger_fun_fact,
            image = Res.drawable.toyger_cat
        ),
        CatBreed(
            id = 53,
            name = Res.string.turkish_angora_name,
            shortDescription = Res.string.turkish_angora_short_description,
            origin = Res.string.origin_turkey,
            size = Res.string.size_medium,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_intelligent,
                Res.string.personality_playful,
                Res.string.personality_independent,
                Res.string.personality_energetic,
                Res.string.personality_social
            ),
            careTips = Res.string.turkish_angora_care_tips,
            funFact = Res.string.turkish_angora_fun_fact,
            image = Res.drawable.turkish_angora_cat
        ),
        CatBreed(
            id = 54,
            name = Res.string.turkish_van_name,
            shortDescription = Res.string.turkish_van_short_description,
            origin = Res.string.origin_turkey,
            size = Res.string.size_large,
            coat = Res.string.coat_semi_long_hair,
            personality = listOf(
                Res.string.personality_energetic,
                Res.string.personality_playful,
                Res.string.personality_intelligent,
                Res.string.personality_affectionate,
                Res.string.personality_active
            ),
            careTips = Res.string.turkish_van_care_tips,
            funFact = Res.string.turkish_van_fun_fact,
            image = Res.drawable.turkish_van_cat
        ),
        CatBreed(
            id = 55,
            name = Res.string.ural_rex_name,
            shortDescription = Res.string.ural_rex_short_description,
            origin = Res.string.origin_russia,
            size = Res.string.size_medium,
            coat = Res.string.coat_curly,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_affectionate,
                Res.string.personality_calm,
                Res.string.personality_intelligent,
                Res.string.personality_social
            ),
            careTips = Res.string.ural_rex_care_tips,
            funFact = Res.string.ural_rex_fun_fact,
            image = Res.drawable.ural_rex_cat
        ),
        CatBreed(
            id = 56,
            name = Res.string.highland_fold_name,
            shortDescription = Res.string.highland_fold_short_description,
            origin = Res.string.origin_scotland,
            size = Res.string.size_medium,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_calm,
                Res.string.personality_affectionate,
                Res.string.personality_patient,
                Res.string.personality_adaptable
            ),
            careTips = Res.string.highland_fold_care_tips,
            funFact = Res.string.highland_fold_fun_fact,
            image = Res.drawable.highland_fold_cat
        ),
        CatBreed(
            id = 57,
            name = Res.string.chartreux_name,
            shortDescription = Res.string.chartreux_short_description,
            origin = Res.string.origin_france,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_calm,
                Res.string.personality_gentle,
                Res.string.personality_intelligent,
                Res.string.personality_loyal,
                Res.string.personality_quiet
            ),
            careTips = Res.string.chartreux_care_tips,
            funFact = Res.string.chartreux_fun_fact,
            image = Res.drawable.chartreux_cat
        ),
        CatBreed(
            id = 58,
            name = Res.string.scottish_fold_name,
            shortDescription = Res.string.scottish_fold_short_description,
            origin = Res.string.origin_scotland,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_calm,
                Res.string.personality_affectionate,
                Res.string.personality_sweet,
                Res.string.personality_adaptable
            ),
            careTips = Res.string.scottish_fold_care_tips,
            funFact = Res.string.scottish_fold_fun_fact,
            image = Res.drawable.scottish_fold_cat
        ),
        CatBreed(
            id = 59,
            name = Res.string.exotic_shorthair_name,
            shortDescription = Res.string.exotic_shorthair_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_affectionate,
                Res.string.personality_calm,
                Res.string.personality_sweet,
                Res.string.personality_quiet
            ),
            careTips = Res.string.exotic_shorthair_care_tips,
            funFact = Res.string.exotic_shorthair_fun_fact,
            image = Res.drawable.exotic_shorthair_cat
        ),
        CatBreed(
            id = 60,
            name = Res.string.javanese_name,
            shortDescription = Res.string.javanese_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_semi_long_hair,
            personality = listOf(
                Res.string.personality_vocal,
                Res.string.personality_intelligent,
                Res.string.personality_active,
                Res.string.personality_social,
                Res.string.personality_playful
            ),
            careTips = Res.string.javanese_care_tips,
            funFact = Res.string.javanese_fun_fact,
            image = Res.drawable.javanese_cat
        ),
        CatBreed(
            id = 61,
            name = Res.string.japanese_bobtail_name,
            shortDescription = Res.string.japanese_bobtail_short_description,
            origin = Res.string.origin_japan,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_playful,
                Res.string.personality_intelligent,
                Res.string.personality_social,
                Res.string.personality_active,
                Res.string.personality_affectionate
            ),
            careTips = Res.string.japanese_bobtail_care_tips,
            funFact = Res.string.japanese_bobtail_fun_fact,
            image = Res.drawable.japanese_bobtail_cat
        ),
        CatBreed(
            id = 62,
            name = Res.string.chausie_name,
            shortDescription = Res.string.chausie_short_description,
            origin = Res.string.origin_egypt,
            size = Res.string.size_large,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_energetic,
                Res.string.personality_intelligent,
                Res.string.personality_independent,
                Res.string.personality_active,
                Res.string.personality_loyal
            ),
            careTips = Res.string.chausie_care_tips,
            funFact = Res.string.chausie_fun_fact,
            image = Res.drawable.chausie_cat
        ),
        CatBreed(
            id = 63,
            name = Res.string.celtic_name,
            shortDescription = Res.string.celtic_short_description,
            origin = Res.string.origin_england,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_independent,
                Res.string.personality_intelligent,
                Res.string.personality_calm,
                Res.string.personality_adaptable,
                Res.string.personality_hardy
            ),
            careTips = Res.string.celtic_care_tips,
            funFact = Res.string.celtic_fun_fact,
            image = Res.drawable.celtic_cat
        ),
        CatBreed(
            id = 64,
            name = Res.string.minskin_name,
            shortDescription = Res.string.minskin_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_small,
            coat = Res.string.coat_hairless,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_playful,
                Res.string.personality_intelligent,
                Res.string.personality_social,
                Res.string.personality_gentle
            ),
            careTips = Res.string.minskin_care_tips,
            funFact = Res.string.minskin_fun_fact,
            image = Res.drawable.minskin_cat
        ),
        CatBreed(
            id = 65,
            name = Res.string.napoleon_name,
            shortDescription = Res.string.napoleon_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_small,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_affectionate,
                Res.string.personality_calm,
                Res.string.personality_social,
                Res.string.personality_docile
            ),
            careTips = Res.string.napoleon_care_tips,
            funFact = Res.string.napoleon_fun_fact,
            image = Res.drawable.napoleon_cat
        ),
        CatBreed(
            id = 66,
            name = Res.string.oriental_longhair_name,
            shortDescription = Res.string.oriental_longhair_short_description,
            origin = Res.string.origin_england,
            size = Res.string.size_medium,
            coat = Res.string.coat_semi_long_hair,
            personality = listOf(
                Res.string.personality_vocal,
                Res.string.personality_social,
                Res.string.personality_intelligent,
                Res.string.personality_active,
                Res.string.personality_affectionate
            ),
            careTips = Res.string.oriental_longhair_care_tips,
            funFact = Res.string.oriental_longhair_fun_fact,
            image = Res.drawable.oriental_longhair_cat
        ),
        CatBreed(
            id = 67,
            name = Res.string.serengeti_name,
            shortDescription = Res.string.serengeti_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_large,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_energetic,
                Res.string.personality_confident,
                Res.string.personality_vocal,
                Res.string.personality_athletic,
                Res.string.personality_curious
            ),
            careTips = Res.string.serengeti_care_tips,
            funFact = Res.string.serengeti_fun_fact,
            image = Res.drawable.serengeti_cat
        ),
        CatBreed(
            id = 68,
            name = Res.string.toybob_name,
            shortDescription = Res.string.toybob_short_description,
            origin = Res.string.origin_russia,
            size = Res.string.size_small,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_playful,
                Res.string.personality_affectionate,
                Res.string.personality_loyal,
                Res.string.personality_intelligent,
                Res.string.personality_social
            ),
            careTips = Res.string.toybob_care_tips,
            funFact = Res.string.toybob_fun_fact,
            image = Res.drawable.toybob_cat
        ),
        CatBreed(
            id = 69,
            name = Res.string.ukrainian_levkoy_name,
            shortDescription = Res.string.ukrainian_levkoy_short_description,
            origin = Res.string.origin_ukraine,
            size = Res.string.size_medium,
            coat = Res.string.coat_hairless,
            personality = listOf(
                Res.string.personality_gentle,
                Res.string.personality_patient,
                Res.string.personality_affectionate,
                Res.string.personality_intelligent,
                Res.string.personality_calm
            ),
            careTips = Res.string.ukrainian_levkoy_care_tips,
            funFact = Res.string.ukrainian_levkoy_fun_fact,
            image = Res.drawable.ukrainian_levkoy_cat
        ),
        CatBreed(
            id = 70,
            name = Res.string.elf_name,
            shortDescription = Res.string.elf_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_hairless,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_social,
                Res.string.personality_playful,
                Res.string.personality_intelligent,
                Res.string.personality_extroverted
            ),
            careTips = Res.string.elf_care_tips,
            funFact = Res.string.elf_fun_fact,
            image = Res.drawable.elf_cat
        ),
        CatBreed(
            id = 71,
            name = Res.string.mekong_bobtail_name,
            shortDescription = Res.string.mekong_bobtail_short_description,
            origin = Res.string.origin_thailand,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_loyal,
                Res.string.personality_gentle,
                Res.string.personality_intelligent,
                Res.string.personality_calm,
                Res.string.personality_patient
            ),
            careTips = Res.string.mekong_bobtail_care_tips,
            funFact = Res.string.mekong_bobtail_fun_fact,
            image = Res.drawable.mekong_bobtail_cat
        ),
        CatBreed(
            id = 72,
            name = Res.string.seychellois_name,
            shortDescription = Res.string.seychellois_short_description,
            origin = Res.string.origin_england,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_vocal,
                Res.string.personality_social,
                Res.string.personality_intelligent,
                Res.string.personality_active,
                Res.string.personality_demanding
            ),
            careTips = Res.string.seychellois_care_tips,
            funFact = Res.string.seychellois_fun_fact,
            image = Res.drawable.seychellois_cat
        ),
        CatBreed(
            id = 73,
            name = Res.string.himalayan_name,
            shortDescription = Res.string.himalayan_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_long_hair,
            personality = listOf(
                Res.string.personality_calm,
                Res.string.personality_gentle,
                Res.string.personality_quiet,
                Res.string.personality_affectionate,
                Res.string.personality_docile
            ),
            careTips = Res.string.himalayan_care_tips,
            funFact = Res.string.himalayan_fun_fact,
            image = Res.drawable.himalayan_cat
        ),
        CatBreed(
            id = 74,
            name = Res.string.dwelf_name,
            shortDescription = Res.string.dwelf_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_small,
            coat = Res.string.coat_hairless,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_social,
                Res.string.personality_gentle,
                Res.string.personality_intelligent,
                Res.string.personality_extroverted
            ),
            careTips = Res.string.dwelf_care_tips,
            funFact = Res.string.dwelf_fun_fact,
            image = Res.drawable.dwelf_cat
        ),
        CatBreed(
            id = 75,
            name = Res.string.kohana_name,
            shortDescription = Res.string.kohana_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_small,
            coat = Res.string.coat_hairless,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_social,
                Res.string.personality_intelligent,
                Res.string.personality_energetic,
                Res.string.personality_playful,
                Res.string.personality_docile
            ),
            careTips = Res.string.kohana_care_tips,
            funFact = Res.string.kohana_fun_fact,
            image = Res.drawable.kohana_cat
        ),
        CatBreed(
            id = 76,
            name = Res.string.safari_name,
            shortDescription = Res.string.safari_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_large,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_intelligent,
                Res.string.personality_athletic,
                Res.string.personality_loyal,
                Res.string.personality_active,
                Res.string.personality_independent,
                Res.string.personality_adventurous
            ),
            careTips = Res.string.safari_care_tips,
            funFact = Res.string.safari_fun_fact,
            image = Res.drawable.safari_cat
        ),
        CatBreed(
            id = 77,
            name = Res.string.skif_tai_don_name,
            shortDescription = Res.string.skif_tai_don_short_description,
            origin = Res.string.origin_russia,
            size = Res.string.size_small,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_affectionate,
                Res.string.personality_playful,
                Res.string.personality_social,
                Res.string.personality_gentle,
                Res.string.personality_intelligent,
                Res.string.personality_loyal
            ),
            careTips = Res.string.skif_tai_don_care_tips,
            funFact = Res.string.skif_tai_don_fun_fact,
            image = Res.drawable.skif_tai_don_cat
        ),
        CatBreed(
            id = 78,
            name = Res.string.oriental_tabby_name,
            shortDescription = Res.string.oriental_tabby_short_description,
            origin = Res.string.origin_united_states,
            size = Res.string.size_medium,
            coat = Res.string.coat_short_hair,
            personality = listOf(
                Res.string.personality_intelligent,
                Res.string.personality_vocal,
                Res.string.personality_social,
                Res.string.personality_athletic,
                Res.string.personality_curious,
                Res.string.personality_extroverted
            ),
            careTips = Res.string.oriental_tabby_care_tips,
            funFact = Res.string.oriental_tabby_fun_fact,
            image = Res.drawable.oriental_tabby_cat
        )
    )
}