package blackrusemod.patches;

import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.badlogic.gdx.math.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.vfx.cardManip.*;

import blackrusemod.cards.Defend_Silver;
import blackrusemod.cards.Strike_Silver;

import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(cls = "com.megacrit.cardcrawl.events.thecity.BackToBasics", method = "upgradeStrikeAndDefends")
public class BackToBasicsPatch
{
    @SpireInsertPatch(rloc = 0)
    public static void Insert(final Object __obj_instance) {
        for (final AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if ((c instanceof Strike_Silver || c instanceof Defend_Silver) && c.canUpgrade()) {
                c.upgrade();
                AbstractDungeon.player.bottledCardUpgradeCheck(c);
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), MathUtils.random(0.1f, 0.9f) * Settings.WIDTH, MathUtils.random(0.2f, 0.8f) * Settings.HEIGHT));
            }
        }
    }
}
