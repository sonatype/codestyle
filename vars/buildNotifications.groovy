/*
 * Copyright (c) 2011-present Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://links.sonatype.com/products/clm/attributions.
 * "Sonatype" is a trademark of Sonatype, Inc.
 */

def call(currentBuild, env) {
  def branch = gitBranch(env)
  def defaultRecipients = 'builds-group@sonatype.com';

  def buildStatus = currentBuild.currentResult

  // Build status values found at http://javadoc.jenkins-ci.org/hudson/model/Result.html
  switch (buildStatus) {
    case ~/FAILURE|UNSTABLE/:
      // Notify the developers who committed to the failed build, the initiator of the build and if on master
      // then notify the build group.
      sendEmailNotification(currentBuild, env,
          [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
          'master' == branch ? defaultRecipients : '')
      break
    case 'SUCCESS':
      // previous failure on master
      if ('master' == branch && currentBuild?.previousBuild?.result =~ /FAILURE|UNSTABLE/) {

        // Notify the developers who committed to the failed build, the build initiator, and the build group.
        sendEmailNotification(currentBuild, env,
            [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
            defaultRecipients)
      }
      else {
        // only notify the person who initiated the build
        sendEmailNotification(currentBuild, env)
      }
      break
    case ~/ABORTED|NOT_BUILT/:
      echo 'Not built.  No notification will be sent.'
      break
    default:
      error 'Unable to determine build status.'
      break
  }
}
