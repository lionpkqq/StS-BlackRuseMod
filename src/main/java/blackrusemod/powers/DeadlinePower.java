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
import com.megacrit.cardcrawl.powers.VulnerablePower;

import blackrusemod.BlackRuseMod;

public class DeadlinePower extends AbstractVisionPower {
	public static final String POWER_ID = "BlackRuseMod:DeadlinePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public DeadlinePower(AbstractMonster target, int amount, boolean prediction) {
		super(NAME, POWER_ID, "no_escape", target, amount, prediction);
	}
	
	public void onVision(boolean result) {
		if(result) {
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, AbstractDungeon.player, new VulnerablePower(this.owner, this.amount, false), this.amount));
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, AbstractDungeon.player, new AmplifyDamagePower(this.owner, this.amount), this.amount));
		}
	}

	public void updateDescription()
	{
		if (this.prediction) this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		else this.description = (DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]);
	}
}