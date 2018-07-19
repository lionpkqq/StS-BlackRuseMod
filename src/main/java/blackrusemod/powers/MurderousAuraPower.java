package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class MurderousAuraPower extends AbstractPower {
	public static final String POWER_ID = "MurderousAuraPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public MurderousAuraPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.img = BlackRuseMod.getMurderousAuraPowerTexture();
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	public void atStartOfTurn() {
		//flash();
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, this.owner, new AmplifyDamagePower(mo, this.amount), this.amount));
			if (AbstractDungeon.player.hasRelic("PaperSwan")) 
				if (AbstractDungeon.cardRandomRng.randomBoolean())
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new AmplifyDamagePower(mo, 1), 1));
		}
	}
}