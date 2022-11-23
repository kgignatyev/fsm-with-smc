#!/usr/bin/env bash

set -e

mvn  smc:smc
mvn -P docs smc:smc
mvn package

 docker run --rm \
 -v $(pwd):/dot/data \
 xpansiv.jfrog.io/default-docker-virtual/xpansiv-smcdot:1 \
 /usr/bin/dot_file2png.py --in data/target/generated-sources/smc/com/xpansiv/demo/fsm/asset_transfer/TransferAssets_sm.dot

