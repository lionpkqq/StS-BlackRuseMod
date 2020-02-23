package blackrusemod.patches;

import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;

import blackrusemod.cards.AbstractShiftCard;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

@SpirePatch(clz=ScryAction.class, method="update")
public class ScryShiftPatch {
	// Add in these actions right after powers
	@SpireInsertPatch(locator=Locator.class, localvars={"c"})
	public static void Insert(AbstractCard c) {
		if(c instanceof AbstractShiftCard) {
			c.triggerOnManualDiscard();
		}
	}

	private static class Locator extends SpireInsertLocator {
		@Override
		public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
			Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "moveToDiscardPile");
			return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
		}
	}
}