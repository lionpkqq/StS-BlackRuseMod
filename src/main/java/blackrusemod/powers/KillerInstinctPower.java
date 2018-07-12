package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import blackrusemod.BlackRuseMod;

public class KillerInstinctPower extends AbstractPower {
	public static final String POWER_ID = "KillerInstinctPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private int STRENGTH = 0;

	public KillerInstinctPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.img = BlackRuseMod.getKillerInstinctPowerTexture();
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public void atStartOfTurn() {
		this.STRENGTH = 0;
		if (this.owner.hasPower("Weakened")) this.STRENGTH += AbstractDungeon.player.getPower("Weakened").amount*this.amount;
		if (this.owner.hasPower("Vulnerable")) this.STRENGTH += AbstractDungeon.player.getPower("Vulnerable").amount*this.amount;
		if (this.owner.hasPower("Frail")) this.STRENGTH += AbstractDungeon.player.getPower("Frail").amount*this.amount;
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.STRENGTH), this.STRENGTH));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new LoseStrengthPower(this.owner, this.STRENGTH), this.STRENGTH));
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}