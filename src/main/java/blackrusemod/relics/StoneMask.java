package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;

public class StoneMask extends CustomRelic {
	private static final String ID = "StoneMask";
	private static final int MAX_HP_AMT = 1;
	private static final int HEAL_AMT = 2;
	
	public StoneMask() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.STONE_MASK_RELIC), ImageMaster.loadImage(BlackRuseMod.STONE_MASK_RELIC_OUTLINE), RelicTier.BOSS, LandingSound.MAGICAL);
	}
	
	public void onMonsterDeath(AbstractMonster m) {
		if ((!m.halfDead) && (!m.hasPower("Minion"))) {
			AbstractDungeon.player.heal(HEAL_AMT);
			AbstractDungeon.player.increaseMaxHp(MAX_HP_AMT, false);
			flash();
			AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		}
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new StoneMask();
	}
}