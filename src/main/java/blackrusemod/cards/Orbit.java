package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ConvertAction;
import blackrusemod.actions.OrbitDamageAction;

public class Orbit extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Orbit.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("orbit.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 3;
	private static final int SATELLITE = 3;
	
	public Orbit() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = SATELLITE;
		this.isMultiDamage = true;
		this.tags.add(Enums.SILVER_BLADES);
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ConvertAction(this.magicNumber));
		addToBot(new OrbitDamageAction(this.multiDamage));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Orbit();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}