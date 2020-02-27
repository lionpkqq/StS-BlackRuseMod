package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class StoneMask extends CustomRelic {
	public static final String ID = BlackRuseMod.makeID(StoneMask.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("stone_mask.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("stone_mask.png"));
	private static final int MAX_HP_AMT = 1;
	private static final int HEAL_AMT = 2;
	
	public StoneMask() {
		super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
	}
	
	@Override
	public void onMonsterDeath(AbstractMonster m) {
		if (!m.halfDead && !m.hasPower(MinionPower.POWER_ID)) {
			AbstractDungeon.player.heal(HEAL_AMT);
			AbstractDungeon.player.increaseMaxHp(MAX_HP_AMT, false);
			flash();
			addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		}
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new StoneMask();
	}
}