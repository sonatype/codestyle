/*
 * Copyright (c) 2011-present Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://links.sonatype.com/products/clm/attributions.
 * "Sonatype" is a trademark of Sonatype, Inc.
 */

/**
 * Uses build job naming conventions to determine which pipeline to run.
 *
 * If the project name ends with 'release' then run the release pipeline.
 * If the project name has 'snapshot' somewhere in the middle, run the deploy pipeline.
 *
 * @param args - optional map of arguments to pass.  Arguments are passed directly to mavenReleasePipeline or
 * mavenDeployPipeline.  See those pipelines for supported arguments.
 */
def call(Map<String, ?> args = [:]) {

  args['notificationSender'] = {
    build, env -> buildNotifications(build, env)
  }

  switch (currentBuild.fullProjectName) {
    case ~/.*\/release$/:
      mavenReleasePipeline(args)
      break
    case ~/.*snapshot.*/:
      mavenDeployPipeline(args)
      break
    default:
      error "${currentBuild.fullProjectName} does not follow naming conventions."
  }
}
