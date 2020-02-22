package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.MakeRandomTemporalCardAction;
import blackrusemod.patches.AbstractCardEnum;

public class Moondial extends CustomCard {
	public static final String ID = "BlackRuseMod:Moondial";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int TEMPORAL = 2;

	public Moondial() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.MOONDIAL), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = TEMPORAL;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new MakeRandomTemporalCardAction(AbstractDungeon.player.hand, this.magicNumber));
		addToBot(new MakeRandomTemporalCardAction(AbstractDungeon.player.drawPile, this.magicNumber));
		//AbstractDungeon.actionManager.addToBottom(new MoondialAction(this.magicNumber));
		//AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ManipulatePower(p, this.magicNumber), this.magicNumber));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Moondial();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}