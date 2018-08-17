package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.KnivesPower;

public class Cleaning extends CustomCard {
	public static final String ID = "Cleaning";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int DISCARD = 2;
	
	public Cleaning() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.CLEANING), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = DISCARD;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, this.magicNumber, false));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KnivesPower(p, this.magicNumber), this.magicNumber));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Cleaning();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}