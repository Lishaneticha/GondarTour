# 🚐 GondarTour Android App

**GondarTour** is an Android application built with Kotlin, Jetpack Compose, and Hilt. It is designed for tourist guiding companies to track vehicle locations and manage fuel consumption efficiently.

## 📦 Download

[⬇️ Download Latest APK](https://github.com/Lishaneticha/GondarTour/tree/main/apk/app-release.apk)

---

## 📱 Features

* 🗺️ Real-time Google Map vehicle tracking
* ⛽ Fuel log recording with timestamps
* 📍 Custom vehicle marker icons
* 🔄 Jump between vehicles with map focus
* 🧭 Location permission handling
* 🔐 Hilt dependency injection
* 🗃️ Room database for offline fuel log storage
* 🔗 Retrofit integration for backend communication

---

## 📸 Screenshots

> Screenshots:

| Driver Dashboard | Fuel Logs |
| ---------------- | --------- |
| ![Dashboard](screenshots/driverDashboard1.jpg) | ![Fuel Logs](screenshots/driverDashboard2.jpg) |
| ![Dashboard](screenshots/driverDashboard2.jpg) |  |

---

## 🏗️ Architecture

* **MVVM** pattern (Model-View-ViewModel)
* **Hilt** for dependency injection
* **Room** for local persistence
* **Jetpack Compose** for UI

---

## 🔧 Setup Instructions

1. **Clone the repository**

   ```bash
   git clone https://github.com/Lishaneticha/GondarTour.git
   ```

2. **Open in Android Studio**

3. **Add Google Maps API Key** In `AndroidManifest.xml`:

   ```xml
   <meta-data
       android:name="com.google.android.geo.API_KEY"
       android:value="YOUR_API_KEY_HERE" />
   ```

4. **Run the app** on an emulator or device.

---

## 📦 Dependencies

* Jetpack Compose
* Hilt
* Retrofit
* Room
* Google Maps SDK for Android
* Accompanist Permissions

---

## 🛡️ Permissions

* `ACCESS_FINE_LOCATION`
* `ACCESS_COARSE_LOCATION`
* `INTERNET`
* `ACCESS_NETWORK_STATE`

---

## 🙋 Contributing

Feel free to fork this repo and submit pull requests! For major changes, please open an issue first.

---

## 📄 License

This project is licensed under the MIT License.

---

## 🔗 Contact

Created by **\[Lishan Eticha]**  ·  [@Lishaneticha](https://github.com/Lishaneticha)
