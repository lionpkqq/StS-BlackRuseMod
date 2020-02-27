package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.TemporalDamageAction;

public class TemporalSlicing extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(TemporalSlicing.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("temporal_slicing.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 4;
	private static final int ATTACK_TIMES = 2;

	public TemporalSlicing() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.isEthereal = true;
		this.exhaust = true;
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = ATTACK_TIMES;
		this.isMultiDamage = true;
		this.tags.add(Enums.TEMP);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < this.magicNumber; i++) {
			addToBot(new SFXAction("ATTACK_HEAVY"));
			addToBot(new VFXAction(p, new CleaveEffect(), 0.3F));
			addToBot(new TemporalDamageAction(this.baseDamage));
		}
	}

	@Override
	public AbstractCard makeCopy() {
		return new TemporalSlicing();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}