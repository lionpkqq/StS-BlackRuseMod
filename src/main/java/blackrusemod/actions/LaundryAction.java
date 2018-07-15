package blackrusemod.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;

public class LaundryAction extends AbstractGameAction
{
	private AbstractPlayer p;
	private ArrayList<AbstractCard> exh = new ArrayList<AbstractCard>();
	
	public LaundryAction(AbstractPlayer p)
	{
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.p = p;
	}

	public void update()
	{
		if (this.duration == Settings.ACTION_DUR_XFAST) {
			this.exh = new ArrayList<AbstractCard>();
			for (AbstractCard c : this.p.drawPile.group) 
				if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) 
					exh.add(c);
			for (AbstractCard c : exh) this.p.drawPile.moveToExhaustPile(c);
			
			this.exh = new ArrayList<AbstractCard>();
			for (AbstractCard c : this.p.discardPile.group) 
				if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) 
					exh.add(c);
			for (AbstractCard c : exh) this.p.discardPile.moveToExhaustPile(c);
			
			this.exh = new ArrayList<AbstractCard>();
			for (AbstractCard c : this.p.hand.group) 
				if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) 
					exh.add(c);
			for (AbstractCard c : exh) this.p.hand.moveToExhaustPile(c);
		}
		tickDuration();
	}
}