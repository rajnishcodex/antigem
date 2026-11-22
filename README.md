# RajnishGem

A small **clean-room** Android app inspired by public feature descriptions of an
upstream project. It is written from scratch: no original source code, assets,
logos, copy or binary files were copied or decompiled.

This first release is deliberately minimal: a chat shell, a terminal shell and an
About panel. It is a functional scaffold, not a full replacement for the original
app's agent runtime, Termux environment, editor or browser.

## What is included

- Chat screen with a local message list and text entry (offline demo responses)
- Terminal screen with a visual shell scaffold
- About screen with creator links
- Original teal/slate palette and a custom gem launcher icon
- Package name: `com.rajnishcodex.rajnishgem`

## Creator links

- Instagram: [@i_rajnishmaurya](https://www.instagram.com/i_rajnishmaurya/)
- Telegram: [@rajnishcodex](https://t.me/rajnishcodex)
- GitHub: [@rajnishcodex](https://github.com/rajnishcodex)

## Build

```sh
chmod +x gradlew
./gradlew assembleRelease
```

The release build is signed with the standard Android debug key so it can be
installed without configuring a secret keystore. The GitHub Actions workflow in
`.github/workflows/release.yml` builds the APK and publishes it as a GitHub
release whenever `version.json` changes on `main`.

## Replica workflow notes

The `replica/` folder contains the recon map, feature matrix, architecture,
design tokens, brand notes and build log produced with the installed
`replica-*` skills. The original APK was downloaded as a reference artifact only
and was **not** decompiled, in line with the skill's clean-room rules.

## License

MIT — see [LICENSE](LICENSE).
