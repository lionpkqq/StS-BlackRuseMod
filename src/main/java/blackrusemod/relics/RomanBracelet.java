package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.cards.TemporalEssence;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class RomanBracelet extends CustomRelic {
	public static final String ID = BlackRuseMod.makeID(RomanBracelet.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("roman_bracelet.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("roman_bracelet.png"));
	
	public RomanBracelet() {
		super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
	}

	@Override
	public void atBattleStart() {
		flash();
		AbstractCard c = new TemporalEssence();
		addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		addToBot(new MakeTempCardInDrawPileAction(c, 2, true, false));
		this.grayscale = true;
	}
	
	@Override
	public void onVictory() {
		this.grayscale = false;
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new RomanBracelet();
	}	
}