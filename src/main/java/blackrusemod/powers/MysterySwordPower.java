package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import blackrusemod.BlackRuseMod;

public class MysterySwordPower extends AbstractPower {
	public static final String POWER_ID = "MysterySwordPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public MysterySwordPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.img = BlackRuseMod.getMysterySwordPowerTexture();
	}
	
	public void onUseCard(AbstractCard card, UseCardAction action) {
		//flash();
		if (card.type == AbstractCard.CardType.ATTACK) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new WeakPower(this.owner, 2, false), 2));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new FrailPower(this.owner, 2, false), 2));
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "MysterySwordPower"));
		}
	}
	
	public void atEndOfTurn (boolean isPlayer) {
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "MysterySwordPower"));
	}

	public void updateDescription()
	{
		this.description = DESCRIPTIONS[0];
	}
	
	public float atDamageGive(float damage, DamageInfo.DamageType type) {
		if (type == DamageInfo.DamageType.NORMAL) {
			return damage * 2.0F;
		}
		return damage;
	}
}

