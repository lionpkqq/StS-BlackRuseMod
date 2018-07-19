package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class ConsumedKnivesPower extends AbstractPower {
	public static final String POWER_ID = "ConsumedKnivesPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public ConsumedKnivesPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 1;
		this.canGoNegative = true;
		updateDescription();
		this.img = BlackRuseMod.getConsumedKnivesPowerTexture();
	}
	
	public void stackPower(int stackAmount)
	{
		//flash();
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public void atEndOfTurn (boolean isPlayer) {
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "ConsumedKnivesPower"));
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + (-this.amount) + DESCRIPTIONS[1]);
	}
}