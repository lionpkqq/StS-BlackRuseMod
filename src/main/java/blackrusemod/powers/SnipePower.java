package blackrusemod.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.actions.ThrowKnivesAction;

public class SnipePower extends AbstractVisionPower {
	public static final String POWER_ID = "BlackRuseMod:SnipePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	public SnipePower(AbstractMonster target, int amount, boolean prediction) {
		super(NAME, POWER_ID, "snipe", target, amount, prediction);
	}
	
	@Override
	public void onVision(boolean result) {
		if (result) addToTop(new ThrowKnivesAction(AbstractDungeon.player, this.owner, new DamageInfo(AbstractDungeon.player, this.amount, DamageType.NORMAL), "Vulnerable"));
	}

	@Override
	public void updateDescription() {
		if (this.prediction) this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		else this.description = (DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]);
	}
}