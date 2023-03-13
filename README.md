# Music App
## Features 
### Artitecure used - MVVM 
#### Network Library - Retrofit (For API Calls) and Glide (For fetching Images)
#### Secured API Key - Using local properties so that reverse engineering gets tricky
#### Pagination 3 - For pagination in Album, Tracks and Artist Features
#### Dagger Hilt - For Dependency Injection 
#### OkHttp - For adding query parameters dynamically and logging the request
#### ViewBinding - For managing XML layout codes
#### Safe Args - For having safe navigation in the app 
#### Navigation Component - For navigation in the app
#### Single Activity - For Better Performance 
#### Lang - 100% Kotlin

 ## Desicions 

#### MVVM - It gives a clear code structure and help in handing orienation 
#### Retrofit - Best network libraray for api calls due to a ton of option like adding common query key in all request using okHttp client
#### Dagger Hilt - For better managing the objects and creating singleton pattern easily 
#### View Binding - Getting rid of findViewBy becuase of null of pointer exception problem and easy to handle the ui 
#### Safe Args - For navigating between fragments without any error of action not found and passing parameter to the fragment 
#### Navigation Component - Ease to implement no need to manullay push and pop it take care of it internally 
#### Pagination - To load only data which is required for the user as well as Api requirement

 ## Assumptions 

#### User must use app in portrait mode for better UI
