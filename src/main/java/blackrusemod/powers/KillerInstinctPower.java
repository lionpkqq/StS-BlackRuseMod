package blackrusemod.powers;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnivesAction;

public class KillerInstinctPower extends AbstractPower {
	public static final String POWER_ID = "KillerInstinctPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private int COUNTER_ATTACK;
	private int COUNTER_ATTACK_DAMAGE = 4;
	private DamageInfo p_info;

	public KillerInstinctPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.img = BlackRuseMod.getKillerInstinctPowerTexture();
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}
	
	public int onAttacked(DamageInfo info, int damageAmount) {
		this.p_info = new DamageInfo(AbstractDungeon.player, COUNTER_ATTACK_DAMAGE, DamageInfo.DamageType.NORMAL);
		if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.owner != this.owner))
		{
			if ((this.owner.hasPower("KnivesPower") && (this.owner.getPower("KnivesPower").amount > 0))) {
				COUNTER_ATTACK = Math.min(this.owner.getPower("KnivesPower").amount, this.amount);
				for (int i = 0; i < COUNTER_ATTACK; i++) {
					flash();
					p_info.applyPowers(AbstractDungeon.player, info.owner);
					AbstractDungeon.actionManager.addToTop(new ThrowKnivesAction(AbstractDungeon.player, info.owner, p_info, false, "Weakened"));
				}
			}
			else {
				if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
					AbstractDungeon.actionManager.addToTop(new TalkAction(true, "身上没有飞刀！", 1.0F, 2.0F));
				}
				else {
				AbstractDungeon.actionManager.addToTop(new TalkAction(true, "I don't have any Knives!", 1.0F, 2.0F));
				}
			}
		}
		return damageAmount;
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}