package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

import blackrusemod.powers.AbstractVisionPower;
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
			// Succeed if our prediction matches what the monster is about to do (attack or not)
			if (power.prediction 
					== ((m.intent == Intent.ATTACK) 
					|| (m.intent == Intent.ATTACK_BUFF) 
					|| (m.intent == Intent.ATTACK_DEBUFF) 
					|| (m.intent == Intent.ATTACK_DEFEND))) {
				visionResult = true;
			}
			// Always succeed if we have True Sight, and flash True Sight
			else if (power.owner.hasPower(TrueSightPower.POWER_ID)) {
				power.owner.getPower(TrueSightPower.POWER_ID).flash();
				visionResult = true;
			}
			
			power.flash();
			power.onVision(visionResult);
			addToBot(new RemoveSpecificPowerAction(m, power.owner, power));
		}
		this.isDone = true;
	}
}