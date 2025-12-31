# Android Wear Stopwatch App

## Overview
This Android Wear application implements a stopwatch functionality using the **bonus 2-button design**:
- **Start/Stop Toggle Button** - Changes functionality based on current state
- **Reset Button** - Resets the stopwatch to zero

## Features
- ✅ Start/Stop/Reset stopwatch functionality
- ✅ Proper button state management (enable/disable based on stopwatch state)
- ✅ Accurate time tracking using SystemClock.elapsedRealtime()
- ✅ UI optimized for Android Wear devices
- ✅ No hardcoded text, colors, or dimensions
- ✅ Comprehensive code comments
- ✅ Listener interface implementation
- ✅ Bonus: 2-button UI design

## Requirements Compliance

### Functional Requirements
- ✅ **Start Stopwatch**: Start button begins timer, becomes disabled when running
- ✅ **Stop Stopwatch**: Stop button halts timer, becomes disabled when stopped
- ✅ **Reset Stopwatch**: Reset button resets to zero, disabled when running/paused
- ✅ **Accurate Timing**: Uses SystemClock.elapsedRealtime() for accuracy even when app is backgrounded

### Non-Functional Requirements
- ✅ **UI Design**: Optimized for small Wear OS screens
- ✅ **Compatibility**: Works with Wear OS devices running Android 13+ (API 30+)
- ✅ **Usability**: Easy-to-use interface with clear visual feedback

### Technical Requirements
- ✅ **SystemClock.elapsedRealtime()**: Used for accurate time calculation
- ✅ **Listener Interface**: OnClickListener implemented at class level
- ✅ **No Hardcoded Values**: All text, colors, and dimensions use resources
- ✅ **Comprehensive Comments**: Every class, method, and significant code segment commented

## Project Structure

```
app/src/main/
├── java/com/example/assignment1/
│   └── MainActivity.java              # 2-button implementation (bonus feature)
├── res/
│   ├── layout/
│   │   └── activity_main.xml          # 2-button layout
│   └── values/
│       ├── strings.xml                # String resources
│       ├── colors.xml                 # Color resources
│       └── dimens.xml                 # Dimension resources
└── AndroidManifest.xml                # App configuration
```

## Button Behavior

### 2-Button Design (Bonus Feature):
- **Start/Stop Button**: 
  - Shows "START" when stopped (green background)
  - Shows "STOP" when running (red background)
  - Toggles functionality based on current state
- **Reset Button**: 
  - Resets timer to zero
  - Disabled when stopwatch is running
  - Enabled when stopwatch is stopped

## Technical Implementation

### Time Calculation
- Uses `SystemClock.elapsedRealtime()` for accurate time tracking
- Continues running even when app is backgrounded
- Updates display every 10ms for smooth animation

### Button State Management
- Dynamic enable/disable based on stopwatch state
- Visual feedback through color changes
- Proper state transitions

### Resource Management
- All strings in `strings.xml`
- All colors in `colors.xml`
- All dimensions in `dimens.xml`
- No hardcoded values in code or layout

## Build and Run

1. Open project in Android Studio
2. Connect Android Wear device or use emulator
3. Build and run the project
4. The app will launch with the 2-button interface (bonus feature)

## Demo Notes

During the demo, you can showcase:
1. Starting the stopwatch (button shows "START" in green)
2. Stopping the stopwatch (button changes to "STOP" in red)
3. Resetting the stopwatch (button becomes disabled when running)
4. Button state changes and visual feedback
5. Time accuracy (leave app and return)
6. 2-button design efficiency (bonus feature)

## Author
Pallavi - Assignment 1 - Android Wear Stopwatch App
