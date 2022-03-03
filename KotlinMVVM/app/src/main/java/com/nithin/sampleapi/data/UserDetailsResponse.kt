package com.nithin.sampleapi.data

data class UserDetailsResponse(
    val `data`: Data?,
    val support: Support?
) {
    data class Data(
        val avatar: String?,
        val email: String?,
        val first_name: String?,
        val id: Int?,
        val last_name: String?
    )

    data class Support(
        val text: String?,
        val url: String?
    )

}