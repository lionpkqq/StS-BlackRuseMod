package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import blackrusemod.BlackRuseMod;

public class ReadPower extends AbstractVisionPower {
	public static final String POWER_ID = BlackRuseMod.makeID(ReadPower.class.getSimpleName());
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public ReadPower(AbstractMonster target, int amount, int amount2) {
		super(NAME, POWER_ID, "read", target, amount);
		this.amount2 = amount2;
		this.isTurnBased = true;
		updateDescription();
	}
	
	@Override
	public void onVision(boolean result) {
		if (result)  {
			addToTop(new ApplyPowerAction(this.owner, AbstractDungeon.player, new WeakPower(this.owner, this.amount2, false), this.amount2));
			addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ProtectionPower(AbstractDungeon.player, this.amount), this.amount));
		}
	}

	@Override
	public void updateDescription() {
		if (this.prediction) this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2]);
		else this.description = (DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.amount2 + DESCRIPTIONS[5]);
	}
}