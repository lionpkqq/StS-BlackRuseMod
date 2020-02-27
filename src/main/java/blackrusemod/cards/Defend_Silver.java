package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;

public class Defend_Silver extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Defend_Silver.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("defend_silver.png");
	private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 5;
	private static final int UPGRADE_PLUS_BLOCK = 3;

	public Defend_Silver() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseBlock = BLOCK_AMT;
		this.tags.add(AbstractCard.CardTags.STARTER_DEFEND);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, this.block));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Defend_Silver();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}
}