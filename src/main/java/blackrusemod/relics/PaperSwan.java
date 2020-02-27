package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.AmplifyDamagePower;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class PaperSwan extends CustomRelic implements OnApplyPowerRelic {
	public static final String ID = BlackRuseMod.makeID(PaperSwan.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("paper_swan.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("paper_swan.png"));
	
	public PaperSwan() {
		super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
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