# Recon map: AntiGem (Android) — clean-room, scoped

Scope: the core mobile loop only — chat with an agent and open a terminal.
For: Rajnish's own branded Android build.
Date: 2026-11-22

## Sources

| # | source | URL | notes |
| --- | --- | --- | --- |
| 1 | public project README | https://github.com/Santoshkurmi/antigem | feature descriptions, positioning |
| 2 | public v2.0.0 release | https://github.com/Santoshkurmi/antigem/releases/tag/v2.0.0 | release listing |
| 3 | downloaded APK artifact | https://github.com/Santoshkurmi/antigem/releases/download/v2.0.0/AntiGem-v2.0.0.apk | reference only; not decompiled |

The APK was downloaded as a reference artifact. Per the replica-recon rules,
its binary was not decompiled and its code/assets were not read.

## Core loop

On a phone, open a chat with a coding agent and drop into a Linux terminal when
you need the machine.

## Screens

| ID | screen | route / how you reach it | purpose | key components | states seen |
| --- | --- | --- | --- | --- | --- |
| S01 | Chat | launch | talk to an agent | message list, text input, send button | empty, filled, offline demo |
| S02 | Terminal | tab | run commands in a Linux shell | monospaced output, prompt | filled scaffold |
| S03 | About | tab | creator links and identity | dialog with three links | open |

## Flows

```
F01 Send a chat message
    S01 -> type -> send -> S01
    happy path clicks: 2
    edge: empty input, long input
F02 Open the terminal
    S01 -> Terminal tab -> S02
    happy path clicks: 1
    edge: none in minimal build
F03 Open creator links
    S01/S02 -> About tab -> link
    happy path clicks: 2
```

## Components

| component | variants | states | used on |
| --- | --- | --- | --- |
| Tab button | chat, terminal, about | default, selected | S01, S02 |
| Message bubble | agent, user | empty, filled | S01 |
| Text input + send | single | empty, filled | S01 |
| Link dialog | three links | open | S03 |
| Terminal output | monospace | filled | S02 |

## Inferred data model

```
Message  id, sender, body, created_at
          evidence: S01 message list
          confidence: medium
TerminalLine  id, text
          evidence: S02 output area
          confidence: guess
```

Relationships: none in the minimal build; messages are in-memory.

## Feature matrix

See `features.csv`. Must: 4, should: 2, could: 6, skip: 5.

## Out of scope (cannot or should not be cloned)

- The original's agent runtime and MCP integrations.
- Bundled Termux rootfs and other binary assets or licences.
- The mini app catalog and any third-party content or network.
- Logos, copy and trademarks of the original.

## Size

Original app: XL (rescope). This clean-room slice: S (a weekend).
Hard parts left out: agent runtime, terminal emulation, browser automation.
Next step: `/replica-architect`.
