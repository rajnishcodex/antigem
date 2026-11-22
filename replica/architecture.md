# Architecture: RajnishGem minimal replica

## Stack

- Android native app, Java 17, framework views only (no third-party libraries)
- minSdk 24, targetSdk 35, AGP 8.7.3, Gradle 8.9
- No network calls, no backend, no accounts
- In-memory data layer with the same shape a real repository would use

## Data shapes

```
Message  { id: long, sender: string, body: string }
TerminalLine { id: long, text: string }
```

## Build order

1. App shell: three tabs, theme tokens wired, custom launcher icon.
2. Vertical slice: type a message, send it, see it in the list.
3. S02 terminal scaffold and S03 About links.
4. CI: build release APK and publish a GitHub release from `version.json`.

## Deliberate non-goals

No agent process, no shell binary, no editor, no browser, no sync.
