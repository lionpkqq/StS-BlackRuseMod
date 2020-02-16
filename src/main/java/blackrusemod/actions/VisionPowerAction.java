package blackrusemod.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackrusemod.BlackRuseMod;
import blackrusemod.cards.Deadline;
import blackrusemod.cards.Read;
import blackrusemod.cards.ReturningBlade;
import blackrusemod.cards.Snipe;
import blackrusemod.cards.TimeTheft;
import blackrusemod.cards._DummyAttack;
import blackrusemod.cards._DummyNotAttack;
import blackrusemod.powers.AbstractVisionPower;
import blackrusemod.powers.DeadlinePower;
import blackrusemod.powers.ReadPower;
import blackrusemod.powers.ReturningBladePower;
import blackrusemod.powers.SnipePower;
import blackrusemod.powers.TimeTheftPower;
import blackrusemod.powers.TrueSightPower;

public class VisionPowerAction extends AbstractGameAction {
	public AbstractVisionPower power;
	
	public VisionPowerAction(AbstractVisionPower p) {
		this.actionType = AbstractGameAction.ActionType.POWER;
		this.duration = Settings.ACTION_DUR_FAST;
		this.power = p;
	}

	public void update() {
		boolean visionResult = false;
		AbstractMonster m = (AbstractMonster)power.owner;
		// Always fail if the monster is dead or just not here anymore
		if((m != null) && (!m.isDeadOrEscaped())) {
			// Always succeed if we have True Sight, and flash True Sight
			if (power.owner.hasPower(TrueSightPower.POWER_ID)) {
				power.owner.getPower(TrueSightPower.POWER_ID).flash();
				visionResult = true;
			}
			// Succeed if our prediction matches what the monster is about to do (attack or not)
			else if (power.prediction 
					== ((m.intent == AbstractMonster.Intent.ATTACK) 
					|| (m.intent == AbstractMonster.Intent.ATTACK_BUFF) 
					|| (m.intent == AbstractMonster.Intent.ATTACK_DEBUFF) 
					|| (m.intent == AbstractMonster.Intent.ATTACK_DEFEND))) {
				visionResult = true;
			}
			power.flash();
			power.onVision(visionResult);
			addToBot(new RemoveSpecificPowerAction(m, power.owner, power));
		}
		this.isDone = true;
	}
}