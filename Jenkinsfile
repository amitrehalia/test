
pipeline {
                agent any
	        environment {
                                  EMAIL_RECIPIENTS = 'amit.reh@gmail.com'
                                  CUR_FOLDER_NAME=sh(script : 'printf ${PWD##*/} | sed -e "s/\\(.*\\)/\\L\\1/" | sed "s/_\\(.\\)/\\L\\1/g"', returnStdout: true)
                                  DOCKER_REGISTRY = "amitrehalia/postgres_web"
				registry = "amitrehalia/tomcat_ant"
			         registryCredential = 'dockerhub'
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
									image "${DOCKER_REGISTRY}"
                                                                        args '-u amitrehalia:Aix910@##'	
                                                                     }
                                                          }
 							steps {
                                                                 catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE')  {
                                                                                                                                                                                                                                                           

                                                                                                                                sh '''
                                                                                                                                      echo "Inside Compile  Code Development  "
                                                                                                                                      echo $PWD
                                                                                                                                      ls -lrt
                                                                                                                                      apt-get update
                                                                                                                                      apt-get -y install ant
                                                                                                                                      ./scipt.sh
                                                                                                                                  '''
                                                                                                                                  
                                                                                                                              }
            
                                                           }
       
                                                      post {
                                                                success {
                                                                           echo 'Development Code Compilation Successful'
                                                                        }
                                                                        
                                                                failure {
                                                                           echo 'Development Code Compilation failed'
                                                                           script {
                                                                                     skipRemainingStages = true
                                                                                     println "skipRemainingStages = ${skipRemainingStages}"
                                                                                   }

                                                                        }
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
