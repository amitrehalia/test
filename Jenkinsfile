
pipeline {
                agent any
	        environment {
                                  EMAIL_RECIPIENTS = 'amit.reh@gmail.com'
                                  CUR_FOLDER_NAME=sh(script : 'printf ${PWD##*/} | sed -e "s/\\(.*\\)/\\L\\1/" | sed "s/_\\(.\\)/\\L\\1/g"', returnStdout: true)
                                  DOCKER_REGISTRY = "amitrehalia/postgres_web"
                                  GIT_AUTH = credentials('amitrehalia')

                          }

 		stages {
                   
                         stage('Check_Changeset') {
                         
                                                    when {
                                                          anyOf {
                                                                  changeset "**/**/*"
                                                    
                                                                  expression {
                                                                                return currentBuild.number == 1
                                                                              }
                                                                }
                                                          }  
                                                  
                                                    steps {
                            
                                                            script {
                                                                     noneedtobuild = false
                                                                     println "noneedtobuild = ${noneedtobuild}"
                                                                              
                                            
                                                                   }
                                                          }
                         
                                                   }
                             
                                                    
                          

                             stage('Compile_Dev') {
                             
                                                    when {
                                                            allOf {
                                                                   expression {
                                                                               !noneedtobuild
                                                                              }
                                                                   expression {
                                                                                !skipRemainingStages
                                                                              }
                                                                  }
                                                        }
                                                        
                                                    agent {
                                                              docker {
																		FROM tomcat:8.5.35-jre10
			 
                                                                     }
                                                          }


                                 stage('Build') {
                                                          steps {
                                                                      sh 'echo Building..'
                                                                   }
                                                       }

                                   stage('Test') {
                                                         steps {
                                                                      sh 'echo Testing..'
                                                                   }
                                                        }

                               stage('Deploy') {
                                                         steps {
                                                                      sh 'echo Deploying..'
                                                                   }
                                                        }
                          }
             }
}
