package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import blackrusemod.BlackRuseMod;

public class MoonPhasePower extends AbstractPower {
	public static final String POWER_ID = "MoonPhasePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public MoonPhasePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.img = BlackRuseMod.getMoonPhasePowerTexture();
	}
	
	public void stackPower(int stackAmount)
	{
		//flash();
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public void atStartOfTurn() {
		//flash();
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, -this.amount), -this.amount));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
	}
}