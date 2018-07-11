package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class DuplicationPower extends AbstractPower {
	public static final String POWER_ID = "DuplicationPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public DuplicationPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.img = BlackRuseMod.getDuplicationPowerTexture();
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if ((!card.purgeOnUse) && (this.amount > 0)) {
			// EchoPower
			
			//flash();
			AbstractMonster m = null;

			if (action.target != null) {
				m = (AbstractMonster)action.target;
			}

			AbstractCard tmp = card.makeStatEquivalentCopy();
			AbstractDungeon.player.limbo.addToBottom(tmp);
			tmp.current_x = card.current_x;
			tmp.current_y = card.current_y;
			tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
			tmp.target_y = (Settings.HEIGHT / 2.0F);
			tmp.freeToPlayOnce = true;
			
			if (m != null) {
				tmp.calculateCardDamage(m);
			}

			tmp.purgeOnUse = true;
			AbstractDungeon.actionManager.cardQueue.add(new com.megacrit.cardcrawl.cards.CardQueueItem(tmp, m, card.energyOnUse));
				
			if (tmp.cardID.equals("Rampage")) {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ModifyDamageAction(card, tmp.magicNumber));
			} else if (tmp.cardID.equals("Genetic Algorithm")) {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction(card.cardID, card.misc + card.magicNumber, card.magicNumber));
			}
			
			this.amount--;
			if (this.amount == 0) AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DuplicationPower"));
		}
	}
	
	public void atEndOfTurn (boolean isPlayer) {
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DuplicationPower"));
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}