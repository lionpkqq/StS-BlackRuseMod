package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import blackrusemod.powers.AmplifyDamagePower;

public class GougeAction extends AbstractGameAction {
	private boolean freeToPlayOnce = false;
	private AbstractPlayer p;
	private AbstractMonster m;
	private int energyOnUse = -1;
	private boolean canUpgrade = false;
	private int damage;
	private DamageType damageType;
	
	public GougeAction (AbstractPlayer p, AbstractMonster m, int damage, DamageType damageType, boolean freeToPlayOnce, int energyOnUse, boolean canUpgrade) {
		this.damage = damage;
		this.damageType = damageType;
		this.energyOnUse = energyOnUse;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.p = p;	
		this.m = m;
		this.freeToPlayOnce = freeToPlayOnce;
		this.canUpgrade = canUpgrade;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
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
		if (!this.canUpgrade) effect += 1;
		if (effect > 0) {
			for (int i = 0; i < effect; i++) {
				addToBot(new DamageAction(this.m, new DamageInfo(p, this.damage, this.damageType), AttackEffect.BLUNT_HEAVY));
				addToBot(new ApplyPowerAction(this.m, p, new AmplifyDamagePower(this.m, 1), 1));
			}
			if (!this.freeToPlayOnce) {
				this.p.energy.use(EnergyPanel.totalCount);
			}
		}
		this.isDone = true;
	}
}