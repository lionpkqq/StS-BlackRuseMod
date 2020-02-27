package blackrusemod.characters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import blackrusemod.BlackRuseMod;
import blackrusemod.cards.*;
import blackrusemod.characters.TheServant;
import blackrusemod.relics.Uniform;

public class TheServant extends CustomPlayer {

	public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_SERVANT;
        @SpireEnum(name = "SERVANT_SILVER_COLOR")
        public static AbstractCard.CardColor COLOR_SILVER;
        @SpireEnum(name = "SERVANT_SILVER_COLOR")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

	public static final int ENERGY_PER_TURN = 3;
	
	public static final String[] orbTextures = {
			"blackrusemodResources/images/char/servant/orb/layer1.png",
			"blackrusemodResources/images/char/servant/orb/layer2.png",
			"blackrusemodResources/images/char/servant/orb/layer3.png",
			"blackrusemodResources/images/char/servant/orb/layer4.png",
			"blackrusemodResources/images/char/servant/orb/layer5.png",
			"blackrusemodResources/images/char/servant/orb/layer6.png",
			"blackrusemodResources/images/char/servant/orb/layer1d.png",
			"blackrusemodResources/images/char/servant/orb/layer2d.png",
			"blackrusemodResources/images/char/servant/orb/layer3d.png",
			"blackrusemodResources/images/char/servant/orb/layer4d.png",
			"blackrusemodResources/images/char/servant/orb/layer5d.png",
	};
	
	public static final float[] layerSpeeds = {
			80.0F, 40.0F, -40.0F, 20.0F, 0.0F,
			16.0F, 8.0F, -8.0F, 5.0F, 0.0F,
	};
	
	public TheServant(String name, PlayerClass setClass) {
		super(name, setClass, orbTextures, BlackRuseMod.makePath("images/char/servant/orb/vfx.png"), layerSpeeds, null, null);
		
		initializeClass(BlackRuseMod.makePath(BlackRuseMod.SERVANT_MAIN), 
				BlackRuseMod.makePath(BlackRuseMod.SERVANT_SHOULDER_2),
				BlackRuseMod.makePath(BlackRuseMod.SERVANT_SHOULDER_1),
				BlackRuseMod.makePath(BlackRuseMod.SERVANT_CORPSE), 
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
	}

	@Override
	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add(Strike_Silver.ID);
		retVal.add(Strike_Silver.ID);
		retVal.add(Strike_Silver.ID);
		retVal.add(Strike_Silver.ID);
		retVal.add(Defend_Silver.ID);
		retVal.add(Defend_Silver.ID);
		retVal.add(Defend_Silver.ID);
		retVal.add(Defend_Silver.ID);
		retVal.add(KidneyShot.ID);
		retVal.add(Exchange.ID);
		return retVal;
	}
	
	@Override
	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add(Uniform.ID);
		UnlockTracker.markRelicAsSeen(Uniform.ID);
		return retVal;
	}
	
	@Override
	public CharSelectInfo getLoadout() {
		if (Settings.language == GameLanguage.ZHS) {
			return new CharSelectInfo("凛光侍从", "恶魔们的侍从。擅长杀戮与家务。 NL 随身携带着一千零一把刀刃。",
				65, 65, 0, 99, 5, this, getStartingRelics(), getStartingDeck(), false);
		} else if (Settings.language == GameLanguage.ZHT) {
			return new CharSelectInfo("凜光侍從", "惡魔們的侍從。擅長殺戮與家務。 NL 隨身攜帶著一千零一把刀刃。",
				65, 65, 0, 99, 5, this, getStartingRelics(), getStartingDeck(), false);
		} else {
			return new CharSelectInfo("The Servant", "A servant of demons. Perfected at killing and housekeeping. NL Holds a thousand and one blades.",
				65, 65, 0, 99, 5, this, getStartingRelics(), getStartingDeck(), false);
		}
	}

	@Override
	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.sound.playV("ATTACK_DAGGER_6", 1.75f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
	}

	@Override
	public int getAscensionMaxHPLoss() {
		return 5;
	}

	@Override
	public CardColor getCardColor() {
		return TheServant.Enums.COLOR_SILVER;
	}

	@Override
	public Color getCardRenderColor() {
		return CardHelper.getColor(131.0f, 156.0f, 165.0f);
	}

	@Override
	public Color getCardTrailColor() {
		return CardHelper.getColor(131.0f, 156.0f, 165.0f);
	}

	@Override
	public String getCustomModeCharacterButtonSoundKey() {
		return "ATTACK_DAGGER_6";
	}

	@Override
	public BitmapFont getEnergyNumFont() {
		return FontHelper.energyNumFontBlue;
	}

	@Override
	public String getLocalizedCharacterName() {
		String char_name;
		if (Settings.language == GameLanguage.ZHS) {
			char_name = "凛光侍从";
		} else if (Settings.language == GameLanguage.ZHT) {
			char_name = "凜光侍從";
		} else {
			char_name = "The Servant";
		}
		return char_name;
	}

	@Override
	public Color getSlashAttackColor() {
		return CardHelper.getColor(131.0f, 156.0f, 165.0f);
	}

	@Override
	public AttackEffect[] getSpireHeartSlashEffect() {
		return new AttackEffect[]{
		        AttackEffect.SLASH_DIAGONAL,
		        AttackEffect.SLASH_HORIZONTAL,
		        AttackEffect.SLASH_DIAGONAL,
		        AttackEffect.SLASH_VERTICAL,
		        AttackEffect.SLASH_HORIZONTAL,
		        AttackEffect.SLASH_VERTICAL
		    };
	}

	@Override
	public String getSpireHeartText() {
		return SpireHeart.DESCRIPTIONS[10];
	}

	@Override
	public AbstractCard getStartCardForEvent() {
		return new KidneyShot();
	}

	@Override
	public String getTitle(PlayerClass arg0) {
		String title;
		if (Settings.language == GameLanguage.ZHS) {
			title = "凛光侍从";
		} else if (Settings.language == GameLanguage.ZHT) {
			title = "凜光侍從";
		} else {
			title = "The Servant";
		}
		return title;
	}

	@Override
	public String getVampireText() {
		return Vampires.DESCRIPTIONS[1];
	}

	@Override
	public AbstractPlayer newInstance() {
		return new TheServant(this.name, Enums.THE_SERVANT);
	}
	
	// Remove temporary cards from the card pool
	@Override
	public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
		tmpPool = super.getCardPool(tmpPool);
		tmpPool.removeIf((c) -> {
			return c.hasTag(AbstractServantCard.Enums.TEMP);
		});
		return tmpPool;
	}
}
