package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.ProtectionPower;

public class Barrier extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Barrier.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("barrier.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 2;
	private static final int BLOCK_AMT = 7;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int PROTECTION_AMT = 7;
	private static final int UPGRADE_PLUS_PROT = 3;
	
	public Barrier() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseBlock = BLOCK_AMT;
		this.protection = this.baseProtection = PROTECTION_AMT;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, this.block));
		addToBot(new ApplyPowerAction(p, p, new ProtectionPower(p, this.protection), this.protection));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Barrier();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeProtection(UPGRADE_PLUS_PROT);
		}
	}
}