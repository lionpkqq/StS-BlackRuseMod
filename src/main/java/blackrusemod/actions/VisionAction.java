package blackrusemod.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.cards._DummyAttack;
import blackrusemod.cards._DummyNotAttack;
import blackrusemod.powers.AbstractVisionPower;

public class VisionAction extends AbstractGameAction {
	private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
	private AbstractVisionPower vision;

	public VisionAction(AbstractCreature p, AbstractCreature m, AbstractVisionPower vision) {
		this.actionType = AbstractGameAction.ActionType.SPECIAL;
		this.duration = Settings.ACTION_DUR_FAST;
		this.source = p;
		this.target = m;
		this.vision = vision;
		this.list.add(new _DummyAttack());
		this.list.add(new _DummyNotAttack());
	}

	@Override
	public void update() {
		if (this.duration == Settings.ACTION_DUR_FAST) {
			String visionText;
			if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
				visionText = "敌人的意图是？";
			}
			else {
				visionText = "The enemy's intent will be?";
			}
			AbstractDungeon.cardRewardScreen.customCombatOpen(list, visionText, false);
			tickDuration();
			return;
		}

		vision.prediction = AbstractDungeon.cardRewardScreen.discoveryCard instanceof _DummyAttack;
		addToBot(new ApplyPowerAction(this.target, this.source, this.vision));
		
		this.isDone = true;
	}
}