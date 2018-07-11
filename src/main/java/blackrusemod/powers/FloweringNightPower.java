package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class FloweringNightPower extends AbstractPower {
	public static final String POWER_ID = "FloweringNightPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public FloweringNightPower(AbstractCreature owner, int amount) {
			this.name = NAME;
			this.ID = POWER_ID;
			this.owner = owner;
			this.amount = amount;
			updateDescription();
			this.img = BlackRuseMod.getFloweringNightPowerTexture();
	}
	
	public void stackPower(int stackAmount) {
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	public void onUseCard(AbstractCard card, UseCardAction action) {
		//flash();
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, this.amount));
	}
	
	public void atEndOfTurn (boolean isPlayer) {
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "FloweringNightPower"));
	}
}