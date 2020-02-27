package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;

import blackrusemod.relics.KneeBrace;

public abstract class AbstractShiftCard extends AbstractServantCard {
	private boolean inDiscardMenu = false;

	public AbstractShiftCard(String id, String texture, int cost, CardType type, CardRarity rarity, CardTarget target) {
		super(id, texture, cost, type, rarity, target);
	}

	// There's a patch in place that allows this to trigger during Scry
	// But only if it's a Shift card, of course
	// (They'll also trigger during Deny and Replace)
	@Override
	public void triggerOnManualDiscard() {
		triggerShift();
		if(AbstractDungeon.player.hasRelic(KneeBrace.ID)) {
			AbstractDungeon.player.getRelic(KneeBrace.ID).flash();
			addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, KneeBrace.BLOCK_AMT));
		}
	}

	// Override this to have something happen when this card Shifts
	public void triggerShift() {}
	
	// Glow gold during discard actions to indicate Shift
	// Changes to blue if selected in a grid screen to show being selected
	@Override
	public void update() {
		super.update();
		AbstractGameAction currAction = AbstractDungeon.actionManager.currentAction;
		if(currAction != null && (currAction.actionType == ActionType.DISCARD || currAction instanceof ScryAction)
				&& ((AbstractDungeon.screen == CurrentScreen.HAND_SELECT && (AbstractDungeon.player.hand.contains(this) || AbstractDungeon.handCardSelectScreen.selectedCards.contains(this)))
				|| AbstractDungeon.screen == CurrentScreen.GRID && AbstractDungeon.gridSelectScreen.targetGroup.contains(this) && !AbstractDungeon.gridSelectScreen.selectedCards.contains(this))) {
			beginGlowing();
			this.glowColor = GOLD_BORDER_GLOW_COLOR;
			this.inDiscardMenu = true;
		} else if(this.inDiscardMenu) {
			this.glowColor = BLUE_BORDER_GLOW_COLOR;
			this.inDiscardMenu = false;
		}
	}
}