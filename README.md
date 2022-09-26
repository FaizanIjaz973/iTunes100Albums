# iTunes100Albums

The code fetches the top 100 albums from iTunes using the following link

On the launch of the app, the data is fetched from the internet using the retrofit library and is populated in the recyclerview in the MainFragment.

Swipes on items of recyclerview are registered to add the favorite albums to the local room database.

The floating action button in the MainFragment takes to the fragment that shows the favorite albums.

The reason for developing this app is to implement best practices like dependency injection using dagger hilt, MVVM clean architecture, and detailed unit/UI tests.

Following further functionalities can be added to make it a comprehensive project.

- Advanced searching of the albums
- Details to be shown in a fragment of the chosen album
