package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class Broom extends CustomRelic {
	public static final String ID = BlackRuseMod.makeID(Broom.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("broom.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("broom.png"));
	public static final int COUNT = 10;
	
	public Broom() {
		super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
		this.counter = 0;
	}
	
	@Override
	public void onManualDiscard() {
		this.counter += 1;
		if (this.counter >= COUNT) {
			flash();
			this.counter = 0;
			addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
			addToBot(new GainEnergyAction(1));
		}
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new Broom();
	}
}