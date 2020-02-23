package blackrusemod.patches;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.helpers.FontHelper;

import javassist.CannotCompileException;
import javassist.CtBehavior;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.core.Settings;

public class RealityMarblePatch {
	// Field to know whether the card has had Ethereal removed by Reality Marble
	@SpirePatch(clz=AbstractCard.class, method=SpirePatch.CLASS)
	public static class marbledField {
		public static SpireField<Boolean> blackrusemod_marbled = new SpireField<Boolean>(() -> false);
	}
	
	// Description modification to add the "Marbled" keyword
	@SpirePatch(clz=AbstractCard.class, method="initializeDescription")
	public static class marbledText {
		@SpireInsertPatch(locator=Locator.class, localvars={"gl"})
		public static void Insert(AbstractCard __instance, GlyphLayout gl) {
			if(marbledField.blackrusemod_marbled.get(__instance)) {
				gl.reset();
				gl.setText(FontHelper.cardDescFont_N, "Marbled.");
				__instance.description.add(new DescriptionLine("*Marbled.", gl.width));
				if(!__instance.keywords.contains("blackrusemod:Marbled")) {
					__instance.keywords.add("blackrusemod:Marbled");
				}
			}
		}
		
		private static class Locator extends SpireInsertLocator {
			@Override
			public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
				Matcher finalMatcher = new Matcher.FieldAccessMatcher(Settings.class, "isDev");
				return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
			}
		}
	}
	
	// Preserve Marbled status on stat equivalent copies
	@SpirePatch(clz=AbstractCard.class, method="makeStatEquivalentCopy")
	public static class marbledPreservation {
		@SpirePostfixPatch
		public static AbstractCard Insert(AbstractCard __result, AbstractCard __instance) {
			if(marbledField.blackrusemod_marbled.get(__instance)) {
				marbledField.blackrusemod_marbled.set(__result, true);
				__result.isEthereal = false;
				__result.initializeDescription();
			}
			return __result;
		}
	}
}