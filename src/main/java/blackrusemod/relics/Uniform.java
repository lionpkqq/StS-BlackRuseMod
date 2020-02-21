package blackrusemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.KnivesPower;

public class Uniform extends CustomRelic {
	public static final String ID = "BlackRuseMod:Uniform";
	public static final int KNIVES = 6;
	
	public Uniform() {
		super(ID, ImageMaster.loadImage(BlackRuseMod.UNIFORM_RELIC), ImageMaster.loadImage(BlackRuseMod.UNIFORM_RELIC_OUTLINE), RelicTier.STARTER, LandingSound.MAGICAL);
	}
	
	@Override
	public void atBattleStart() {
		flash();
		addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KnivesPower(AbstractDungeon.player, KNIVES)));
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