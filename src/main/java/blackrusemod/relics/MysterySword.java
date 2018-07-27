package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.MysterySwordPower;

public class MysterySword extends CustomRelic {
	private static final String ID = "MysterySword";
	
	public MysterySword() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.MYSTERY_SWORD_RELIC), ImageMaster.loadImage(BlackRuseMod.MYSTERY_SWORD_RELIC_OUTLINE), RelicTier.BOSS, LandingSound.CLINK);
	}
	
	public void atTurnStart()
	{
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new MysterySwordPower(AbstractDungeon.player, -1), -1));
	}
	
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new MysterySword();
	}
}