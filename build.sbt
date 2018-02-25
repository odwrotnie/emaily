organization in ThisBuild := "it.wext"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test
val scalaTestPlay = "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

lazy val emaily = (project in file("."))
  .aggregate(emailyApi, emailyImpl, emailyStreamApi, emailyStreamImpl, webGateway)

lazy val emailyApi = (project in file("emaily-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val emailyImpl = (project in file("emaily-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(emailyApi)

lazy val emailyStreamApi = (project in file("emaily-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val emailyStreamImpl = (project in file("emaily-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(emailyStreamApi, emailyApi)

lazy val webGateway = (project in file("web-gateway"))
  //.settings(commonSettings: _*)
  .enablePlugins(PlayScala && LagomPlay)
  .dependsOn(emailyApi)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomScaladslServer,
      macwire,
      scalaTest,

      "org.ocpsoft.prettytime" % "prettytime" % "3.2.7.Final",

      "org.webjars" % "foundation" % "6.2.3",
      "org.webjars" % "foundation-icon-fonts" % "d596a3cfb3"
    ),
    EclipseKeys.preTasks := Seq(compile in Compile)
  )

//

//run := {
//  val () = (run in ui in Compile).evaluated
//  val () = (runAll in emaily in Compile).value
//  ()
//}
