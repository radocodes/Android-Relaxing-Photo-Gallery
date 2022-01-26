# Android-Relaxing-Photo-Gallery

This is Android demo application. It's Java based and presents some basic and common Android dev tools, libraries and approaches.


The business logic in this app contains of the following:
- The app consumes free public web API for art photos - [https://picsum.photos](https://picsum.photos)
- List presentation of all photos by Dashboard screen 
- You can filter the photo list by author name - ascending/descending order
- You can search photo by author name
- You can choose a photo from Dashboard which leads to another separate Photo Details screen
- You can add to favorites or respectively remove from favorites a photo through Photo Details screen
- List presentation of all favorites in separate Favorites screen
- You can choose a photo from Favorites which leads to the already mentioned Photo Details screen
- For determining Favorite items, pointer of every favorite item is stored at local DB
- Bottom navigation menu leads to Dashboard screen and to Favorites screen

Expect some more improvements in future!

### Technical Specifications:
- Android
- Java
- Retrofit - Networking
- Jetpack
  - Navigation
  - LiveData
  - ViewModel
  - Room
- Hilt - Dependency injection
- Glide - Image loading library
- RxJava - Async Java library 
- Separation of concerns
- Repository pattern
- Observer pattern


#### App screenshots:

Dashboard or Favorites screen and Photo details screen:

<img src="/documentation_resources/app_screenshots/dashboard_favorites_screen.png" width="300" height="580"/> <img src="/documentation_resources/app_screenshots/photo_details_screen.png" width="300" height="580"/>


