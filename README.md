## Taxi Locator:
This app allows to see a list of taxi cabs near to certain coordinates. One screen to see the list of POIs, and second to see them located on a map. Data obtained from remote database. 

### Technology stack:
- Kotlin
- Dagger 2.16
- Mockito
- RxKotlin
- Retrofit2 + Gson
- Google Maps

See Unit Tests under app/src/test. 

##### To improve, given more time: 
 -  add network response caching backed by some database (Realm, Room,
    etc)
-  add more tests specially of business logic (domain package)
