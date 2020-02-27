package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;

public class TimeTheftPower extends AbstractVisionPower {
	public static final String POWER_ID = BlackRuseMod.makeID(TimeTheftPower.class.getSimpleName());
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public TimeTheftPower(AbstractMonster target, int amount) {
		super(NAME, POWER_ID, "time_theft", target, amount);
		this.amount2 = amount;
		this.isTurnBased = true;
	}
	
	@Override
	public void onVision(boolean result) {
		if (result) {
			addToTop(new DrawCardAction(AbstractDungeon.player, this.amount));
			addToTop(new GainEnergyAction(this.amount));
		}
	}

	@Override
	public void updateDescription() {
		if (this.prediction) this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
		else this.description = (DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.amount + DESCRIPTIONS[5]);
	}
}