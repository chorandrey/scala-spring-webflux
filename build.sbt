ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

val springVersion = "6.2.6"
val nettyVersion = "1.2.5"

enablePlugins(JavaAppPackaging)

lazy val root = (project in file("."))
  .settings(
    name := "scala-spring-webflux",
    idePackagePrefix := Some("com.andy"),
    libraryDependencies ++= Seq(
      "io.projectreactor.netty" % "reactor-netty-http" % nettyVersion,

      "org.springframework" % "spring-core" % springVersion,
      "org.springframework" % "spring-context" % springVersion,
      "org.springframework" % "spring-webflux" % springVersion,
    )
  )
