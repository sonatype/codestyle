/*
 * Copyright (c) 2011-present Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://links.sonatype.com/products/clm/attributions.
 * "Sonatype" is a trademark of Sonatype, Inc.
 */
@Library(['private-pipeline-library', 'jenkins-shared']) _

def settings = [
  deployBranch: 'main',
  javaVersion: 'Java 8',
  mavenVersion: 'Maven 3.8.x',
  usePublicSettingsXmlFile: true,
  useEventSpy: false,
  testResults: ['**/target/*-reports/*.xml'],
  distFiles: [
    includes: ['**/target/*.jar'],
    excludes: ['**/*-sources.jar*']
  ],
  notificationSender: { currentBuild, env ->
    notifyChat(currentBuild: currentBuild, env: env, room: 'bnr-notifications')
  }
]

if (params) {
  mavenReleasePipeline(settings)
} else {
  mavenSnapshotPipeline(settings)
}
