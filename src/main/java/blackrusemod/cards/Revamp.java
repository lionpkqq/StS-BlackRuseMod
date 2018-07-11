package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.DrawManipulationPower;

public class Revamp extends CustomCard {
	public static final String ID = "Revamp";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int DRAW_MORE = 2;
	
	public Revamp() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.REVAMP), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ShuffleAllAction());
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ShuffleAction(p.drawPile));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawManipulationPower(p, DRAW_MORE), DRAW_MORE));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Revamp();
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