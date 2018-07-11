package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ReduceDebuffsAction;
import blackrusemod.patches.AbstractCardEnum;

public class Reset extends CustomCard {
	public static final String ID = "Reset";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	
	public Reset() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.RESET), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ReduceDebuffsAction(p, 99));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Reset();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADED_DESCRIPTION;
			this.initializeDescription();
			this.exhaust = false;
		}
	}
}