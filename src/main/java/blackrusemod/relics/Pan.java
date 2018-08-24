package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.AmplifyDamagePower;

public class Pan extends CustomRelic {
	private static final String ID = "Pan";
	private static final int BLIGHT = 2;
	
	public Pan() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.PAN_RELIC), ImageMaster.loadImage(BlackRuseMod.PAN_RELIC_OUTLINE), RelicTier.SHOP, LandingSound.SOLID);
	}

	@Override
	public void atBattleStart() {
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new AmplifyDamagePower(mo, BLIGHT), BLIGHT));
		}
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new Pan();
	}	
}