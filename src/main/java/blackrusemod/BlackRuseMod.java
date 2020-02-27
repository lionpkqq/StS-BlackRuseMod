package blackrusemod;

import java.nio.charset.StandardCharsets;

import blackrusemod.cards.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.AbstractUnlock;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.AutoAdd;
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
import blackrusemod.variables.ProtectionVariable;
import blackrusemod.powers.*;
import blackrusemod.relics.*;

@SpireInitializer
public class BlackRuseMod implements PostInitializeSubscriber,
	EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber,
	EditStringsSubscriber, SetUnlocksSubscriber, EditKeywordsSubscriber {
    public static final Logger logger = LogManager.getLogger(BlackRuseMod.class.getName());
    private static String modID;
	
	private static final String MODNAME = "The Servant";
    private static final String AUTHOR = "BlackRuse";
    private static final String DESCRIPTION = "Adds The Servant as a playable character, based on Touhou's Sakuya.";
    
    private static final Color SERVANT_SILVER = CardHelper.getColor(131.0f, 156.0f, 165.0f);
    
    // card backgrounds
    private static final String ATTACK_SILVER = "images/512/bg_attack_silver.png";
    private static final String SKILL_SILVER = "images/512/bg_skill_silver.png";
    private static final String POWER_SILVER = "images/512/bg_power_silver.png";
    private static final String ENERGY_ORB_SILVER = "images/512/card_silver_orb.png";
    
    private static final String ATTACK_SILVER_PORTRAIT = "images/1024/bg_attack_silver.png";
    private static final String SKILL_SILVER_PORTRAIT = "images/1024/bg_skill_silver.png";
    private static final String POWER_SILVER_PORTRAIT = "images/1024/bg_power_silver.png";
    private static final String ENERGY_ORB_SILVER_PORTRAIT = "images/1024/card_silver_orb.png";
    
    // power images
    public static final String CUSTOM_POWERS = "images/powers/custom_powers.atlas";
    
    // servant assets
    private static final String SERVANT_BUTTON = "images/charSelect/ServantButton.png";
    public static final String SERVANT_MAIN = "images/char/servant/main.png";
    private static final String SERVANT_PORTRAIT = "images/charSelect/ServantPortraitBG.jpg";
    public static final String SERVANT_SHOULDER_1 = "images/char/servant/shoulder.png";
    public static final String SERVANT_SHOULDER_2 = "images/char/servant/shoulder2.png";
    public static final String SERVANT_CORPSE = "images/char/servant/corpse.png";
    
    // badge
    public static final String BADGE_IMG = "images/Badge.png";
    
    // texture loaders
    public static TextureAtlas getPowerTextureAtlas() {
    	return new TextureAtlas(makePath(CUSTOM_POWERS));
    }
    
    public static final String makePath(String resourcePath) {
    	return getModID() + "Resources/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return makePath("images/cards/" + resourcePath);
    }
    
    public static String makeRelicPath(String resourcePath) {
        return makePath("images/relics/" + resourcePath);
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return makeRelicPath("outline/" + resourcePath);
    }
    
    public static String makeOrbPath(String resourcePath) {
        return makePath("orbs/" + resourcePath);
    }
    
    public static String makePowerPath(String resourcePath) {
        return makePath("images/powers/" + resourcePath);
    }
    
    public static String makeEventPath(String resourcePath) {
        return makePath("images/events/" + resourcePath);
    }
    
    public BlackRuseMod() {
    	logger.info("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
        logger.info("Done subscribing");

        setModID("blackrusemod");

        logger.info("Creating the color " + TheServant.Enums.COLOR_SILVER.toString());
        BaseMod.addColor(TheServant.Enums.COLOR_SILVER, SERVANT_SILVER,
        		makePath(ATTACK_SILVER), makePath(SKILL_SILVER),
        		makePath(POWER_SILVER), makePath(ENERGY_ORB_SILVER),
        		makePath(ATTACK_SILVER_PORTRAIT), makePath(SKILL_SILVER_PORTRAIT),
                makePath(POWER_SILVER_PORTRAIT), makePath(ENERGY_ORB_SILVER_PORTRAIT), null);
        logger.info("Done creating the color");
    }

    public static void setModID(String ID) {
        modID = ID;
    }
    
    public static String getModID() {
        return modID;
    }
    
    public static void initialize() {
    	logger.info("=========================== Initializing The Servant ============================");
		
		@SuppressWarnings("unused")
		BlackRuseMod blackruse = new BlackRuseMod();
		
		logger.info("=========================== /The Servant Initialized/ ===========================");
    }
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        // Mod badge
        Texture badgeTexture = new Texture(makePath(BADGE_IMG));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("The Servant does not have any settings (yet)!", 400.0f, 700.0f, settingsPanel, (me)->{}));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
        logger.info("Done loading badge image and mod options");
    }
    
    @Override
	public void receiveEditCharacters() {
		logger.info("Beginning to edit characters.");
		
		logger.info("Add " + TheServant.Enums.THE_SERVANT.toString());
		
		BaseMod.addCharacter(new TheServant("The Servant", TheServant.Enums.THE_SERVANT),
				makePath(SERVANT_BUTTON), makePath(SERVANT_PORTRAIT),
				TheServant.Enums.THE_SERVANT);
		logger.info("done editing characters");
	}
    
    @Override
	public void receiveEditRelics() {
		logger.info("Adding relics");
        
        // Add relics
        BaseMod.addRelicToCustomPool(new Uniform(), TheServant.Enums.COLOR_SILVER);
        BaseMod.addRelicToCustomPool(new Broom(), TheServant.Enums.COLOR_SILVER);
        BaseMod.addRelicToCustomPool(new KneeBrace(), TheServant.Enums.COLOR_SILVER);
        BaseMod.addRelicToCustomPool(new PocketWatch(), TheServant.Enums.COLOR_SILVER);
        BaseMod.addRelicToCustomPool(new StoneMask(), TheServant.Enums.COLOR_SILVER);
        BaseMod.addRelicToCustomPool(new MysterySword(), TheServant.Enums.COLOR_SILVER);
        BaseMod.addRelicToCustomPool(new SplendidAttire(), TheServant.Enums.COLOR_SILVER);
        BaseMod.addRelicToCustomPool(new PaperSwan(), TheServant.Enums.COLOR_SILVER);
        BaseMod.addRelicToCustomPool(new OldScarf(), TheServant.Enums.COLOR_SILVER);
        BaseMod.addRelicToCustomPool(new Pan(), TheServant.Enums.COLOR_SILVER);
        BaseMod.addRelicToCustomPool(new RomanBracelet(), TheServant.Enums.COLOR_SILVER);
        logger.info("Done adding relics!");
	}
    
    @Override
	public void receiveEditCards() {
        logger.info("Adding variables");

        BaseMod.addDynamicVariable(new ProtectionVariable());

		logger.info("Adding cards for " + TheServant.Enums.THE_SERVANT.toString());
		
		new AutoAdd(getModID()).packageFilter(AbstractServantCard.class).cards();
		
		BaseMod.addPower(AmplifyDamagePower.class, AmplifyDamagePower.POWER_ID);
		BaseMod.addPower(ElegancePower.class, ElegancePower.POWER_ID);
		BaseMod.addPower(EmbodimentPower.class, EmbodimentPower.POWER_ID);
		BaseMod.addPower(FalseFlawlessFormPower.class, FalseFlawlessFormPower.POWER_ID);
		BaseMod.addPower(FlawlessFormPower.class, FlawlessFormPower.POWER_ID);
		BaseMod.addPower(FloweringNightPower.class, FloweringNightPower.POWER_ID);
		BaseMod.addPower(ForesightPower.class, ForesightPower.POWER_ID);
		BaseMod.addPower(KnivesPower.class, KnivesPower.POWER_ID);
		BaseMod.addPower(MatrixPower.class, MatrixPower.POWER_ID);
		BaseMod.addPower(MoonPhasePower.class, MoonPhasePower.POWER_ID);
		BaseMod.addPower(MurderousAuraPower.class, MurderousAuraPower.POWER_ID);
		BaseMod.addPower(ProperPracticePower.class, ProperPracticePower.POWER_ID);
		BaseMod.addPower(ProtectionPower.class, ProtectionPower.POWER_ID);
		BaseMod.addPower(RealityMarblePower.class, RealityMarblePower.POWER_ID);
		BaseMod.addPower(SatellitePower.class, SatellitePower.POWER_ID);
		BaseMod.addPower(SilverBladesPower.class, SilverBladesPower.POWER_ID);
		BaseMod.addPower(StarChefPower.class, StarChefPower.POWER_ID);
		BaseMod.addPower(SuppressingFirePower.class, SuppressingFirePower.POWER_ID);
		BaseMod.addPower(TheWorldPower.class, TheWorldPower.POWER_ID);
		BaseMod.addPower(TrueSightPower.class, TrueSightPower.POWER_ID);
		BaseMod.addPower(UpgradedEmbodimentPower.class, UpgradedEmbodimentPower.POWER_ID);
		BaseMod.addPower(WasteNotPower.class, WasteNotPower.POWER_ID);
		
		logger.info("Done adding cards!");
    }
    
    public String getLanguage() {
        switch(Settings.language) {
            case ZHS:
                return "zhs";
            case ZHT:
                return "zht";
            default:
                return "eng";
        }
    }
    
    @Override
	public void receiveEditStrings() {
		logger.info("Beginning to edit strings for mod with ID: " + getModID());
        String lang = getLanguage();

        BaseMod.loadCustomStringsFile(RelicStrings.class, 
            makePath("localization/"+lang+"/blackrusemod-Relic-Strings.json"));
        BaseMod.loadCustomStringsFile(CardStrings.class, 
            makePath("localization/"+lang+"/blackrusemod-Card-Strings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, 
            makePath("localization/"+lang+"/blackrusemod-Power-Strings.json"));
        
		logger.info("Done editing strings");
	}

	@Override
	public void receiveSetUnlocks() {
        // servant unlock 1
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				Manipulate.ID, Moondial.ID, Embodiment.ID
				), TheServant.Enums.THE_SERVANT, 0);
		UnlockTracker.addCard(Manipulate.ID);
		UnlockTracker.addCard(Moondial.ID);
		UnlockTracker.addCard(Embodiment.ID);
		
		// servant unlock 2
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				AbstractUnlock.UnlockType.RELIC, Broom.ID, KneeBrace.ID, Pan.ID
				), TheServant.Enums.THE_SERVANT, 1);
		UnlockTracker.addRelic(Broom.ID);
		UnlockTracker.addRelic(KneeBrace.ID);
		UnlockTracker.addRelic(Pan.ID);
		
		// servant unlock 3
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				Deadline.ID, TimeTheft.ID, TrueSight.ID
				), TheServant.Enums.THE_SERVANT, 2);
		UnlockTracker.addCard(Deadline.ID);
		UnlockTracker.addCard(TimeTheft.ID);
		UnlockTracker.addCard(TrueSight.ID);
		
		// servant unlock 4
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				AbstractUnlock.UnlockType.RELIC, PaperSwan.ID, RomanBracelet.ID, OldScarf.ID
				), TheServant.Enums.THE_SERVANT, 3);
		UnlockTracker.addRelic(PaperSwan.ID);
		UnlockTracker.addRelic(RomanBracelet.ID);
		UnlockTracker.addRelic(OldScarf.ID);
				
		// servant unlock 5
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				Orbit.ID, DancingSilver.ID, SilverMatrix.ID
				), TheServant.Enums.THE_SERVANT, 4);
		UnlockTracker.addCard(Orbit.ID);
		UnlockTracker.addCard(DancingSilver.ID);
        UnlockTracker.addCard(SilverMatrix.ID);
        
        // Retroactive unlocks
        switch(UnlockTracker.getUnlockLevel(TheServant.Enums.THE_SERVANT)) {
            case 5:
                UnlockTracker.unlockCard(Orbit.ID);
                UnlockTracker.unlockCard(DancingSilver.ID);
                UnlockTracker.unlockCard(SilverMatrix.ID);
            case 4:
                UnlockTracker.lockedRelics.removeIf((r) -> {
                    return r == PaperSwan.ID || r == RomanBracelet.ID || r == OldScarf.ID;
                });
            case 3:
                UnlockTracker.unlockCard(Deadline.ID);
                UnlockTracker.unlockCard(TimeTheft.ID);
                UnlockTracker.unlockCard(TrueSight.ID);
            case 2:
                UnlockTracker.lockedRelics.removeIf((r) -> {
                    return r == Broom.ID || r == KneeBrace.ID || r == Pan.ID;
                });
            case 1:
                UnlockTracker.unlockCard(Manipulate.ID);
                UnlockTracker.unlockCard(Moondial.ID);
                UnlockTracker.unlockCard(Embodiment.ID);
            default:
        }
	}

	@Override
	public void receiveEditKeywords() {
        logger.info("Setting up custom keywords");
        String lang = getLanguage();
        Gson gson = new Gson();
        String json = Gdx.files.internal(makePath("localization/"+lang+"/blackrusemod-Keyword-Strings.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
        logger.info("Done setting up custom keywords");
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}