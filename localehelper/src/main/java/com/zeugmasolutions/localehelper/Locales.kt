package com.zeugmasolutions.localehelper

import java.util.*

object Locales {
    val Afrikaans: Locale by lazy { Locale("af", "ZA") }
    val Albanian: Locale by lazy { Locale("sq", "AL") }
    val Arabic: Locale by lazy { Locale("ar", "SA") }
    val Armenian: Locale by lazy { Locale("hy", "AM") }
    val Belarus: Locale by lazy { Locale("be", "BY") }
    val Bulgarian: Locale by lazy { Locale("bg", "BG") }
    val Danish: Locale by lazy { Locale("da", "DK") }
    val Dutch: Locale by lazy { Locale("nl", "NL") }
    val English: Locale by lazy { Locale("en", "US") }
    val Estonian: Locale by lazy { Locale("et", "EE") }
    val Filipino: Locale by lazy { Locale("fil", "PH") }
    val Finnish: Locale by lazy { Locale("fi", "FI") }
    val French: Locale by lazy { Locale("fr", "FR") }
    val Georgian: Locale by lazy { Locale("ka", "GE") }
    val German: Locale by lazy { Locale("de", "DE") }
    val Greek: Locale by lazy { Locale("el", "GR") }
    val Hawaiian: Locale by lazy { Locale("haw", "US") }
    val Hebrew: Locale by lazy { Locale("he", "IL") }
    val Hindi: Locale by lazy { Locale("hi", "IN") }
    val Hungarian: Locale by lazy { Locale("hu", "HU") }
    val Icelandic: Locale by lazy { Locale("is", "IS") }
    val Indonesian: Locale by lazy { Locale("id", "ID") }
    val Irish: Locale by lazy { Locale("ga", "IE") }
    val Italian: Locale by lazy { Locale("it", "IT") }
    val Japanese: Locale by lazy { Locale("ja", "JP") }
    val Korean: Locale by lazy { Locale("ko", "KR") }
    val Latvian: Locale by lazy { Locale("lv", "LV") }
    val Lithuanian: Locale by lazy { Locale("lt", "LT") }
    val Luo: Locale by lazy { Locale("luo", "KE") }
    val Macedonian: Locale by lazy { Locale("mk", "MK") }
    val Malagasy: Locale by lazy { Locale("mg", "MG") }
    val Malay: Locale by lazy { Locale("ms", "MY") }
    val Nepali: Locale by lazy { Locale("ne", "NP") }
    val NorwegianBokmal: Locale by lazy { Locale("nb", "NO") }
    val NorwegianNynorsk: Locale by lazy { Locale("nn", "NO") }
    val Persian: Locale by lazy { Locale("fa", "IR") }
    val Polish: Locale by lazy { Locale("pl", "PL") }
    val Portuguese: Locale by lazy { Locale("pt", "PT") }
    val Romanian: Locale by lazy { Locale("ro", "RO") }
    val Russian: Locale by lazy { Locale("ru", "RU") }
    val Slovak: Locale by lazy { Locale("sk", "SK") }
    val Slovenian: Locale by lazy { Locale("sl", "SI") }
    val Spanish: Locale by lazy { Locale("es", "ES") }
    val Swedish: Locale by lazy { Locale("sv", "SE") }
    val Thai: Locale by lazy { Locale("th", "TH") }
    val Turkish: Locale by lazy { Locale("tr", "TR") }
    val Ukrainian: Locale by lazy { Locale("uk", "UA") }
    val Urdu: Locale by lazy { Locale("ur", "IN") }
    val Vietnamese: Locale by lazy { Locale("vi", "VN") }
    val Zulu: Locale by lazy { Locale("zu", "ZA") }

    /**
     * List of language codes for Right-To-Left languages
     */
    val RTL: Set<String> by lazy {
        hashSetOf(
            "ar",
            "dv",
            "fa",
            "ha",
            "he",
            "iw",
            "ji",
            "ps",
            "sd",
            "ug",
            "ur",
            "yi"
        )
    }
}