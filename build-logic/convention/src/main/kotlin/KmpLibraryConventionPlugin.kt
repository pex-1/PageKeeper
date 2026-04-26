import com.android.build.api.dsl.LibraryExtension
import com.example.pagekeeper.convention.configureKotlinAndroid
import com.example.pagekeeper.convention.configureKotlinMultiplatform
import com.example.pagekeeper.convention.libs
import com.example.pagekeeper.convention.pathToResourcePrefix
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("com.android.library")
            }

            configureKotlinMultiplatform()

            extensions.configure<LibraryExtension>{
                configureKotlinAndroid(this)

                resourcePrefix = this@with.pathToResourcePrefix()

                //Required to make the debug build of the app run in iOS simulator
                experimentalProperties["android.experimental.kmp.enableAndroidResources"]
            }

            dependencies {
                "commonMainImplementation"(libs.findLibrary("kotlinx-serialization-json").get())
                "commonTestImplementation"(libs.findLibrary("kotlin-test").get())
            }
        }
    }
}