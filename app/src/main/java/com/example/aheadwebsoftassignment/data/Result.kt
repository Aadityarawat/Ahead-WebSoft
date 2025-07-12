package com.example.aheadwebsoftassignment.data

data class Result(
    val title: String,
    val user_photo: String,
    val friend_req_count: Int?,
    val loggedin_user_id: Int?,
    val menus: List<Menu>,
    val message_count: Int?,
    val notification_count: Int?
)