package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;

public class FollowUp2 extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(FollowUp2.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("follow_up_2.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 15;
	private static final int UPGRADE_PLUS_DMG = 6;

	public FollowUp2() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
		this.isEthereal = true;
		this.cardsToPreview = new FinishingTouch();
		this.tags.add(Enums.TEMP);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
		addToBot(new MakeTempCardInHandAction(this.cardsToPreview, false));
	}

	@Override
	public AbstractCard makeCopy() {
		return new FollowUp2();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			upgradeDamage(UPGRADE_PLUS_DMG);
			this.cardsToPreview.upgrade();
		}
	}
}