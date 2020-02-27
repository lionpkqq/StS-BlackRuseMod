package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;

public class FastForward extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(FastForward.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("fast_forward.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int BLOCK = 9;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int DRAW = 2;
	
	public FastForward() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = DRAW;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new GainBlockAction(p, p, this.block));
		if (AbstractDungeon.player.hand.getAttacks().size() == 0) {
			addToBot(new DrawCardAction(p, magicNumber));
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FastForward();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}
}