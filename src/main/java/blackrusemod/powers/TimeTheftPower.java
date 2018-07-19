package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class TimeTheftPower extends AbstractPower {
	public static final String POWER_ID = "TimeTheftPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private AbstractCreature source;
	private AbstractMonster target;
	private boolean prediction;
	
	public TimeTheftPower(AbstractCreature owner, AbstractCreature source, int amount, boolean prediction) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.amount = amount;
		this.owner = owner;
		this.source = source;
		this.target = (AbstractMonster)this.source;
		this.amount = amount;
		this.prediction = prediction;
		this.type = AbstractPower.PowerType.BUFF;
		updateDescription();
		this.img = BlackRuseMod.getTimeTheftPowerTexture();
	}
	
	public void atStartOfTurnPostDraw() {
		//flash();
		if (!this.prediction && !(this.target.intent == AbstractMonster.Intent.ATTACK) && !(this.target.intent == AbstractMonster.Intent.ATTACK_BUFF) && !(this.target.intent == AbstractMonster.Intent.ATTACK_DEBUFF) && !(this.target.intent == AbstractMonster.Intent.ATTACK_DEFEND))
		{	
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, this.amount));
			if (AbstractDungeon.player.hasRelic("OldScarf")) AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
			if (this.owner.hasPower("TrueSightPower")) 
				for (int i = 0; i < this.owner.getPower("TrueSightPower").amount; i++) {
					AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
					AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, this.amount));
					if (AbstractDungeon.player.hasRelic("OldScarf")) AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
				}
		}
		else if (this.prediction && ((this.target.intent == AbstractMonster.Intent.ATTACK) || (this.target.intent == AbstractMonster.Intent.ATTACK_BUFF) || (this.target.intent == AbstractMonster.Intent.ATTACK_DEBUFF) || (this.target.intent == AbstractMonster.Intent.ATTACK_DEFEND)))
		{
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, this.amount));
			if (AbstractDungeon.player.hasRelic("OldScarf")) AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
			if (this.owner.hasPower("TrueSightPower")) 
				for (int i = 0; i < this.owner.getPower("TrueSightPower").amount; i++) {
					AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
					AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, this.amount));
					if (AbstractDungeon.player.hasRelic("OldScarf")) AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
				}
		}
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "TimeTheftPower"));
	}

	public void updateDescription()
	{
		if (this.prediction) this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
		else this.description = (DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.amount + DESCRIPTIONS[5]);
	}
}