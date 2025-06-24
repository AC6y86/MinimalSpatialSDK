# Minimal Spatial SDK

A minimal Meta Spatial SDK project demonstrating basic 3D scene creation for Quest devices.

## Features

- Basic Spatial SDK setup
- Creates a room with 4 walls around the user
- Gray walls with a darker floor
- Built for Meta Quest devices

## Requirements

- Android SDK
- Meta Quest device
- Spatial SDK 0.5.5

## Building

```bash
./gradlew assembleQuestDebug
```

## Installing

```bash
adb install app/build/outputs/apk/quest/debug/app-quest-debug.apk
```

## Project Structure

- `app/` - Main Android application module
- `spatial_editor/` - Spatial Editor project files
- `MainActivity.kt` - Main activity that creates the 3D scene

## License

This project is for demonstration purposes.