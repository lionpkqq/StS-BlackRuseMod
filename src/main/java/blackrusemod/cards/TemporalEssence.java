package blackrusemod.cards;

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

public class TemporalEssence extends CustomCard {
	public static final String ID = "BlackRuseMod:TemporalEssence";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int DRAW = 2;

	public TemporalEssence() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TEMPORAL_ESSENCE), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);
		this.isEthereal = true;
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = DRAW;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
		if (!this.canUpgrade()) AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
	}

	public AbstractCard makeCopy() {
		return new TemporalEssence();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADED_DESCRIPTION;
			this.initializeDescription();
			upgradeMagicNumber(1);
		}
	}
}