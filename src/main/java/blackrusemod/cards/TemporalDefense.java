package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.ProtectionPower;

public class TemporalDefense extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(TemporalDefense.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("temporal_defense.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int BLOCK_AMT = 4;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	private static final int PROTECTION_AMT = 4;
	private static final int UPGRADE_PLUS_PROT = 2;

	public TemporalDefense() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.isEthereal = true;
		this.exhaust = true;
		this.baseBlock = BLOCK_AMT;
		this.protection = this.baseProtection = PROTECTION_AMT;
		this.tags.add(Enums.TEMP);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, this.block));
		addToBot(new ApplyPowerAction(p, p, new ProtectionPower(p, this.protection), this.protection));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new TemporalDefense();
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