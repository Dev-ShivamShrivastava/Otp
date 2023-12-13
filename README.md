Simple Compose Otp View

[![](https://jitpack.io/v/Dev-ShivamShrivastava/otp.svg)](https://jitpack.io/#Dev-ShivamShrivastava/otp)


Step:1  Add the jitpack repository to your Project setting file


    dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}


Step:2 Add Dependencies

	dependencies {
	        implementation 'com.github.Dev-ShivamShrivastava:otp:1.0.0'
	}

 OtpView Type :- Round, Circle, Line (Called this funcation in composable funcation)

    OtpView(type = OtpViewType.Round){otp->
                     
    }

 Preview: 
 

https://github.com/Dev-ShivamShrivastava/Otp/assets/146315874/3f2c9fb8-02cd-4e2c-8c6c-d815b596eb81




 
