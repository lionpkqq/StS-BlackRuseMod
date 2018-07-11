package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class FlawlessFormPower extends AbstractPower {
	public static final String POWER_ID = "FlawlessFormPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public FlawlessFormPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = -amount;
			updateDescription();
			this.canGoNegative = true;
			this.img = BlackRuseMod.getFlawlessFormPowerTexture();
	}
	
	public void stackPower(int stackAmount) {
		this.fontScale = 8.0F;
		this.amount -= stackAmount;
	}
	
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		this.type = AbstractPower.PowerType.DEBUFF;
	}
	
	public void atEndOfTurn(boolean isPlayer) {
		//flash();
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DrawManipulationPower(this.owner, this.amount), this.amount));
	}
}