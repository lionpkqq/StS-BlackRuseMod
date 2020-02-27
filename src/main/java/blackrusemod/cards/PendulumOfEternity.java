package blackrusemod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import blackrusemod.BlackRuseMod;

public class PendulumOfEternity extends AbstractShiftCard {
	public static final String ID = BlackRuseMod.makeID(PendulumOfEternity.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("pendulum.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 5;
	private static final int COST_REDUCTION_WHEN_UPGRADED = 1;
	private static final int DEBUFFS = 3;
	private static final int ATTACK_DMG = 33;
	
	public PendulumOfEternity() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = DEBUFFS;
		this.baseDamage = ATTACK_DMG;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, new Color(0.0F, 1.0F, 1.0F, 1.0F))));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, new Color(0.0F, 1.0F, 1.0F, 1.0F))));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, new Color(0.0F, 1.0F, 1.0F, 1.0F))));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new WaitAction(0.1F));
		addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
		addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
		addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
	}
	
	@Override
	public void triggerShift() {
		this.superFlash();
		if (this.cost > 0) this.cost -= 1;
		this.isCostModified = true;
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new PendulumOfEternity();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			int newCost = this.cost;
			newCost -= COST_REDUCTION_WHEN_UPGRADED;
			if (newCost < 0) newCost = 0;
			upgradeBaseCost(newCost);		
		}
	}
}
