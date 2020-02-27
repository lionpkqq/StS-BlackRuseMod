package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.KnivesPower;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class Uniform extends CustomRelic {
	public static final String ID = BlackRuseMod.makeID(Uniform.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("uniform.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("uniform.png"));
	private static final int KNIVES = 6;
	
	public Uniform() {
		super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
	}
	
	@Override
	public void atBattleStart() {
		flash();
		addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KnivesPower(AbstractDungeon.player, KNIVES)));
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new Uniform();
	}	
}