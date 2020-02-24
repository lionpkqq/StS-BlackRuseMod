package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnivesAction;
import blackrusemod.cards.Interfaces.KnivesCard;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.KnivesPower;

public class KillingDoll extends CustomCard implements KnivesCard {
	public static final String ID = "BlackRuseMod:KillingDoll";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 2;
	private static final int UPGRADE_PLUS_DMG = 1;
	private int KNIVES = 0;

	public KillingDoll() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.KILLING_DOLL), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(KnivesPower.POWER_ID)) {
			this.KNIVES = p.getPower(KnivesPower.POWER_ID).amount;
			for (int i = 0; i < KNIVES; i++)
				addToBot(new ThrowKnivesAction(p, m, new DamageInfo(p, this.damage, this.damageTypeForTurn), "Golden"));
			this.KNIVES = p.getPower(KnivesPower.POWER_ID).amount;
			for (int i = 0; i < KNIVES; i++)
				addToBot(new ThrowKnivesAction(p, m, new DamageInfo(p, this.damage, this.damageTypeForTurn), "Golden"));
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new KillingDoll();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}