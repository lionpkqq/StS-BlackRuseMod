package blackrusemod.patches;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.actions.VisionAction;
import blackrusemod.powers.AbstractVisionPower;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import com.megacrit.cardcrawl.monsters.city.BanditPointy;
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