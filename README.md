# StS-BlackRuseMod 0.9.0

**BlackRuseMod** adds The Servant as a new playable character to **Slay The Spire**.

Currently supported versions: 
* Slay The Spire (08-09-2018)
* ModTheSpire 2.9.1
* BaseMod 2.19.0 (Week 37 Patch)

## Content/Gameplay ##
**BlackRuseMod** for **Slay The Spire** adds an entirely new character class, **The Servant**, along with a new set of 75 cards and 11 exclusive relics.

**The Servant** is based on Sakuya Izayoi (十六夜 咲夜), a female character appears in Touhou games. She is able to control the flow of time and uses throwing-knives as her primary weapons. The gameplay of this character rewards thinking and planning, and allows the player to defeat enemies in many different and interesting ways.

You can check the updates at the bottom of this page.

### Compatibility ###
This Mod makes no changes to the base game other than adding **The Servant**. In other words, it should be compatible with other Mods.

## New Mechanics ##
* **Knives**: Knives are the Servent's most dedicated weapons.
* **Blight**: Blighted enemies will take extra damage when attacked.
* **Temporal**: Temporal cards are Unplayable and Ethereal. Their effects will be triggered when drawn.
* **Shift**: Shift effects can only be triggered by discarding the cards.
* **Vision**: Predict the enemy intent for the next turn. If correct, trigger the effect(s).
* **Backlash X**: Apply 1 random Weak or Frail to you X times.
* **Protection**: Unblocked damage will consume Protection instead of your HP.
* **Satellite**: Whenever you use an Attack, lose 1 Satellite and attack an extra time for 4 damage. Whenever you are attacked, lose 1 Satellite and deal 4 damage to the attacker.

## Usage ##
### Required Downloads ###
* ModTheSpire v2.9.0+ (https://github.com/kiooeht/ModTheSpire/releases)
* BaseMod 2.19.0+ (https://github.com/daviscook477/BaseMod/releases)

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
* Vision cards will crash the game if you are not playing as the Servant.
* [Stone Mask] only triggers once after killing all Darklings (you only get +1 Max HP) because they only die once. I think this is part of the original game logics so I don't think I will be able to fix it.
* Players may skip the discard phase of [Replace] by choosing nothing, due to a bug from the original game.
* Even though the description of the relic [Splendid Attire] says "Replace Uniform", players may still get this relic after any boss fight without having the [Uniform]. I haven't found a way to solve this issue without modifying the code from the original game (which may cause some compatibility issues).

## Credits / Acknowledgements ##
* BlackRuse (https://github.com/lionpkqq) - design, programming and project management.
* Kuzomari (https://www.deviantart.com/kuzomari) - character concept arts and models.
* Dimlight (~~咕咕~~昏暗) - UI and card illustrations.
* OPM (一下午) (Tencent QQ: 1315659893) - texture filter patch, ascension patch and programming consultation.
* Gogo (https://github.com/gogo81745) - programming consultation.
* HOYKJ (https://www.pixiv.net/member.php?id=9562609) - programming consultation.

* Thank you people for play-testing: 胡逆天, 久远寺天豪, 响厨act2, prttt26, PFPkeima, gygrazok, KurryOpossum, KongMD-Steam, maou, aerosmaster, notque, 5m1l35, Lunaraia, ETO灬XL, sin-genjitsu xxx, 辉夜姬, killerjwa, Athanasiosdk, 哓吉, sin-genjitsu xxx, 不悲不喜, and more!

## Special Thanks ##

I couldn't have created anything without:

### The [devs](https://www.megacrit.com/) of SlayTheSpire ###

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
* Looking for an (presumably spine) animator.
* Needs more play-testing.
* Please let me know if you can rephrase any sentences to make them sound more natural.

0.9.0

Balance:
* [Barrier] gain 8(11) Block and Protection -> gain 7(10) Block and Protection
* [Canned Time] is no longer Ethereal
* [Comet] cost 0 -> cost 1
* [Comet] deal 5(8) damage -> deal 10(14) damage
* [Cosmic Inflation] gain 8(10) Protection -> gain 7(9) Protection
* [Defy] now has Retain
* [Double Edge] deal 13(17) damage -> deal 15(21) damage
* [Double Edge] Backlash 2 -> Backlash
* [Dual Dimension] Shift: gain 5 Protection -> gain 6 Protection
* [Duplication] Backlash 3 -> Backlash
* [Finishing Touch] Backlash 3 -> Backlash
* [Flawless Form] gain 16 Protection -> gain 12 Protection
* [Flawless Form] upgrade to Innate -> upgrade to Retain
* [Hightail] gain 7 Protection -> gain 6(8) Protection
* [Hightail] Backlash X(X+1) -> Backlash
* [Orbit] deal 2 damage -> deal 4 damage
* [Pocket Watch] (rare relic) draw one more card next turn -> draw a card
* [Read] gain 16(24) Protection -> gain 15(20) Protection
* [Sabotage] deal 5(8) damage -> deal 7(10) damage
* [Solidify] choose 1 (upgraded) Temporal card -> choose 1 Temporal card twice
* [Special Formula] Backlash 3 -> Backlash
* [Special Formula] gain 2 Strength and Dexterity -> gain 1(2) Strength and Dexterity
* [Spin] deal 11(15) damage -> deal 13(18) damage
* [Spin] Backlash 2 -> Backlash
* [Star Chef] now has Ethereal
* [Star Chef] upgrade to Innate -> upgrade to lose Ethereal
* [Sunlight] apply 1 Blight -> apply 1(2) Blight
* [Temporal Defense] gain 4(6) Block and Protection -> gain 5(8) Block and Protection
* [Time Warp] cost 1 -> cost 0
* [Time Warp] deal 10(14) damage -> deal 6(8) damage
* [True Sight] upgrade to Innate -> upgrade to Retain
* [Unsheathe] Backlash 3 -> Backlash

Changes and reworks:
* Reworked Backlash: now it always applies 1 Weak, Vulnerable AND Frail to you.
* Reworked Temporal: now it means Ethereal and Exhaust.
* Changed all Temporal cards to accommodate the change above.
* Reworked [Advance] and renamed it to [Instant Armor].
* Deleted [Decisive Attack] and added a new [Advance].
* Renamed [Unsheathe] to [Light Flow].
* [Proper Practice] now has lower priority, which means its effect will be triggered after most powers.
* Reworked rare relic [Old Scarf].
* Reworked [Capture].
* Reworked [Flowering Night].
* Changed [Collapse] illustration to avoid confusion.

0.8.9

Balance:
* [Deadline] apply 3(5) Blight and Vulnerable -> apply 2(3) Blight and Vulnerable
* [Desolation] deal 12(18) damage -> deal 12(16) damage
* [D. Weaponry] obtain 3(4) Knives per turn -> obtain 2(3) Knives per turn
* [House Cleaning] obtain 3 Knives -> obtain 2(3) Knives
* [Misdirection] gain 5 Block and apply 1(2) Weak and Blight -> gain 5(8) Block and apply 1 Weak and Blight
* [Murderous Aura] is now a Rare card.
* [Read] gain 15(21) Protection -> gain 16(24) Protection
* [Rearm] obtain 6 Knives -> obtain 5 Knives

Changes and reworks:
* New card illustration and visual effect for [Killing Doll].
* Reworked the Satellite mechanic.
* Rephrased descriptions for all Knives-related cards.
* Changed [Gear Up] slightly to accommodate to the new Satellite mechanic.
* Reworked [Garbage Disposal].
* Reworked [Dancing Silver].
* Reworked [Silver Soul] and renamed it as [Silver Matrix].
* Deleted [Parthian Shot] and added [Multi-Shot].
* Deleted [Haste] and added [Proper Practice].
* Deleted [D. Passage] and added [Collapse].
* Deleted [Revamp] and added [Orbit].

Bug fixes:
* Fixed: Cards retained by [Reality Marble] only lose their Ethereal for 1 turn.
* Fixed: [Pendulum of Eternity]'s upgraded version deals excessive damage.
* Fixed: [Ricochet] does not spend a Knife.
* Fixed: [Temporal Defense], [Temporal Slicing] and [Temporal Misd.] have incorrect descriptions.
* Fixed: [Returning Blade] becomes unrecognizable after returning from the exhaust pile.
* Fixed: If you discard [Potential] with [Stress Reliever] and shuffle the deck by drawing cards, it will not return to your hand.

0.8.8 HOTFIX

Balance:
* [Gear Up] no longer draw a card
* [Murderous Aura] cost 1 -> 2

Bug fixes:
* Fixed: [Silver Soul] cost Knives to gain Satellites.

0.8.8

Added a new mechanic: Satellite.

Balance:
* [Advance] Backlash 2 -> Ethereal
* [Advance] now gives 6 Protection.
* [Cosmic Inflation] gain 9(12) Block -> gain 8(10) Protection
* [Dual Dimension] double your Knives -> double your Protection
* [Dual Dimension] cost 1(0) -> 2(1)
* [Dual Dimension] Shift: gain 6 Protection -> gain 5 Protection
* [D. Weaponry] obtain 2(3) Knives per turn -> obtain 3(4) Knives per turn
* [Fan of Knives] deal 9 damage -> deal 8(11) damage
* [Flawless Form] now has an extra effect: gain 16 Protection
* [Gouge] deal 5 damage -> deal 6 damage
* [Gouge] now has exhaust.
* [Hightail] gain 8 Protection -> gain 7 Protection
* [Killing Doll] cost 3(2) -> cost 2
* [Killing Doll] deal 2 damage -> deal 2(3) damage
* [Moonlight] cost 2 -> 1
* [Moonlight] deal 7 damage -> deal 4 damage
* [Moonlight] is no longer Ethereal
* [Read] gain Protection 16(24) -> 15(21)
* [Rearm] cost 0 -> 1
* [Rearm] obtain 3 Knives -> obtain 6 Knives
* [Rearm] draw 1 card -> draw 2(3) cards
* [Sabotage] deal 6(9) damage -> deal 5(8) damage
* [Silver Blades] 3(5) bonus damage -> 2(3) bonus damage
* [Starlight] cost 1 -> 2
* [Starlight] throw 3(4) knives -> throw 6(8) knives
* [Starlight] deal 5 damage -> deal 4 damage
* [Starlight] is no longer Ethereal
* [Sunlight] deal 24(32) damage -> deal 16(24) damage
* [Sunlight] now apply 1 Blight to ALL enemies
* [Sunlight] is no longer Ethereal
* [Temporal Defense] now gives 4(6) Block and 4(6) Protection instead of 8(12) Block and 1(2) Weak.
* [Temporal Misd.] now has an extra effect: apply 1(2) Weak
* [Temporal Slicing] deal 5(8) damage 2 times -> deal 5 damage 2(3) times
* [Time Warp] has an extra effect: discard 1 card
* [Time Warp]'s Shift effect no longer discards cards.
* [Time Warp] is now a common card.
* [Trash to Treasure] is now a common card.

Changes and reworks:
* Reworked [Mystery Sword].
* Reworked [Barrier].
* Reworked [Capture].
* Reworked [House Cleaning].
* Reworked [Reality Marble].
* Reworked [Shifting Gears], now it resembles the effect of [Shifting Thoughts].
* Reworked [Time Embedded].
* Deleted [Unparalleled] and added [Silver Soul].
* Deleted [Killer Instinct] and added [Waste Not].
* Deleted [Farseeing] and added [Gear Up].
* Deleted [Borrowed Time] and added [Pendulum of Eternity].
* Deleted [Shifting Thoughts] and added [Dancing Silver].

Bug fixes:
* Fixed: Choosing Elegance during the Ancient Writing event results in an incorrect outcome.
* Fixed: [Gouge] scales inversely with Blight.
* Fixed: The Knives power icon remains even when its amount is 0.
* Fixed: Sometimes, Vision effects are triggered even though the target is dead or missing.
* Fixed: [The World] works as if the player has the relic [Mummified Hand] even when the player does not.

0.8.7
Balance:
* [Comet] draw 2(3) cards -> draw 2(2) cards
* [Deny] discard 2(4) -> 2(3)
* [Desolation] cost 2 -> 1
* [Desolation] damage 16(24) -> 12(18)
* [Double Edge] damage 14(18) -> 13(17)
* [Duplication] cost 1 -> 2
* [Farseeing] gain 9 Block next turn -> draw 2 more cards next turn
* [Killer Instinct] cost 2 -> 1
* [Killer Instinct] gain 3(5) Block -> gain 2(3) Block
* [Moon Phase] cost 2 -> 1
* [Moon Phase] gain 2(3) Block -> gain 1(2) Block
* [Shifting Gears] draw 2 cards -> draw 3 cards
* [Silver Blades] 2(3) bonus damage -> 3(5) bonus damage
* [Snipe] damage 12(20) -> 12(18)
* [Spin] damage 12(16) -> 11(15)
* [Sunlight] deal 21(30) damage -> deal 24(32) damage
* [Read] gain Protection 14(20) -> 16(24)

Changes and reworks:
* Renamed [Morning Call] to [Canned Time] and it's no longer Innate.
* [Fan of Knives] is now an uncommon card.
* [Initiator] is now a rare card.
* Reworked [Gouge].
* Reworked [Returning Blade].
* Reworked [Shifting Thoughts].
* Reworked [Time Theft].
* Reworked [True Sight].
* Reworked [Unparalleled].
* Deleted [First Strike] and added [Ricochet].
* Deleted [Laundry] and added [D. Passage].
* New illustrations for [Defy] and [Flawless Form].

Bug fixes:
* Fixed: [The World] does not actually reduce the cost of the next card you play.
* Now [The World] will not reset the cost of the card affected by [Mummified Hand].
* Fixed: [Temporal Slicing] has weird animation.

0.8.6
* Fixed: Using Knives to finish the Awakened One crashes the game.
* Removed dialogues: Now the Servant will NOT remind the player when the Knives run out. 
* Fixed: [Returning Blade] can only deal 1 damage to Nemesis.
* Fixed: Using a power after using [The World] nullifies the effect of [Mummified Hand].
* Fixed: [Surpressing Fire]'s visual effects appear on top of the enemy instead of the Servant.
* Fixed: Sometimes, [Temporal Slicing] only deals base damage.
* Reworked [Returning Blade].
* Renamed [No Escape] to [Deadline].
* [Moon Phase] cost 1 -> 2
* [Unruled] damage bonus 3(5) -> 2(3)
* [Read] Protection 12(16) -> 14(20)

0.8.5
* Fixed: Knives transfer to other targets when the main target is dead.

0.8.4
* All custom powers have custom icons now.
* Reworked [Time Warp].
* Reworked [Unparalleled].
* Renamed [Entangle] to [Defy] and changed its illustration.
* Renamed [Feint] to [Decisive Attack].
* Fixed: [Decisive Attack]'s upgraded description is not showing in combat.
* Fixed: [Killer Instinct] is triggered more frequently than it should be in the Gremlin Leader fight.
* Fixed: [FanOfKnives], [Starlight], [KillingDoll], [Shattered Reality] and [Temporal Slicing] deal the same damage to all enemies, ignoring their individual powers.
* Now [Shattered Reality] does DOUBLE damage to ALL enemies when discarded.

0.8.3
* The Servant has become much more intelligent: now she is able stack Vision effects!
* Fixed: Upgraded [Borrowed Time] reduce next turn draw by 5 instead of 3.
* Fixed: [Read] would deal damage instead of gaining Block while interacting with [True Sight].
* [Read] now gives Protection instead of Block.
* Renamed [Temporal Arms] to [Temporal Defense]. It now applies Weak to ALL enemies.
* [Temporal Misd] now apply Vulnearble to ALL enemies instead of Weak.
* Slightly decreased [Initiator]'s damage.
* Reworked the upgraded version of [Moonlight] and [Starlight].
* Reworked the upgraded version of [Fan of Knives] and [Hightail].
* Reworked [Feint].
* Reworked [Killer Instinct].

0.8.2
* Uploaded even more custom power icons.
* Reworked [Advance].
* Reworked [Comet].
* Reworked [Entangle].
* Reworked [Fast Forward].
* Reworked [First Strike].
* Reworked [Revamp].
* Reworked [Sabotage].
* Now [Mystery Sword] only triggers backlash when the player uses Attack.
* Fixed: The second [Unparalleled] would certainly fail if the first one failed.
* Fixed: [Rearm]'s description has been corrected.

0.8.1
* Buffed most Knives-related cards. Now the Servant has more viable ways to apply vulnerable.
* Reworked [Gouge].
* Uploaded more custom power icons.
* Fixed: [Stone Mask] may gain Max HP repeatedly from revived Darklings.
* Fixed: [Chemical X] does not increase the number of effects of X-cost cards.

0.8.0
* Thanks to **Kuzomari**, we now have new character arts!
* Thanks to **Dimlight**, we now have new energy, relic and power icons and original illustrations for some cards! More illustrations are coming!
* Optimized the knife-throwing system to fix some issues and create smoother gameplay.
* Updated the texture filter patch - contributed by **OPM**.
* Major balance updates.
* Added a new mechanic: **Backlash**.
* Reworked [Duplication].
* Reworked [Fan of Knives].
* Reworked [Fast Forward].
* Reworked [First Strike].
* Reworked [Follow Up], now it is called [Initiator].
* Reworked [Indiscriminate], now it is called [Capture].
* Reworked [Rearm].
* Reworked [Returning Blades].
* Reworked [Revamp].
* Reworked [Rewind].
* Reworked [Time Embedded].
* Reworked [Time Warp].
* Reworked BOSS relic [Stone Mask].
* Reworked BOSS relic [Mystery Sword].
* Switched the effects between [Read] and [No Escape].
* Deleted the [Debuffs] keyword and uses Weak, Vulnerable and Frail in card descriptions instead.
* Renamed [Shifting Reality] to [Shattered Reality].

0.6.3
* Reworked [Spin] and [Killer Instinct].
* Greatly enhanced The Servant's survivability.
* Deleted [Brute Force] and added [Parthian Shot].
* Renamed [Full Offense] as [Feint].
* Renamed [Full Defense] as [Hightail].

0.6.2
* Added two new exclusive relics: [Pan] and [Roman Bracelet].
* Reworked [Indiscriminate] and [Gouge].
* Fixed: Sometimes [Potential] does not go back to hand after being discarded.
* Fixed: Picking up relic [Splendid Attire] will break the game.

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