package blackrusemod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import javassist.CannotCompileException;
import javassist.CtBehavior;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.ArrayList;

@SpirePatch(clz = GameActionManager.class, method = "getNextAction")
public class VisionApplyPatch {
	// List of vision actions to apply
	public static ArrayList<AbstractGameAction> visionActions = new ArrayList<AbstractGameAction>();

	// Add in these actions right after powers
	@SpireInsertPatch(locator=Locator.class)
	public static void Insert() {
		for (AbstractGameAction a : visionActions) {
			AbstractDungeon.actionManager.addToBottom(a);
		}
		visionActions.clear();
	}

	private static class Locator extends SpireInsertLocator {
		@Override
		public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
			Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnOrbs");
			return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
		}
	}
}