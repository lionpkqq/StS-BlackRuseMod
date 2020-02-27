package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.VisionAction;
import blackrusemod.powers.ReadPower;

public class Read extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Read.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("read.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 2;
	private static final int PROTECTION = 15;
	private static final int UPGRADE_PLUS_PROT = 5;
	private static final int WEAK = 2;

	public Read() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.protection = this.baseProtection = PROTECTION;
		this.magicNumber = this.baseMagicNumber = WEAK;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new VisionAction(p, m, new ReadPower(m, this.protection, this.magicNumber)));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Read();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_PROT);
			upgradeMagicNumber(1);
		}
	}
}