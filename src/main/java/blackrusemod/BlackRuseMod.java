package blackrusemod;

import java.nio.charset.StandardCharsets;

import blackrusemod.cards.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.KeywordStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.OnPowersModifiedSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.SetUnlocksSubscriber;

import blackrusemod.characters.TheServant;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.patches.TheServantEnum;
import blackrusemod.relics.Uniform;

@SpireInitializer
public class BlackRuseMod implements PostInitializeSubscriber,
	EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber,
	EditStringsSubscriber, SetUnlocksSubscriber, EditKeywordsSubscriber {
	public static final Logger logger = LogManager.getLogger(BlackRuseMod.class.getName());
	
	private static final String MODNAME = "BlackRuseMod";
    private static final String AUTHOR = "BlackRuse";
    private static final String DESCRIPTION = "v0.0.1\n Adds The Servant as a playable third character";
    
    private static final Color SILVER = CardHelper.getColor(131.0f, 156.0f, 165.0f);
    private static final String BLACKRUSE_MOD_ASSETS_FOLDER = "img";
    
    // card backgrounds
    private static final String ATTACK_SILVER = "512/bg_attack_silver.png";
    private static final String SKILL_SILVER = "512/bg_attack_silver.png";
    private static final String POWER_SILVER = "512/bg_attack_silver.png";
    private static final String ENERGY_ORB_SILVER = "512/card_silver_orb.png";
    
    private static final String ATTACK_SILVER_PORTRAIT = "1024/bg_attack_silver.png";
    private static final String SKILL_SILVER_PORTRAIT = "1024/bg_attack_silver.png";
    private static final String POWER_SILVER_PORTRAIT = "1024/bg_attack_silver.png";
    private static final String ENERGY_ORB_SILVER_PORTRAIT = "1024/card_silver_orb.png";
    
    // card images
    //public static final String ANOMALY = "cards/anomaly.png";
    public static final String ADVANCE = "cards/advance.png";
    public static final String BARRIER = "cards/barrier.png";
    public static final String BORROWED_TIME = "cards/borrowed_time.png";
    public static final String BRUTE_FORCE = "cards/brute_force.png";
    public static final String COMET = "cards/comet.png";
    public static final String COSMIC_INFLATION = "cards/cosmic_inflation.png";
    public static final String D_WEAPONRY = "cards/d_weaponry.png";
    public static final String DEFEND_SILVER = "cards/defend_silver.png";
    public static final String DENY = "cards/deny.png";
    public static final String DOUBLE_EDGE = "cards/double_edge.png";
    public static final String DUAL_DIMENSION = "cards/dual_dimension.png";
    public static final String DUPLICATION = "cards/duplication.png";
    public static final String ENBODIMENT = "cards/enbodiment.png";
    public static final String EXCHANGE = "cards/exchange.png";
    public static final String FAN_OF_KNIVES = "cards/fan_of_knives.png";
    public static final String FARSEEING = "cards/farseeing.png";
    public static final String FAST_FORWARD = "cards/fast_forward.png";
    public static final String FIRST_STRIKE = "cards/first_strike.png";
    public static final String FLAWLESS_FORM = "cards/flawless_form.png";
    public static final String FLOWERING_NIGHT = "cards/flowering_night.png";
    public static final String FOLLOW_UP = "cards/follow_up.png";
    public static final String FULL_DEFENSE = "cards/full_defense.png";
    public static final String FULL_OFFENSE = "cards/full_offense.png";
    public static final String GARBAGE_DISPOSAL = "cards/garbage_disposal.png";
    public static final String GOUGE = "cards/gouge.png";
    public static final String HASTE = "cards/haste.png";
    public static final String HOUSE_CLEANING = "cards/house_cleaning.png";
    public static final String INDISCRIMINATE = "cards/indiscriminate.png";
    public static final String KIDNEY_SHOT = "cards/kidney_shot.png";
    public static final String KILLER_INSTINCT = "cards/killer_instinct.png";
    public static final String KILLING_DOLL = "cards/killing_doll.png";
    public static final String LAUNDRY = "cards/laundry.png";
    public static final String MISDIRECTION = "cards/misdirection.png";
    public static final String MOON_PHASE = "cards/moon_phase.png";
    public static final String MOONLIGHT = "cards/moonlight.png";
    public static final String MORNING_CALL = "cards/morning_call.png";
    public static final String MURDEROUS_AURA = "cards/murderous_aura.png";
    public static final String NO_ESCAPE = "cards/no_escape.png";
    public static final String POTENTIAL = "cards/potential.png";
    public static final String PULL_OUT = "cards/pull_out.png";
    public static final String READ = "cards/read.png";
    public static final String REALITY_MARBLE = "cards/reality_marble.png";
    public static final String REARM = "cards/rearm.png";
    public static final String REPLACE = "cards/replace.png";
    public static final String RESET = "cards/reset.png";
    public static final String RETURNING_BLADES = "cards/returning_blades.png";
    public static final String REVAMP = "cards/revamp.png";
    public static final String REWIND = "cards/rewind.png";
    public static final String SABOTAGE = "cards/sabotage.png";
    public static final String SHIFTING_GEARS = "cards/shifting_gears.png";
    public static final String SHIFTING_REALITY = "cards/shifting_reality.png";
    public static final String SHIFTING_THOUGHTS = "cards/shifting_thoughts.png";
    public static final String SILVER_BLADES = "cards/silver_blades.png";
    public static final String SNIPE = "cards/snipe.png";
    public static final String SOUL_SCULPTURE = "cards/soul_sculpture.png";
    public static final String SPECIAL_FORMULA = "cards/special_formula.png";
    public static final String SPIN = "cards/spin.png";
    public static final String STAR_CHEF = "cards/star_chef.png";
    public static final String STARLIGHT = "cards/starlight.png";
    public static final String STRESS_RELIEVER = "cards/stress_reliever.png";
    public static final String STRIKE_SILVER = "cards/strike_silver.png";
    public static final String SUNLIGHT = "cards/sunlight.png";
    public static final String SURPRESSING_FIRE = "cards/surpressing_fire.png";
    public static final String TAUNT = "cards/taunt.png";
    public static final String TEMPORAL_ARMS = "cards/temporal_arms.png";
    public static final String TEMPORAL_ESSENCE = "cards/temporal_essence.png";
    public static final String TEMPORAL_MISD = "cards/temporal_misd.png";
    public static final String TEMPORAL_SLICING = "cards/temporal_slicing.png";
    public static final String THE_WORLD = "cards/the_world.png";
    public static final String TIME_EMBEDDED = "cards/time_embedded.png";
    public static final String TIME_WARP = "cards/time_warp.png";
    public static final String TRASH_TO_TREASURE = "cards/trash_to_treasure.png";
    public static final String UNPARALLELED = "cards/unparalleled.png";
    public static final String UNRULED = "cards/unruled.png";
    public static final String UNSHEATHE = "cards/unsheathe.png";
    
    // power images
    public static final String D_WEAPONRY_POWER = "powers/d_weaponry.png";
    public static final String DUPLICATION_POWER = "powers/duplication.png";
    public static final String ENBODIMENT_POWER = "powers/enbodiment.png";
    public static final String FLAWLESS_FORM_POWER = "powers/flawless_form.png";
    public static final String HASTE_POWER = "powers/haste.png";
    public static final String KILLER_INSTINCT_POWER = "powers/killer_instinct.png";
    public static final String MOON_PHASE_POWER = "powers/moon_phase.png";
    public static final String MURDEROUS_AURA_POWER = "powers/murderous_aura.png";
    public static final String OPPORTUNIST_POWER = "powers/opportunist.png";
    public static final String REALITY_MARBLE_POWER = "powers/reality_marble.png";
    public static final String SILVER_BLADES_POWER = "powers/silver_blades.png";
    public static final String STAR_CHEF_POWER = "powers/star_chef.png";
    public static final String STRESS_RELIEVER_POWER = "powers/stress_reliever.png";
    public static final String SURPRESSING_FIRE_POWER = "powers/surpressing_fire.png";
    public static final String DRAW_MANIPULATION_POWER = "powers/draw_manipulation.png";
    public static final String BARRIER_POWER = "powers/barrier.png";
    public static final String ENERGY_MANIPULATION_POWER = "powers/energy_manipulation.png";
    public static final String KNIVES_POWER = "powers/knives.png";
    public static final String FLOWERING_NIGHT_POWER = "powers/flowering_night.png";
    public static final String AMPLIFY_DAMAGE_POWER = "powers/amplify_damage.png";
    public static final String READ_POWER = "powers/read.png";
    public static final String THE_WORLD_POWER = "powers/the_world.png";
    public static final String UNPARALLELED_POWER = "powers/unparalleled.png";
    
    // relic images
    public static final String UNIFORM_RELIC = "relics/uniform.png";
    
    // servant assets
    private static final String SERVANT_BUTTON = "charSelect/ServantButton.png";
    private static final String SERVANT_PORTRAIT = "charSelect/ServantPortraitBG.jpg";
    public static final String SERVANT_SHOULDER_1 = "char/servant/shoulder.png";
    public static final String SERVANT_SHOULDER_2 = "char/servant/shoulder2.png";
    public static final String SERVANT_CORPSE = "char/servant/corpse.png";
    public static final String SERVANT_SKELETON_ATLAS = "char/servant/skeleton.atlas";
    public static final String SERVANT_SKELETON_JSON = "char/servant/skeleton.json";
    
    // badge
    public static final String BADGE_IMG = "BRBadge.png";
    
    // texture loaders
    public static Texture getDWeaponryPowerTexture() {
    	return new Texture(makePath(D_WEAPONRY_POWER));
    }
    
    public static Texture getDuplicationPowerTexture() {
    	return new Texture(makePath(DUPLICATION_POWER));
    }
    
    public static Texture getEnbodimentPowerTexture() {
    	return new Texture(makePath(ENBODIMENT_POWER));
    }
    
    public static Texture getFlawlessFormPowerTexture() {
    	return new Texture(makePath(FLAWLESS_FORM_POWER));
    }
    
    public static Texture getHastePowerTexture() {
    	return new Texture(makePath(HASTE_POWER));
    }
    
    public static Texture getKillerInstinctPowerTexture() {
    	return new Texture(makePath(KILLER_INSTINCT_POWER));
    }
    
    public static Texture getMoonPhasePowerTexture() {
    	return new Texture(makePath(MOON_PHASE_POWER));
    }
    
    public static Texture getMurderousAuraPowerTexture() {
    	return new Texture(makePath(MURDEROUS_AURA_POWER));
    }
    
    public static Texture getOpportunistPowerTexture() {
    	return new Texture(makePath(OPPORTUNIST_POWER));
    }
    
    public static Texture getRealityMarblePowerTexture() {
    	return new Texture(makePath(REALITY_MARBLE_POWER));
    }
    
    public static Texture getSilverBladesPowerTexture() {
    	return new Texture(makePath(SILVER_BLADES_POWER));
    }
    
    public static Texture getStarChefPowerTexture() {
    	return new Texture(makePath(STAR_CHEF_POWER));
    }
    
    public static Texture getStressRelieverPowerTexture() {
    	return new Texture(makePath(STRESS_RELIEVER_POWER));
    }
    
    public static Texture getSurpressingFirePowerTexture() {
    	return new Texture(makePath(SURPRESSING_FIRE_POWER));
    }
    
    public static Texture getDrawManipulationPowerTexture() {
    	return new Texture(makePath(DRAW_MANIPULATION_POWER));
    }
    
    public static Texture getBarrierPowerTexture() {
    	return new Texture(makePath(BARRIER_POWER));
    }
    
    public static Texture getEnergyManipulationPowerTexture() {
    	return new Texture(makePath(ENERGY_MANIPULATION_POWER));
    }
    
    public static Texture getKnivesPowerTexture() {
    	return new Texture(makePath(KNIVES_POWER));
    }
    
    public static Texture getFloweringNightPowerTexture() {
    	return new Texture(makePath(FLOWERING_NIGHT_POWER));
    }
    
    public static Texture getAmplifyDamagePowerTexture() {
    	return new Texture(makePath(AMPLIFY_DAMAGE_POWER));
    }
    
    public static Texture getReadPowerTexture() {
    	return new Texture(makePath(READ_POWER));
    }
    
    public static Texture getTheWorldPowerTexture() {
    	return new Texture(makePath(THE_WORLD_POWER));
    }
    
    public static Texture getUnparalleledPowerTexture() {
    	return new Texture(makePath(UNPARALLELED_POWER));
    }
    
    public static Texture getUniformTexture() {
    	return new Texture(makePath(UNIFORM_RELIC));
    }
    
    /**
     * Makes a full path for a resource path
     * @param resource the resource, must *NOT* have a leading "/"
     * @return the full path
     */
    public static final String makePath(String resource) {
    	return BLACKRUSE_MOD_ASSETS_FOLDER + "/" + resource;
    }
    
    public BlackRuseMod() {
    	logger.info("subscribing to everything");
        BaseMod.subscribe(this);

        logger.info("creating the color " + AbstractCardEnum.SILVER.toString());
        BaseMod.addColor(AbstractCardEnum.SILVER.toString(),
        		SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER,
        		makePath(ATTACK_SILVER), makePath(SKILL_SILVER),
        		makePath(POWER_SILVER), makePath(ENERGY_ORB_SILVER),
        		makePath(ATTACK_SILVER_PORTRAIT), makePath(SKILL_SILVER_PORTRAIT),
        		makePath(POWER_SILVER_PORTRAIT), makePath(ENERGY_ORB_SILVER_PORTRAIT));
    }
    
    public static void initialize() {
    	logger.info("========================= BLACKRUSEMOD INIT =========================");
		
		@SuppressWarnings("unused")
		BlackRuseMod blackruse = new BlackRuseMod();
		
		logger.info("================================================================");
    }
    
    @Override
    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture(makePath(BADGE_IMG));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("BlackruseMod does not have any settings (yet)!", 400.0f, 700.0f, settingsPanel, (me)->{}));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }
    
    @Override
	public void receiveEditCharacters() {
		logger.info("begin editting characters");
		
		logger.info("add " + TheServantEnum.THE_SERVANT.toString());
		if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
			BaseMod.addCharacter(TheServant.class, "致命侍从", "Servant class string",
					AbstractCardEnum.SILVER.toString(), "致命侍从",
					makePath(SERVANT_BUTTON), makePath(SERVANT_PORTRAIT),
					TheServantEnum.THE_SERVANT.toString());
		}
		else {
			BaseMod.addCharacter(TheServant.class, "The Servant", "Servant class string",
				AbstractCardEnum.SILVER.toString(), "The Servant",
				makePath(SERVANT_BUTTON), makePath(SERVANT_PORTRAIT),
				TheServantEnum.THE_SERVANT.toString());
		}
		logger.info("done editting characters");
	}
    
    @Override
	public void receiveEditRelics() {
		logger.info("begin editting relics");
        
        // Add relics
		BaseMod.addRelicToCustomPool(new Uniform(), AbstractCardEnum.SILVER.toString());
//		BaseMod.addRelicToCustomPool(new Blueberries(), AbstractCardEnum.SEEKER_PURPLE.toString());
//		BaseMod.addRelicToCustomPool(new PaperPengwin(), AbstractCardEnum.SEEKER_PURPLE.toString());
//		BaseMod.addRelicToCustomPool(new CosmicSieve(), AbstractCardEnum.SEEKER_PURPLE.toString());
//		BaseMod.addRelicToCustomPool(new SolarEgg(), AbstractCardEnum.SEEKER_PURPLE.toString());
//		BaseMod.addRelicToCustomPool(new RodOfNegation(), AbstractCardEnum.SEEKER_PURPLE.toString());
//		BaseMod.addRelicToCustomPool(new Telescope(), AbstractCardEnum.SEEKER_PURPLE.toString());
        
        logger.info("done editting relics");
	}
    
    @Override
	public void receiveEditCards() {
		logger.info("begin editting cards");
		
		logger.info("add cards for " + TheServantEnum.THE_SERVANT.toString());
		
		BaseMod.addCard(new Strike_Silver());
		BaseMod.addCard(new Defend_Silver());
		
		BaseMod.addCard(new Advance());
		BaseMod.addCard(new Barrier());
		BaseMod.addCard(new BorrowedTime());
		BaseMod.addCard(new BruteForce());
		BaseMod.addCard(new Comet());
		BaseMod.addCard(new CosmicInflation());
		BaseMod.addCard(new DWeaponry());
		BaseMod.addCard(new Deny());
		BaseMod.addCard(new DoubleEdge());
		BaseMod.addCard(new DualDimension());
		BaseMod.addCard(new Duplication());
		BaseMod.addCard(new Enbodiment());
		BaseMod.addCard(new Exchange());
		BaseMod.addCard(new FanOfKnives());		
		BaseMod.addCard(new Farseeing());
		BaseMod.addCard(new FastForward());
		BaseMod.addCard(new FirstStrike());
		BaseMod.addCard(new FlawlessForm());
		BaseMod.addCard(new FloweringNight());
		BaseMod.addCard(new FollowUp());
		BaseMod.addCard(new FullDefense());
		BaseMod.addCard(new FullOffense());
		BaseMod.addCard(new GarbageDisposal());
		BaseMod.addCard(new Gouge());
		BaseMod.addCard(new Haste());
		BaseMod.addCard(new HouseCleaning());
		BaseMod.addCard(new Indiscriminate());
		BaseMod.addCard(new KidneyShot());
		BaseMod.addCard(new KillerInstinct());
		BaseMod.addCard(new KillingDoll());
		BaseMod.addCard(new Laundry());
		BaseMod.addCard(new Misdirection());
		BaseMod.addCard(new MoonPhase());
		BaseMod.addCard(new Moonlight());
		BaseMod.addCard(new MorningCall());
		BaseMod.addCard(new MurderousAura());
		BaseMod.addCard(new NoEscape());
		BaseMod.addCard(new Potential());
		BaseMod.addCard(new PullOut());
		BaseMod.addCard(new Read());
		BaseMod.addCard(new RealityMarble());
		BaseMod.addCard(new Rearm());
		BaseMod.addCard(new Replace());
		BaseMod.addCard(new Reset());
		BaseMod.addCard(new ReturningBlades());
		BaseMod.addCard(new Revamp());
		BaseMod.addCard(new Rewind());
		BaseMod.addCard(new Sabotage());
		BaseMod.addCard(new ShiftingGears());
		BaseMod.addCard(new ShiftingReality());
		BaseMod.addCard(new ShiftingThoughts());
		BaseMod.addCard(new SilverBlades());
		BaseMod.addCard(new Snipe());
		BaseMod.addCard(new SoulSculpture());
		BaseMod.addCard(new SpecialFormula());
		BaseMod.addCard(new Spin());
		BaseMod.addCard(new StarChef());
		BaseMod.addCard(new Starlight());
		BaseMod.addCard(new StressReliever());
		BaseMod.addCard(new Sunlight());
		BaseMod.addCard(new SurpressingFire());
		BaseMod.addCard(new Taunt());
		BaseMod.addCard(new TemporalArms());
		BaseMod.addCard(new TemporalEssence());
		BaseMod.addCard(new TemporalMisd());
		BaseMod.addCard(new TemporalSlicing());
		BaseMod.addCard(new TheWorld());
		BaseMod.addCard(new TimeEmbedded());
		BaseMod.addCard(new TimeWarp());
		BaseMod.addCard(new TrashToTreasure());
		BaseMod.addCard(new Unparalleled());
		BaseMod.addCard(new Unruled());
		BaseMod.addCard(new Unsheathe());
		
		// make sure everything is always unlocked		
		UnlockTracker.unlockCard("Strike_S");
		UnlockTracker.unlockCard("Defend_S");
		
		UnlockTracker.unlockCard("Advance");
		UnlockTracker.unlockCard("Barrier");
		UnlockTracker.unlockCard("BorrowedTime");
		UnlockTracker.unlockCard("BruteForce");
		UnlockTracker.unlockCard("Comet");
		UnlockTracker.unlockCard("CosmicInflation");
		UnlockTracker.unlockCard("DWeaponry");
		UnlockTracker.unlockCard("Deny");		
		UnlockTracker.unlockCard("DoubleEdge");
		UnlockTracker.unlockCard("DualDimension");		
		UnlockTracker.unlockCard("Duplication");
		UnlockTracker.unlockCard("Enbodiment");
		UnlockTracker.unlockCard("Exchange");
		UnlockTracker.unlockCard("FanOfKnives");		
		UnlockTracker.unlockCard("Farseeing");
		UnlockTracker.unlockCard("FastForward");
		UnlockTracker.unlockCard("FirstStrike");
		UnlockTracker.unlockCard("FlawlessForm");
		UnlockTracker.unlockCard("FloweringNight");
		UnlockTracker.unlockCard("FollowUp");
		UnlockTracker.unlockCard("FullDefense");
		UnlockTracker.unlockCard("FullOffense");
		UnlockTracker.unlockCard("GarbageDisposal");
		UnlockTracker.unlockCard("Gouge");
		UnlockTracker.unlockCard("Haste");
		UnlockTracker.unlockCard("HouseCleaning");
		UnlockTracker.unlockCard("Indiscriminate");
		UnlockTracker.unlockCard("KidneyShot");
		UnlockTracker.unlockCard("KillerInstinct");
		UnlockTracker.unlockCard("KillingDoll");
		UnlockTracker.unlockCard("Laundry");
		UnlockTracker.unlockCard("Misdirection");
		UnlockTracker.unlockCard("MoonPhase");
		UnlockTracker.unlockCard("Moonlight");
		UnlockTracker.unlockCard("MorningCall");
		UnlockTracker.unlockCard("MurderousAura");
		UnlockTracker.unlockCard("NoEscape");
		UnlockTracker.unlockCard("Potential");
		UnlockTracker.unlockCard("PullOut");
		UnlockTracker.unlockCard("Read");
		UnlockTracker.unlockCard("RealityMarble");
		UnlockTracker.unlockCard("Rearm");
		UnlockTracker.unlockCard("Replace");
		UnlockTracker.unlockCard("Reset");
		UnlockTracker.unlockCard("ReturningBlades");
		UnlockTracker.unlockCard("Revamp");
		UnlockTracker.unlockCard("Rewind");
		UnlockTracker.unlockCard("Sabotage");
		UnlockTracker.unlockCard("ShiftingGears");
		UnlockTracker.unlockCard("ShiftingReality");
		UnlockTracker.unlockCard("ShiftingThoughts");
		UnlockTracker.unlockCard("SilverBlades");
		UnlockTracker.unlockCard("Snipe");
		UnlockTracker.unlockCard("SoulSculpture");
		UnlockTracker.unlockCard("SpecialFormula");
		UnlockTracker.unlockCard("Spin");
		UnlockTracker.unlockCard("StarChef");
		UnlockTracker.unlockCard("Starlight");
		UnlockTracker.unlockCard("StressReliever");
		UnlockTracker.unlockCard("Sunlight");
		UnlockTracker.unlockCard("SurpressingFire");
		UnlockTracker.unlockCard("Taunt");
		UnlockTracker.unlockCard("TemporalArms");
		UnlockTracker.unlockCard("TemporalEssence");
		UnlockTracker.unlockCard("TemporalMisd");
		UnlockTracker.unlockCard("TemporalSlicing");
		UnlockTracker.unlockCard("TheWorld");
		UnlockTracker.unlockCard("TimeEmbedded");
		UnlockTracker.unlockCard("TimeWarp");
		UnlockTracker.unlockCard("TrashToTreasure");
		UnlockTracker.unlockCard("Unparalleled");
		UnlockTracker.unlockCard("Unruled");
		UnlockTracker.unlockCard("Unsheathe");
		
		logger.info("done editting cards");
	}
    
    @Override
	public void receiveEditStrings() {
		logger.info("begin editting strings");
		
        // RelicStrings
		if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
			String relicStrings = Gdx.files.internal("localization/BlackruseMod-RelicStrings-zhs.json").readString(String.valueOf(StandardCharsets.UTF_8));
			BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);}
		else {
			String relicStrings = Gdx.files.internal("localization/BlackruseMod-RelicStrings-eng.json").readString(String.valueOf(StandardCharsets.UTF_8));
	        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);}
        
        // CardStrings
        if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
        	String cardStrings = Gdx.files.internal("localization/BlackruseMod-CardStrings-zhs.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(CardStrings.class, cardStrings);}
        else {String cardStrings = Gdx.files.internal("localization/BlackruseMod-CardStrings-eng.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(CardStrings.class, cardStrings);}
        
		// PowerStrings
        if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
        	String powerStrings = Gdx.files.internal("localization/BlackruseMod-PowerStrings-zhs.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);}
        else {String powerStrings = Gdx.files.internal("localization/BlackruseMod-PowerStrings-eng.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);}
        
     // KeywordStrings
//        if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
//        	String keywordStrings = Gdx.files.internal("localization/BlackruseMod-KeywordStrings-zhs.json").readString(String.valueOf(StandardCharsets.UTF_8));
//        	BaseMod.loadCustomStrings(KeywordStrings.class, keywordStrings);}
//        else {String keywordStrings = Gdx.files.internal("localization/BlackruseMod-KeywordStrings-eng.json").readString(String.valueOf(StandardCharsets.UTF_8));
//        	BaseMod.loadCustomStrings(KeywordStrings.class, keywordStrings);}
        
		logger.info("done editting strings");
	}

	@Override
	public void receiveSetUnlocks() {
//		UnlockTracker.addCard("Flicker");
//		UnlockTracker.addCard("Transference");
//		UnlockTracker.addCard("ForceRipple");
//		// seeker unlock 1
//		BaseMod.addUnlockBundle(new CustomUnlockBundle(
//				"Flicker", "Transference", "ForceRipple"
//				), TheSeekerEnum.THE_SEEKER, 1);
//		
//		// seeker unlock 2
//		BaseMod.addUnlockBundle(new CustomUnlockBundle(
//				"Channel", "Shimmer", "ThoughtRaze"
//				), TheSeekerEnum.THE_SEEKER, 2);
//		UnlockTracker.addCard("Channel");
//		UnlockTracker.addCard("Shimmer");
//		UnlockTracker.addCard("ThoughtRaze");
//		
//		// seeker unlock 3 (Vacuum tmp in place of Feedback)
//		BaseMod.addUnlockBundle(new CustomUnlockBundle(
//				"Convergence", "Hypothesis", "Nexus"
//				), TheSeekerEnum.THE_SEEKER, 3);
//		UnlockTracker.addCard("Convergence");
//		UnlockTracker.addCard("Hypothesis");
//		UnlockTracker.addCard("Nexus");
	}
	

	@Override
	public void receiveEditKeywords() {
        logger.info("setting up custom keywords");
        BaseMod.addKeyword(new String[] {"投掷"}, "投掷会使用你的 飞刀 。如果飞刀耗尽， 投掷将会失去效果。");
        BaseMod.addKeyword(new String[] {"飞刀"}, "飞刀是致命侍从的专属武器。 投掷 飞刀的卡会消耗飞刀的数量。");
        BaseMod.addKeyword(new String[] {"幻时"}, "幻时卡 不能被打出 且具有 虚无 ，在抽到的时候会产生特定作用。");
        BaseMod.addKeyword(new String[] {"负面状态"}, "负面状态包括 虚弱 、 易伤 以及 脆弱 。");
        BaseMod.addKeyword(new String[] {"伤害加深"}, "受到攻击时会额外承受伤害。额外伤害不受 易伤 影响。");
        BaseMod.addKeyword(new String[] {"固定伤害"}, "固定伤害不受 力量 、 易伤 或者 伤害加深 影响。");
        BaseMod.addKeyword(new String[] {"Throw", "throw"}, "Throw will spend your Knives. If you have depleted your Knives, the card will stop working.");
        BaseMod.addKeyword(new String[] {"Knives", "knives", "Knife", "knife"}, "Knives are The Servent's most dedicated weapons. They are spent by cards that Throw knives.");
        BaseMod.addKeyword(new String[] {"Temporal", "temporal"}, "Temporal cards are Unplayable and Ethereal and trigger unique effects when drawn.");
        BaseMod.addKeyword(new String[] {"Debuffs", "debuffs", "debuff", "Debuff"}, "Debuffs include Weak, Vulnerable and Frail.");
        BaseMod.addKeyword(new String[] {"Amplify Damage", "amplify damage", "Damage Amplified", "damage amplified"}, "Takes extra damage when attacked. Extra damage will not be affected by with Vulnerable.");
        BaseMod.addKeyword(new String[] {"Static Damage", "static damage"}, "Static damage is not affected by Strength, Vulnerable or Amplify Damage.");
        logger.info("done setting up custom keywords");
	}
}