package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnivesAction;
import blackrusemod.powers.KnivesPower;

public class KillingDoll extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(KillingDoll.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("killing_doll.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 2;
	private static final int UPGRADE_PLUS_DMG = 1;

	public KillingDoll() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
		this.tags.add(Enums.SILVER_BLADES);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(KnivesPower.POWER_ID)) {
			int knives = p.getPower(KnivesPower.POWER_ID).amount;
			addToBot(new ThrowKnivesAction(p, null, new DamageInfo(p, this.damage, this.damageTypeForTurn), knives, null, true));
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