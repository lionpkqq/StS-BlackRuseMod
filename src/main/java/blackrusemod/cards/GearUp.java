package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.KnivesPower;
import blackrusemod.powers.ProtectionPower;

public class GearUp extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(GearUp.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("gear_up.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int PROTECTION_AMT = 6;
	private static final int UPGRADE_PLUS_PROT = 3;
	
	public GearUp() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.protection = this.baseProtection = PROTECTION_AMT;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new ProtectionPower(p, this.protection), this.protection));
		addToBot(new ApplyPowerAction(p, p, new KnivesPower(p, 3), 3));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new GearUp();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeProtection(UPGRADE_PLUS_PROT);
		}
	}
}