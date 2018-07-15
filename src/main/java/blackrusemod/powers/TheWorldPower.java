package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.cards.TheWorld;

public class TheWorldPower extends AbstractPower {
	public static final String POWER_ID = "TheWorldPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public TheWorldPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.img = BlackRuseMod.getTheWorldPowerTexture();
	}
	
	public void onInitialApplication() {
		for (AbstractCard c : AbstractDungeon.player.hand.group) 
			 c.setCostForTurn(-9);
	}
	
	public void onRemove()
	{
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			 c.costForTurn = c.cost;
			 c.isCostModifiedForTurn = false;
		}
	}
	
	public void onUseCard(AbstractCard card, UseCardAction action) {
		//flash();
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "TheWorldPower"));
		if (card instanceof TheWorld) 
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new TheWorldPower(AbstractDungeon.player, -1), -1));
	}
	
	public void atEndOfTurn (boolean isPlayer) {
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "TheWorldPower"));
	}

	public void updateDescription()
	{
		this.description = DESCRIPTIONS[0];
	}
}

