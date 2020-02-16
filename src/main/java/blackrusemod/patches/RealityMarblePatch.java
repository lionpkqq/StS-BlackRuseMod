package blackrusemod.patches;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import com.megacrit.cardcrawl.monsters.city.BanditPointy;
import java.util.ArrayList;

public class RealityMarblePatch
{
	// Field to know whether the card has had Ethereal removed by Reality Marble
	@SpirePatch(clz=AbstractCard.class, method=SpirePatch.CLASS)
	public static class marbledField {
		public static SpireField<Boolean> blackrusemod_marbled = new SpireField<Boolean>(() -> false);
	}
	
	// Description modification to add the "Marbled" keyword
	@SpirePatch(clz=AbstractCard.class, method="initializeDescription")
	public static class marbledText {
		@SpireInsertPatch(rloc=118, localvars={"gl"})
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
	}
	
	// Preserve Marbled status on stat equivalent copies
	@SpirePatch(clz=AbstractCard.class, method="makeStatEquivalentCopy")
	public static class marbledPreservation {
		@SpireInsertPatch(rloc=24, localvars={"card"})
		public static void Insert(AbstractCard __instance, AbstractCard card) {
			if(marbledField.blackrusemod_marbled.get(__instance)) {
				marbledField.blackrusemod_marbled.set(card, true);
				card.isEthereal = false;
				card.initializeDescription();
			}
		}
	}
}