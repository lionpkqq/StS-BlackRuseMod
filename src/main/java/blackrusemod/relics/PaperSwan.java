package blackrusemod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.AmplifyDamagePower;

public class PaperSwan extends CustomRelic implements OnApplyPowerRelic {
	public static final String ID = "BlackRuseMod:PaperSwan";
	
	public PaperSwan() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.PAPER_SWAN_RELIC), ImageMaster.loadImage(BlackRuseMod.PAPER_SWAN_RELIC_OUTLINE), RelicTier.UNCOMMON, LandingSound.FLAT);
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new PaperSwan();
	}

	@Override
	public boolean onApplyPower(AbstractPower p, AbstractCreature target, AbstractCreature source) {
		return true;
	}
	
	@Override
	public int onApplyPowerStacks(AbstractPower p, AbstractCreature target, AbstractCreature source, int stackAmount) {
		if(p instanceof AmplifyDamagePower) {
			if(AbstractDungeon.miscRng.randomBoolean()) {
				flash();
				addToTop(new RelicAboveCreatureAction(target, this));
				return stackAmount + 1;
			}
		}
		return stackAmount;
	}
}