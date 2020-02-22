package blackrusemod.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.BlackRuseMod;
import blackrusemod.cards.TemporalDefense;
import blackrusemod.cards.TemporalEssence;
import blackrusemod.cards.TemporalMisd;
import blackrusemod.cards.TemporalSlicing;

public class SolidifyAction extends AbstractGameAction {
	private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();

	public SolidifyAction(AbstractCreature p) {
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.duration = Settings.ACTION_DUR_FAST;
		this.source = p;
		this.list.add(new TemporalSlicing());
		this.list.add(new TemporalMisd());
		this.list.add(new TemporalDefense());
		this.list.add(new TemporalEssence());
	}

	@Override
	public void update()
	{
		if (this.duration == Settings.ACTION_DUR_FAST) {
			if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT)
				BlackRuseMod.vs.open(this.list, null, "选择一张幻时卡");
			else BlackRuseMod.vs.open(this.list, null, "Choose a Temporal card");
			tickDuration();
			return;
		}

		if (BlackRuseMod.vs.prediction != null) {
			AbstractCard card = BlackRuseMod.vs.prediction.makeStatEquivalentCopy();
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(card, 1, false, false));
			this.isDone = true;
		}
		tickDuration();
	}
}