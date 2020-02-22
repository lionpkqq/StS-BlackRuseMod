package blackrusemod.actions;

import java.util.ArrayList;
import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.MummifiedHand;

import blackrusemod.powers.TheWorldPower;

import org.apache.logging.log4j.Logger;

public class MummifiedAction extends AbstractGameAction {
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(MummifiedHand.class.getName());
	private AbstractCard card;
	private TheWorldPower source;
	
	public MummifiedAction(AbstractCard card, TheWorldPower source) {
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.card = card;
		this.source = source;
	}

	@Override
	public void update() {
		if (this.duration == Settings.ACTION_DUR_XFAST) {
			if (this.card.type == AbstractCard.CardType.POWER) {
				ArrayList<AbstractCard> groupCopy = new ArrayList<AbstractCard>();
				for (AbstractCard c : AbstractDungeon.player.hand.group) {
					if ((c.cost > 0) && (c.costForTurn > 0)) {
						groupCopy.add(c);
					} else {
						logger.info("COST IS 0: " + c.name);
					}
				}
				CardQueueItem i;
				for (Iterator<CardQueueItem> it = AbstractDungeon.actionManager.cardQueue.iterator(); it.hasNext();) {
					i = (CardQueueItem)it.next();
					if (i.card != null) {
						logger.info("INVALID: " + i.card.name);
						groupCopy.remove(i.card);
					}
				}
				AbstractCard c = null;
				if (!groupCopy.isEmpty()) {
					logger.info("VALID CARDS: ");
				for (AbstractCard cc : groupCopy) {
					logger.info(cc.name);
				}
				c = (AbstractCard)groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
				} else {
					logger.info("NO VALID CARDS");
				}
				if (c != null) {
					logger.info("Mummified hand: " + c.name);
					c.setCostForTurn(0);
					source.zero_cost.add(c);
				} else {
					logger.info("ERROR: MUMMIFIED HAND NOT WORKING");
				}
			}
		}
		tickDuration();
	}
}