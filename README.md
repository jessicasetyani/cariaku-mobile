# CariAku Mobile

A cross-platform mobile application built with Kotlin Multiplatform Mobile (KMM) that provides an AI-powered assistant chat interface. CariAku helps users interact with AI assistants through an intuitive and engaging mobile experience.

## 🚀 Features

- **Cross-Platform**: Single codebase for both Android and iOS using Kotlin Multiplatform
- **AI Chat Interface**: Interactive chat with AI assistants featuring rich messaging capabilities
- **Modern UI**: Built with Jetpack Compose (Android) and SwiftUI (iOS)
- **Quick Access**: Fast access to frequently used assistants
- **Recent Chats**: View and continue previous conversations
- **Topic Suggestions**: Discover new topics to explore with AI assistants
- **Rich Media Support**: Support for images and formatted text in chat messages
- **Quick Actions**: Pre-defined quick reply buttons for faster interactions

## 🏗️ Architecture

This project follows a clean architecture pattern with:

- **Shared Module**: Common business logic, data models, and networking
- **Android App**: Android-specific UI implementation using Jetpack Compose
- **iOS App**: iOS-specific UI implementation using SwiftUI
- **MVVM Pattern**: Model-View-ViewModel architecture for better separation of concerns

## 🛠️ Tech Stack

### Core Technologies
- **Kotlin Multiplatform Mobile (KMM)**: Cross-platform development
- **Jetpack Compose**: Modern Android UI toolkit
- **SwiftUI**: iOS native UI framework
- **Kotlin Coroutines**: Asynchronous programming

### Libraries & Dependencies
- **Ktor**: HTTP client for API communication
- **Koin**: Dependency injection framework
- **Kotlinx Serialization**: JSON serialization/deserialization
- **Coil**: Image loading library
- **DataStore**: Data persistence
- **Navigation Compose**: Navigation for Android
- **Material 3**: Material Design components

### Development Tools
- **Gradle**: Build system with version catalogs
- **Android Studio**: Primary IDE
- **Xcode**: iOS development and testing

## 📋 Prerequisites

- **Android Studio**: Arctic Fox or later
- **Xcode**: 13.0 or later (for iOS development)
- **JDK**: 17 or later
- **Kotlin**: 2.0.0
- **Android SDK**: API level 30 or higher
- **iOS**: iOS 14.0 or later

## 🚀 Getting Started

### Clone the Repository
```bash
git clone <repository-url>
cd cariaku-mobile
```

### Android Setup
1. Open the project in Android Studio
2. Sync the project with Gradle files
3. Run the `androidApp` configuration

### iOS Setup
1. Open `iosApp/iosApp.xcodeproj` in Xcode
2. Select your target device or simulator
3. Build and run the project

### Build Commands
```bash
# Build Android app
./gradlew :androidApp:assembleDebug

# Build iOS framework
./gradlew :shared:assembleXCFramework

# Run tests
./gradlew test

# Clean build
./gradlew clean
```

## 📱 Project Structure

```
cariaku-mobile/
├── androidApp/                 # Android-specific code
│   ├── src/main/
│   └── build.gradle.kts
├── iosApp/                     # iOS-specific code
│   ├── iosApp/
│   └── iosApp.xcodeproj
├── shared/                     # Shared Kotlin code
│   ├── src/
│   │   ├── commonMain/         # Common code
│   │   ├── androidMain/        # Android-specific shared code
│   │   └── iosMain/           # iOS-specific shared code
│   └── build.gradle.kts
├── doc/                        # Documentation files
├── gradle/                     # Gradle wrapper and dependencies
├── build.gradle.kts           # Root build configuration
└── settings.gradle.kts        # Project settings
```

## 🎨 UI Components

### Welcome Page
- Search bar for finding assistants and topics
- Quick access section for frequently used assistants
- Recent chats overview with summaries
- Topic suggestions for exploration
- Bottom navigation bar

### Chat Interface
- Message bubbles with distinct styling for user and assistant
- Rich media support (images, formatted text)
- Quick reply buttons for faster interactions
- Typing indicators with CariAku branding
- Smooth animations and transitions

## 🔧 Configuration

### Gradle Configuration
The project uses Gradle version catalogs for dependency management. Key configurations:

- **Minimum SDK**: 30
- **Target SDK**: 34
- **Compile SDK**: 34
- **JVM Target**: 17

### Dependencies Management
Dependencies are managed through `gradle/libs.versions.toml` for consistent versioning across modules.

## 🧪 Testing

```bash
# Run all tests
./gradlew test

# Run Android tests
./gradlew :androidApp:test

# Run shared module tests
./gradlew :shared:test
```

## 📚 Documentation

- [Technical Documentation](tech_doc.md) - Detailed technical specifications
- [User Stories](cariaku-chat-interface-user-story.md) - Feature requirements and acceptance criteria
- [Class Diagram](doc/class_diagram.puml) - UML class diagrams
- [C4 Model](C4%20model.drawio) - Architecture diagrams

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Add comments for complex logic
- Ensure proper error handling

## 🐛 Troubleshooting

### Common Issues

**Build Failures**
- Ensure you have the correct JDK version (17+)
- Clean and rebuild the project
- Check Gradle wrapper version compatibility

**iOS Build Issues**
- Verify Xcode version compatibility
- Clean derived data in Xcode
- Ensure iOS deployment target is set correctly

**Dependency Issues**
- Run `./gradlew --refresh-dependencies`
- Check version catalog for conflicts

## 📄 License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.

## 👥 Team

- **Development Team**: StyleTheory
- **Project**: CariAku Mobile Application

## 📞 Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the documentation for common solutions

---

**Note**: This is an MVP (Minimum Viable Product) implementation with hardcoded data for demonstration purposes. Future versions will include backend integration and persistent data storage.
