/*
 * Copyright (c) 2011-present Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://links.sonatype.com/products/clm/attributions.
 * "Sonatype" is a trademark of Sonatype, Inc.
 */
@Library(['private-pipeline-library', 'jenkins-shared']) _

def settings = [
  mavenVersion: 'Maven 3.2.x',
  usePublicSettingsXmlFile: false,
  useEventSpy: false,
  testResults: ['**/target/*-reports/*.xml'],
  distFiles: [
    includes: ['**/target/*.jar'],
    excludes: ['**/*-sources.jar*']
  ],
  notificationSender: { currentBuild, env ->
    notifyChat(currentBuild: currentBuild, env: env, room: 'ops-builds')
  }
]

if (params) {
  settings.javaVersion = params.javaVersion ?: 'Java 8'
  if (settings.javaVersion == 'Java 7') {
    settings.mavenOptions = '-Dmaven.compiler.source=1.7 -Dmaven.compiler.target=1.7'
  }
  mavenReleasePipeline(settings)
} else {
  mavenSnapshotPipeline(settings)
}
