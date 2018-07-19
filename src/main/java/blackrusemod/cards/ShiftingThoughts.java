package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ShiftingThoughtsAction;
import blackrusemod.patches.AbstractCardEnum;

public class ShiftingThoughts extends CustomCard {
	public static final String ID = "ShiftingThoughts";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int DRAW = 2;

	public ShiftingThoughts() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.SHIFTING_THOUGHTS), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = DRAW;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ShiftingThoughtsAction(this.magicNumber));
	}
	
	public void triggerOnManualDiscard() {
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
		if (AbstractDungeon.player.hasRelic("KneeBrace")) 
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 3));
	}

	public AbstractCard makeCopy() {
		return new ShiftingThoughts();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}