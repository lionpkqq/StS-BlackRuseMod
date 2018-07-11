package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class Exchange extends CustomCard {
	public static final String ID = "Exchange";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int COST = 1;
	private static final int STACK = 4;

	public Exchange() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.EXCHANGE), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = 0;
		this.magicNumber = this.baseMagicNumber = STACK;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
	}

	public AbstractCard makeCopy() {
		return new Exchange();
	}
	
	public void applyPowers()
	{
		this.baseBlock = 0;
		if (AbstractDungeon.player.hasPower("Weakened")) this.baseBlock += AbstractDungeon.player.getPower("Weakened").amount*this.magicNumber;
		if (AbstractDungeon.player.hasPower("Vulnerable")) this.baseBlock += AbstractDungeon.player.getPower("Weakened").amount*this.magicNumber;
		if (AbstractDungeon.player.hasPower("Frail")) this.baseBlock += AbstractDungeon.player.getPower("Weakened").amount*this.magicNumber;
		super.applyPowers();
		this.rawDescription = DESCRIPTION;
		this.rawDescription += EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(2);
		}
	}
}