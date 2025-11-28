
# Jubiri App（ジブリアプリ）
## 📄 Description（應用程式簡介）
這是一個專門展示 吉卜力工作室（Studio Ghibli）系列電影資訊的應用程式。
提供一個簡潔、美觀的介面，讓使用者能夠輕鬆瀏覽和了解經典吉卜力電影的詳細資料。

## 🛠 Technology Stack（技術棧）
此應用程式主要使用 Kotlin 語言開發，並採用以下 Android 技術和架構組件：

- Ktor：用於高效能、非同步的網路請求，負責從 API 取得電影資料。
- Room Database：本地裝置儲存。
- Coil：輕量級且快速的圖片載入函式庫，用於處理電影海報和相關圖片的顯示。
- Koin：實用的依賴注入（Dependency Injection, DI）框架。
- Navigation：處理應用程式內所有畫面之間的導航。
- Compose UI (Jetpack Compose)：使用現代化的聲明式 UI 框架來構建應用程式介面。
- MVI Architecture (Model-View-Intent)：採用的架構模式，確保狀態管理清晰、可預測和單向數據流。

## 💡 Key Features（主要功能）
電影列表瀏覽。
電影詳細資訊。
離線瀏覽支援 (Offline Support)。

## 🔗 Resource（資料來源）
所有電影資料皆透過以下公開 API 取得
Ghibli API： https://ghibliapi.vercel.app/

## ✨ Screenshots
<div style="display: flex; gap: 32px;">
<img src="https://github.com/encorex32268/Jiburi/blob/master/screenshots/home_screenshot.png"  width="240" height="480">
<img src="https://github.com/encorex32268/Jiburi/blob/master/screenshots/detail_screenshot_1.png" width="240" height="480">
<img src="https://github.com/encorex32268/Jiburi/blob/master/screenshots/detail_screenshot_2.png"  width="240" height="480">
</div>
