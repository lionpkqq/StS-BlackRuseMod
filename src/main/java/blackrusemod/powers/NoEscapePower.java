package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import blackrusemod.BlackRuseMod;

public class NoEscapePower extends AbstractPower {
	public static final String POWER_ID = "NoEscapePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private AbstractCreature source;
	private AbstractMonster target;
	private boolean prediction;
	private int vul;
	
	public NoEscapePower(AbstractCreature owner, AbstractCreature source, int amount, int amount2, boolean prediction) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.amount = amount;
		this.owner = owner;
		this.source = source;
		this.target = (AbstractMonster)this.source;
		this.amount = amount;
		this.vul = amount2;
		this.prediction = prediction;
		this.type = AbstractPower.PowerType.BUFF;
		updateDescription();
		this.img = BlackRuseMod.getNoEscapePowerTexture();
	}
	
	public void atStartOfTurnPostDraw() {
		//flash();
		if (!this.prediction && !(this.target.intent == AbstractMonster.Intent.ATTACK) && !(this.target.intent == AbstractMonster.Intent.ATTACK_BUFF) && !(this.target.intent == AbstractMonster.Intent.ATTACK_DEBUFF) && !(this.target.intent == AbstractMonster.Intent.ATTACK_DEFEND))
		{
			AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.owner, this.amount, DamageType.NORMAL),
					AbstractGameAction.AttackEffect.SLASH_VERTICAL));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.owner, new VulnerablePower(this.target, this.vul, false), this.vul));
			if (this.owner.hasPower("TrueSightPower")) 
				for (int i = 0; i < this.owner.getPower("TrueSightPower").amount; i++)
				{
					AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.owner, this.amount, DamageType.NORMAL),
							AbstractGameAction.AttackEffect.SLASH_VERTICAL));
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.owner, new VulnerablePower(this.target, this.vul, false), this.vul));
				}
		}
		else if (this.prediction && ((this.target.intent == AbstractMonster.Intent.ATTACK) || (this.target.intent == AbstractMonster.Intent.ATTACK_BUFF) || (this.target.intent == AbstractMonster.Intent.ATTACK_DEBUFF) || (this.target.intent == AbstractMonster.Intent.ATTACK_DEFEND)))
		{
			AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.owner, this.amount, DamageType.NORMAL),
					AbstractGameAction.AttackEffect.SLASH_VERTICAL));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.owner, new VulnerablePower(this.target, this.vul, false), this.vul));
			if (this.owner.hasPower("TrueSightPower")) 
				for (int i = 0; i < this.owner.getPower("TrueSightPower").amount; i++)
				{
					AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.owner, this.amount, DamageType.NORMAL),
							AbstractGameAction.AttackEffect.SLASH_VERTICAL));
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.owner, new VulnerablePower(this.target, this.vul, false), this.vul));
				}
		}
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "NoEscapePower"));
	}

	public void updateDescription()
	{
		if (this.prediction) this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.vul + DESCRIPTIONS[2]);
		else this.description = (DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.vul + DESCRIPTIONS[5]);
	}
}