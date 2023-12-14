# Reps Per Game - RPG - An android fitness app
#### Video Demo:  https://youtu.be/SkMF94BpaoU
#### Description:

My project is an Android app called Reps Per Game or RPG. It is an exercise app that takes your scores in games, such as kills, deaths and assists and tells you how many reps of an exercise to do. This was born out of being a gamer but also wanting to make sure that I still exercise a bit. 

The application got away from me, it was probably too much to take on but I got an idea and couldn't get it out of my head. At the moment only a few of the components work but the application compiles and launches so I am going to just have to submit what I have because the year is almost over and I started the class in 2022. 

###### File structure:
The application is in the style of clean architecture, this seems to be a common file structure type that groups files by feature. This is done so that functions are grouped and don't have the ability to break future features which would be group separately. Even though this is the first feature set I decided this would be good so that as I learn and keep building it out I can get used to a concept used in production applications.

A internal database is used, versus having one hosted externally. I wanted to make sure that this was done because I want to make sure that user data is kept local. This obviously has the tradeoff of not being able to be multiplatform but if that is something that really bothers me in the future I thought added a export import function would be a good solution.

A DAO, data access object, is used for the queries in the database. This defines the function calls to the SQL like queries. I liked that this style of design uses this since it is familiar from doing the SQL week project which was a lot of fun.

Both of these are placed in a `data_source` folder with a repository that acts as the middle between the DAO and function calls. This is done for testing as well as the ability to switch out the the repository in the event that a remote connection is needed. What this would mean is that two repositories could be written, one with a proper API call and one with data that is only used for testing. So the repository acts as a gate between the database and functions.

Clean Architecture places this implementation in the `AppModule` in the root, so that if the project grows it is still accessible by other feature sets.

Down from there, the domain contains the models, where the key value definitions are located. This was really confusing to me at first but I can see how this would be useful as I had a few variables that I wasn't sure what datatype they would be and being able to go to one place to change it made it much simpler than going through every place it may have been used to change it!

The repository in the domain is where the data gets received on the feature level, so DB through the DAO and data repository, are handed to the domain by the `AppModule` and this translates that data through the model to assign type to be used in variables in the `use-case`

A Use case is there to define what function is called and validates the returned information. This seemed to me to be a lot of the same call and define as before but the validation happens here since it is then going to be used. Where if there is a blank area in the database it still could be complete enough for a function in a future feature.  This layer will throw exceptions and makes it a single spot to make sure data is correct no matter the source which can allow for data to come from internal or remote sources.
Utility classes are added just to make it possible to sub sort the query data without a separate call to the database, such as sorting ascending vs descending.

Presentation layer is where all the fun happens. Components are where the smaller sections of a screen, these are broken down and created so that they can be used in more than one spot in the app. And example is an entry in the history. This is going to be used for each returned entry and should be the same every time. Even if a group isn't going to be used more than once it is still good to break down things into smaller pieces so that the code is more readable.

Events are also kept in this layer. This takes the function calls and places them into memory to be used. State is something I still am trying to wrap my head around as there seems to be a few changes to how this is implemented as Android evolves. It seems this is a place where data can be exposed and state is used to move data in memory from one screen to another. I decided to go with shared flow state which can is called in the view model. I tried to do another newer way but I can't seem to find the correct Gradle files to make it work. At least not with material 3. 

The last and most fun part in my opinion was to do the screen composable files. This is where the components are put together and the UI is built. When I was making this app I did it with an older build that wasn't using jetpack compose and instead built everything using XML, this was a bit more clear what things were doing but also much more repetitive and now that I have done this a bit I am happy for the change. It was a good lesson though!

Since I have two screens (not that the button is working at the moment) the presentation layer has the same for another screen. 

Finally `MainActivity` is in the last utility folder. I equate this to being the same as calling `__main__` since it calls all the other layers and declares the navigation path with a similar concept as URL. Default values here are to tell the shared state flow what to do, and colours that will be displayed. 

###### Things I have learned:
This is all so much harder than I could have ever imagined! I needed a lot of help and found it mostly in YouTube videos by [Philipp Lackner](https://www.youtube.com/@PhilippLackner/videos). I really liked a short he had that mentioned that you often developers have a standard set of Gradle plugins that there are familiar with. After doing this it totally makes sense since there are so many compatibility issues with different plugins and dependencies. As you build you learn what works and what  doesn't!

I also learned that even if you have to  get a lot of help, making something really is the best tool to learn. The frustrating moments sometimes feel never ending but when you find a fix it is such an amazing feeling that is almost makes it worth it!!

##### Going forward
I have so much bugs to still work on, I feel like this is one of those things that never end. I for sure didn't test it on every device and could definitely optimize things better. Testing is going to be the bulk of my learning to come. Unit,  Integration and End-to-end tests are the 3 types from what I know but I don't know how to implement them yet. 

I will be making this project public on [GitHub](https://github.com/aliocantinea/RepsPerGame) going forward. Anyone that would like to help, please reach out as I would love the feedback! All my socials can be found on my blog [Bradley-James.ca](https://bradley-james.ca/)



Thank you!! It has been a tough but amazing class to do! I can't wait to continue with more!
