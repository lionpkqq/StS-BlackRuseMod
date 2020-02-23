package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;

import basemod.abstracts.CustomCard;
import blackrusemod.relics.KneeBrace;

public abstract class AbstractShiftCard extends CustomCard {
	private boolean inDiscardMenu = false;

	public AbstractShiftCard(String id, String name, String texture, int cost, String desc, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
		super(id, name, texture, cost, desc, type, color, rarity, target);
	}
	
	@Override
	public void triggerOnManualDiscard() {
		triggerShift();
		if (AbstractDungeon.player.hasRelic(KneeBrace.ID)) {
			AbstractDungeon.player.getRelic(KneeBrace.ID).flash();
			addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, KneeBrace.BLOCK_AMT));
		}
	}
	
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