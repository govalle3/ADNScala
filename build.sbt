name := """ADNScalaCorreccion"""
organization := "com.ceiba"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"
val catsVersion = "2.1.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies += "com.typesafe.play" %% "play-slick" % "4.0.2"

libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.36.0.2"
libraryDependencies += "joda-time" % "joda-time" % "2.10.6"
libraryDependencies += "javax.inject" % "javax.inject" % "1"
libraryDependencies += "org.typelevel" %% "cats-core" % catsVersion
libraryDependencies += "org.typelevel" %% "cats-macros" % catsVersion
libraryDependencies += "org.typelevel" %% "cats-kernel" % catsVersion
libraryDependencies += "io.monix" %% "monix" % "3.2.1"
libraryDependencies += "io.swagger" %% "swagger-scala-module" % "1.0.6"
libraryDependencies += "org.mockito" % "mockito-core" % "3.3.3" % Test
libraryDependencies += "org.specs2" %% "specs2-mock" % "4.9.4" % Test






// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
