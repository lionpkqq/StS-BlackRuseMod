package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;

public class Comet extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Comet.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("comet.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 3;
	private static final int DRAW = 2;

	public Comet() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = DRAW;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SMASH));
		// Use base magic number instead of current to avoid edge-case scenarios, like chaos potion
		int cardsToDraw = this.baseMagicNumber - (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1);
		addToBot(new DrawCardAction(p, Math.max(cardsToDraw, 0)));
	}
	
	// Real-time feedback on how many cards Comet will draw
	@Override
	public void triggerOnGlowCheck() {
		this.magicNumber = this.baseMagicNumber - AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
		if(this.magicNumber < 0) this.magicNumber = 0;
		this.isMagicNumberModified = this.upgradedMagicNumber || !(this.magicNumber == this.baseMagicNumber);
		if(this.magicNumber == 1) {
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
		} else {
			this.rawDescription = this.strings.DESCRIPTION;
		}
		initializeDescription();
		// Have Comet glow gold if it is able to draw the maximum number of cards
		if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0) {
			this.glowColor = GOLD_BORDER_GLOW_COLOR;
		} else {
			this.glowColor = BLUE_BORDER_GLOW_COLOR;
		}
	}

	@Override
	public void onMoveToDiscard() {
		this.rawDescription = this.strings.DESCRIPTION;
		initializeDescription();
	}

	@Override
	public AbstractCard makeCopy() {
		return new Comet();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}