package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.ProtectionPower;

public class Collapse extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Collapse.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("collapse.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;
    private static final int COST_UPGRADED = 1;
	
	public Collapse() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.isMultiDamage = true;
		this.baseDamage = 0;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(ProtectionPower.POWER_ID)) 
			this.baseDamage = p.getPower(ProtectionPower.POWER_ID).amount;
		else this.baseDamage = 0;
		calculateCardDamage(m);
		addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AttackEffect.FIRE));
		this.rawDescription = this.strings.DESCRIPTION;
		initializeDescription();
	}
	
	@Override
	public void applyPowers() {
		if (AbstractDungeon.player.hasPower(ProtectionPower.POWER_ID))
			this.baseDamage = AbstractDungeon.player.getPower(ProtectionPower.POWER_ID).amount;
		else this.baseDamage = 0;
		super.applyPowers();

		this.rawDescription = this.strings.DESCRIPTION;
		this.rawDescription += this.strings.EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}
	
	@Override
	public void onMoveToDiscard() {
		this.rawDescription = this.strings.DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		super.calculateCardDamage(mo);
		this.rawDescription = this.strings.DESCRIPTION;
		this.rawDescription += this.strings.EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Collapse();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}