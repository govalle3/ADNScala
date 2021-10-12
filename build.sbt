name := """ADNScalaCorreccion"""
organization := "com.ceiba"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"
val catsVersion = "2.6.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.36.0.2"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.6.1"
libraryDependencies += "io.monix" %% "monix" % "3.4.0"
libraryDependencies += "io.swagger" %% "swagger-scala-module" % "1.0.6"






// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
