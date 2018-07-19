package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.DrawManipulationPower;

public class PocketWatch extends CustomRelic {
	private static final String ID = "PocketWatch";
	
	public PocketWatch() {
		super(ID, BlackRuseMod.getPocketWatchTexture(), RelicTier.RARE, LandingSound.MAGICAL);
	}
	
	public void onExhaust(AbstractCard card)
	{
		flash();
		AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new DrawManipulationPower(AbstractDungeon.player, 1), 1));
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new PocketWatch();
	}
}