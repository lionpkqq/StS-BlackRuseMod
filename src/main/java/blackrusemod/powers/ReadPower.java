package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class ReadPower extends AbstractPower {
	public static final String POWER_ID = "ReadPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private AbstractCreature source;
	private AbstractMonster target;
	
	public ReadPower(AbstractCreature owner, AbstractCreature source, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.source = source;
		this.target = (AbstractMonster)this.source;
		this.amount = amount;
		this.type = AbstractPower.PowerType.BUFF;
		updateDescription();
		this.img = BlackRuseMod.getReadPowerTexture();
	}
	
	public void atStartOfTurnPostDraw() {
		//flash();
		if (!(this.target.intent == AbstractMonster.Intent.ATTACK) && !(this.target.intent == AbstractMonster.Intent.ATTACK_BUFF) && !(this.target.intent == AbstractMonster.Intent.ATTACK_DEBUFF) && !(this.target.intent == AbstractMonster.Intent.ATTACK_DEFEND))
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.owner, new AmplifyDamagePower(this.target, 2), 2));
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "ReadPower"));
	}

	public void updateDescription()
	{
		this.description = DESCRIPTIONS[0];
	}
}