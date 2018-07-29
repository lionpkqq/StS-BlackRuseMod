package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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
import blackrusemod.powers.EnergyManipulationPower;

public class BorrowedTime extends CustomCard {
	public static final String ID = "BorrowedTime";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int DRAW = 5;
	private static final int DRAW_LESS = 5;
	
	public BorrowedTime() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.BORROWED_TIME), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = DRAW_LESS;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, DRAW));
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(3));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawManipulationPower(p, -this.magicNumber), -this.magicNumber));
		if (this.canUpgrade()) AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnergyManipulationPower(p, -3), -3));
		else AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnergyManipulationPower(p, -2), -2));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new BorrowedTime();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADED_DESCRIPTION;
			this.initializeDescription();
			upgradeMagicNumber(-2);
		}
	}
}