package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class HastePower extends AbstractPower {
	public static final String POWER_ID = "HastePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public HastePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.img = BlackRuseMod.getHastePowerTexture();
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}

	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card.type == AbstractCard.CardType.ATTACK) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DrawManipulationPower(this.owner, this.amount), this.amount));
			//flash();
		}
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}