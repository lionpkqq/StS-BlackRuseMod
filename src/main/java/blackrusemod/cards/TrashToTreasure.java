package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.TrashToTreasureAction;
import blackrusemod.patches.AbstractCardEnum;

public class TrashToTreasure extends CustomCard {
	public static final String ID = "TrashToTreasure";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int DRAW = 1;
	
	public TrashToTreasure() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TRASH_TO_TREASURE), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = DRAW;
		this.exhaust = true;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (AbstractDungeon.player.discardPile.size() > 0) {
			AbstractDungeon.actionManager.addToBottom(new TrashToTreasureAction(this.magicNumber));
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new TrashToTreasure();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}