package blackrusemod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;

import blackrusemod.cards.AbstractShiftCard;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(clz=ScryAction.class, method="update")
public class ScryShiftPatch
{
	// Add in these actions right after powers
	@SpireInsertPatch(rloc=28, localvars={"c"})
	public static void Insert(AbstractCard c) {
		if(c instanceof AbstractShiftCard) {
			c.triggerOnManualDiscard();
		}
	}
}