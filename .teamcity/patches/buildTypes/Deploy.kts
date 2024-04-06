package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Deploy'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, BuildType({
    id("Deploy")
    name = "Deploy"

    params {
        password("env.AWS_SECRET_ACCESS_KEY", "credentialsJSON:178c777e-0fb8-4d69-a5af-76c7bdb5c8c2")
        password("env.AWS_ACCESS_KEY_ID", "credentialsJSON:a7938976-7bc6-4bcb-94e2-172540860d7f")
    }

    vcs {
        root(RelativeId("HttpsGithubComThainguyensunyaHelloWorldJavaRefsHeadsMaster1"))
    }

    steps {
        script {
            name = "Deploy jar file to S3"
            id = "Deploy_jar_file_to_S3"
            scriptContent = "aws s3 cp *.jar s3://teamcity-demo-hello-world-app/"
        }
    }

    triggers {
        vcs {
            branchFilter = "+:dev"
        }
    }

    features {
        perfmon {
        }
    }

    dependencies {
        dependency(RelativeId("Package")) {
            snapshot {
            }

            artifacts {
                artifactRules = "gs-maven-*.jar"
            }
        }
    }
}))

