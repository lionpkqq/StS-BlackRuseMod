package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.VisionAction;
import blackrusemod.powers.ReturningBladePower;

public class ReturningBlade extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(ReturningBlade.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("returning_blade.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;

	public ReturningBlade() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new VisionAction(p, m, new ReturningBladePower(m, this.damage, this)));
	}

	@Override
	public AbstractCard makeCopy() {
		return new ReturningBlade();
	}
	
	@Override
	public void upgrade() {
		upgradeDamage(2 + this.timesUpgraded);
		this.timesUpgraded += 1;
		this.upgraded = true;
		this.name = (this.strings.NAME + "+" + this.timesUpgraded);
		initializeTitle();
	}

	@Override
	public boolean canUpgrade() {
		return true;
	}
}