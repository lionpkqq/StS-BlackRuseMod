package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class StoneMask extends CustomRelic {
	public static final String ID = "BlackRuseMod:StoneMask";
	public static final int MAX_HP_AMT = 1;
	public static final int HEAL_AMT = 2;
	
	public StoneMask() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.STONE_MASK_RELIC), ImageMaster.loadImage(BlackRuseMod.STONE_MASK_RELIC_OUTLINE), RelicTier.BOSS, LandingSound.MAGICAL);
	}
	
	@Override
	public void onMonsterDeath(AbstractMonster m) {
		if ((!m.halfDead) && (!m.hasPower(MinionPower.POWER_ID))) {
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