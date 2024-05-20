package com.example.primodemoappliicaton.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class Feed(
    @field:Element(name = "channel")
    var channel: Channel? = null
)

@Root(name = "channel", strict = false)
data class Channel(
    @field:Element(name = "title", required = false)
    var title: String = "",

    @field:ElementList(entry = "item", inline = true)
    var items: List<FeedItem>? = null,

    @field:Element(name = "image", required = false)
    var feedImage: FeedImage? = null,
)

@Entity(tableName = "feed_items")
@Root(name = "item", strict = false)
data class FeedItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @field:Element(name = "title", required = false)
    var title: String = "",

    @field:Element(name = "link", required = false)
    var link: String = "",

    @field:Element(name = "encoded", required = false)
    @Namespace(prefix = "content")
    var content: String = ""
)

@Root(name = "image", strict = false)
data class FeedImage(
    @field:Element(name = "url", required = false)
    var url: String = "",

    @field:Element(name = "title", required = false)
    var title: String = "",

    @field:Element(name = "link", required = false)
    var link: String = "",
)