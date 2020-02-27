package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import blackrusemod.powers.ProtectionPower;

public class HightailAction extends AbstractGameAction {
	private boolean freeToPlayOnce = false;
	private AbstractPlayer p;
	private int energyOnUse = -1;
	private int magicNumber;
	
	public HightailAction (AbstractPlayer p, int magicNumber, boolean freeToPlayOnce, int energyOnUse)
	{
		this.energyOnUse = energyOnUse;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.p = p;	
		this.magicNumber = magicNumber;
		this.freeToPlayOnce = freeToPlayOnce;
		this.duration = Settings.ACTION_DUR_XFAST;
	}
	
	@Override
	public void update() {
		int effect = EnergyPanel.totalCount;
		if (this.energyOnUse != -1) {
			effect = this.energyOnUse;
		}
		if (this.p.hasRelic(ChemicalX.ID)) {
			effect += 2;
			this.p.getRelic(ChemicalX.ID).flash();
		}
		if (effect > 0) {
			for (int i = 0; i < effect; i++) 
				addToBot(new ApplyPowerAction(p, p, new ProtectionPower(p, this.magicNumber), this.magicNumber));
			if (!this.freeToPlayOnce) {
				this.p.energy.use(EnergyPanel.totalCount);
			}
		}
		addToBot(new BacklashAction(1));
		this.isDone = true;
	}
}