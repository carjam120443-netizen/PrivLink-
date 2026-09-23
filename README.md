# PrivLink

Independent Android privileged-service manager prototype.

## v0.2.0
- Material 3 Compose UI
- Foreground service
- Binder/AIDL service interface
- Root capability detection through `su -c id`
- ADB shell-context detection
- Backend status UI

### Architecture

```
PrivLink UI
    |
Service / Binder
    |
+---+---+
|       |
Root   ADB
```

The Binder interface is intentionally small while the project establishes the clean-room backend boundary.

**Important:** starting the normal Android service from the app does not grant elevated privileges. Root mode requires an already-rooted device, and ADB mode requires an ADB-launched process running in the shell context. The next stage is a dedicated server entrypoint that can be launched by root or ADB and then exposes the Binder interface.

Open the repository in Android Studio, sync Gradle, and build the app module.
