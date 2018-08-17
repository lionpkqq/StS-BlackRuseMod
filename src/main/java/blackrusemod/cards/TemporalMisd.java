package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.AmplifyDamagePower;

public class TemporalMisd extends CustomCard {
	public static final String ID = "TemporalMisd";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int MISD = 1;

	public TemporalMisd() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.TEMPORAL_MISD), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.isEthereal = true;
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = MISD;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, 
					new AmplifyDamagePower(mo, this.magicNumber), this.magicNumber));
			if (AbstractDungeon.player.hasRelic("PaperSwan")) 
				if (AbstractDungeon.cardRandomRng.randomBoolean())
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, 
							new AmplifyDamagePower(mo, 1), 1));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, 
					new WeakPower(mo, this.magicNumber, false), this.magicNumber));
		}
	}

	public AbstractCard makeCopy() {
		return new TemporalMisd();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}