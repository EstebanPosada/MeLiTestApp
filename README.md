# MeLiTestApp (Mercado Libre App)

<!--- Replace <OWNER> with your Github Username and <REPOSITORY> with the name of your repository. -->
<!--- You can find both of these in the url bar when you open your repository in github. -->
<!--![Workflow result](https://github.com/Zhuinden/android-dev-challenge-compose-design/workflows/Check/badge.svg)-->


## :scroll: Description

Welcome to the repository for the Mercado Libre Android App Challenge! This project demonstrates a sample application designed to showcase a streamlined shopping experience, featuring three core screens:

Search Screen: Users can enter search queries to find products. The screen has a search bar and a logo.

Results Screen: This screen displays a list of products based on the user's search query. Each item in the list shows a brief overview, such as the product name, image, and price, allowing users to browse and select items for more details.

Product Detail Screen: Upon selecting a product from the results, users are taken to this screen, which provides comprehensive information about the chosen item. Details include a larger image, price, available quantity and any additional relevant information.

This project is built using [Android SDK/Kotlin], and follows best practices for mobile development to ensure a smooth and intuitive user experience. The app also incorporates efficient data handling and user interface design principles to provide a high-quality shopping experience.


## :bulb: Tech Stack

The following technologies and libraries have been chosen for this project to ensure a robust, efficient, and maintainable Android application:

- ### Hilt:
  Chosen for dependency injection to streamline the management of dependencies throughout the app. Hilt simplifies the setup and injection of dependencies, which enhances code clarity and promotes a modular architecture.

- ### Retrofit & OkHttp:
  Used to handle network requests and data fetching efficiently. 

- ### Room:
  Used for storing data fetched from the server and caching it locally. This allows the app to display information from local storage, ensuring quick access and offline availability.

- ### Coil:
  Used for its efficiency in image loading and caching. Coil was chosen for its simplicity, performance, and ease of integration with Jetpack Compose.

- ### Jetpack Compose:
  Adopted for building the app’s user interface due to its modern, declarative approach to UI development. Compose allows for more dynamic and flexible UI creation, making it easier to implement complex interfaces and manage UI state.

- ### Compose Navigation:
  Facilitates smooth transitions and state management between screens.

- ### ViewModel:
  Used to manage and retain UI-related data across configuration changes. View Models help in maintaining a clear separation between the UI and business logic, which contributes to a more organized and maintainable codebase.
  
- ### Use Cases:
  Implemented to encapsulate business logic and define application-specific operations.

- ### MockK:
  Chosen for unit testing due to its Kotlin-specific features and ease of use.

Each component of the tech stack was selected to address specific needs of the project, resulting in an efficient, maintainable, and high-performance application.

## :camera_flash: Screenshots

![image](https://github.com/user-attachments/assets/f44b1198-1731-410e-825c-f840329bdf86) &emsp; ![image](https://github.com/user-attachments/assets/1a6f2289-6b4c-43e3-ae33-953822d53ccc) &emsp; ![image](https://github.com/user-attachments/assets/14a73f28-ae46-4a10-8fcb-056fec656045)
