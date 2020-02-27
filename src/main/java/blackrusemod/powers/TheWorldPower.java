package blackrusemod.powers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.MummifiedHand;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.MummifiedAction;
import blackrusemod.cards.TheWorld;

public class TheWorldPower extends AbstractPower {
	public static final String POWER_ID = BlackRuseMod.makeID(TheWorldPower.class.getSimpleName());
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	public ArrayList<AbstractCard> zero_cost = new ArrayList<AbstractCard>();
	private boolean doNothing;
	
	public TheWorldPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.region48 = powerAltas.findRegion("the_world48");
		this.region128 = powerAltas.findRegion("the_world128");
		this.doNothing = false;
	}
	
	@Override
	public void onInitialApplication() {
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if (c.costForTurn == 0) zero_cost.add(c);
			else c.setCostForTurn(-9);
		}
	}
	
	@Override
	public void onDrawOrDiscard() {
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			c.setCostForTurn(-9);
		}
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		flash();
		addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
		if (AbstractDungeon.player.hasRelic(MummifiedHand.ID))
			addToBot(new MummifiedAction(card, this));
		if (card instanceof TheWorld) 
			addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new TheWorldPower(AbstractDungeon.player, -1), -1));
	}
	
	@Override
	public void onRemove() {
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			 for (AbstractCard c2 : zero_cost)
				 if (c == c2)
					 this.doNothing =  true;
			 if (!this.doNothing) {
				 c.costForTurn = c.cost;
				 c.isCostModifiedForTurn = false;
			 }
			 this.doNothing =  false;
		}
	}
	
	@Override
	public void atEndOfTurn (boolean isPlayer) {
		addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
	}

	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
}

