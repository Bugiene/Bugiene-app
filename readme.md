<p align="center">
  <img width="500" src="https://raw.githubusercontent.com/Bugiene/Bugiene-app/master/assets/text-hijau.svg">
</p>
<h1 align="center">BUGIENE: An Android-Based Mobile Application for Fruit Freshness Classifier Through Fruit Images Classification</h1>

**[Demo App](https://drive.google.com/file/d/11cGNiyM7-L1fp5DLZrRyULXxIPt3Rh4G/view?usp=sharing) - [Download App](https://drive.google.com/file/d/1Y4d-aQ7V_emExz7EuUUEmMpTsPSCLb2P/view?usp=sharing) - [Product Orientation](https://docs.google.com/presentation/d/1yAfovJBNfScIK0mmg1fSs6Z8q53hoMbsWlkZNrVa63Y/edit?usp=sharing)**
  
This app is a part of Bangkit 2022 Capstone Project from team C23-PC669 (Bugiene):

- (ML) M163DKY4124 - Laila Nur Anggamurti - Dian Nuswantoro University
- (ML) M030DSX2503 - Sandro Sinaga - Del Institute of Technology 
- (ML) M329DSX2504 - Deni Irawan - Respati University Yogyakarta 
- (CC) C151DSY2634 - Nazwa Anandia - Brawijaya University
- (CC) C042DKX3995 - Muhammad Faiz Al Azmi - Telkom Purwokerto Institute of Technology 
- (MD) A360DSX2975 - Raihan Chaira - Telkom University


## Background
Our team noticed that consumers often struggle to determine the freshness of fruits in supermarkets. This can result in them purchasing low-quality or spoiled fruits, which contributes to food waste. To address this problem, we plan to develop a mobile application that uses machine learning to accurately detect the key indicators of fruit freshness. Our goal is to provide a painkiller solution for consumers who struggle with determining the freshness of fruits in supermarkets. By doing so, we hope to empower consumers to make more informed purchasing decisions and reduce food waste caused by rotten fruits. From our research results regarding the issue, we did not find any Android-based application for detecting fruit freshness, which could be a solution to address the problem faced by consumers who have difficulty determining the freshness of fruits.

## Advantages
The application will have an easy-to-use interface that provides consumers with real-time information on the quality of the fruits they are considering purchasing. By doing so, we hope to empower consumers to make more informed purchasing decisions and reduce food waste.

## User Interface and User Experience Application
This is User Interview of Bugiene app. This design is adapted to the results of UX research. The UI/UX design is done using the design thinking method in the figma application. The following is the overall result of a series of design thinking methods [UI/UX Design](https://www.figma.com/file/WdskoPJdzXUjC1npEYWwYr/Capstone?type=design&node-id=0%3A1&t=RW8zO8jGKZFqUnAI-1). 

## Machine Learning Model
A machine learning model was developed to classify fruits into rotten or fresh categories using computer vision and a convolutional neural network (CNN). The model was built using the TensorFlow Keras library. In this case, the CNN model was trained on a dataset of images of fruits, such as: apples, avocados, bananas, grapes, guavas, oranges. The dataset included images of both rotten and fresh fruits. The CNN was able to learn to distinguish between the two types of fruit based on the features it extracted from the images.The output from our model is prediction result "Fresh" or "Rotten" and the accuracy.

## BackEnd
API Endpoint was developed for allows users to register, log in, perform fruit freshness detection, and view detection history in the application. API Endpoint develops using Node.js with the framework Express.js. Then, API Endpoint deploys to the Google Cloud Platform using Cloud Run. The data is stored in MySQL and the image is stored in Cloud Storage.



| Splash Screen | Onboarding Page | Register Page | Login Page |
| ------------- | --------------- | ------------- | ----------- |
| <img src="https://github.com/Bugiene/Bugiene-app/raw/master/assets/Splash%20Screen.png" width="200" height="350"> | <img src="https://github.com/Bugiene/Bugiene-app/raw/master/assets/Onboarding%20Page.png" width="200" height="350"> | <img src="https://github.com/Bugiene/Bugiene-app/raw/master/assets/Register%20Page.png" width="200" height="350"> | <img src="https://github.com/Bugiene/Bugiene-app/raw/master/assets/Login%20Page.png" width="200" height="350"> |



| Home Page  | News Page| Detection Page | Result Page
| ------------- | ------------- | ------------- | ------------- | 
| <img src="https://github.com/Bugiene/Bugiene-app/raw/master/assets/Home%20Page.png" width="200" height="350"> | <img src="https://github.com/Bugiene/Bugiene-app/raw/master/assets/News%20Article%20Page.png" width="200" height="350"> | <img src="https://github.com/Bugiene/Bugiene-app/raw/master/assets/Detection%20Page.png" width="200" height="350"> | <img src="https://github.com/Bugiene/Bugiene-app/raw/master/assets/Image%20Loaded%20Detection%20Paage.png" width="200" height="350"> 

| Logout Dialog  | 
| ------------- |
 <img src="https://github.com/Bugiene/Bugiene-app/raw/master/assets/Log%20Out%20Validation%20Page.png" width="200" height="400"> 

## Download Tutorial
1.  Download the latest APK file from the [Releases](https://github.com/organization/repo/releases) section of this GitHub repository.
2.  Transfer the APK file to your Android device using a USB cable or any preferred method.
3.  On your Android device, go to **Settings** and enable **Unknown sources** or **Install unknown apps** (the exact wording may vary depending on your Android version).
4.  Use a file manager app to navigate to the location where you transferred the APK file.
5.  Tap on the APK file to begin the installation process.
6.  Follow the on-screen prompts to complete the installation.
7.  Once installed, you should see the app icon in your app drawer or home screen.
8.  Launch the app and proceed with any initial setup or onboarding steps, if applicable.

