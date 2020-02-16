package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import blackrusemod.powers.ManipulatePower;

public class ManipulateAction extends AbstractGameAction {
	private boolean freeToPlayOnce = false;
	private AbstractPlayer p;
	private int energyOnUse = -1;
	private boolean canUpgrade = false;
	
	public ManipulateAction (AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse, boolean canUpgrade)
	{
		this.energyOnUse = energyOnUse;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.p = p;	
		this.freeToPlayOnce = freeToPlayOnce;
		this.canUpgrade = canUpgrade;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
	}
	
	public void update() {
		int effect = EnergyPanel.totalCount;
		if (this.energyOnUse != -1) {
			effect = this.energyOnUse + 1;
		}
		if (this.p.hasRelic(ChemicalX.ID)) {
			effect += 2;
			this.p.getRelic(ChemicalX.ID).flash();
		}
		if (!this.canUpgrade) effect += 1;
		if (effect > 0) {
			addToBot(new ApplyPowerAction(p, p, new ManipulatePower(p, effect), effect));
			if (!this.freeToPlayOnce) {
				this.p.energy.use(EnergyPanel.totalCount);
			}
		}
		this.isDone = true;
	}
}