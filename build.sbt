name := """my-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, ConductRSandbox)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  ws,
  "com.typesafe.conductr" %% "play24-conductr-bundle-lib" % "1.0.0"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

// Deployment settings
import ByteConversions._
BundleKeys.nrOfCpus := 1.0
BundleKeys.memory := 64.MiB
BundleKeys.diskSpace := 10.MB
BundleKeys.roles := Set("web")
BundleKeys.endpoints := Map("test" -> Endpoint("http", services = Set(URI("http://:9000"))))
BundleKeys.startCommand += "-Dhttp.address=$TEST_BIND_IP -Dhttp.port=$TEST_BIND_PORT"
SandboxKeys.image in Global := "typesafe-docker-internal-docker.bintray.io/conductr/conductr-dev:latest"
