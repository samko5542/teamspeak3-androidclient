#!/usr/bin/env bash
set -euo pipefail

: "${TS3_SDK_URL:?Set TS3_SDK_URL to your licensed TeamSpeak 3 SDK download URL}"
mkdir -p third_party
curl -fL "$TS3_SDK_URL" -o third_party/ts3sdk.zip
unzip -o third_party/ts3sdk.zip -d third_party/ts3sdk
