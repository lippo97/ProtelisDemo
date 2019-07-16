plugins {
    java
    application
}

dependencies {
    compile("org.protelis:protelis:12.1.0")
    compile("org.jgrapht:jgrapht-core:1.3.1")
    runtime(files("src/main/resources"))
}


application {
    mainClassName = "demo.HelloProtelis"
}

defaultTasks("run")
