package blackrusemod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.actions.ReturningBladeAction;

public class ReturningBladePower extends AbstractVisionPower {
	public static final String POWER_ID = "BlackRuseMod:ReturningBladePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private AbstractCard itself;
	
	public ReturningBladePower(AbstractMonster target, int amount, boolean prediction, AbstractCard c) {
		super(NAME, POWER_ID, "returning_blade", target, amount, prediction);
		this.itself = c;
	}
	
	@Override
	public void onVision(boolean result) {
		if(result) addToTop(new ReturningBladeAction(this.owner, this.amount, this.itself));
	}

	@Override
	public void updateDescription() {
		if (this.prediction) this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		else this.description = (DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]);
	}
}