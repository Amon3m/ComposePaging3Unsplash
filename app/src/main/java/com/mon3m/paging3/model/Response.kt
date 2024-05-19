package com.mon3m.paging3.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Response(

	@field:SerializedName("Response")
	val response: List<ResponseItem?>? = null
)

data class TexturesPatterns(

	@field:SerializedName("status")
	val status: String? = null
)

data class Links(

	@field:SerializedName("followers")
	val followers: String? = null,

	@field:SerializedName("portfolio")
	val portfolio: String? = null,

	@field:SerializedName("following")
	val following: String? = null,

	@field:SerializedName("self")
	val self: String? = null,

	@field:SerializedName("html")
	val html: String? = "",

	@field:SerializedName("photos")
	val photos: String? = null,

	@field:SerializedName("likes")
	val likes: String? = null,

	@field:SerializedName("download")
	val download: String? = null,

	@field:SerializedName("download_location")
	val downloadLocation: String? = null
)

data class ProfileImage(

	@field:SerializedName("small")
	val small: String? = null,

	@field:SerializedName("large")
	val large: String? = null,

	@field:SerializedName("medium")
	val medium: String? = null
)

data class ArchitectureInterior(

	@field:SerializedName("status")
	val status: String? = null
)

@Serializable
data class User(
	@SerialName("links")
	@Embedded
	val userLinks: UserLinks?,
	val username: String
)

@Serializable
data class UserLinks(
	val html: String?=""
)
data class StreetPhotography(

	@field:SerializedName("approved_on")
	val approvedOn: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Wallpapers(

	@field:SerializedName("approved_on")
	val approvedOn: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ResponseItem(

	@field:SerializedName("topic_submissions")
	val topicSubmissions: TopicSubmissions? = null,

	@field:SerializedName("current_user_collections")
	val currentUserCollections: List<Any?>? = null,

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("sponsorship")
	val sponsorship: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("liked_by_user")
	val likedByUser: Boolean? = null,

	@field:SerializedName("urls")
	val urls: Urls? = null,

	@field:SerializedName("alternative_slugs")
	val alternativeSlugs: AlternativeSlugs? = null,

	@field:SerializedName("alt_description")
	val altDescription: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("blur_hash")
	val blurHash: String? = null,

	@field:SerializedName("asset_type")
	val assetType: String? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("promoted_at")
	val promotedAt: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("breadcrumbs")
	val breadcrumbs: List<Any?>? = null,

	@field:SerializedName("height")
	val height: Int? = null,

	@field:SerializedName("likes")
	val likes: Int? = null
)

data class JsonMember3dRenders(

	@field:SerializedName("status")
	val status: String? = null
)

data class AlternativeSlugs(

	@field:SerializedName("de")
	val de: String? = null,

	@field:SerializedName("ko")
	val ko: String? = null,

	@field:SerializedName("pt")
	val pt: String? = null,

	@field:SerializedName("ja")
	val ja: String? = null,

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("it")
	val it: String? = null,

	@field:SerializedName("fr")
	val fr: String? = null,

	@field:SerializedName("es")
	val es: String? = null
)

data class Social(

	@field:SerializedName("twitter_username")
	val twitterUsername: Any? = null,

	@field:SerializedName("paypal_email")
	val paypalEmail: Any? = null,

	@field:SerializedName("instagram_username")
	val instagramUsername: String? = null,

	@field:SerializedName("portfolio_url")
	val portfolioUrl: String? = null
)

data class Urls(

	@field:SerializedName("small")
	val small: String? = null,

	@field:SerializedName("small_s3")
	val smallS3: String? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("raw")
	val raw: String? = null,

	@field:SerializedName("regular")
	val regular: String? = null,

	@field:SerializedName("full")
	val full: String? = null
)

data class TopicSubmissions(

	@field:SerializedName("wallpapers")
	val wallpapers: Wallpapers? = null,

	@field:SerializedName("street-photography")
	val streetPhotography: StreetPhotography? = null,

	@field:SerializedName("textures-patterns")
	val texturesPatterns: TexturesPatterns? = null,

	@field:SerializedName("3d-renders")
	val jsonMember3dRenders: JsonMember3dRenders? = null,

	@field:SerializedName("business-work")
	val businessWork: BusinessWork? = null,

	@field:SerializedName("architecture-interior")
	val architectureInterior: ArchitectureInterior? = null
)

data class BusinessWork(

	@field:SerializedName("status")
	val status: String? = null
)
