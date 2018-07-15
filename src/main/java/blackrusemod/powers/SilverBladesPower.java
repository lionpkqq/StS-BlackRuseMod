package blackrusemod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.cards.FanOfKnives;
import blackrusemod.cards.KidneyShot;
import blackrusemod.cards.KillingDoll;
import blackrusemod.cards.Moonlight;
import blackrusemod.cards.Snipe;
import blackrusemod.cards.Starlight;
import blackrusemod.cards.Sunlight;

public class SilverBladesPower extends AbstractPower {
	public static final String POWER_ID = "SilverBladesPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public SilverBladesPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.img = BlackRuseMod.getSilverBladesPowerTexture();
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if (isKnives(c)) {
				c.baseDamage += stackAmount;
			}
		}
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			if (isKnives(c)) {
				c.baseDamage += stackAmount;
			}
		}
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
			if (isKnives(c)) {
				c.baseDamage += stackAmount;
			}
		}
	}
	
	public void onInitialApplication() {
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if (isKnives(c)) {
				c.baseDamage += this.amount;
			}
		}
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			if (isKnives(c)) {
				c.baseDamage += this.amount;
			}
		}
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
			if (isKnives(c)) {
				c.baseDamage += this.amount;
			}
		}
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	public boolean isKnives(AbstractCard c)
	{
		if ((c instanceof Snipe)) return true;
		if ((c instanceof KidneyShot)) return true;
		if ((c instanceof Starlight)) return true;
		if ((c instanceof Moonlight)) return true;
		if ((c instanceof Sunlight)) return true;
		if ((c instanceof FanOfKnives)) return true;
		if ((c instanceof KillingDoll)) return true;
		return false;
	}
}