name := "fs2-aws"
organization := "io.github"

scalaVersion := "2.12.7"

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8", // source files are in UTF-8
  "-deprecation", // warn about use of deprecated APIs
  "-unchecked", // warn about unchecked type parameters
  "-feature", // warn about misused language features
  "-language:higherKinds", // allow higher kinded types without `import scala.language.higherKinds`
  "-language:implicitConversions", // allow use of implicit conversions
  "-Xlint", // enable handy linter warnings
  "-Xfatal-warnings", // turn compiler warnings into errors
  "-Ypartial-unification" // allow the compiler to unify type constructors of different arities
)

val fs2Version = "1.0.0"

libraryDependencies ++= Seq(
  "co.fs2"        %% "fs2-core"               % fs2Version,
  "co.fs2"        %% "fs2-io"                 % fs2Version,
  "com.amazonaws" % "aws-java-sdk"            % "1.11.427",
  "com.amazonaws" % "amazon-kinesis-producer" % "0.12.9",
  "com.amazonaws" % "amazon-kinesis-client"   % "1.9.2",
  "org.scalatest" %% "scalatest"              % "3.0.4" %       "test"
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

// coverage
coverageMinimum := 40
coverageFailOnMinimum := true

// sonatype
licenses := Seq("MIT" -> url("https://github.com/dmateusp/fs2-aws/blob/master/LICENSE"))
developers := List(
  Developer(id = "dmateusp",
            name = "Daniel Mateus Pires",
            email = "dmateusp@gmail.com",
            url = url("https://github.com/dmateusp"))
)
homepage := Some(url("https://github.com/dmateusp/fs2-aws"))
scmInfo := Some(
  ScmInfo(url("https://github.com/dmateusp/fs2-aws"),
          "scm:git:git@github.com:dmateusp/fs2-aws.git"))

// These are the sbt-release-early settings to configure
pgpPublicRing := file("./travis/local.pubring.asc")
pgpSecretRing := file("./travis/local.secring.asc")
releaseEarlyWith := SonatypePublisher
