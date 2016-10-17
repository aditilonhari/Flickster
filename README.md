# Project 1 - Flickster

Flickster shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: 4 hours spent in total

## User Stories

The following **required** functionality is completed:

* User can **scroll through current movies** from the Movie Database API
* Layout is optimized with the [ViewHolder](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern) pattern.
* For each movie displayed, user can see the following details:
  * Title, Poster Image, Overview (Portrait mode)
  * Title, Backdrop Image, Overview (Landscape mode)

The following **optional** features are implemented:

* User can **pull-to-refresh** popular stream to get the latest movies.
* Display a nice default [placeholder graphic](http://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#configuring-picasso) for each image during loading.
* Improved the user interface through styling and coloring.

The following **bonus** features are implemented:

* Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [ ] When viewing a popular movie (i.e. a movie voted for more than 5 stars) the video should show the full backdrop image as the layout.  Uses [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) or [Heterogenous RecyclerView](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) to show different layouts.
* Allow video trailers to be played in full-screen using the YouTubePlayerView.
    * Overlay a play icon for videos that can be played.
    * More popular movies should start a separate activity that plays the video immediately.
    * Less popular videos rely on the detail page should show ratings and a YouTube preview.
* Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)
* [ ] Replaced android-async-http network client with the popular [OkHttp](http://guides.codepath.com/android/Using-OkHttp) or [Volley](http://guides.codepath.com/android/Networking-with-the-Volley-Library) networking libraries.


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/aditilonhari/Flickster/blob/master/flickster.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

## Notes

Describe any challenges encountered while building the app.

This was the first project I worked on for my Android Development class hosted by CodePath. 
I learned a lot about Views and Layouts while working on this project. Some of the issues I encountered was : 
 - It was the first time I tried running my app on a device (Nexus 5) as against an emulator and I ran into multiple driver issues. 
 - Also, the new Android Studio 2.2 has a lot of new features and settings to it which needed to be taken care of while working on this app.
 - Adding alternate layout resourses like a separate landscape view to the existing app was something new I learnt in this app.
 - I also used ViewHolder pattern which is really an efficient way of handling multiple views being loaded in a listview.
 - Tried my hands at Youtube Player for the first time with overlay play icon on the poster image and it was quite challenging to get the details right.
 - Working with the ButterKnife library was very simple and straight forward although I still have some unresolved issues for adding @BindDrawable 
	and @BindColor which I will continue to work on.
 - I also tried using the Volley network library, but it had issues so haven't completed that yet. 

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [YouTube Data API] - For playing Youtube videos.
- [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) - Reduces view boilerplate code

## License

    Copyright 2016 Aditi Lonhari

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
