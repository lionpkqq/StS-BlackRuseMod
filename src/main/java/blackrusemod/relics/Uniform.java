package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.KnivesPower;

public class Uniform extends CustomRelic {
	private static final String ID = "Uniform";
	private static final int KNIVES = 4;
	
	public Uniform() {
		super(ID, BlackRuseMod.getUniformTexture(), RelicTier.STARTER, LandingSound.MAGICAL);
	}

	@Override
	public void atBattleStart() {
		AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new KnivesPower(AbstractDungeon.player, KNIVES)));
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new Uniform();
	}	
}