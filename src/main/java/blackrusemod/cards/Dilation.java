package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.ProtectionPower;

public class Dilation extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Dilation.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("dilation.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int PROTECTION_AMT = 3;
	private static final int UPGRADE_PLUS_PROT = 1;
	public int used;

	public Dilation() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.protection = this.baseProtection = PROTECTION_AMT;
		this.used = 0;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new ProtectionPower(p, this.protection), this.protection));
		this.protection++;
		this.isProtectionModified = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Dilation();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeProtection(UPGRADE_PLUS_PROT);
		}
	}
}