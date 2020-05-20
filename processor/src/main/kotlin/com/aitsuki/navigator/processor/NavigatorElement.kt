package com.aitsuki.navigator.processor

import com.aitsuki.navigator.annotation.Navigator
import com.aitsuki.navigator.processor.util.GEN_CLASS_SUFFIX
import com.aitsuki.navigator.processor.util.packageName
import com.squareup.kotlinpoet.ClassName
import javax.lang.model.element.TypeElement

class NavigatorElement(val element: TypeElement) {

    val packageName = element.packageName()
    val inputClassName = element.simpleName.toString()
    val generateClassName = inputClassName + GEN_CLASS_SUFFIX
    val navigator = element.getAnnotation(Navigator::class.java)

    companion object {
        val androidContext = ClassName("android.content", "Context")
    }
}