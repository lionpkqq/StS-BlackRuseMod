package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.DuplicationPower;

public class Duplication extends CustomCard {
	public static final String ID = "Duplication";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int TWICE = 1;

	public Duplication() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.DUPLICATION), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = TWICE;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DuplicationPower(p, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FrailPower(p, this.magicNumber, false), this.magicNumber));
	}

	public AbstractCard makeCopy() {
		return new Duplication();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}