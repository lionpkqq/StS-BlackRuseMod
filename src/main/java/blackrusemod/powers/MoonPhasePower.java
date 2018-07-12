package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class MoonPhasePower extends AbstractPower {
	public static final String POWER_ID = "MoonPhasePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private int BLOCK = 0;
	
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
	
	public void atEndOfTurn(boolean isPlayer) {
		this.BLOCK = 0;
		if (this.owner.hasPower("Weakened")) this.BLOCK += AbstractDungeon.player.getPower("Weakened").amount*this.amount;
		if (this.owner.hasPower("Vulnerable")) this.BLOCK += AbstractDungeon.player.getPower("Vulnerable").amount*this.amount;
		if (this.owner.hasPower("Frail")) this.BLOCK += AbstractDungeon.player.getPower("Frail").amount*this.amount;
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.BLOCK));
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}