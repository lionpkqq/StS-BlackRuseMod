package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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

	@Override
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
			else if (AbstractDungeon.player.hasPower(TrueSightPower.POWER_ID)) {
				AbstractDungeon.player.getPower(TrueSightPower.POWER_ID).flash();
				visionResult = true;
			}
			
			// We use addToTop here so that everything happens on time
			addToTop(new RemoveSpecificPowerAction(m, m, power));
			power.flash();
			power.onVision(visionResult);
		}
		this.isDone = true;
	}
}