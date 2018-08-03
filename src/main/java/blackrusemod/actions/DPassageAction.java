package blackrusemod.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DPassageAction extends AbstractGameAction
{
	private AbstractPlayer p;
	private ArrayList<AbstractCard> exh = new ArrayList<AbstractCard>();
	
	public DPassageAction(AbstractPlayer p)
	{
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.p = p;
	}

	public void update()
	{
		if (this.duration == Settings.ACTION_DUR_XFAST) {
			this.exh = new ArrayList<AbstractCard>();
			for (AbstractCard c : this.p.exhaustPile.group) exh.add(c);
			for (AbstractCard c : exh) {
				this.p.exhaustPile.removeCard(c);
				c.unfadeOut();
				this.p.exhaustPile.moveToDeck(c, true);
			}
			AbstractDungeon.actionManager.addToBottom(new ShuffleAction(this.p.drawPile));
		}
		tickDuration();
	}
}