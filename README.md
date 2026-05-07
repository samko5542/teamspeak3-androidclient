# TeamSpeak 3 Android Client (Android 14+)

This repository contains an Android 14+ TS3 client starter with:
- Push-to-talk UI toggle.
- Bluetooth mic/audio routing (SCO for headset microphones).
- Android CI on GitHub Actions.
- A `TeamSpeakClient` abstraction ready for TeamSpeak 3 SDK wiring.

## Current status

The app compiles and runs as a functional Android shell today (connect UI, permission flow, Bluetooth audio routing, PTT state handling).
`NativeTeamSpeakClient` is currently a mock implementation; replace it with TeamSpeak 3 SDK calls for real server voice transport.

## Fetch TeamSpeak 3 SDK

TeamSpeak 3 Client SDK is proprietary and requires a license from TeamSpeak Systems.
After obtaining a licensed download URL, fetch it with:

```bash
TS3_SDK_URL='https://<your-licensed-url>' ./scripts_fetch_ts3_sdk.sh
```

Then wire SDK calls in `app/src/main/java/com/example/ts3client/TeamSpeakClient.kt` (or JNI if you prefer native integration).

## Build locally

```bash
./gradlew assembleDebug
```

## GitHub Actions

Workflow: `.github/workflows/android.yml`.
