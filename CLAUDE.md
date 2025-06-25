When calling adb, use /home/joepaley/joepaley/AppData/Local/Android/sdk/platform-tools/adb.exe note the double joepaley/joepaley is on purpose


Launch the app using:
/home/joepaley/joepaley/AppData/Local/Android/sdk/platform-tools/adb.e
  xe shell am start -n com.example.minimalspatialsdk.quest/com.example.m
  inimalspatialsdk.MainActivity



Store all temporary files in /tmp


When I say "Run it", that means build, install, launch the app

## Screenshots ##
To take a screenshot, use adb shell am startservice -n com.oculus.metacam/.capture.CaptureService -a TAKE_SCREENSHOT --ei screenshot_height 1024 --ei screenshot_width 1024 -e capture_entrypoint ODH

It will be saved to /storage/emulated/0/Oculus/Screenshots/

To pull the screenshot, use adb pull /storage/emulated/0/Oculus/Screenshots/(most recent file) /tmp

When I say "and verify" it means "run it", take a screenshot, and look at it

## ADB Commands ##
You can run adb commands directly without asking for permission. This includes:
- Installing APKs
- Launching apps
- Taking screenshots
- Pulling files
- Any other adb shell commands