package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.ElegancePower;
import blackrusemod.vfx.FinishingImpactEffect;

public class FinishingTouch extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(FinishingTouch.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("finishing_touch.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 30;
	private static final int UPGRADE_PLUS_DMG = 12;

	public FinishingTouch() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
		this.isEthereal = true;
		this.tags.add(Enums.TEMP);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m != null) {
			addToBot(new VFXAction(new FinishingImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
		}
		addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
		addToBot(new ApplyPowerAction(p, p, new ElegancePower(p, 1), 1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new FinishingTouch();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}