package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class TemporalSlicing extends CustomCard {
	public static final String ID = "TemporalSlicing";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int COST = -2;

	public TemporalSlicing() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TEMPORAL_SLICING), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
	}
	
	public void triggerWhenDrawn() {
		AbstractDungeon.actionManager.addToBottom(new SwordBoomerangAction(
				AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(AbstractDungeon.player, 5), 2));
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m)
	{
		this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		return false;
	}

	public AbstractCard makeCopy() {
		return new TemporalSlicing();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADED_DESCRIPTION;
			this.initializeDescription();
			this.isEthereal = false;
		}
	}
}