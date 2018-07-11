package blackrusemod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;

public class RealityMarblePower extends AbstractPower {
	public static final String POWER_ID = "RealityMarblePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

	public RealityMarblePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		updateDescription();
		this.img = BlackRuseMod.getRealityMarblePowerTexture();
	}
	
	public void onInitialApplication() {
		for (AbstractCard c : AbstractDungeon.player.hand.group) c.isEthereal = false;
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) c.isEthereal = false;
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) c.isEthereal = false;
	}
	
	public void atStartOfTurn() {
		for (AbstractCard c : AbstractDungeon.player.hand.group) c.isEthereal = false;
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) c.isEthereal = false;
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) c.isEthereal = false;
	}

	public void updateDescription()
	{
		this.description = DESCRIPTIONS[0];
	}
}