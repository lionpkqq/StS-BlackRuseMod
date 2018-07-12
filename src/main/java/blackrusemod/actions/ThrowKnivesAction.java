package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;

import blackrusemod.powers.AmplifyDamagePower;
import blackrusemod.powers.KnivesPower;

public class ThrowKnivesAction extends AbstractGameAction {
	private DamageInfo info;
	private boolean isRandom;
	private String debuff;
	private boolean depleted = false;
	public ThrowKnivesAction(AbstractPlayer p, AbstractCreature target, int amount, DamageInfo info, boolean isRandom, String debuff)
	{
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.source = p;
		this.target = target;
		this.amount = amount;
		this.info = info;
		this.isRandom = isRandom;
		this.debuff = debuff;
		this.depleted = false;
	}

	public void update()
	{
		if (this.source.hasPower("KnivesPower")) {
			if (this.source.getPower("KnivesPower").amount <= this.amount) {
				this.amount = this.source.getPower("KnivesPower").amount; 
				this.depleted = true;
			}
			for (int i = 0; i < this.amount; i++) {
				if (this.isRandom) this.target = AbstractDungeon.getMonsters().getRandomMonster(true);
				if ((this.target != null) && (this.target.hb != null)) {
					AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, this.info, true));
				}
				if ((this.target != null) && (this.target.hb != null)) {
					AbstractDungeon.actionManager.addToTop(new VFXAction(new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
				}
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.source, this.source, new KnivesPower(this.source, -1), -1));
				if (this.source.hasPower("SurpressingFirePower")) AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.source, this.source, this.source.getPower("SurpressingFirePower").amount));
				if (this.debuff != null && this.debuff == "Weakened")  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new WeakPower(this.target, 1, false), 1));
				if (this.debuff != null && this.debuff == "Amplify Damage")  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new AmplifyDamagePower(this.target, 1), 1));
			}
			if (this.depleted) {
				if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
					AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "我的飞刀用光了！", 1.0F, 2.0F));
				}
				else {
				AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "I have depleted my Knives!", 1.0F, 2.0F));
				}
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

		this.isDone = true;
	}
}