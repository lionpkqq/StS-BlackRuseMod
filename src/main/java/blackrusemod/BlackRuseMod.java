package blackrusemod;

import java.nio.charset.StandardCharsets;

import blackrusemod.cards.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.AbstractUnlock;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.abstracts.CustomUnlockBundle;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.SetUnlocksSubscriber;

import blackrusemod.characters.TheServant;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.patches.TheServantEnum;
import blackrusemod.powers.*;
import blackrusemod.relics.*;
import blackrusemod.screens.VisionScreen;

@SpireInitializer
public class BlackRuseMod implements PostInitializeSubscriber,
	EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber,
	EditStringsSubscriber, SetUnlocksSubscriber, EditKeywordsSubscriber {
	public static final Logger logger = LogManager.getLogger(BlackRuseMod.class.getName());
	public static VisionScreen vs;
	
	private static final String MODNAME = "BlackRuseMod";
    private static final String AUTHOR = "BlackRuse";
    private static final String DESCRIPTION = "v0.9.2.1\n Adds The Servant as a playable third character";
    
    private static final Color SILVER = CardHelper.getColor(131.0f, 156.0f, 165.0f);
    private static final String BLACKRUSE_MOD_ASSETS_FOLDER = "img";
    
    // card backgrounds
    private static final String ATTACK_SILVER = "512/bg_attack_silver.png";
    private static final String SKILL_SILVER = "512/bg_skill_silver.png";
    private static final String POWER_SILVER = "512/bg_power_silver.png";
    private static final String ENERGY_ORB_SILVER = "512/card_silver_orb.png";
    
    private static final String ATTACK_SILVER_PORTRAIT = "1024/bg_attack_silver.png";
    private static final String SKILL_SILVER_PORTRAIT = "1024/bg_skill_silver.png";
    private static final String POWER_SILVER_PORTRAIT = "1024/bg_power_silver.png";
    private static final String ENERGY_ORB_SILVER_PORTRAIT = "1024/card_silver_orb.png";
    
    // card images
    public static final String _DUMMYATTACK = "cards/_dummy_attack.png";
    public static final String _DUMMYNOTATTACK = "cards/_dummy_not_attack.png";
    public static final String INSTANT_ARMOR = "cards/instant_armor.png";
    public static final String ALLEVIATE = "cards/alleviate.png";
    public static final String BARRIER = "cards/barrier.png";
    public static final String PENDULUM = "cards/pendulum.png";
    public static final String ORBIT = "cards/orbit.png";
    public static final String LUMINOSITY = "cards/luminosity.png";
    public static final String COMET = "cards/comet.png";
    public static final String DILATION = "cards/dilation.png";
    public static final String MOONDIAL = "cards/moondial.png";
    public static final String DEFEND_SILVER = "cards/defend_silver.png";
    public static final String DENY = "cards/deny.png";
    public static final String DOUBLE_EDGE = "cards/double_edge.png";
    public static final String DUAL_DIMENSION = "cards/dual_dimension.png";
    public static final String DUPLICATION = "cards/duplication.png";
    public static final String EMBODIMENT = "cards/embodiment.png";
    public static final String DEFY = "cards/defy.png";
    public static final String FAN_OF_KNIVES = "cards/fan_of_knives.png";
    public static final String GEAR_UP = "cards/gear_up.png";
    public static final String FAST_FORWARD = "cards/fast_forward.png";
    public static final String RICOCHET = "cards/ricochet.png";
    public static final String FLAWLESS_FORM = "cards/flawless_form.png";
    public static final String FLOWERING_NIGHT = "cards/flowering_night.png";
    public static final String INITIATOR = "cards/initiator.png";
    public static final String FOLLOW_UP = "cards/follow_up.png";
    public static final String FOLLOW_UP_2 = "cards/follow_up_2.png";
    public static final String FINISHING_TOUCH = "cards/finishing_touch.png";
    public static final String HIGHTAIL = "cards/hightail.png";
    public static final String ADVANCE = "cards/advance.png";
    public static final String FORGED_IN_TIME = "cards/forged_in_time.png";
    public static final String GOUGE = "cards/gouge.png";
    public static final String BEST_PRACTICE = "cards/best_practice.png";
    public static final String EXCHANGE = "cards/exchange.png";
    public static final String KIDNEY_SHOT = "cards/kidney_shot.png";
    public static final String WASTE_NOT = "cards/waste_not.png";
    public static final String KILLING_DOLL = "cards/killing_doll.png";
    public static final String COLLAPSE = "cards/collapse.png";
    public static final String MANIPULATE = "cards/manipulate.png";
    public static final String MISDIRECTION = "cards/misdirection.png";
    public static final String MOON_PHASE = "cards/moon_phase.png";
    public static final String MOONLIGHT = "cards/moonlight.png";
    public static final String BOTTLED_TIME = "cards/bottled_time.png";
    public static final String MURDEROUS_AURA = "cards/murderous_aura.png";
    public static final String DEADLINE = "cards/deadline.png";
    public static final String POTENTIAL = "cards/potential.png";
    public static final String DESOLATION = "cards/desolation.png";
    public static final String READ = "cards/read.png";
    public static final String REALITY_MARBLE = "cards/reality_marble.png";
    public static final String REARM = "cards/rearm.png";
    public static final String REPLACE = "cards/replace.png";
    public static final String RESET = "cards/reset.png";
    public static final String RETURNING_BLADE = "cards/returning_blade.png";
    public static final String SHUTTLE = "cards/shuttle.png";
    public static final String REWIND = "cards/rewind.png";
    public static final String SABOTAGE = "cards/sabotage.png";
    public static final String SHIFTING_GEARS = "cards/shifting_gears.png";
    public static final String SHATTERED_REALITY = "cards/shattered_reality.png";
    public static final String SILVER_MATRIX = "cards/silver_matrix.png";
    public static final String SILVER_BLADES = "cards/silver_blades.png";
    public static final String SNIPE = "cards/snipe.png";
    public static final String SOLIDIFY = "cards/solidify.png";
    public static final String SOUL_SCULPTURE = "cards/soul_sculpture.png";
    public static final String SPECIAL_FORMULA = "cards/special_formula.png";
    public static final String SPIN = "cards/spin.png";
    public static final String STAR_CHEF = "cards/star_chef.png";
    public static final String STARLIGHT = "cards/starlight.png";
    public static final String FORESIGHT = "cards/foresight.png";
    public static final String STRIKE_SILVER = "cards/strike_silver.png";
    public static final String SUNLIGHT = "cards/sunlight.png";
    public static final String SUPPRESSING_FIRE = "cards/suppressing_fire.png";
    public static final String TIME_THEFT = "cards/time_theft.png";
    public static final String TEMPORAL_DEFENSE = "cards/temporal_defense.png";
    public static final String TEMPORAL_ESSENCE = "cards/temporal_essence.png";
    public static final String TEMPORAL_MISD = "cards/temporal_misd.png";
    public static final String TEMPORAL_SLICING = "cards/temporal_slicing.png";
    public static final String THE_WORLD = "cards/the_world.png";
    public static final String CONTRACTION = "cards/contraction.png";
    public static final String TIME_WARP = "cards/time_warp.png";
    public static final String REVAMP = "cards/revamp.png";
    public static final String TRUE_SIGHT = "cards/true_sight.png";
    public static final String DANCING_SILVER = "cards/dancing_silver.png";
    public static final String UNRULED = "cards/unruled.png";
    public static final String LIGHT_FLOW = "cards/light_flow.png";
    
    // power images
    public static final String CUSTOM_POWERS = "powers/custom_powers.atlas";
    
    // relic images
    public static final String UNIFORM_RELIC = "img/relics/uniform.png";
    public static final String BROOM_RELIC = "img/relics/broom.png";
    public static final String KNEE_BRACE_RELIC = "img/relics/knee_brace.png";
    public static final String POCKET_WATCH_RELIC = "img/relics/pocket_watch.png";
    public static final String STONE_MASK_RELIC = "img/relics/stone_mask.png";
    public static final String MYSTERY_SWORD_RELIC = "img/relics/mystery_sword.png";
    public static final String PAPER_SWAN_RELIC = "img/relics/paper_swan.png";
    public static final String SPLENDID_ATTIRE_RELIC = "img/relics/splendid_attire.png";
    public static final String OLD_SCARF_RELIC = "img/relics/old_scarf.png";
    public static final String PAN_RELIC = "img/relics/pan.png";
    public static final String ROMAN_BRACELET_RELIC = "img/relics/roman_bracelet.png";
    
    // relic outlines
    public static final String UNIFORM_RELIC_OUTLINE = "img/relics/outline/uniform.png";
    public static final String BROOM_RELIC_OUTLINE = "img/relics/outline/broom.png";
    public static final String KNEE_BRACE_RELIC_OUTLINE = "img/relics/outline/knee_brace.png";
    public static final String POCKET_WATCH_RELIC_OUTLINE = "img/relics/outline/pocket_watch.png";
    public static final String STONE_MASK_RELIC_OUTLINE = "img/relics/outline/stone_mask.png";
    public static final String MYSTERY_SWORD_RELIC_OUTLINE = "img/relics/outline/mystery_sword.png";
    public static final String PAPER_SWAN_RELIC_OUTLINE = "img/relics/outline/paper_swan.png";
    public static final String SPLENDID_ATTIRE_RELIC_OUTLINE = "img/relics/outline/splendid_attire.png";
    public static final String OLD_SCARF_RELIC_OUTLINE = "img/relics/outline/old_scarf.png";
    public static final String PAN_RELIC_OUTLINE = "img/relics/outline/pan.png";
    public static final String ROMAN_BRACELET_RELIC_OUTLINE = "img/relics/outline/roman_bracelet.png";
    
    // servant assets
    private static final String SERVANT_BUTTON = "charSelect/ServantButton.png";
    public static final String SERVANT_MAIN = "char/servant/main.png";
    private static final String SERVANT_PORTRAIT = "charSelect/ServantPortraitBG.jpg";
    public static final String SERVANT_SHOULDER_1 = "char/servant/shoulder.png";
    public static final String SERVANT_SHOULDER_2 = "char/servant/shoulder2.png";
    public static final String SERVANT_CORPSE = "char/servant/corpse.png";
    
    // badge
    public static final String BADGE_IMG = "BRBadge.png";
    
    // texture loaders
    public static TextureAtlas getPowerTextureAtlas() {
    	return new TextureAtlas(makePath(CUSTOM_POWERS));
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
        BaseMod.addColor(AbstractCardEnum.SILVER,
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
        
        // If player hasn't seen the strike card (due to ID change), but already has unlocks
        // then unlock progress needs to be reset (so player can unlock cards/relics again)
        if(!UnlockTracker.isCardSeen(Strike_Silver.ID) && UnlockTracker.getUnlockLevel(TheServantEnum.THE_SERVANT) > 0) {
        	UnlockTracker.resetUnlockProgress(TheServantEnum.THE_SERVANT);
        }
        
        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }
    
    @Override
	public void receiveEditCharacters() {
		logger.info("begin editting characters");
		
		logger.info("add " + TheServantEnum.THE_SERVANT.toString());
		if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
			BaseMod.addCharacter(new TheServant("The Servant", TheServantEnum.THE_SERVANT),
					makePath(SERVANT_BUTTON), makePath(SERVANT_PORTRAIT),
					TheServantEnum.THE_SERVANT);
		}
		else {
			BaseMod.addCharacter(new TheServant("The Servant", TheServantEnum.THE_SERVANT),
					makePath(SERVANT_BUTTON), makePath(SERVANT_PORTRAIT),
					TheServantEnum.THE_SERVANT);
		}
		logger.info("done editting characters");
	}
    
    @Override
	public void receiveEditRelics() {
		logger.info("begin editting relics");
        
        // Add relics
		BaseMod.addRelicToCustomPool(new Uniform(), AbstractCardEnum.SILVER);
		BaseMod.addRelicToCustomPool(new Broom(), AbstractCardEnum.SILVER);
		BaseMod.addRelicToCustomPool(new KneeBrace(), AbstractCardEnum.SILVER);
		BaseMod.addRelicToCustomPool(new PocketWatch(), AbstractCardEnum.SILVER);
		BaseMod.addRelicToCustomPool(new StoneMask(), AbstractCardEnum.SILVER);
		BaseMod.addRelicToCustomPool(new MysterySword(), AbstractCardEnum.SILVER);
		BaseMod.addRelicToCustomPool(new SplendidAttire(), AbstractCardEnum.SILVER);
		BaseMod.addRelicToCustomPool(new PaperSwan(), AbstractCardEnum.SILVER);
		BaseMod.addRelicToCustomPool(new OldScarf(), AbstractCardEnum.SILVER);
		BaseMod.addRelicToCustomPool(new Pan(), AbstractCardEnum.SILVER);
		BaseMod.addRelicToCustomPool(new RomanBracelet(), AbstractCardEnum.SILVER);
        logger.info("done editting relics");
	}
    
    @Override
	public void receiveEditCards() {
		logger.info("begin editting cards");
		logger.info("add cards for " + TheServantEnum.THE_SERVANT.toString());
		
		BaseMod.addCard(new Strike_Silver());
		BaseMod.addCard(new Defend_Silver());
		
		BaseMod.addCard(new InstantArmor());
		BaseMod.addCard(new Alleviate());
		BaseMod.addCard(new Barrier());
		BaseMod.addCard(new PendulumOfEternity());
		BaseMod.addCard(new Orbit());
		BaseMod.addCard(new Comet());
		BaseMod.addCard(new Dilation());
		BaseMod.addCard(new Moondial());
		BaseMod.addCard(new Deny());
		BaseMod.addCard(new DoubleEdge());
		BaseMod.addCard(new DualDimension());
		BaseMod.addCard(new Duplication());
		BaseMod.addCard(new Embodiment());
		BaseMod.addCard(new Defy());
		BaseMod.addCard(new FanOfKnives());		
		BaseMod.addCard(new GearUp());
		BaseMod.addCard(new FastForward());
		BaseMod.addCard(new Ricochet());
		BaseMod.addCard(new FlawlessForm());
		BaseMod.addCard(new FloweringNight());
		BaseMod.addCard(new Initiator());
		BaseMod.addCard(new Hightail());
		BaseMod.addCard(new Advance());
		BaseMod.addCard(new ForgedInTime());
		BaseMod.addCard(new Gouge());
		BaseMod.addCard(new BestPractice());
		BaseMod.addCard(new Exchange());
		BaseMod.addCard(new Luminosity());
		BaseMod.addCard(new KidneyShot());
		BaseMod.addCard(new WasteNot());
		BaseMod.addCard(new KillingDoll());
		BaseMod.addCard(new Collapse());
		BaseMod.addCard(new Manipulate());
		BaseMod.addCard(new Misdirection());
		BaseMod.addCard(new MoonPhase());
		BaseMod.addCard(new Moonlight());
		BaseMod.addCard(new BottledTime());
		BaseMod.addCard(new MurderousAura());
		BaseMod.addCard(new Deadline());
		BaseMod.addCard(new Potential());
		BaseMod.addCard(new Desolation());
		BaseMod.addCard(new Read());
		BaseMod.addCard(new RealityMarble());
		BaseMod.addCard(new Rearm());
		BaseMod.addCard(new Replace());
		BaseMod.addCard(new Reset());
		BaseMod.addCard(new ReturningBlade());
		BaseMod.addCard(new Shuttle());
		BaseMod.addCard(new Rewind());
		BaseMod.addCard(new Sabotage());
		BaseMod.addCard(new ShiftingGears());
		BaseMod.addCard(new ShatteredReality());
		BaseMod.addCard(new SilverMatrix());
		BaseMod.addCard(new SilverBlades());
		BaseMod.addCard(new Snipe());
		BaseMod.addCard(new Solidify());
		BaseMod.addCard(new SoulSculpture());
		BaseMod.addCard(new SpecialFormula());
		BaseMod.addCard(new Spin());
		BaseMod.addCard(new StarChef());
		BaseMod.addCard(new Starlight());
		BaseMod.addCard(new Foresight());
		BaseMod.addCard(new Sunlight());
		BaseMod.addCard(new SuppressingFire());
		BaseMod.addCard(new TimeTheft());
		BaseMod.addCard(new TheWorld());
		BaseMod.addCard(new Contraction());
		BaseMod.addCard(new TimeWarp());
		BaseMod.addCard(new Revamp());
		BaseMod.addCard(new TrueSight());
		BaseMod.addCard(new DancingSilver());
		BaseMod.addCard(new Unruled());
		BaseMod.addCard(new LightFlow());
		
		BaseMod.addCard(new Initiator());
		BaseMod.addCard(new FollowUp());
		BaseMod.addCard(new FollowUp2());
		BaseMod.addCard(new FinishingTouch());
		BaseMod.addCard(new TemporalDefense());
		BaseMod.addCard(new TemporalEssence());
		BaseMod.addCard(new TemporalMisd());
		BaseMod.addCard(new TemporalSlicing());
		
		BaseMod.addPower(AmplifyDamagePower.class, AmplifyDamagePower.POWER_ID);
		BaseMod.addPower(DeadlinePower.class, DeadlinePower.POWER_ID);
		BaseMod.addPower(ElegancePower.class, ElegancePower.POWER_ID);
		BaseMod.addPower(EmbodimentPower.class, EmbodimentPower.POWER_ID);
		BaseMod.addPower(FalseFlawlessFormPower.class, FalseFlawlessFormPower.POWER_ID);
		BaseMod.addPower(FlawlessFormPower.class, FlawlessFormPower.POWER_ID);
		BaseMod.addPower(FloweringNightPower.class, FloweringNightPower.POWER_ID);
		BaseMod.addPower(ForesightPower.class, ForesightPower.POWER_ID);
		BaseMod.addPower(KnivesPower.class, KnivesPower.POWER_ID);
		//BaseMod.addPower(ManipulatePower.class, ManipulatePower.POWER_ID);
		BaseMod.addPower(MatrixPower.class, MatrixPower.POWER_ID);
		BaseMod.addPower(MoonPhasePower.class, MoonPhasePower.POWER_ID);
		BaseMod.addPower(MurderousAuraPower.class, MurderousAuraPower.POWER_ID);
		BaseMod.addPower(ProperPracticePower.class, ProperPracticePower.POWER_ID);
		BaseMod.addPower(ProtectionPower.class, ProtectionPower.POWER_ID);
		BaseMod.addPower(ReadPower.class, ReadPower.POWER_ID);
		BaseMod.addPower(RealityMarblePower.class, RealityMarblePower.POWER_ID);
		BaseMod.addPower(ReturningBladePower.class, ReturningBladePower.POWER_ID);
		BaseMod.addPower(SatellitePower.class, SatellitePower.POWER_ID);
		BaseMod.addPower(SilverBladesPower.class, SilverBladesPower.POWER_ID);
		BaseMod.addPower(SnipePower.class, SnipePower.POWER_ID);
		BaseMod.addPower(StarChefPower.class, StarChefPower.POWER_ID);
		BaseMod.addPower(SuppressingFirePower.class, SuppressingFirePower.POWER_ID);
		BaseMod.addPower(TheWorldPower.class, TheWorldPower.POWER_ID);
		BaseMod.addPower(TimeTheftPower.class, TimeTheftPower.POWER_ID);
		BaseMod.addPower(TrueSightPower.class, TrueSightPower.POWER_ID);
		BaseMod.addPower(UpgradedEmbodimentPower.class, UpgradedEmbodimentPower.POWER_ID);
		BaseMod.addPower(WasteNotPower.class, WasteNotPower.POWER_ID);
		
		logger.info("done editting cards");
	}
    
    @Override
	public void receiveEditStrings() {
		logger.info("begin editting strings");
		
        // RelicStrings
		if (Settings.language == GameLanguage.ZHS) {
	        String relicStrings = Gdx.files.internal("localization/BlackruseMod-RelicStrings-zhs.json").readString(String.valueOf(StandardCharsets.UTF_8));
	        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);}
		else if (Settings.language == GameLanguage.ZHT) {
	        String relicStrings = Gdx.files.internal("localization/BlackruseMod-RelicStrings-zht.json").readString(String.valueOf(StandardCharsets.UTF_8));
	        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);}
		else {String relicStrings = Gdx.files.internal("localization/BlackruseMod-RelicStrings-eng.json").readString(String.valueOf(StandardCharsets.UTF_8));
	        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);}
        
        // CardStrings
        if (Settings.language == GameLanguage.ZHS) {
        	String cardStrings = Gdx.files.internal("localization/BlackruseMod-CardStrings-zhs.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(CardStrings.class, cardStrings);}
		else if (Settings.language == GameLanguage.ZHT) {
        	String cardStrings = Gdx.files.internal("localization/BlackruseMod-CardStrings-zht.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(CardStrings.class, cardStrings);}
        else {String cardStrings = Gdx.files.internal("localization/BlackruseMod-CardStrings-eng.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(CardStrings.class, cardStrings);}
        
		// PowerStrings
        if (Settings.language == GameLanguage.ZHS) {
        	String powerStrings = Gdx.files.internal("localization/BlackruseMod-PowerStrings-zhs.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);}
        else if (Settings.language == GameLanguage.ZHT) {
        	String powerStrings = Gdx.files.internal("localization/BlackruseMod-PowerStrings-zht.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);}
        else {String powerStrings = Gdx.files.internal("localization/BlackruseMod-PowerStrings-eng.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);}
        
		logger.info("done editting strings");
	}

	@Override
	public void receiveSetUnlocks() {
		// servant unlock 1
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				Manipulate.ID, Moondial.ID, Embodiment.ID
				), TheServantEnum.THE_SERVANT, 0);
		UnlockTracker.addCard(Manipulate.ID);
		UnlockTracker.addCard(Moondial.ID);
		UnlockTracker.addCard(Embodiment.ID);
		
		// servant unlock 2
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				AbstractUnlock.UnlockType.RELIC, Broom.ID, KneeBrace.ID, Pan.ID
				), TheServantEnum.THE_SERVANT, 1);
		UnlockTracker.addRelic(Broom.ID);
		UnlockTracker.addRelic(KneeBrace.ID);
		UnlockTracker.addRelic(Pan.ID);
		
		// servant unlock 3
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				Deadline.ID, TimeTheft.ID, TrueSight.ID
				), TheServantEnum.THE_SERVANT, 2);
		UnlockTracker.addCard(Deadline.ID);
		UnlockTracker.addCard(TimeTheft.ID);
		UnlockTracker.addCard(TrueSight.ID);
		
		// servant unlock 4
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				AbstractUnlock.UnlockType.RELIC, PaperSwan.ID, RomanBracelet.ID, OldScarf.ID
				), TheServantEnum.THE_SERVANT, 3);
		UnlockTracker.addRelic(PaperSwan.ID);
		UnlockTracker.addRelic(RomanBracelet.ID);
		UnlockTracker.addRelic(OldScarf.ID);
				
		// servant unlock 5
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				Orbit.ID, DancingSilver.ID, SilverMatrix.ID
				), TheServantEnum.THE_SERVANT, 4);
		UnlockTracker.addCard(Orbit.ID);
		UnlockTracker.addCard(DancingSilver.ID);
		UnlockTracker.addCard(SilverMatrix.ID);
	}

	@Override
	public void receiveEditKeywords() {
        logger.info("setting up custom keywords");
		String modid = "blackrusemod";
		//ZHT Translation
        if (Settings.language == Settings.GameLanguage.ZHT) {
                BaseMod.addKeyword(new String[] {"飛刀"}, "飛刀是凜光侍從的專屬武器。可以被投擲或轉化");
                BaseMod.addKeyword(new String[] {"幻時"}, "幻時牌是 #y0 耗能且具有 #y虛無 和 #y消耗");
                BaseMod.addKeyword(new String[] {"枯萎"}, "受到攻擊時會額外承受傷害。額外傷害不受 #y易傷 影響");
                BaseMod.addKeyword(new String[] {"變換"}, "變換 效果會在牌被手動丟棄後觸發");
                BaseMod.addKeyword(new String[] {"視界"}, "預測敵人下回合的意圖。如果預測正確則在你的下個回合開始時觸發效果");
                BaseMod.addKeyword(new String[] {"反衝"}, "給予自身 #b1 層 #y虛弱 、 #y易傷 和 #y脆弱");
                BaseMod.addKeyword(new String[] {"護盾"}, "當你損失生命時， 消耗 #y護盾 而不是生命。下回合開始時不會失去護盾");
                BaseMod.addKeyword(new String[] {"風華"}, "風華增加從牌中獲得的 格擋 和 護盾");
                BaseMod.addKeyword(new String[] {"矩陣"}, "將所受到的 #y攻擊 傷害降低 #b50% 。每受到 #b1 次攻擊損失 #b1 層矩陣");
                BaseMod.addKeyword(new String[] {"衛星"}, "使用攻擊牌時，失去1層衛星，額外造成1次4點傷害的攻擊。你每受到1次攻擊，失去1層衛星，對攻擊者造成4點傷害。衛星視為 #y飛刀");
        }
        //ZHS Translation
        if (Settings.language == Settings.GameLanguage.ZHS) {
                BaseMod.addKeyword(new String[] {"飞刀"}, "飞刀是凛光侍从的专属武器。可以被投掷或转化。");
                BaseMod.addKeyword(new String[] {"幻时"}, "幻时牌 #y费用为0且具有 #y虚无 和 #y消耗 。");
                BaseMod.addKeyword(new String[] {"枯萎"}, "受到攻击时会额外承受伤害。额外伤害不受 #y易伤 影响。");
                BaseMod.addKeyword(new String[] {"变换"}, "变换 效果会在牌被手动丢弃后触发。");
                BaseMod.addKeyword(new String[] {"视界"}, "预测敌人下回合的意图。如果预测正确则在你的下个回合开始时触发效果。");
                BaseMod.addKeyword(new String[] {"反冲"}, "对你附加 #b1 层 #y虚弱 、 #y易伤 和 #y脆弱 。");
                BaseMod.addKeyword(new String[] {"护盾"}, "当你损失生命时，消耗 #y护盾 而不是你的生命。护盾你的下一回合开始时不会消失。");
                BaseMod.addKeyword(new String[] {"风华"}, "风华增加你从牌中获得的格挡值和护盾值。");
                BaseMod.addKeyword(new String[] {"矩阵"}, "将所受到的 #y攻击 伤害降低 #b50% 。每受到 #b1 次攻击损失 #b1 层矩阵。");
                BaseMod.addKeyword(new String[] {"卫星"}, "使用攻击牌时，失去1层卫星，额外攻击一次，造成4点伤害。你每受到一次攻击，失去1层卫星，对攻击者造成4点伤害。卫星视为 #y飞刀 。");
        }
        BaseMod.addKeyword(modid, null, new String[] {"Knives", "knives", "Knife", "knife"}, "Knives are the Servant's most dedicated weapons. Can be thrown or converted into #ySatellites.");
        BaseMod.addKeyword(modid, "Reality Marble", new String[] {"Marbled"}, "This card is not #yEthereal.");
        BaseMod.addKeyword(modid, null, new String[] {"Temporal", "temporal"}, "Temporal cards cost 0 and have #yEthereal and #yExhaust.");
        BaseMod.addKeyword(modid, null, new String[] {"Blight", "blight", "blighted", "Blighted"}, "Blighted creatures take extra damage when attacked.");
        BaseMod.addKeyword(modid, null, new String[] {"Shift", "shift"}, "Shift effects can only be triggered by manually discarding the card.");
        BaseMod.addKeyword(modid, null, new String[] {"Vision", "vision", "Visions", "visions"}, "Predict the enemy intent for the next turn. If correct, trigger the effect(s) at the start of your next turn.");
        BaseMod.addKeyword(modid, null, new String[] {"Backlash", "backlash"}, "Apply #b1 #yWeak, #yVulnerable, AND #yFrail to you.");
        BaseMod.addKeyword(modid, null, new String[] {"Protection", "protection"}, "Whenever you lose HP, lose Protection instead.");
        BaseMod.addKeyword(modid, null, new String[] {"Elegance", "elegance"}, "Elegance improves #yBlock and #yProtection gained from cards.");
        BaseMod.addKeyword(modid, null, new String[] {"Matrix", "matrix"}, "Reduces Attack damage taken by #b50% once.");
        BaseMod.addKeyword(modid, null, new String[] {"Satellites", "satellites", "Satellite", "satellite"}, "When you play an Attack or are attacked, throw #b1 Satellite at the target/attacker for #b4 damage.");
        logger.info("done setting up custom keywords");
	}
}