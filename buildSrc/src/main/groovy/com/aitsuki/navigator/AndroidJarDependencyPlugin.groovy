package com.aitsuki.navigator

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidJarDependencyPlugin implements Plugin<Project> {

    private static final SDK_DIR_PROPERTY = "sdk.dir"
    private static final ANDROID_JAR_FILENAME = "android.jar"

    @Override
    void apply(Project project) {
        project.logger.lifecycle("=============================>>>apply AndroidJarDependencyPlugin")
        project.dependencies.ext.androidJar = {
            // By default, concatenate android.jar path from build environment variables
            def sdkDir = loadProperties(project.rootProject.file("local.properties"))
                    .getProperty(SDK_DIR_PROPERTY)
            def platformDir = "android-${project.android.compileSdkVersion}"

            def directory = "$sdkDir/platforms/$platformDir"
            project.logger.lifecycle("=============================>>> $directory")

            if (!new File(directory, ANDROID_JAR_FILENAME).exists()) {
                throw new RuntimeException("can't find android.jar in folder '$directory'!")
            }

            def dependency = project.fileTree(dir: directory, includes: [ANDROID_JAR_FILENAME])
            return project.dependencies.create(dependency)
        }
    }

    private static Properties loadProperties(File file) {
        def properties = new Properties()
        file.withReader { properties.load(it) }
        return properties
    }
}