package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.ElegancePower;
import blackrusemod.powers.KnivesPower;
import blackrusemod.powers.SatellitePower;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class SplendidAttire extends CustomRelic {
	public static final String ID = BlackRuseMod.makeID(SplendidAttire.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("splendid_attire.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("splendid_attire.png"));
	public static final int KNIVES = 12;
	
	public SplendidAttire() {
		super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
	}

	@Override
	public void obtain() {
		if (AbstractDungeon.player.hasRelic(Uniform.ID)) this.instantObtain(AbstractDungeon.player, 0, false);
		else super.obtain();
	}
	
	@Override
	public void atBattleStart() {
		flash();
		addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KnivesPower(AbstractDungeon.player, KNIVES)));
		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SatellitePower(AbstractDungeon.player, 3), 3));
		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ElegancePower(AbstractDungeon.player, 1), 1));
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new SplendidAttire();
	}	
}