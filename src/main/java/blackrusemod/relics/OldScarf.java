package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.OldScarfAction;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class OldScarf extends CustomRelic {
	public static final String ID = BlackRuseMod.makeID(OldScarf.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("old_scarf.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("old_scarf.png"));
	
	public OldScarf() {
		super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
	}
	
	@Override
	public void atBattleStart() {
		flash();
		addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		addToBot(new OldScarfAction());
		this.grayscale = true;
	}
	
	@Override
	public void onVictory() {
		this.grayscale = false;
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new OldScarf();
	}
}