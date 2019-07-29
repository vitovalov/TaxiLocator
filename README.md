## Taxi Locator:
This app allows to see a list of taxi cabs near to certain coordinates. One screen to see the list of POIs, and second to see them located on a map. Data obtained from remote database. 

### Important!
Insert your Google Maps API key in [app/src/main/res/values/google_maps_api.xml](https://github.com/vitovalov/TaxiLocator/blob/master/app/src/main/res/values/google_maps_api.xml)

### Technology stack:
- Kotlin
- Dagger 2.16
- Mockito
- RxKotlin
- Retrofit2 + Gson
- Google Maps

See Unit Tests under app/src/test. 

##### To improve, given more time: 
 -  Add network response caching backed by some database (Realm, Room,
    etc)
-  Add more tests specially of business logic (domain package)
