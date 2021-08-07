plugins {
	id("fabric-loom")
		val kotlinVersion: String by System.getProperties()
		kotlin("jvm").version(kotlinVersion)
}
base {
	val archivesBaseName: String by project
		archivesName.set(archivesBaseName)
}
val modV: String by project
version = modV
val group: String by project
minecraft {}
repositories {}
dependencies {
	  val mcV: String by project
		minecraft("com.mojang:minecraft:$mcV")
		val yarnmappings: String by project
		mappings("net.fabricmc:yarn:$yarnmappings:v2")
		val loaderV: String by project
		modImplementation("net.fabricmc:fabric-loader:$loaderV")
		val fabricV: String by project
		modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricV")
		val fabricKotlinV: String by project
		modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinV")
}
tasks {
	val javaVersion = JavaVersion.VERSION_16
		withType<JavaCompile> {
			options.encoding = "UTF-8"
				sourceCompatibility = javaVersion.toString()
				targetCompatibility = javaVersion.toString()
				options.release.set(javaVersion.toString().toInt())
		}
	withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions { jvmTarget = javaVersion.toString() }
		sourceCompatibility = javaVersion.toString()
			targetCompatibility = javaVersion.toString()
	}
	jar { from("LICENSE") { rename { "${it}_${base.archivesName}" } } }
	processResources {
		inputs.property("version", project.version)
			filesMatching("fabric.mod.json") { expand(mutableMapOf("version" to project.version)) }
	}
	java {
		toolchain { languageVersion.set(JavaLanguageVersion.of(javaVersion.toString())) }
		sourceCompatibility = javaVersion
			targetCompatibility = javaVersion
			withSourcesJar()
	}
}
