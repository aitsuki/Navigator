package com.aitsuki.navigator.processor

import com.aitsuki.navigator.annotation.Argument
import com.aitsuki.navigator.annotation.Navigator
import com.squareup.kotlinpoet.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

class NavProcessor : AbstractProcessor() {

    override fun process(set: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val elements = roundEnv.getElementsAnnotatedWith(Navigator::class.java)
        for (element in elements) {
            val file = createFile(NavigatorElement(element as TypeElement))
            file.writeTo(processingEnv.filer)
        }
        return true
    }

    private fun createFile(element: NavigatorElement): FileSpec {
        val context = ClassName("android.content", "Context")
        return FileSpec.builder(element.packageName, element.generateClassName)
            .addType(
                TypeSpec.classBuilder(element.generateClassName)
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .addParameter("context", context)
                            .build()
                    )
                    .addProperty(
                        PropertySpec.builder("context", context, KModifier.PRIVATE)
                            .initializer("context")
                            .build()
                    )
                    .build()
            )
            .build()
    }

    private fun TypeSpec.constructorWithProperties() {

    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Navigator::class.java.name, Argument::class.java.name)
    }
}