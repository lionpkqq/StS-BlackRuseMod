package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackrusemod.powers.KnivesPower;
import blackrusemod.powers.ProtectionPower;

public class DisposalAction extends AbstractGameAction {
	private float startingDuration;
	private AbstractPlayer p;

	public DisposalAction(AbstractPlayer p, int magicNumber) {
		this.amount = magicNumber;
		this.p = p;
		this.actionType = AbstractGameAction.ActionType.EXHAUST;
		this.startingDuration = Settings.ACTION_DUR_FAST;
		this.duration = this.startingDuration;
	}

	@Override
	public void update() {
		if (this.duration == this.startingDuration) {
			int count = AbstractDungeon.player.hand.size();
			for (int i = 0; i < count; i++) {
				addToTop(new ApplyPowerAction(p, p, new ProtectionPower(p, this.amount), this.amount));
				addToTop(new ApplyPowerAction(p, p, new KnivesPower(p, 2), 2));
			}
			for (int i = 0; i < count; i++) {
				addToTop(new ExhaustAction(p, p, 1, true, true));
			}
		}
		tickDuration();
	}
}