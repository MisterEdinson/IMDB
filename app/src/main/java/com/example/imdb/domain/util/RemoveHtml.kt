package com.example.imdb.domain.util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun removeHtmlTags(input: String?): String {
    val document: Document = Jsoup.parse(input)
    val textWithoutHtml = document.text()
    return textWithoutHtml
}