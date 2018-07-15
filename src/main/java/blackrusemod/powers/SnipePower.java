package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnivesAction;

public class SnipePower extends AbstractPower {
	public static final String POWER_ID = "SnipePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private AbstractCreature source;
	private AbstractMonster target;
	private boolean prediction;
	
	public SnipePower(AbstractCreature owner, AbstractCreature source, int amount, boolean prediction) {
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
		this.img = BlackRuseMod.getSnipePowerTexture();
	}
	
	public void atStartOfTurnPostDraw() {
		//flash();
		if (this.owner.hasPower("KnivesPower")) {
			if (!this.prediction && !(this.target.intent == AbstractMonster.Intent.ATTACK) && !(this.target.intent == AbstractMonster.Intent.ATTACK_BUFF) && !(this.target.intent == AbstractMonster.Intent.ATTACK_DEBUFF) && !(this.target.intent == AbstractMonster.Intent.ATTACK_DEFEND))
			{	
				AbstractDungeon.actionManager.addToBottom(new ThrowKnivesAction(AbstractDungeon.player, this.target, new DamageInfo(this.owner, this.amount, DamageType.NORMAL), false, null));
				if (this.owner.hasPower("TrueSightPower")) 
					for (int i = 0; i < Math.min(this.owner.getPower("TrueSightPower").amount, this.owner.getPower("KnivesPower").amount); i++)
						AbstractDungeon.actionManager.addToBottom(new ThrowKnivesAction(AbstractDungeon.player, this.target, new DamageInfo(this.owner, this.amount, DamageType.NORMAL), false, null));
			}
			else if (this.prediction && ((this.target.intent == AbstractMonster.Intent.ATTACK) || (this.target.intent == AbstractMonster.Intent.ATTACK_BUFF) || (this.target.intent == AbstractMonster.Intent.ATTACK_DEBUFF) || (this.target.intent == AbstractMonster.Intent.ATTACK_DEFEND)))
			{
				AbstractDungeon.actionManager.addToBottom(new ThrowKnivesAction(AbstractDungeon.player, this.target, new DamageInfo(this.owner, this.amount, DamageType.NORMAL), false, null));
				if (this.owner.hasPower("TrueSightPower")) 
					for (int i = 0; i < Math.min(this.owner.getPower("TrueSightPower").amount, this.owner.getPower("KnivesPower").amount); i++)
						AbstractDungeon.actionManager.addToBottom(new ThrowKnivesAction(AbstractDungeon.player, this.target, new DamageInfo(this.owner, this.amount, DamageType.NORMAL), false, null));
			}
		}
		else {
			if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
				AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "身上没有飞刀！", 1.0F, 2.0F));
			}
			else {
				AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "I don't have any Knives!", 1.0F, 2.0F));
			}
		}
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "SnipePower"));
	}

	public void updateDescription()
	{
		if (this.prediction) this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		else this.description = (DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]);
	}
}