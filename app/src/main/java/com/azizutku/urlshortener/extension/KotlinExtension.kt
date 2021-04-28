package com.azizutku.urlshortener.extension

fun Double.format(digits: Int) = "%.${digits}f".format(this).toDouble()