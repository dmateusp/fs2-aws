name         := "fs2-aws"
scalaVersion := "2.12.10"

lazy val root = (project in file("."))
  .aggregate(`fs2-aws`, `fs2-aws-testkit`)
  .settings(
    skip in publish := true
  )

lazy val `fs2-aws` = (project in file("fs2-aws"))
  .settings(commonSettings)
  .settings(publishSettings)

lazy val `fs2-aws-testkit` = (project in file("fs2-aws-testkit"))
  .dependsOn(`fs2-aws`)
  .settings(commonSettings)
  .settings(publishSettings)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.10")
addCommandAlias("format", ";scalafmt;test:scalafmt;scalafmtSbt")
addCommandAlias("checkFormat", ";scalafmtCheck;test:scalafmtCheck;scalafmtSbtCheck")

lazy val commonSettings = Seq(
  organization := "io.laserdisc",
  scalacOptions ++= Seq(
    "-target:jvm-1.8",
    "-encoding",
    "UTF-8",                         // source files are in UTF-8
    "-deprecation",                  // warn about use of deprecated APIs
    "-unchecked",                    // warn about unchecked type parameters
    "-feature",                      // warn about misused language features
    "-language:higherKinds",         // allow higher kinded types without `import scala.language.higherKinds`
    "-language:implicitConversions", // allow use of implicit conversions
    "-Xlint",                        // enable handy linter warnings
    "-Xfatal-warnings",              // turn compiler warnings into errors
    "-Ypartial-unification"          // allow the compiler to unify type constructors of different arities
  )
)

lazy val publishSettings = Seq(
  publishMavenStyle      := true,
  Test / publishArtifact := true,
  pomIncludeRepository   := (_ => false),
  developers := List(
    Developer(
      "dmateusp",
      "Daniel Mateus Pires",
      "dmateusp@gmail.com",
      url("https://github.com/dmateusp")
    ),
    Developer("semenodm", "Dmytro Semenov", "", url("https://github.com/semenodm"))
  ),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/laserdisc-io/fs2-aws/tree/master"),
      "scm:git:git@github.com:laserdisc-io/fs2-aws.git",
      "scm:git:git@github.com:laserdisc-io/fs2-aws.git"
    )
  ),
  homepage := Some(url("https://github.com/laserdisc-io/fs2-aws/")),
  licenses := Seq(
    "MIT" -> url("https://raw.githubusercontent.com/laserdisc-io/fs2-aws/master/LICENSE")
  ),
  pgpPublicRing    := file(".travis/local.pubring.asc"),
  pgpSecretRing    := file(".travis/local.secring.asc"),
  releaseEarlyWith := SonatypePublisher
)
