package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.cards.TemporalEssence;

public class RomanBracelet extends CustomRelic {
	private static final String ID = "RomanBracelet";
	
	public RomanBracelet() {
		super(ID, BlackRuseMod.getRomanBraceletTexture(), RelicTier.RARE, LandingSound.MAGICAL);
	}

	@Override
	public void atBattleStart() {
		AbstractCard c = new TemporalEssence().makeCopy();
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 2, true, false));
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new RomanBracelet();
	}	
}