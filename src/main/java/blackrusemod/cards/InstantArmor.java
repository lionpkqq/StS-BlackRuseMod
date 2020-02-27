package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ConvertAction;
import blackrusemod.powers.ProtectionPower;

public class InstantArmor extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(InstantArmor.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("instant_armor.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int ARMOR_AMT = 6;
	private static final int SATELLITE = 3;
	
	public InstantArmor() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.isInnate = true;
		this.exhaust = true;
		this.protection = this.baseProtection = ARMOR_AMT;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new ProtectionPower(p, this.protection), this.protection));
		addToBot(new ConvertAction(SATELLITE));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new InstantArmor();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeProtection(3);
		}
	}
}