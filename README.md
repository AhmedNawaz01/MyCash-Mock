# Base Architecture Setup sample-SystemsLimited
>Mock Authentication Application with predefined user **eve.holt@reqres.in**
>Open REST API : [Reqres](https://reqres.in/) *receiving an auth token

## Android Components
- #### Architecture
  - [ MVVM ](https://developer.android.com/jetpack/guide?gclid=Cj0KCQiAmpyRBhC-ARIsABs2EAqICQ2g4gc7yE49sLowTYExmCoFDDt4axPWf-tj5PAVrrcjYWyFBWcaAkaLEALw_wcB&gclsrc=aw.ds) -
  - [Repository pattern with use-cases](https://developer.android.com/jetpack/guide) -
  - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) -
- #### JetPack
  - [Room](https://developer.android.com/jetpack/androidx/releases/room) - For Local Database
  - [Data Store](https://developer.android.com/topic/libraries/architecture/datastore?gclid=Cj0KCQiAmpyRBhC-ARIsABs2EAr_kurojVxkrScPHJZobMTZMjXbZPuDrn9ux1oEa6r6wO49zPqAiGIaAiitEALw_wcB&gclsrc=aw.ds) - data storage solution that allows you to store key-value pairs or typed objects
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=Cj0KCQiAmpyRBhC-ARIsABs2EApGLWHpf3KFObH09D6OlZ48q8B0FDye427sDBm5DyG5v_c0h9CBrykaAlM9EALw_wcB&gclsrc=aw.ds) -
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) -
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) -
- #### Asynchronous Threading
  - [Kotlin coroutines](https://developer.android.com/kotlin/coroutines?gclid=Cj0KCQiAmpyRBhC-ARIsABs2EAqyfdql-DCx407qflhkZFbIDl4tmfmzGnxVYt2qKyoWWm3eIVDPm_oaAunHEALw_wcB&gclsrc=aw.ds) -
  - [Kotlin Flows](https://developer.android.com/kotlin/flow) -
- #### Networking/Remote
  - [Retrofit2](https://square.github.io/retrofit/) -
  - [Moshi](https://github.com/square/moshi) - JSON library
- #### Logging
  - [Timber](https://github.com/JakeWharton/timber) -
- [Coil](https://coil-kt.github.io/coil/) - An image loading library


# Concept of action(Authentication)
Use only these credentials for testing REST API
Email : **eve.holt@reqres.in**
First of All register an user using above email then attempt a login.
On successfully Login signedIn email will be stored into DataStore[the key-value email denotes what the previously authenticated user was]
When the app opens the next time, the first thing that is checked is this email key-value. Then the email is used to query the token from the Room Persistence library. If the token is found, the user is automatically authenticated. If the token isn't found, the user won't be authenticated.
When the user logs out, the token is deleted from the database, so even though the email is still in the DataStore, the token will never be found, so the user won't be authenticated