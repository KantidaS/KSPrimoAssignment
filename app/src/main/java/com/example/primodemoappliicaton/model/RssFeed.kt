package com.example.primodemoappliicaton.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class Feed(
    @field:Element(name = "channel")
    var channel: Channel? = null
)

@Root(name = "channel", strict = false)
data class Channel(
    @field:ElementList(entry = "item", inline = true)
    var items: List<Item>? = null
)

@Root(name = "item", strict = false)
data class Item(
    @field:Element(name = "title", required = false)
    var title: String = "",

    @field:ElementList(name = "category", required = false, inline = true)
    var categories: List<String>? = null,

    @field:Element(name = "link", required = false)
    var link: String = ""
)