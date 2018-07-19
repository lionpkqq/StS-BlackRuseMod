# StS-BlackRuseMod 0.6.1

**BlackRuseMod** adds The Servant as a new playable character to **Slay The Spire**

Currently supported versions: 
* Slay The Spire (07-12-2018)
* ModTheSpire 2.8.0
* BaseMod 2.16.0

## Content/Gameplay ##
**BlackRuseMod** for **Slay The Spire** adds an entirely new character class, **The Servant**, a new set of 75 cards and 11 relics for her to use. 

**The Servant** is based on Sakuya Izayoi (十六夜 咲夜), a female character appears in Touhou games. She is able to control the flow of time and uses throwing-knives as her primary weapons. Her most iconic move is to use her time-control power to set up hundreds of knives and release them altogether on her enemies. The gameplay of this character rewards thinking and planning, and allows the player to defeat enemies in many different and interesting ways. 

### Compatibility ###
This Mod makes no changes to the base game other than adding **The Servant**. In other words, it should be compatible with other Mods.

## New Mechanics ##
* **Knives**: Knives are The Servent's most dedicated weapons. They are spent by cards that Throw knives.
* **Throw**: Throw will spend your Knives. If you have depleted your Knives, the card will stop working.
* **Blight**: Blighted enemies will take extra damage when attacked.
* **Temporal**: Temporal cards are Unplayable and Ethereal. Their unique effects will be triggered when drawn.
* **Shift**: Shift effects can only be triggered by discarding the cards.
* **Vision**: Predict the enemy intent for the next turn. If correct, trigger the effect(s).

## Usage ##
### Required Downloads ###
* ModTheSpire v2.7.0+ (https://github.com/kiooeht/ModTheSpire/releases)
* BaseMod 2.15.0+ (https://github.com/daviscook477/BaseMod/releases)

### Install ModTheSpire ###
1. Copy `ModTheSpire.jar` to your Slay the Spire install directory.
    * For Windows, copy `MTS.cmd` to your Slay the Spire install directory.
    * For Linux, copy `MTS.sh` to your Slay the Spire install directory and make it executable.
2. Create a `mods` directory. Place `BaseMod.jar` and `BlackRuseMod.jar` into the `mods` directory.

### Running Mods ###
1. Run ModTheSpire
    * For Windows, run `MTS.cmd`.
    * For Linux, run `MTS.sh`.
    * Or run `ModTheSpire.jar` with Java 8.
2. Select `BaseMod` and `StS-BlackRuse`.
3. Press 'Play'.

## Wiki ##
The Wiki is still under construction. Thanks for your patience!

## List of known issues ##
* If you choose Elegance in the Ancient Writing event, both Elegance and Simplicity will be triggered (which is not a bad thing, to be honest). 

## Credits / Acknowledgements ##
* BlackRuse (https://github.com/lionpkqq): design, programming and project management.

* Thank you people for play-testing: 胡逆天, 久远寺天豪, 响厨act2, prttt26, PFPkeima, gygrazok, KurryOpossum, and more!

## Special Thanks ##

I couldn't have created anything without:


The [devs](https://www.megacrit.com/) of **SlayTheSpire**


Contributors of **ModTheSpire**
* kiooeht - Original author
* t-larson - Multi-loading, mod initialization, some UI work
* test447 - Some launcher UI work, Locator
* reckter - Maven setup
* FlipskiZ - Mod initialization
* pk27602017 - UTF-8 support in ModInfo

Contributors of **BaseMod**
* t-larson - Original author
* FlipskiZ - `hand` command, bug fixes
* daviscook477 - Custom players, custom colors, custom cards, more API hooks, code cleanup, bugfixes
* Haashi - custom potion support (w/ hooks for obtaining potions and relics) and dev console support for potions and powers
* BlankTheEvil - custom rendering for cards on a card-by-card basis and custom energy orb support
* kiooeht - Support custom cards in card library screen, fix character select memory leak, modal card choices, custom dynamic variables
* DemoXinMC - CardBasic
* robojumper - Bug fixes

Contributors of **FruityMod**
* Fruitstrike (https://github.com/gskleres) for game design, project management, and being a hype train
* ColdRain451 (https://github.com/dvalldejuli) for a ton of card implementations and getting the code base started
* test447 (https://github.com/daviscook477) for card implementations and adding lots of needed API hooks to BaseMod 
* fiiiiilth (https://github.com/fiiiiilth) for testing, bug fixes and card updates/implementations
* Pal (https://github.com/Paltorz) for testing and feedback
* Grumpai (@Grumpai on Discord) for the current character model
* Jrawly (@Jrawly on Discord) for character concept art
* Butcherberries (@Butcherberries on Discord) for card art
* LikeAWass, Celerity, JohnDruitt, Zoochz, SirJesterful, and Jimquisitive for playtesting and feedback

## Current State/Updates ##
Needs more play-testing.

0.6.1
* Added three new exclusive relics: [Splendid Attire], [Paper Swan] and [Old Scarf].

0.6.0
* New card frameworks.
* Added five new exclusive relics: [Mystery Sword], [Stone Mask], [Pocket Watch], [Knee Brace] and [Broom].
* Minor balance updates.
* Renamed [Taunt] to [Time Theft].
* Reworked [Rewind], [Rearm] and [Returning Blades].
* Fixed: [Alleviate] does not trigger Shift effects.
* Fixed: [Silver Blades] does not apply to cards acquired from Nilrys Codex.
* Fixed: [The World] does not reduce the cost of the card drawn by [Flowering Night].

0.5.5
* Updated the character select sound.
* Minor balance updates.
* Added new cards: [Manipulate], [Solidify], [Entangle] and [Alleviate].
* Reworked [Flawless Form].
* Reworked all the [Temporal] cards. Now they can only be acquired by using [Enbodiment], [Manipulate] and [Solidify].

0.5.1
* Fixed: If the player uses [Trash To Treasure+] when they have a full hand, the game would break.
* Fixed: [Rearm] now has proper description and works correctly.

0.5.0
* Added a new mechanic: Vision.
* Added a new card: [True Sight].
* Deleted a card: [Exchange]
* Reworked [No Escape].
* Reworked [Read].
* Reworked [Silver Blades].
* Reworked [Snipe].
* Reworked [Special Formula].
* Reworked [Taunt].
* Reworked [Unruled].
* Fixed: When the player uses [Moon Phase] the game would break.
* Fixed: The second [The World] would not work if played right after the first [The World].
* Fixed many typos that makes some cards have overpowered effects ([Temporal Slicing], ect).
* [Reality Marble] is now actually innate, as described.