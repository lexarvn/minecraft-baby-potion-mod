# Minecraft Fabric Mod: Baby Potion

This mod adds a new potion to Minecraft 26.1.1 (Java 25, Fabric) brewed with the Golden Dandelion and an Awkward Potion. The potion temporarily reverts mobs to their baby form (or scales them down if no baby form exists).

## Features
- **Potion of Infantilism**: Brewed with Awkward Potion + Golden Dandelion.
- **Effect**: Reverts mobs to baby form for 3 minutes.
- **No effect**: If mob is already in the target state.
- **Game Rule**: `Custom Babies' Scaling` game rule is used to control whether or not to include mobs that don't natively have a baby form. Also can be set to `Chibi` mode where it tweaks the models to try and match the style of the native babies.

## Known Issues
- Equipment doesn't render on baby mounts (saddles and armor will still be equipped).
- Mounting logic is odd. You can infantilize a mob you are riding, but if you unmount it, you can't remount it while it is still under the effect. Adult mobs riding baby mobs is odd and they probably should be auto-dismounted.

## Ideas (not a roadmap)
- Brew infantilism potion with a fermented spider eye to make a potion with an opposite effect (temporarily make baby mobs adults).