# Plantimals
![Paper 1.20.4](https://img.shields.io/badge/Paper/spigot_1.20.4+-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Java 17+](https://img.shields.io/badge/Java_17+-007FFF?style=for-the-badge&logo=java&logoColor=white)

Meet Plantimals, a straightforward Minecraft plugin that adds a unique twist to farming gameplay. With Plantimals, players have the ability to "plant" various meat items such as Raw Porkchop, Raw Mutton, Raw Rabbit, Raw Chicken, and Raw Beef directly onto farmland. These items visually represent as they lay on the ground, simulating a planting process.

After a predefined duration, which can be individually configured for each type of meat, the corresponding animal will spawn from where the item was planted.

The plugin's configuration allows for customization of several aspects, including the type of particles for each animal, the time it takes for them to "grow," and the option to require players to sneak while planting.

Plantimals offers a novel approach to interacting with the Minecraft environment, blending familiar mechanics with a touch of whimsy. Whether you're looking to add a bit of magic to your farm or simply want a new way to spawn animals, Plantimals provides a simple yet engaging solution.

## Available Plantimals
- Pig
- Sheep
- Rabbit
- Chicken
- Cow

## Configuration Options
This plugin introduces specific conditions under which animals can spawn. Below are the configurable options available in `config.yml`, allowing you to tailor the spawning mechanism to your liking:

- **onlyActiveWhenSneaking**: Determines whether the spawning mechanism is active.
    - `true` - The mechanism is only active when the player is sneaking.
    - `false` - The mechanism is always active, regardless of the player's stance.

- **requiredMoistureLevel**: Sets the minimum moisture level of farmland required for the mechanism to trigger.
    - `0` to `7` - Farmland moisture level, where 0 is dry and 7 is fully hydrated.
    - Example: `1` indicates that the farmland must be slightly moist.

- **ticksTill[Animal]Spawns**: Specifies the waiting period in ticks before an animal spawns once the conditions are met. Replace `[Animal]` with the specific animal name (Pig, Sheep, Rabbit, Chicken, Cow).
    - `6000` - Equivalent to 5 minutes.

### Example Configuration:

```yaml
onlyActiveWhenSneaking: true
requiredMoistureLevel: 1
ticksTillPigSpawns: 300
ticksTillSheepSpawns: 300
ticksTillRabbitSpawns: 300
ticksTillChickenSpawns: 300
ticksTillCowSpawns: 300
```

## Permissions
The only permission this plugin has is `plantimals.use`. It allows players to plant **Plantimals**.

## Technical information

Built with **paperweight-userdev** for Paper 1.20.4 and Java 17+ (*sourceCompatibility Java 17*).

This plugin uses jackson-configuration by [Eldoria](https://github.com/eldoriarpg) and some related libraries for config management.
