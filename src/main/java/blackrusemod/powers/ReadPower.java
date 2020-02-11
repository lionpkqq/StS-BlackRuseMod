package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import blackrusemod.BlackRuseMod;

public class ReadPower extends AbstractVisionPower {
	public static final String POWER_ID = "BlackRuseMod:ReadPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private int w;
	
	public ReadPower(AbstractCreature owner, AbstractMonster target, int amount, int amount2, boolean prediction) {
		super(NAME, POWER_ID, "read", owner, target, amount, prediction);
		this.w = amount2;
		updateDescription();
	}
	
	public void onVision(boolean result) {
		if (result)  {
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.owner, new WeakPower(this.target, this.w, false), this.w));
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new ProtectionPower(this.owner, this.amount), this.amount));
		}
	}

	public void updateDescription()
	{
		if (this.prediction) this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.w + DESCRIPTIONS[2]);
		else this.description = (DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.w + DESCRIPTIONS[5]);
	}
}