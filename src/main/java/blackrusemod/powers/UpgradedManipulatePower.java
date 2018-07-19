package blackrusemod.powers;

import java.util.Random;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.cards.TemporalArms;
import blackrusemod.cards.TemporalEssence;
import blackrusemod.cards.TemporalMisd;
import blackrusemod.cards.TemporalSlicing;

public class UpgradedManipulatePower extends AbstractPower {
	public static final String POWER_ID = "UpgradedManipulatePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public UpgradedManipulatePower(AbstractCreature owner) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		updateDescription();
		this.img = BlackRuseMod.getUpgradedEnbodimentPowerTexture();
	}

	public void onInitialApplication() {
		//flash();
		AbstractCard c;
		Random random = new Random();
		for (int i = 0; i < 2; i++) {
			int randomNum = random.nextInt(10) + 1;
			if (randomNum == 1) c = new TemporalSlicing().makeCopy();
			else if (randomNum == 2) c = new TemporalMisd().makeCopy();
			else if (randomNum == 3) c = new TemporalArms().makeCopy();
			else if (randomNum == 4) c = new TemporalSlicing().makeCopy();
			else if (randomNum == 5) c = new TemporalMisd().makeCopy();
			else if (randomNum == 6) c = new TemporalArms().makeCopy();
			else if (randomNum == 7) c = new TemporalSlicing().makeCopy();
			else if (randomNum == 8) c = new TemporalMisd().makeCopy();
			else if (randomNum == 9) c = new TemporalArms().makeCopy();
			else c = new TemporalEssence().makeCopy();
			c.upgrade();
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, false));
		}
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "UpgradedManipulatePower"));
	}

	public void updateDescription()
	{
		this.description = DESCRIPTIONS[0];
	}
}