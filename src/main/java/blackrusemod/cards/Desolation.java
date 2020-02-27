package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.AmplifyDamagePower;

public class Desolation extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Desolation.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("desolation.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 12;
	private static final int UPGRADE_PLUS_DMG = 4;

	public Desolation() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_HEAVY));
		if (m.hasPower(AmplifyDamagePower.POWER_ID)) {
			addToBot(new ApplyPowerAction(m, p, new AmplifyDamagePower(m, m.getPower(AmplifyDamagePower.POWER_ID).amount), m.getPower(AmplifyDamagePower.POWER_ID).amount));
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new Desolation();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}