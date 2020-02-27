package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class KneeBrace extends CustomRelic {
	public static final String ID = BlackRuseMod.makeID(KneeBrace.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("knee_brace.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("knee_brace.png"));
	public static final int BLOCK_AMT = 3;
	
	public KneeBrace() {
		super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new KneeBrace();
	}
}