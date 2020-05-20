package com.aitsuki.navigator.processor.util

import javax.lang.model.element.Element
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement

fun TypeElement.packageName() = enclosingElement.packageName()

private fun Element?.packageName(): String {
    return when (this) {
        is TypeElement -> packageName()
        is PackageElement -> qualifiedName.toString()
        else -> this?.enclosingElement?.packageName() ?: ""
    }
}