
pipeline {
                agent any
	     stages {
                   
                                                        
                          

                             stage('Compile_Dev') {
                             
                                              	steps {
                                                           sh '''
                                                                                                                                      echo "Inside Compile  Code Development  "
                                                                                                                                      echo $PWD
                                                                                                                                      ls -lrt
                                                                                                                                      ant -f build.xml
                                                                                                                                  '''
                                                                                                                                  
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
