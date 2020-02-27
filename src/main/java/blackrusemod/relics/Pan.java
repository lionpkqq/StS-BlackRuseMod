package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.AmplifyDamagePower;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class Pan extends CustomRelic {
	public static final String ID = BlackRuseMod.makeID(Pan.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("pan.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("pan.png"));
	public static final int BLIGHT = 2;
	
	public Pan() {
		super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.SOLID);
	}

	@Override
	public void atBattleStart() {
		flash();
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			addToBot(new RelicAboveCreatureAction(mo, this));
			addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new AmplifyDamagePower(mo, BLIGHT), BLIGHT));
		}
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
		return new Pan();
	}	
}