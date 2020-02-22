package blackrusemod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import java.util.ArrayList;

@SpirePatch(clz=GameActionManager.class, method="getNextAction")
public class VisionApplyPatch
{
	// List of vision actions to apply
	public static ArrayList<AbstractGameAction> visionActions = new ArrayList<AbstractGameAction>();
	
	// Add in these actions right after powers
	@SpireInsertPatch(rloc=240)
	public static void Insert() {
		for(AbstractGameAction a : visionActions) {
			AbstractDungeon.actionManager.addToBottom(a);
		}
		visionActions.clear();
	}
}