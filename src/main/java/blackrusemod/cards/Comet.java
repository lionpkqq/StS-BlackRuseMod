package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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

public class Comet extends CustomCard {
	public static final String ID = "BlackRuseMod:Comet";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String SINGULAR_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 3;
	private static final int DRAW = 2;

	public Comet() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.COMET), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = DRAW;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SMASH));
		// Use base magic number instead of current to avoid edge-case scenarios, like chaos potion
		int cardsToDraw = this.baseMagicNumber - (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1);
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, Math.max(cardsToDraw, 0)));
	}
	
	// Real-time feedback on how many cards Comet will draw	
	public void triggerOnGlowCheck() {
		this.magicNumber = this.baseMagicNumber - AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
		if(this.magicNumber < 0) this.magicNumber = 0;
		this.isMagicNumberModified = this.upgraded || !(this.magicNumber == this.baseMagicNumber);
		if(this.magicNumber == 1) {
			this.rawDescription = SINGULAR_DESCRIPTION;
		} else {
			this.rawDescription = DESCRIPTION;
		}
		initializeDescription();
		// Have Comet glow gold if it is able to draw the maximum number of cards
		if(this.magicNumber == this.baseMagicNumber) {
			this.glowColor = GOLD_BORDER_GLOW_COLOR;
		} else {
			this.glowColor = BLUE_BORDER_GLOW_COLOR;
		}
	}

	public AbstractCard makeCopy() {
		return new Comet();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}