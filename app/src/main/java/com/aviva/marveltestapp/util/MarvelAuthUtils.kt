package com.aviva.marveltestapp.util

import java.security.MessageDigest

fun generateMarvelHash(timestamp: String, publicKey: String, privateKey: String): String {
    val input = timestamp + privateKey + publicKey
    return MessageDigest.getInstance("MD5").digest(input.toByteArray()).joinToString("") {
        "%02x".format(it)
    }
}